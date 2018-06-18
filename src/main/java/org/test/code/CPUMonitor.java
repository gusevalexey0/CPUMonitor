package org.test.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CPUMonitor implements ICPUMonitor {
    private static final int IDLE_COLUMN_INDEX = 12;
    private static final int CORE_INDEX = 2;

    private Map<String, Double> getCPUUsageList() throws IOException {

        Map<String, Double> cpuUsageList = new HashMap<>();

        Process process = null;

        try {
            process = Runtime.getRuntime().exec("mpstat -P ALL 1 1");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

                br.readLine();
                br.readLine();
                br.readLine();

                String line;

                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    String[] coloumns = line.replaceAll(",", ".").split("\\s+");
                    double idleValue = Double.parseDouble(coloumns[IDLE_COLUMN_INDEX]);
                    cpuUsageList.put(coloumns[CORE_INDEX], Double.parseDouble(String.format("%.2f", idleValue)));
                }
            } catch (IOException e) {
                throw e;
            }
        } finally {
            if (process != null) {
                process.destroy();
            }
        }

        return cpuUsageList;
    }

    @Override
    public Map<String, Double> getAllCPUUsage() throws IOException {
        return getCPUUsageList();
    }

    @Override
    public int getCoresCount() throws IOException {
        int coresCount = getCPUUsageList().size() - 1;
        return coresCount < 0 ? 0 : coresCount;
    }

    public String toString() {
        try {
            return getCPUUsageList().toString();
        } catch (IOException e) {
            return null;
        }
    }
}
