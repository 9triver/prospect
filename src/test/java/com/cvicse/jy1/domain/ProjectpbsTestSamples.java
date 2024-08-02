package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ProjectpbsTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Projectpbs getProjectpbsSample1() {
        return new Projectpbs()
            .id("id1")
            .pbsname("pbsname1")
            .parentpbsid("parentpbsid1")
            .productlevel(1)
            .ifkey(1)
            .ifimporttant(1)
            .description("description1")
            .progress(1)
            .type(1)
            .priorty(1);
    }

    public static Projectpbs getProjectpbsSample2() {
        return new Projectpbs()
            .id("id2")
            .pbsname("pbsname2")
            .parentpbsid("parentpbsid2")
            .productlevel(2)
            .ifkey(2)
            .ifimporttant(2)
            .description("description2")
            .progress(2)
            .type(2)
            .priorty(2);
    }

    public static Projectpbs getProjectpbsRandomSampleGenerator() {
        return new Projectpbs()
            .id(UUID.randomUUID().toString())
            .pbsname(UUID.randomUUID().toString())
            .parentpbsid(UUID.randomUUID().toString())
            .productlevel(intCount.incrementAndGet())
            .ifkey(intCount.incrementAndGet())
            .ifimporttant(intCount.incrementAndGet())
            .description(UUID.randomUUID().toString())
            .progress(intCount.incrementAndGet())
            .type(intCount.incrementAndGet())
            .priorty(intCount.incrementAndGet());
    }
}
