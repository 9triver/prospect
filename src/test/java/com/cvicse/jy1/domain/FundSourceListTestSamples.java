package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class FundSourceListTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static FundSourceList getFundSourceListSample1() {
        return new FundSourceList().id(1).paymentid("paymentid1").contractcode("contractcode1").contractname("contractname1");
    }

    public static FundSourceList getFundSourceListSample2() {
        return new FundSourceList().id(2).paymentid("paymentid2").contractcode("contractcode2").contractname("contractname2");
    }

    public static FundSourceList getFundSourceListRandomSampleGenerator() {
        return new FundSourceList()
            .id(intCount.incrementAndGet())
            .paymentid(UUID.randomUUID().toString())
            .contractcode(UUID.randomUUID().toString())
            .contractname(UUID.randomUUID().toString());
    }
}
