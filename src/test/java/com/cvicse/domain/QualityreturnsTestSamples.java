package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class QualityreturnsTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Qualityreturns getQualityreturnsSample1() {
        return new Qualityreturns().id("id1").qualityreturnsname("qualityreturnsname1").qualitytype(1);
    }

    public static Qualityreturns getQualityreturnsSample2() {
        return new Qualityreturns().id("id2").qualityreturnsname("qualityreturnsname2").qualitytype(2);
    }

    public static Qualityreturns getQualityreturnsRandomSampleGenerator() {
        return new Qualityreturns()
            .id(UUID.randomUUID().toString())
            .qualityreturnsname(UUID.randomUUID().toString())
            .qualitytype(intCount.incrementAndGet());
    }
}
