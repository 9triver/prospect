package com.cvicse.domain;

import java.util.UUID;

public class PermissionTestSamples {

    public static Permission getPermissionSample1() {
        return new Permission().id("id1").permissionname("permissionname1").description("description1");
    }

    public static Permission getPermissionSample2() {
        return new Permission().id("id2").permissionname("permissionname2").description("description2");
    }

    public static Permission getPermissionRandomSampleGenerator() {
        return new Permission()
            .id(UUID.randomUUID().toString())
            .permissionname(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
