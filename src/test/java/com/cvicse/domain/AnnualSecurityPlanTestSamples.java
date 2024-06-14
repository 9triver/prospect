package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AnnualSecurityPlanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static AnnualSecurityPlan getAnnualSecurityPlanSample1() {
        return new AnnualSecurityPlan()
            .id(1L)
            .securityplanid(1L)
            .securityplanname("securityplanname1")
            .creatorname("creatorname1")
            .version(1);
    }

    public static AnnualSecurityPlan getAnnualSecurityPlanSample2() {
        return new AnnualSecurityPlan()
            .id(2L)
            .securityplanid(2L)
            .securityplanname("securityplanname2")
            .creatorname("creatorname2")
            .version(2);
    }

    public static AnnualSecurityPlan getAnnualSecurityPlanRandomSampleGenerator() {
        return new AnnualSecurityPlan()
            .id(longCount.incrementAndGet())
            .securityplanid(longCount.incrementAndGet())
            .securityplanname(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString())
            .version(intCount.incrementAndGet());
    }
}
