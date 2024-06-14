package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class WbsmanageTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Wbsmanage getWbsmanageSample1() {
        return new Wbsmanage()
            .id(1L)
            .wbsid("wbsid1")
            .wbsname("wbsname1")
            .description("description1")
            .result("result1")
            .administratorname("administratorname1")
            .responsiblename("responsiblename1")
            .department("department1");
    }

    public static Wbsmanage getWbsmanageSample2() {
        return new Wbsmanage()
            .id(2L)
            .wbsid("wbsid2")
            .wbsname("wbsname2")
            .description("description2")
            .result("result2")
            .administratorname("administratorname2")
            .responsiblename("responsiblename2")
            .department("department2");
    }

    public static Wbsmanage getWbsmanageRandomSampleGenerator() {
        return new Wbsmanage()
            .id(longCount.incrementAndGet())
            .wbsid(UUID.randomUUID().toString())
            .wbsname(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .result(UUID.randomUUID().toString())
            .administratorname(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString());
    }
}
