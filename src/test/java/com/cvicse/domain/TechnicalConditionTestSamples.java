package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TechnicalConditionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TechnicalCondition getTechnicalConditionSample1() {
        return new TechnicalCondition()
            .id(1L)
            .caption("caption1")
            .projectname("projectname1")
            .decumentid(1L)
            .claimant("claimant1")
            .applicant("applicant1")
            .validrange("validrange1");
    }

    public static TechnicalCondition getTechnicalConditionSample2() {
        return new TechnicalCondition()
            .id(2L)
            .caption("caption2")
            .projectname("projectname2")
            .decumentid(2L)
            .claimant("claimant2")
            .applicant("applicant2")
            .validrange("validrange2");
    }

    public static TechnicalCondition getTechnicalConditionRandomSampleGenerator() {
        return new TechnicalCondition()
            .id(longCount.incrementAndGet())
            .caption(UUID.randomUUID().toString())
            .projectname(UUID.randomUUID().toString())
            .decumentid(longCount.incrementAndGet())
            .claimant(UUID.randomUUID().toString())
            .applicant(UUID.randomUUID().toString())
            .validrange(UUID.randomUUID().toString());
    }
}
