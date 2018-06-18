package org.test.code;

import java.io.IOException;
import java.util.Map;

public abstract interface ICPUMonitor {
    public abstract Map<String, Double> getAllCPUUsage() throws IOException;

    public abstract int getCoresCount() throws IOException;
}
