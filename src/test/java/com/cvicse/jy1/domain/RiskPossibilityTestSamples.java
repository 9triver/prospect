package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RiskPossibilityTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static RiskPossibility getRiskPossibilitySample1() {
        return new RiskPossibility().id(1).name("name1");
    }

    public static RiskPossibility getRiskPossibilitySample2() {
        return new RiskPossibility().id(2).name("name2");
    }

    public static RiskPossibility getRiskPossibilityRandomSampleGenerator() {
        return new RiskPossibility().id(intCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
