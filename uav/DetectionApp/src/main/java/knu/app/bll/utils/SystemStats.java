package knu.app.bll.utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

public class SystemStats {

    private final SystemInfo systemInfo;
    private final CentralProcessor processor;
    private final GlobalMemory memory;
    private long[] prevTicks;
    private long lastUpdateTime = 0;
    private double cachedCpuLoad = 0.0;

    public SystemStats() {
        systemInfo = new SystemInfo();
        processor = systemInfo.getHardware().getProcessor();
        memory = systemInfo.getHardware().getMemory();
        prevTicks = processor.getSystemCpuLoadTicks();
    }

    public double getCpuLoadPercent() {
        long now = System.currentTimeMillis();
        if (now - lastUpdateTime > 300) {
            cachedCpuLoad = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100.0;
            prevTicks = processor.getSystemCpuLoadTicks();
            lastUpdateTime = now;
        }
        return cachedCpuLoad;
    }

    public long getUsedMemoryMB() {
        return (memory.getTotal() - memory.getAvailable()) / (1024 * 1024);
    }

    public long getTotalMemoryMB() {
        return memory.getTotal() / (1024 * 1024);
    }
}
