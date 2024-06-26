package com.cvicse.domain;

import java.util.UUID;

public class SecrecymanagementTestSamples {

    public static Secrecymanagement getSecrecymanagementSample1() {
        return new Secrecymanagement().id("id1").name("name1").description("description1");
    }

    public static Secrecymanagement getSecrecymanagementSample2() {
        return new Secrecymanagement().id("id2").name("name2").description("description2");
    }

    public static Secrecymanagement getSecrecymanagementRandomSampleGenerator() {
        return new Secrecymanagement()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
