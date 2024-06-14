package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class QualitymanagementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Qualitymanagement getQualitymanagementSample1() {
        return new Qualitymanagement().id(1L).qualityid(1L).creatorname("creatorname1");
    }

    public static Qualitymanagement getQualitymanagementSample2() {
        return new Qualitymanagement().id(2L).qualityid(2L).creatorname("creatorname2");
    }

    public static Qualitymanagement getQualitymanagementRandomSampleGenerator() {
        return new Qualitymanagement()
            .id(longCount.incrementAndGet())
            .qualityid(longCount.incrementAndGet())
            .creatorname(UUID.randomUUID().toString());
    }
}
