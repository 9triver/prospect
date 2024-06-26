package com.cvicse.domain;

import java.util.UUID;

public class CycleplanTestSamples {

    public static Cycleplan getCycleplanSample1() {
        return new Cycleplan().id("id1").cycleplanname("cycleplanname1").responsiblename("responsiblename1");
    }

    public static Cycleplan getCycleplanSample2() {
        return new Cycleplan().id("id2").cycleplanname("cycleplanname2").responsiblename("responsiblename2");
    }

    public static Cycleplan getCycleplanRandomSampleGenerator() {
        return new Cycleplan()
            .id(UUID.randomUUID().toString())
            .cycleplanname(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString());
    }
}
