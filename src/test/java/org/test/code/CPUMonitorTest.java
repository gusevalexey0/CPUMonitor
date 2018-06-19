package org.test.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CPUMonitorTest {

    @Test
    void getCoresCountShouldReturnPositiveNumber() throws IOException {
        ICPUMonitor monitor = new CPUMonitor();
        assertTrue(monitor.getCoresCount() >= 0);
    }

    @Test
    void getCPUUsageListShouldHaveNames() throws IOException, NumberFormatException, ArrayIndexOutOfBoundsException {
        ICPUMonitor monitor = new CPUMonitor();
        Map<String, Double> CPUUsageList = monitor.getAllCPUUsage();
        for (Map.Entry e : CPUUsageList.entrySet()) {
            assertNotNull(e.getKey());
        }
    }


    @Test
    void getCPUUsageListShouldHaveValidValues() throws IOException, NumberFormatException, ArrayIndexOutOfBoundsException {
        ICPUMonitor monitor = new CPUMonitor();
        Map<String, Double> CPUUsageList = monitor.getAllCPUUsage();
        for (Map.Entry e : CPUUsageList.entrySet()) {
            System.err.println(e.getValue());
            assertTrue(String.valueOf(e.getValue()).split("\\.")[1].length() <= 2);
        }
    }}