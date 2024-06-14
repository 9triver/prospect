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
            .id(1L)
            .contractualid(1L)
            .department(1L)
            .year(1L)
            .status(1)
            .responsibleunitname("responsibleunitname1")
            .accountbank(1L);
    }

    public static Contractualfunds getContractualfundsSample2() {
        return new Contractualfunds()
            .id(2L)
            .contractualid(2L)
            .department(2L)
            .year(2L)
            .status(2)
            .responsibleunitname("responsibleunitname2")
            .accountbank(2L);
    }

    public static Contractualfunds getContractualfundsRandomSampleGenerator() {
        return new Contractualfunds()
            .id(longCount.incrementAndGet())
            .contractualid(longCount.incrementAndGet())
            .department(longCount.incrementAndGet())
            .year(longCount.incrementAndGet())
            .status(intCount.incrementAndGet())
            .responsibleunitname(UUID.randomUUID().toString())
            .accountbank(longCount.incrementAndGet());
    }
}
