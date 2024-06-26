package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RiskidentificationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Riskidentification getRiskidentificationSample1() {
        return new Riskidentification()
            .id("id1")
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

    public static Riskidentification getRiskidentificationSample2() {
        return new Riskidentification()
            .id("id2")
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

    public static Riskidentification getRiskidentificationRandomSampleGenerator() {
        return new Riskidentification()
            .id(UUID.randomUUID().toString())
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
