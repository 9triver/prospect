package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RoleTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Role getRoleSample1() {
        return new Role().id(1L).roleid(1L).rolename("rolename1").description("description1");
    }

    public static Role getRoleSample2() {
        return new Role().id(2L).roleid(2L).rolename("rolename2").description("description2");
    }

    public static Role getRoleRandomSampleGenerator() {
        return new Role()
            .id(longCount.incrementAndGet())
            .roleid(longCount.incrementAndGet())
            .rolename(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
