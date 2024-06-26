package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgressbaselineTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Progressbaseline getProgressbaselineSample1() {
        return new Progressbaseline()
            .id("id1")
            .requestdeportment("requestdeportment1")
            .chargetype(1)
            .chargecontent("chargecontent1")
            .status(1)
            .version(1)
            .remark("remark1");
    }

    public static Progressbaseline getProgressbaselineSample2() {
        return new Progressbaseline()
            .id("id2")
            .requestdeportment("requestdeportment2")
            .chargetype(2)
            .chargecontent("chargecontent2")
            .status(2)
            .version(2)
            .remark("remark2");
    }

    public static Progressbaseline getProgressbaselineRandomSampleGenerator() {
        return new Progressbaseline()
            .id(UUID.randomUUID().toString())
            .requestdeportment(UUID.randomUUID().toString())
            .chargetype(intCount.incrementAndGet())
            .chargecontent(UUID.randomUUID().toString())
            .status(intCount.incrementAndGet())
            .version(intCount.incrementAndGet())
            .remark(UUID.randomUUID().toString());
    }
}
