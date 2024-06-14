package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ProjectSecrecyTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ProjectSecrecy getProjectSecrecySample1() {
        return new ProjectSecrecy().id(1L).projectname("projectname1").description("description1");
    }

    public static ProjectSecrecy getProjectSecrecySample2() {
        return new ProjectSecrecy().id(2L).projectname("projectname2").description("description2");
    }

    public static ProjectSecrecy getProjectSecrecyRandomSampleGenerator() {
        return new ProjectSecrecy()
            .id(longCount.incrementAndGet())
            .projectname(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
