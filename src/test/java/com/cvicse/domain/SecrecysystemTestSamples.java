package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SecrecysystemTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Secrecysystem getSecrecysystemSample1() {
        return new Secrecysystem().id("id1").publishedby("publishedby1").documentname("documentname1").documenttype(1).documentsize(1L);
    }

    public static Secrecysystem getSecrecysystemSample2() {
        return new Secrecysystem().id("id2").publishedby("publishedby2").documentname("documentname2").documenttype(2).documentsize(2L);
    }

    public static Secrecysystem getSecrecysystemRandomSampleGenerator() {
        return new Secrecysystem()
            .id(UUID.randomUUID().toString())
            .publishedby(UUID.randomUUID().toString())
            .documentname(UUID.randomUUID().toString())
            .documenttype(intCount.incrementAndGet())
            .documentsize(longCount.incrementAndGet());
    }
}
