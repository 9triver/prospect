package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class EvaluationCriteriaTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static EvaluationCriteria getEvaluationCriteriaSample1() {
        return new EvaluationCriteria().id("id1").standardtype(1).standardname("standardname1");
    }

    public static EvaluationCriteria getEvaluationCriteriaSample2() {
        return new EvaluationCriteria().id("id2").standardtype(2).standardname("standardname2");
    }

    public static EvaluationCriteria getEvaluationCriteriaRandomSampleGenerator() {
        return new EvaluationCriteria()
            .id(UUID.randomUUID().toString())
            .standardtype(intCount.incrementAndGet())
            .standardname(UUID.randomUUID().toString());
    }
}
