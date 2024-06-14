package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ProgressmanagementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Progressmanagement getProgressmanagementSample1() {
        return new Progressmanagement()
            .id(1L)
            .progressid(1L)
            .progressname("progressname1")
            .workfocus("workfocus1")
            .creatorname("creatorname1")
            .responsiblename("responsiblename1")
            .baselineid(1L);
    }

    public static Progressmanagement getProgressmanagementSample2() {
        return new Progressmanagement()
            .id(2L)
            .progressid(2L)
            .progressname("progressname2")
            .workfocus("workfocus2")
            .creatorname("creatorname2")
            .responsiblename("responsiblename2")
            .baselineid(2L);
    }

    public static Progressmanagement getProgressmanagementRandomSampleGenerator() {
        return new Progressmanagement()
            .id(longCount.incrementAndGet())
            .progressid(longCount.incrementAndGet())
            .progressname(UUID.randomUUID().toString())
            .workfocus(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString())
            .baselineid(longCount.incrementAndGet());
    }
}
