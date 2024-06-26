package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ContractualfundsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Contractualfunds getContractualfundsSample1() {
        return new Contractualfunds()
            .id("id1")
            .department("department1")
            .year(1L)
            .status(1)
            .responsibleunitname("responsibleunitname1")
            .accountbank("accountbank1");
    }

    public static Contractualfunds getContractualfundsSample2() {
        return new Contractualfunds()
            .id("id2")
            .department("department2")
            .year(2L)
            .status(2)
            .responsibleunitname("responsibleunitname2")
            .accountbank("accountbank2");
    }

    public static Contractualfunds getContractualfundsRandomSampleGenerator() {
        return new Contractualfunds()
            .id(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .year(longCount.incrementAndGet())
            .status(intCount.incrementAndGet())
            .responsibleunitname(UUID.randomUUID().toString())
            .accountbank(UUID.randomUUID().toString());
    }
}
