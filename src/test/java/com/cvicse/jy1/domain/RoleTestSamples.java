package com.cvicse.jy1.domain;

import java.util.UUID;

public class RoleTestSamples {

    public static Role getRoleSample1() {
        return new Role().id("id1").rolename("rolename1").description("description1");
    }

    public static Role getRoleSample2() {
        return new Role().id("id2").rolename("rolename2").description("description2");
    }

    public static Role getRoleRandomSampleGenerator() {
        return new Role().id(UUID.randomUUID().toString()).rolename(UUID.randomUUID().toString()).description(UUID.randomUUID().toString());
    }
}
