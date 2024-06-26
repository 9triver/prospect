package com.cvicse.domain;

import java.util.UUID;

public class ProgressmanagementTestSamples {

    public static Progressmanagement getProgressmanagementSample1() {
        return new Progressmanagement().id("id1").name("name1").description("description1");
    }

    public static Progressmanagement getProgressmanagementSample2() {
        return new Progressmanagement().id("id2").name("name2").description("description2");
    }

    public static Progressmanagement getProgressmanagementRandomSampleGenerator() {
        return new Progressmanagement()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
