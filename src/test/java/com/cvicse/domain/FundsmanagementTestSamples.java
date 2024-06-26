package com.cvicse.domain;

import java.util.UUID;

public class FundsmanagementTestSamples {

    public static Fundsmanagement getFundsmanagementSample1() {
        return new Fundsmanagement().id("id1").name("name1").description("description1");
    }

    public static Fundsmanagement getFundsmanagementSample2() {
        return new Fundsmanagement().id("id2").name("name2").description("description2");
    }

    public static Fundsmanagement getFundsmanagementRandomSampleGenerator() {
        return new Fundsmanagement()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
