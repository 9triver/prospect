package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PlanstrategyTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Planstrategy getPlanstrategySample1() {
        return new Planstrategy().id(1L).strategyid("strategyid1").strategyname("strategyname1").number(1).type("type1");
    }

    public static Planstrategy getPlanstrategySample2() {
        return new Planstrategy().id(2L).strategyid("strategyid2").strategyname("strategyname2").number(2).type("type2");
    }

    public static Planstrategy getPlanstrategyRandomSampleGenerator() {
        return new Planstrategy()
            .id(longCount.incrementAndGet())
            .strategyid(UUID.randomUUID().toString())
            .strategyname(UUID.randomUUID().toString())
            .number(intCount.incrementAndGet())
            .type(UUID.randomUUID().toString());
    }
}
