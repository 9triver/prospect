package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ComprehensivecontrolTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Comprehensivecontrol getComprehensivecontrolSample1() {
        return new Comprehensivecontrol()
            .id(1L)
            .controlid(1L)
            .description("description1")
            .number(1)
            .type(1)
            .result("result1")
            .acceptancetype("acceptancetype1")
            .responsiblename("responsiblename1");
    }

    public static Comprehensivecontrol getComprehensivecontrolSample2() {
        return new Comprehensivecontrol()
            .id(2L)
            .controlid(2L)
            .description("description2")
            .number(2)
            .type(2)
            .result("result2")
            .acceptancetype("acceptancetype2")
            .responsiblename("responsiblename2");
    }

    public static Comprehensivecontrol getComprehensivecontrolRandomSampleGenerator() {
        return new Comprehensivecontrol()
            .id(longCount.incrementAndGet())
            .controlid(longCount.incrementAndGet())
            .description(UUID.randomUUID().toString())
            .number(intCount.incrementAndGet())
            .type(intCount.incrementAndGet())
            .result(UUID.randomUUID().toString())
            .acceptancetype(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString());
    }
}
