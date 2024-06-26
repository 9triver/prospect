package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ManagementCapacityEvaluationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ManagementCapacityEvaluation getManagementCapacityEvaluationSample1() {
        return new ManagementCapacityEvaluation()
            .id("id1")
            .year(1L)
            .deprotment("deprotment1")
            .status(1)
            .ratingpersonname("ratingpersonname1")
            .ratingdepartment("ratingdepartment1");
    }

    public static ManagementCapacityEvaluation getManagementCapacityEvaluationSample2() {
        return new ManagementCapacityEvaluation()
            .id("id2")
            .year(2L)
            .deprotment("deprotment2")
            .status(2)
            .ratingpersonname("ratingpersonname2")
            .ratingdepartment("ratingdepartment2");
    }

    public static ManagementCapacityEvaluation getManagementCapacityEvaluationRandomSampleGenerator() {
        return new ManagementCapacityEvaluation()
            .id(UUID.randomUUID().toString())
            .year(longCount.incrementAndGet())
            .deprotment(UUID.randomUUID().toString())
            .status(intCount.incrementAndGet())
            .ratingpersonname(UUID.randomUUID().toString())
            .ratingdepartment(UUID.randomUUID().toString());
    }
}
