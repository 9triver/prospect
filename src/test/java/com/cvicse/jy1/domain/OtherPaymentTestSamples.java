package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class OtherPaymentTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OtherPayment getOtherPaymentSample1() {
        return new OtherPayment()
            .id(1)
            .name("name1")
            .subjectid(1)
            .subjectname("subjectname1")
            .contractcode("contractcode1")
            .contractname("contractname1")
            .wbsid("wbsid1")
            .wbsname("wbsname1");
    }

    public static OtherPayment getOtherPaymentSample2() {
        return new OtherPayment()
            .id(2)
            .name("name2")
            .subjectid(2)
            .subjectname("subjectname2")
            .contractcode("contractcode2")
            .contractname("contractname2")
            .wbsid("wbsid2")
            .wbsname("wbsname2");
    }

    public static OtherPayment getOtherPaymentRandomSampleGenerator() {
        return new OtherPayment()
            .id(intCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .subjectid(intCount.incrementAndGet())
            .subjectname(UUID.randomUUID().toString())
            .contractcode(UUID.randomUUID().toString())
            .contractname(UUID.randomUUID().toString())
            .wbsid(UUID.randomUUID().toString())
            .wbsname(UUID.randomUUID().toString());
    }
}
