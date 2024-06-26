package com.cvicse.domain;

import java.util.UUID;

public class AnnualplanTestSamples {

    public static Annualplan getAnnualplanSample1() {
        return new Annualplan().id("id1").annualplanname("annualplanname1").creatorname("creatorname1");
    }

    public static Annualplan getAnnualplanSample2() {
        return new Annualplan().id("id2").annualplanname("annualplanname2").creatorname("creatorname2");
    }

    public static Annualplan getAnnualplanRandomSampleGenerator() {
        return new Annualplan()
            .id(UUID.randomUUID().toString())
            .annualplanname(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString());
    }
}
