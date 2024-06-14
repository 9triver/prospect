package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CycleplanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Cycleplan getCycleplanSample1() {
        return new Cycleplan().id(1L).cycleplanid(1L).cycleplanname("cycleplanname1").responsiblename("responsiblename1");
    }

    public static Cycleplan getCycleplanSample2() {
        return new Cycleplan().id(2L).cycleplanid(2L).cycleplanname("cycleplanname2").responsiblename("responsiblename2");
    }

    public static Cycleplan getCycleplanRandomSampleGenerator() {
        return new Cycleplan()
            .id(longCount.incrementAndGet())
            .cycleplanid(longCount.incrementAndGet())
            .cycleplanname(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString());
    }
}
