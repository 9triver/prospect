package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ProjectTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Project getProjectSample1() {
        return new Project()
            .id(1L)
            .projectid(1L)
            .projectname("projectname1")
            .description("description1")
            .number(1)
            .projecttype(1)
            .responsiblename("responsiblename1")
            .priorty(1)
            .progressid(1L)
            .returnsid(1L)
            .qualityid(1L)
            .fundsid(1L);
    }

    public static Project getProjectSample2() {
        return new Project()
            .id(2L)
            .projectid(2L)
            .projectname("projectname2")
            .description("description2")
            .number(2)
            .projecttype(2)
            .responsiblename("responsiblename2")
            .priorty(2)
            .progressid(2L)
            .returnsid(2L)
            .qualityid(2L)
            .fundsid(2L);
    }

    public static Project getProjectRandomSampleGenerator() {
        return new Project()
            .id(longCount.incrementAndGet())
            .projectid(longCount.incrementAndGet())
            .projectname(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .number(intCount.incrementAndGet())
            .projecttype(intCount.incrementAndGet())
            .responsiblename(UUID.randomUUID().toString())
            .priorty(intCount.incrementAndGet())
            .progressid(longCount.incrementAndGet())
            .returnsid(longCount.incrementAndGet())
            .qualityid(longCount.incrementAndGet())
            .fundsid(longCount.incrementAndGet());
    }
}
