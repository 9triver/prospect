package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ProjectchargeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Projectcharge getProjectchargeSample1() {
        return new Projectcharge()
            .id(1L)
            .projectname("projectname1")
            .formid("formid1")
            .requestdeportment("requestdeportment1")
            .chargetype(1)
            .chargecontent("chargecontent1");
    }

    public static Projectcharge getProjectchargeSample2() {
        return new Projectcharge()
            .id(2L)
            .projectname("projectname2")
            .formid("formid2")
            .requestdeportment("requestdeportment2")
            .chargetype(2)
            .chargecontent("chargecontent2");
    }

    public static Projectcharge getProjectchargeRandomSampleGenerator() {
        return new Projectcharge()
            .id(longCount.incrementAndGet())
            .projectname(UUID.randomUUID().toString())
            .formid(UUID.randomUUID().toString())
            .requestdeportment(UUID.randomUUID().toString())
            .chargetype(intCount.incrementAndGet())
            .chargecontent(UUID.randomUUID().toString());
    }
}
