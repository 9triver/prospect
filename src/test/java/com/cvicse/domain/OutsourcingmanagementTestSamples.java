package com.cvicse.domain;

import java.util.UUID;

public class OutsourcingmanagementTestSamples {

    public static Outsourcingmanagement getOutsourcingmanagementSample1() {
        return new Outsourcingmanagement().id("id1").name("name1").description("description1");
    }

    public static Outsourcingmanagement getOutsourcingmanagementSample2() {
        return new Outsourcingmanagement().id("id2").name("name2").description("description2");
    }

    public static Outsourcingmanagement getOutsourcingmanagementRandomSampleGenerator() {
        return new Outsourcingmanagement()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
