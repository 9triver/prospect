package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SafetycheckTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Safetycheck getSafetycheckSample1() {
        return new Safetycheck()
            .id("id1")
            .safetycheckname("safetycheckname1")
            .checksource("checksource1")
            .operatinglocation("operatinglocation1")
            .deprotment("deprotment1")
            .phonenumber(1L);
    }

    public static Safetycheck getSafetycheckSample2() {
        return new Safetycheck()
            .id("id2")
            .safetycheckname("safetycheckname2")
            .checksource("checksource2")
            .operatinglocation("operatinglocation2")
            .deprotment("deprotment2")
            .phonenumber(2L);
    }

    public static Safetycheck getSafetycheckRandomSampleGenerator() {
        return new Safetycheck()
            .id(UUID.randomUUID().toString())
            .safetycheckname(UUID.randomUUID().toString())
            .checksource(UUID.randomUUID().toString())
            .operatinglocation(UUID.randomUUID().toString())
            .deprotment(UUID.randomUUID().toString())
            .phonenumber(longCount.incrementAndGet());
    }
}
