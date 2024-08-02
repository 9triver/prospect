package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CostControlSystemTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CostControlSystem getCostControlSystemSample1() {
        return new CostControlSystem().id("id1").type(1).managementregistrationnumber(1).financialregistrationnumber(1);
    }

    public static CostControlSystem getCostControlSystemSample2() {
        return new CostControlSystem().id("id2").type(2).managementregistrationnumber(2).financialregistrationnumber(2);
    }

    public static CostControlSystem getCostControlSystemRandomSampleGenerator() {
        return new CostControlSystem()
            .id(UUID.randomUUID().toString())
            .type(intCount.incrementAndGet())
            .managementregistrationnumber(intCount.incrementAndGet())
            .financialregistrationnumber(intCount.incrementAndGet());
    }
}
