package com.cvicse.domain;

import java.util.UUID;

public class TechnicalmanagementTestSamples {

    public static Technicalmanagement getTechnicalmanagementSample1() {
        return new Technicalmanagement().id("id1").name("name1").description("description1");
    }

    public static Technicalmanagement getTechnicalmanagementSample2() {
        return new Technicalmanagement().id("id2").name("name2").description("description2");
    }

    public static Technicalmanagement getTechnicalmanagementRandomSampleGenerator() {
        return new Technicalmanagement()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
