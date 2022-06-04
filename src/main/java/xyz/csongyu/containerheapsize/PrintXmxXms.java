package xyz.csongyu.containerheapsize;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PrintXmxXms implements CommandLineRunner {
    @Override
    public void run(final String... args) {
        final int mb = 1024 * 1024;
        final MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        final long xms = memoryBean.getHeapMemoryUsage().getInit() / mb;
        final long xmx = memoryBean.getHeapMemoryUsage().getMax() / mb;
        log.info("Initial Memory (xms): {}mb", xms);
        log.info("Max Memory (xmx): {}mb", xmx);

        final RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        final String vmName = runtimeBean.getVmName();
        final String vmVersion = runtimeBean.getVmVersion();
        log.info("VM Info: {} {}", vmName, vmVersion);
    }
}
