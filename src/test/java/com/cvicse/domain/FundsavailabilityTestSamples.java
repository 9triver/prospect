package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FundsavailabilityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Fundsavailability getFundsavailabilitySample1() {
        return new Fundsavailability().id("id1").fundsid("fundsid1").year(1L);
    }

    public static Fundsavailability getFundsavailabilitySample2() {
        return new Fundsavailability().id("id2").fundsid("fundsid2").year(2L);
    }

    public static Fundsavailability getFundsavailabilityRandomSampleGenerator() {
        return new Fundsavailability()
            .id(UUID.randomUUID().toString())
            .fundsid(UUID.randomUUID().toString())
            .year(longCount.incrementAndGet());
    }
}
