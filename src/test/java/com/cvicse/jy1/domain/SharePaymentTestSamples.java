package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class SharePaymentTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static SharePayment getSharePaymentSample1() {
        return new SharePayment().id(1).planpaymentnode("planpaymentnode1").financialvoucherid("financialvoucherid1");
    }

    public static SharePayment getSharePaymentSample2() {
        return new SharePayment().id(2).planpaymentnode("planpaymentnode2").financialvoucherid("financialvoucherid2");
    }

    public static SharePayment getSharePaymentRandomSampleGenerator() {
        return new SharePayment()
            .id(intCount.incrementAndGet())
            .planpaymentnode(UUID.randomUUID().toString())
            .financialvoucherid(UUID.randomUUID().toString());
    }
}
