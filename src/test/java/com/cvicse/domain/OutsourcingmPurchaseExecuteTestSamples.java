package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OutsourcingmPurchaseExecuteTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OutsourcingmPurchaseExecute getOutsourcingmPurchaseExecuteSample1() {
        return new OutsourcingmPurchaseExecute()
            .id(1L)
            .outsourcingexecuteid(1L)
            .matarialname("matarialname1")
            .purchasingmethod(1)
            .supplierid(1L);
    }

    public static OutsourcingmPurchaseExecute getOutsourcingmPurchaseExecuteSample2() {
        return new OutsourcingmPurchaseExecute()
            .id(2L)
            .outsourcingexecuteid(2L)
            .matarialname("matarialname2")
            .purchasingmethod(2)
            .supplierid(2L);
    }

    public static OutsourcingmPurchaseExecute getOutsourcingmPurchaseExecuteRandomSampleGenerator() {
        return new OutsourcingmPurchaseExecute()
            .id(longCount.incrementAndGet())
            .outsourcingexecuteid(longCount.incrementAndGet())
            .matarialname(UUID.randomUUID().toString())
            .purchasingmethod(intCount.incrementAndGet())
            .supplierid(longCount.incrementAndGet());
    }
}
