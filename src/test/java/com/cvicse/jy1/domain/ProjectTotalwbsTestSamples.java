package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ProjectTotalwbsTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ProjectTotalwbs getProjectTotalwbsSample1() {
        return new ProjectTotalwbs()
            .id("id1")
            .wbsname("wbsname1")
            .parentwbsid("parentwbsid1")
            .pbsid("pbsid1")
            .description("description1")
            .belongfront("belongfront1")
            .progress(1)
            .type(1)
            .priorty(1)
            .deliverables("deliverables1")
            .workbag(1);
    }

    public static ProjectTotalwbs getProjectTotalwbsSample2() {
        return new ProjectTotalwbs()
            .id("id2")
            .wbsname("wbsname2")
            .parentwbsid("parentwbsid2")
            .pbsid("pbsid2")
            .description("description2")
            .belongfront("belongfront2")
            .progress(2)
            .type(2)
            .priorty(2)
            .deliverables("deliverables2")
            .workbag(2);
    }

    public static ProjectTotalwbs getProjectTotalwbsRandomSampleGenerator() {
        return new ProjectTotalwbs()
            .id(UUID.randomUUID().toString())
            .wbsname(UUID.randomUUID().toString())
            .parentwbsid(UUID.randomUUID().toString())
            .pbsid(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .belongfront(UUID.randomUUID().toString())
            .progress(intCount.incrementAndGet())
            .type(intCount.incrementAndGet())
            .priorty(intCount.incrementAndGet())
            .deliverables(UUID.randomUUID().toString())
            .workbag(intCount.incrementAndGet());
    }
}
