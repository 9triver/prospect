package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class QualityreturnsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Qualityreturns getQualityreturnsSample1() {
        return new Qualityreturns().id(1L).qualityreturnsid(1L).qualityreturnsname("qualityreturnsname1").qualitytype(1);
    }

    public static Qualityreturns getQualityreturnsSample2() {
        return new Qualityreturns().id(2L).qualityreturnsid(2L).qualityreturnsname("qualityreturnsname2").qualitytype(2);
    }

    public static Qualityreturns getQualityreturnsRandomSampleGenerator() {
        return new Qualityreturns()
            .id(longCount.incrementAndGet())
            .qualityreturnsid(longCount.incrementAndGet())
            .qualityreturnsname(UUID.randomUUID().toString())
            .qualitytype(intCount.incrementAndGet());
    }
}
