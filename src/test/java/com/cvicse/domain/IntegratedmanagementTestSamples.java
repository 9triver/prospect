package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class IntegratedmanagementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Integratedmanagement getIntegratedmanagementSample1() {
        return new Integratedmanagement().id(1L).name("name1").description("description1");
    }

    public static Integratedmanagement getIntegratedmanagementSample2() {
        return new Integratedmanagement().id(2L).name("name2").description("description2");
    }

    public static Integratedmanagement getIntegratedmanagementRandomSampleGenerator() {
        return new Integratedmanagement()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
