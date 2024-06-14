package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class EvaluationCriteriaTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static EvaluationCriteria getEvaluationCriteriaSample1() {
        return new EvaluationCriteria().id(1L).standardtype(1).standardname("standardname1");
    }

    public static EvaluationCriteria getEvaluationCriteriaSample2() {
        return new EvaluationCriteria().id(2L).standardtype(2).standardname("standardname2");
    }

    public static EvaluationCriteria getEvaluationCriteriaRandomSampleGenerator() {
        return new EvaluationCriteria()
            .id(longCount.incrementAndGet())
            .standardtype(intCount.incrementAndGet())
            .standardname(UUID.randomUUID().toString());
    }
}
