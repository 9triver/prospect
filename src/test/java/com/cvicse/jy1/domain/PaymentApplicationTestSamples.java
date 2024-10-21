package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class PaymentApplicationTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static PaymentApplication getPaymentApplicationSample1() {
        return new PaymentApplication().id(1).workbagid("workbagid1").contractcode("contractcode1").planpaymentnode("planpaymentnode1");
    }

    public static PaymentApplication getPaymentApplicationSample2() {
        return new PaymentApplication().id(2).workbagid("workbagid2").contractcode("contractcode2").planpaymentnode("planpaymentnode2");
    }

    public static PaymentApplication getPaymentApplicationRandomSampleGenerator() {
        return new PaymentApplication()
            .id(intCount.incrementAndGet())
            .workbagid(UUID.randomUUID().toString())
            .contractcode(UUID.randomUUID().toString())
            .planpaymentnode(UUID.randomUUID().toString());
    }
}
