package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ProjectRiskTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ProjectRisk getProjectRiskSample1() {
        return new ProjectRisk()
            .id("id1")
            .year(1L)
            .nodename("nodename1")
            .risktype(1)
            .decumentid(1L)
            .version(1)
            .systemlevel(1)
            .limitationtime("limitationtime1")
            .closetype(1);
    }

    public static ProjectRisk getProjectRiskSample2() {
        return new ProjectRisk()
            .id("id2")
            .year(2L)
            .nodename("nodename2")
            .risktype(2)
            .decumentid(2L)
            .version(2)
            .systemlevel(2)
            .limitationtime("limitationtime2")
            .closetype(2);
    }

    public static ProjectRisk getProjectRiskRandomSampleGenerator() {
        return new ProjectRisk()
            .id(UUID.randomUUID().toString())
            .year(longCount.incrementAndGet())
            .nodename(UUID.randomUUID().toString())
            .risktype(intCount.incrementAndGet())
            .decumentid(longCount.incrementAndGet())
            .version(intCount.incrementAndGet())
            .systemlevel(intCount.incrementAndGet())
            .limitationtime(UUID.randomUUID().toString())
            .closetype(intCount.incrementAndGet());
    }
}
