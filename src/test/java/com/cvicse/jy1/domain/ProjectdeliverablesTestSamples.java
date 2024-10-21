package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ProjectdeliverablesTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Projectdeliverables getProjectdeliverablesSample1() {
        return new Projectdeliverables().id(1).parentcode("parentcode1").status("status1");
    }

    public static Projectdeliverables getProjectdeliverablesSample2() {
        return new Projectdeliverables().id(2).parentcode("parentcode2").status("status2");
    }

    public static Projectdeliverables getProjectdeliverablesRandomSampleGenerator() {
        return new Projectdeliverables()
            .id(intCount.incrementAndGet())
            .parentcode(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString());
    }
}
