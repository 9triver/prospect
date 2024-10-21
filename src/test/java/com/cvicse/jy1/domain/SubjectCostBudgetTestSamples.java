package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SubjectCostBudgetTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static SubjectCostBudget getSubjectCostBudgetSample1() {
        return new SubjectCostBudget().id(1L).contractid("contractid1").subjectid(1).subjectname("subjectname1");
    }

    public static SubjectCostBudget getSubjectCostBudgetSample2() {
        return new SubjectCostBudget().id(2L).contractid("contractid2").subjectid(2).subjectname("subjectname2");
    }

    public static SubjectCostBudget getSubjectCostBudgetRandomSampleGenerator() {
        return new SubjectCostBudget()
            .id(longCount.incrementAndGet())
            .contractid(UUID.randomUUID().toString())
            .subjectid(intCount.incrementAndGet())
            .subjectname(UUID.randomUUID().toString());
    }
}
