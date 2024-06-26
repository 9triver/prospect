package com.cvicse.domain;

import java.util.UUID;

public class SecuritymanagementTestSamples {

    public static Securitymanagement getSecuritymanagementSample1() {
        return new Securitymanagement().id("id1").name("name1").description("description1");
    }

    public static Securitymanagement getSecuritymanagementSample2() {
        return new Securitymanagement().id("id2").name("name2").description("description2");
    }

    public static Securitymanagement getSecuritymanagementRandomSampleGenerator() {
        return new Securitymanagement()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
