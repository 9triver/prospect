package com.cvicse.domain;

import java.util.UUID;

public class MonthplanTestSamples {

    public static Monthplan getMonthplanSample1() {
        return new Monthplan().id("id1").monthplanname("monthplanname1").creatorname("creatorname1");
    }

    public static Monthplan getMonthplanSample2() {
        return new Monthplan().id("id2").monthplanname("monthplanname2").creatorname("creatorname2");
    }

    public static Monthplan getMonthplanRandomSampleGenerator() {
        return new Monthplan()
            .id(UUID.randomUUID().toString())
            .monthplanname(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString());
    }
}
