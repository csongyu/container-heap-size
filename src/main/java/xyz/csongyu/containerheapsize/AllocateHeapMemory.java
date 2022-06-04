package xyz.csongyu.containerheapsize;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AllocateHeapMemory implements CommandLineRunner {
    private static final List<byte[]> TOTAL = new ArrayList<>();

    @Value("${app.allocate-heap-size}")
    private int allocateHeapSize;

    @Override
    public void run(final String... args) {
        final int mb = 1024 * 1024;
        final byte[] bytes = new byte[mb];
        IntStream.range(0, this.allocateHeapSize).forEach(i -> TOTAL.add(bytes));

        final MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        final long used = memoryBean.getHeapMemoryUsage().getUsed() / mb;
        log.info("Used Memory: {}mb", used);
    }
}
