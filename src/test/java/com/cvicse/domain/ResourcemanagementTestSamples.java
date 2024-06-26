package com.cvicse.domain;

import java.util.UUID;

public class ResourcemanagementTestSamples {

    public static Resourcemanagement getResourcemanagementSample1() {
        return new Resourcemanagement().id("id1").name("name1").description("description1");
    }

    public static Resourcemanagement getResourcemanagementSample2() {
        return new Resourcemanagement().id("id2").name("name2").description("description2");
    }

    public static Resourcemanagement getResourcemanagementRandomSampleGenerator() {
        return new Resourcemanagement()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
