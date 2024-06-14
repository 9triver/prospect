package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TotalbudgetTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Totalbudget getTotalbudgetSample1() {
        return new Totalbudget().id(1L).totalbudgetid(1L).valuationsubjects("valuationsubjects1").remarks("remarks1");
    }

    public static Totalbudget getTotalbudgetSample2() {
        return new Totalbudget().id(2L).totalbudgetid(2L).valuationsubjects("valuationsubjects2").remarks("remarks2");
    }

    public static Totalbudget getTotalbudgetRandomSampleGenerator() {
        return new Totalbudget()
            .id(longCount.incrementAndGet())
            .totalbudgetid(longCount.incrementAndGet())
            .valuationsubjects(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString());
    }
}
