package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ProjectTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Project getProjectSample1() {
        return new Project()
            .id("id1")
            .projectname("projectname1")
            .parentid("parentid1")
            .pbsid("pbsid1")
            .description("description1")
            .number(1)
            .projecttype(1)
            .priorty(1)
            .progress(1);
    }

    public static Project getProjectSample2() {
        return new Project()
            .id("id2")
            .projectname("projectname2")
            .parentid("parentid2")
            .pbsid("pbsid2")
            .description("description2")
            .number(2)
            .projecttype(2)
            .priorty(2)
            .progress(2);
    }

    public static Project getProjectRandomSampleGenerator() {
        return new Project()
            .id(UUID.randomUUID().toString())
            .projectname(UUID.randomUUID().toString())
            .parentid(UUID.randomUUID().toString())
            .pbsid(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .number(intCount.incrementAndGet())
            .projecttype(intCount.incrementAndGet())
            .priorty(intCount.incrementAndGet())
            .progress(intCount.incrementAndGet());
    }
}
