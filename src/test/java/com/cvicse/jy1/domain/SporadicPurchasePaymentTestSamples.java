package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class SporadicPurchasePaymentTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static SporadicPurchasePayment getSporadicPurchasePaymentSample1() {
        return new SporadicPurchasePayment().id(1).planpaymentnode("planpaymentnode1").financialvoucherid("financialvoucherid1");
    }

    public static SporadicPurchasePayment getSporadicPurchasePaymentSample2() {
        return new SporadicPurchasePayment().id(2).planpaymentnode("planpaymentnode2").financialvoucherid("financialvoucherid2");
    }

    public static SporadicPurchasePayment getSporadicPurchasePaymentRandomSampleGenerator() {
        return new SporadicPurchasePayment()
            .id(intCount.incrementAndGet())
            .planpaymentnode(UUID.randomUUID().toString())
            .financialvoucherid(UUID.randomUUID().toString());
    }
}
