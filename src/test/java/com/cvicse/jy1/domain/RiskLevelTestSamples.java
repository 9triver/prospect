package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RiskLevelTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static RiskLevel getRiskLevelSample1() {
        return new RiskLevel().id(1).name("name1");
    }

    public static RiskLevel getRiskLevelSample2() {
        return new RiskLevel().id(2).name("name2");
    }

    public static RiskLevel getRiskLevelRandomSampleGenerator() {
        return new RiskLevel().id(intCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
