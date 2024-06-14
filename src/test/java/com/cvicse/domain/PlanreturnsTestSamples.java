package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PlanreturnsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Planreturns getPlanreturnsSample1() {
        return new Planreturns().id(1L).planreturnsid(1L).planreturnsname("planreturnsname1").plantype(1);
    }

    public static Planreturns getPlanreturnsSample2() {
        return new Planreturns().id(2L).planreturnsid(2L).planreturnsname("planreturnsname2").plantype(2);
    }

    public static Planreturns getPlanreturnsRandomSampleGenerator() {
        return new Planreturns()
            .id(longCount.incrementAndGet())
            .planreturnsid(longCount.incrementAndGet())
            .planreturnsname(UUID.randomUUID().toString())
            .plantype(intCount.incrementAndGet());
    }
}
