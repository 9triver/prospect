package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PlanmonitorTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Planmonitor getPlanmonitorSample1() {
        return new Planmonitor().id(1L).type("type1").year(1L).status(1);
    }

    public static Planmonitor getPlanmonitorSample2() {
        return new Planmonitor().id(2L).type("type2").year(2L).status(2);
    }

    public static Planmonitor getPlanmonitorRandomSampleGenerator() {
        return new Planmonitor()
            .id(longCount.incrementAndGet())
            .type(UUID.randomUUID().toString())
            .year(longCount.incrementAndGet())
            .status(intCount.incrementAndGet());
    }
}
