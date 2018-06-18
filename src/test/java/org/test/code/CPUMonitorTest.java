package org.test.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CPUMonitorTest {
    @Test
    public void getAllCPUUsageTest() {
        ICPUMonitor monitor = new CPUMonitor();
        try {
            Map<String, Double> cpuUsageList = monitor.getAllCPUUsage();
            for (Map.Entry e : cpuUsageList.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue() + "%");
            }
        } catch (IOException e) {
            assert false;
        }
    }

    @Test
    void getCoresCountTest() {
        ICPUMonitor monitor = new CPUMonitor();
        try {
            System.out.println("Number of cores :" + monitor.getCoresCount());
        } catch (IOException e) {
            assert false;
        }
    }

}