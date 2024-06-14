package com.cvicse.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class FundsavailabilityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Fundsavailability getFundsavailabilitySample1() {
        return new Fundsavailability().id(1L).fundsavailabilityid(1L).fundsid(1L).year(1L);
    }

    public static Fundsavailability getFundsavailabilitySample2() {
        return new Fundsavailability().id(2L).fundsavailabilityid(2L).fundsid(2L).year(2L);
    }

    public static Fundsavailability getFundsavailabilityRandomSampleGenerator() {
        return new Fundsavailability()
            .id(longCount.incrementAndGet())
            .fundsavailabilityid(longCount.incrementAndGet())
            .fundsid(longCount.incrementAndGet())
            .year(longCount.incrementAndGet());
    }
}
