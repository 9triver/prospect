package com.cvicse.domain;

import java.util.UUID;

public class PlanexecuteTestSamples {

    public static Planexecute getPlanexecuteSample1() {
        return new Planexecute().id("id1").planname("planname1");
    }

    public static Planexecute getPlanexecuteSample2() {
        return new Planexecute().id("id2").planname("planname2");
    }

    public static Planexecute getPlanexecuteRandomSampleGenerator() {
        return new Planexecute().id(UUID.randomUUID().toString()).planname(UUID.randomUUID().toString());
    }
}
