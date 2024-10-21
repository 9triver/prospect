package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class PaymentCostListTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static PaymentCostList getPaymentCostListSample1() {
        return new PaymentCostList().id(1).wbsid("wbsid1").wbsname("wbsname1").parentwbsid("parentwbsid1").unit("unit1");
    }

    public static PaymentCostList getPaymentCostListSample2() {
        return new PaymentCostList().id(2).wbsid("wbsid2").wbsname("wbsname2").parentwbsid("parentwbsid2").unit("unit2");
    }

    public static PaymentCostList getPaymentCostListRandomSampleGenerator() {
        return new PaymentCostList()
            .id(intCount.incrementAndGet())
            .wbsid(UUID.randomUUID().toString())
            .wbsname(UUID.randomUUID().toString())
            .parentwbsid(UUID.randomUUID().toString())
            .unit(UUID.randomUUID().toString());
    }
}
