package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class QualityobjectivesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Qualityobjectives getQualityobjectivesSample1() {
        return new Qualityobjectives().id("id1").qualityobjectivesname("qualityobjectivesname1").year(1L).creatorname("creatorname1");
    }

    public static Qualityobjectives getQualityobjectivesSample2() {
        return new Qualityobjectives().id("id2").qualityobjectivesname("qualityobjectivesname2").year(2L).creatorname("creatorname2");
    }

    public static Qualityobjectives getQualityobjectivesRandomSampleGenerator() {
        return new Qualityobjectives()
            .id(UUID.randomUUID().toString())
            .qualityobjectivesname(UUID.randomUUID().toString())
            .year(longCount.incrementAndGet())
            .creatorname(UUID.randomUUID().toString());
    }
}
