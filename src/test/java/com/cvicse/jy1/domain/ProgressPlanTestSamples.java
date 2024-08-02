package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgressPlanTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ProgressPlan getProgressPlanSample1() {
        return new ProgressPlan()
            .id("id1")
            .planname("planname1")
            .plantype(1)
            .planstage("planstage1")
            .description("description1")
            .deliverables("deliverables1")
            .planobjectives("planobjectives1")
            .preplan("preplan1")
            .progress(1)
            .iskey(1)
            .remark("remark1");
    }

    public static ProgressPlan getProgressPlanSample2() {
        return new ProgressPlan()
            .id("id2")
            .planname("planname2")
            .plantype(2)
            .planstage("planstage2")
            .description("description2")
            .deliverables("deliverables2")
            .planobjectives("planobjectives2")
            .preplan("preplan2")
            .progress(2)
            .iskey(2)
            .remark("remark2");
    }

    public static ProgressPlan getProgressPlanRandomSampleGenerator() {
        return new ProgressPlan()
            .id(UUID.randomUUID().toString())
            .planname(UUID.randomUUID().toString())
            .plantype(intCount.incrementAndGet())
            .planstage(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .deliverables(UUID.randomUUID().toString())
            .planobjectives(UUID.randomUUID().toString())
            .preplan(UUID.randomUUID().toString())
            .progress(intCount.incrementAndGet())
            .iskey(intCount.incrementAndGet())
            .remark(UUID.randomUUID().toString());
    }
}
