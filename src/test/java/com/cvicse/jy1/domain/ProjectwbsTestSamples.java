package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ProjectwbsTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Projectwbs getProjectwbsSample1() {
        return new Projectwbs()
            .id("id1")
            .wbsname("wbsname1")
            .pbsid("pbsid1")
            .parentwbsid("parentwbsid1")
            .description("description1")
            .progress(1)
            .type(1)
            .priorty(1)
            .workbag(1);
    }

    public static Projectwbs getProjectwbsSample2() {
        return new Projectwbs()
            .id("id2")
            .wbsname("wbsname2")
            .pbsid("pbsid2")
            .parentwbsid("parentwbsid2")
            .description("description2")
            .progress(2)
            .type(2)
            .priorty(2)
            .workbag(2);
    }

    public static Projectwbs getProjectwbsRandomSampleGenerator() {
        return new Projectwbs()
            .id(UUID.randomUUID().toString())
            .wbsname(UUID.randomUUID().toString())
            .pbsid(UUID.randomUUID().toString())
            .parentwbsid(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .progress(intCount.incrementAndGet())
            .type(intCount.incrementAndGet())
            .priorty(intCount.incrementAndGet())
            .workbag(intCount.incrementAndGet());
    }
}
