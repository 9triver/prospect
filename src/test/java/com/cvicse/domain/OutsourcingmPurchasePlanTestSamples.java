package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OutsourcingmPurchasePlanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OutsourcingmPurchasePlan getOutsourcingmPurchasePlanSample1() {
        return new OutsourcingmPurchasePlan().id(1L).outsourcingplanid(1L).matarialname("matarialname1").purchasingmethod(1).supplierid(1L);
    }

    public static OutsourcingmPurchasePlan getOutsourcingmPurchasePlanSample2() {
        return new OutsourcingmPurchasePlan().id(2L).outsourcingplanid(2L).matarialname("matarialname2").purchasingmethod(2).supplierid(2L);
    }

    public static OutsourcingmPurchasePlan getOutsourcingmPurchasePlanRandomSampleGenerator() {
        return new OutsourcingmPurchasePlan()
            .id(longCount.incrementAndGet())
            .outsourcingplanid(longCount.incrementAndGet())
            .matarialname(UUID.randomUUID().toString())
            .purchasingmethod(intCount.incrementAndGet())
            .supplierid(longCount.incrementAndGet());
    }
}
