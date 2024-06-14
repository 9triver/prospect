package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PlanexecuteTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Planexecute getPlanexecuteSample1() {
        return new Planexecute().id(1L).planname("planname1");
    }

    public static Planexecute getPlanexecuteSample2() {
        return new Planexecute().id(2L).planname("planname2");
    }

    public static Planexecute getPlanexecuteRandomSampleGenerator() {
        return new Planexecute().id(longCount.incrementAndGet()).planname(UUID.randomUUID().toString());
    }
}
