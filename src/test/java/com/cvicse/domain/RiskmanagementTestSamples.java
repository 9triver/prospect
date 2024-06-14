package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RiskmanagementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Riskmanagement getRiskmanagementSample1() {
        return new Riskmanagement()
            .id(1L)
            .riskid(1L)
            .projectname("projectname1")
            .year(1L)
            .nodename("nodename1")
            .risktype(1)
            .decumentid(1L)
            .version(1)
            .systemlevel(1)
            .limitationtime("limitationtime1")
            .closetype(1);
    }

    public static Riskmanagement getRiskmanagementSample2() {
        return new Riskmanagement()
            .id(2L)
            .riskid(2L)
            .projectname("projectname2")
            .year(2L)
            .nodename("nodename2")
            .risktype(2)
            .decumentid(2L)
            .version(2)
            .systemlevel(2)
            .limitationtime("limitationtime2")
            .closetype(2);
    }

    public static Riskmanagement getRiskmanagementRandomSampleGenerator() {
        return new Riskmanagement()
            .id(longCount.incrementAndGet())
            .riskid(longCount.incrementAndGet())
            .projectname(UUID.randomUUID().toString())
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
