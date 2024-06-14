package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AnnualplanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Annualplan getAnnualplanSample1() {
        return new Annualplan().id(1L).annualplanid(1L).annualplanname("annualplanname1").creatorname("creatorname1");
    }

    public static Annualplan getAnnualplanSample2() {
        return new Annualplan().id(2L).annualplanid(2L).annualplanname("annualplanname2").creatorname("creatorname2");
    }

    public static Annualplan getAnnualplanRandomSampleGenerator() {
        return new Annualplan()
            .id(longCount.incrementAndGet())
            .annualplanid(longCount.incrementAndGet())
            .annualplanname(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString());
    }
}
