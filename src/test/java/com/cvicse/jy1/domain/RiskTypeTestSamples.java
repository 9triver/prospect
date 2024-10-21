package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RiskTypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static RiskType getRiskTypeSample1() {
        return new RiskType().id(1).name("name1");
    }

    public static RiskType getRiskTypeSample2() {
        return new RiskType().id(2).name("name2");
    }

    public static RiskType getRiskTypeRandomSampleGenerator() {
        return new RiskType().id(intCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
