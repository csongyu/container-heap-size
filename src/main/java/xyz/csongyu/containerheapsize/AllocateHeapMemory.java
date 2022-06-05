package xyz.csongyu.containerheapsize;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AllocateHeapMemory {
    private static final List<SoftReference<byte[]>> TOTAL = new ArrayList<>();

    @Value("${app.allocate-heap-size}")
    private int allocateHeapSize;

    @PostConstruct
    public void allocate() {
        final int mb = 1024 * 1024;
        IntStream.range(0, this.allocateHeapSize).forEach(i -> TOTAL.add(new SoftReference<>(new byte[mb])));

        final MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        final long used = memoryBean.getHeapMemoryUsage().getUsed() / mb;
        log.info("Used Memory: {}mb", used);
    }
}
