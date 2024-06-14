package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ResourcemanagementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Resourcemanagement getResourcemanagementSample1() {
        return new Resourcemanagement()
            .id(1L)
            .resourceid(1L)
            .projectname("projectname1")
            .clientname("clientname1")
            .creatorname("creatorname1");
    }

    public static Resourcemanagement getResourcemanagementSample2() {
        return new Resourcemanagement()
            .id(2L)
            .resourceid(2L)
            .projectname("projectname2")
            .clientname("clientname2")
            .creatorname("creatorname2");
    }

    public static Resourcemanagement getResourcemanagementRandomSampleGenerator() {
        return new Resourcemanagement()
            .id(longCount.incrementAndGet())
            .resourceid(longCount.incrementAndGet())
            .projectname(UUID.randomUUID().toString())
            .clientname(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString());
    }
}
