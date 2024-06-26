package com.cvicse.domain;

import java.util.UUID;

public class RiskmanagementTestSamples {

    public static Riskmanagement getRiskmanagementSample1() {
        return new Riskmanagement().id("id1").name("name1").description("description1");
    }

    public static Riskmanagement getRiskmanagementSample2() {
        return new Riskmanagement().id("id2").name("name2").description("description2");
    }

    public static Riskmanagement getRiskmanagementRandomSampleGenerator() {
        return new Riskmanagement()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
