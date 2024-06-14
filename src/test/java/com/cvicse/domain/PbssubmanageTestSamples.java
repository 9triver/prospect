package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PbssubmanageTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Pbssubmanage getPbssubmanageSample1() {
        return new Pbssubmanage()
            .id(1L)
            .pbssubid("pbssubid1")
            .pbssubname("pbssubname1")
            .responsiblename("responsiblename1")
            .responsibledepartment("responsibledepartment1")
            .relevantdepartment("relevantdepartment1")
            .type("type1");
    }

    public static Pbssubmanage getPbssubmanageSample2() {
        return new Pbssubmanage()
            .id(2L)
            .pbssubid("pbssubid2")
            .pbssubname("pbssubname2")
            .responsiblename("responsiblename2")
            .responsibledepartment("responsibledepartment2")
            .relevantdepartment("relevantdepartment2")
            .type("type2");
    }

    public static Pbssubmanage getPbssubmanageRandomSampleGenerator() {
        return new Pbssubmanage()
            .id(longCount.incrementAndGet())
            .pbssubid(UUID.randomUUID().toString())
            .pbssubname(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString())
            .responsibledepartment(UUID.randomUUID().toString())
            .relevantdepartment(UUID.randomUUID().toString())
            .type(UUID.randomUUID().toString());
    }
}
