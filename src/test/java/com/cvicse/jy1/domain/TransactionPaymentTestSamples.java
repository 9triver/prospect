package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionPaymentTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static TransactionPayment getTransactionPaymentSample1() {
        return new TransactionPayment().id(1).planpaymentnode("planpaymentnode1").financialvoucherid("financialvoucherid1");
    }

    public static TransactionPayment getTransactionPaymentSample2() {
        return new TransactionPayment().id(2).planpaymentnode("planpaymentnode2").financialvoucherid("financialvoucherid2");
    }

    public static TransactionPayment getTransactionPaymentRandomSampleGenerator() {
        return new TransactionPayment()
            .id(intCount.incrementAndGet())
            .planpaymentnode(UUID.randomUUID().toString())
            .financialvoucherid(UUID.randomUUID().toString());
    }
}
