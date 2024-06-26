package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OutsourcingPurchasePlanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OutsourcingPurchasePlan getOutsourcingPurchasePlanSample1() {
        return new OutsourcingPurchasePlan().id("id1").matarialname("matarialname1").purchasingmethod(1).supplierid(1L);
    }

    public static OutsourcingPurchasePlan getOutsourcingPurchasePlanSample2() {
        return new OutsourcingPurchasePlan().id("id2").matarialname("matarialname2").purchasingmethod(2).supplierid(2L);
    }

    public static OutsourcingPurchasePlan getOutsourcingPurchasePlanRandomSampleGenerator() {
        return new OutsourcingPurchasePlan()
            .id(UUID.randomUUID().toString())
            .matarialname(UUID.randomUUID().toString())
            .purchasingmethod(intCount.incrementAndGet())
            .supplierid(longCount.incrementAndGet());
    }
}
