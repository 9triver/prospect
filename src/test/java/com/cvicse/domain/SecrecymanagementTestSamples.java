package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SecrecymanagementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Secrecymanagement getSecrecymanagementSample1() {
        return new Secrecymanagement()
            .id(1L)
            .secrecyid(1L)
            .publishedby("publishedby1")
            .documentname("documentname1")
            .documenttype(1)
            .documentsize(1L);
    }

    public static Secrecymanagement getSecrecymanagementSample2() {
        return new Secrecymanagement()
            .id(2L)
            .secrecyid(2L)
            .publishedby("publishedby2")
            .documentname("documentname2")
            .documenttype(2)
            .documentsize(2L);
    }

    public static Secrecymanagement getSecrecymanagementRandomSampleGenerator() {
        return new Secrecymanagement()
            .id(longCount.incrementAndGet())
            .secrecyid(longCount.incrementAndGet())
            .publishedby(UUID.randomUUID().toString())
            .documentname(UUID.randomUUID().toString())
            .documenttype(intCount.incrementAndGet())
            .documentsize(longCount.incrementAndGet());
    }
}
