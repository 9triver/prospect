package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class WbsbaselineTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Wbsbaseline getWbsbaselineSample1() {
        return new Wbsbaseline()
            .id(1L)
            .formid("formid1")
            .requestdeportment("requestdeportment1")
            .chargetype(1)
            .chargecontent("chargecontent1")
            .status(1)
            .version(1)
            .remark("remark1");
    }

    public static Wbsbaseline getWbsbaselineSample2() {
        return new Wbsbaseline()
            .id(2L)
            .formid("formid2")
            .requestdeportment("requestdeportment2")
            .chargetype(2)
            .chargecontent("chargecontent2")
            .status(2)
            .version(2)
            .remark("remark2");
    }

    public static Wbsbaseline getWbsbaselineRandomSampleGenerator() {
        return new Wbsbaseline()
            .id(longCount.incrementAndGet())
            .formid(UUID.randomUUID().toString())
            .requestdeportment(UUID.randomUUID().toString())
            .chargetype(intCount.incrementAndGet())
            .chargecontent(UUID.randomUUID().toString())
            .status(intCount.incrementAndGet())
            .version(intCount.incrementAndGet())
            .remark(UUID.randomUUID().toString());
    }
}
