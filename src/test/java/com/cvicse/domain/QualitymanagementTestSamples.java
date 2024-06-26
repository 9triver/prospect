package com.cvicse.domain;

import java.util.UUID;

public class QualitymanagementTestSamples {

    public static Qualitymanagement getQualitymanagementSample1() {
        return new Qualitymanagement().id("id1").name("name1").description("description1");
    }

    public static Qualitymanagement getQualitymanagementSample2() {
        return new Qualitymanagement().id("id2").name("name2").description("description2");
    }

    public static Qualitymanagement getQualitymanagementRandomSampleGenerator() {
        return new Qualitymanagement()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
