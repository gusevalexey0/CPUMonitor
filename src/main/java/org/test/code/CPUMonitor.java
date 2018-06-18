package org.test.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CPUMonitor implements ICPUMonitor {
    public Process p = null;
    private static final int IDLE_COLUMN_INDEX = 12;
    private static final int CORE_INDEX = 2;

    private static Map<String, Double> getCPUUsageList() throws IOException {

        Map<String, Double> cpuUsageList = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("mpstat -P ALL 1 1").getInputStream()))){

            br.readLine();
            br.readLine();
            br.readLine();

            String line;

            while (!(line = br.readLine()).isEmpty()) {
                String[] coloumns = line.replaceAll(",", ".").split("\\s+");
                Double idleValue = Double.parseDouble(coloumns[IDLE_COLUMN_INDEX]);
                cpuUsageList.put(coloumns[CORE_INDEX], BigDecimal.valueOf(100 - idleValue).setScale(2, RoundingMode.CEILING).doubleValue());
            }

        } catch (IOException e) {
            throw e;
        }

        return cpuUsageList;
    }

    @Override
    public Map<String, Double> getAllCPUUsage() throws IOException {
        return getCPUUsageList();
    }

    @Override
    public int getCoresCount() throws IOException {
        try {
            return getCPUUsageList().size() - 1;
        } catch (IOException e) {
            throw e;
        }
    }

    public String toString() {
        try {
            return getCPUUsageList().toString();
        } catch (IOException e) {
            return null;
        }
    }
}
