package com.cvicse.domain;

import java.util.UUID;

public class TotalbudgetTestSamples {

    public static Totalbudget getTotalbudgetSample1() {
        return new Totalbudget().id("id1").valuationsubjects("valuationsubjects1").remarks("remarks1");
    }

    public static Totalbudget getTotalbudgetSample2() {
        return new Totalbudget().id("id2").valuationsubjects("valuationsubjects2").remarks("remarks2");
    }

    public static Totalbudget getTotalbudgetRandomSampleGenerator() {
        return new Totalbudget()
            .id(UUID.randomUUID().toString())
            .valuationsubjects(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString());
    }
}
