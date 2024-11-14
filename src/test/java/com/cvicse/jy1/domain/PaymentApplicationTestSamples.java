package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class PaymentApplicationTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static PaymentApplication getPaymentApplicationSample1() {
        return new PaymentApplication()
            .id(1)
            .workbagname("workbagname1")
            .outsourcingcontractid("outsourcingcontractid1")
            .outsourcingcontractname("outsourcingcontractname1")
            .planpaymentnode("planpaymentnode1")
            .planpaymentname("planpaymentname1")
            .contractpaymentid(1)
            .status("status1");
    }

    public static PaymentApplication getPaymentApplicationSample2() {
        return new PaymentApplication()
            .id(2)
            .workbagname("workbagname2")
            .outsourcingcontractid("outsourcingcontractid2")
            .outsourcingcontractname("outsourcingcontractname2")
            .planpaymentnode("planpaymentnode2")
            .planpaymentname("planpaymentname2")
            .contractpaymentid(2)
            .status("status2");
    }

    public static PaymentApplication getPaymentApplicationRandomSampleGenerator() {
        return new PaymentApplication()
            .id(intCount.incrementAndGet())
            .workbagname(UUID.randomUUID().toString())
            .outsourcingcontractid(UUID.randomUUID().toString())
            .outsourcingcontractname(UUID.randomUUID().toString())
            .planpaymentnode(UUID.randomUUID().toString())
            .planpaymentname(UUID.randomUUID().toString())
            .contractpaymentid(intCount.incrementAndGet())
            .status(UUID.randomUUID().toString());
    }
}
