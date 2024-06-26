package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class PlanreturnsTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Planreturns getPlanreturnsSample1() {
        return new Planreturns().id("id1").planreturnsname("planreturnsname1").plantype(1);
    }

    public static Planreturns getPlanreturnsSample2() {
        return new Planreturns().id("id2").planreturnsname("planreturnsname2").plantype(2);
    }

    public static Planreturns getPlanreturnsRandomSampleGenerator() {
        return new Planreturns()
            .id(UUID.randomUUID().toString())
            .planreturnsname(UUID.randomUUID().toString())
            .plantype(intCount.incrementAndGet());
    }
}
