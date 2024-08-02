package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class PlanReturnsTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static PlanReturns getPlanReturnsSample1() {
        return new PlanReturns()
            .id("id1")
            .planreturnsname("planreturnsname1")
            .plantype(1)
            .description("description1")
            .deliverables("deliverables1")
            .progress(1)
            .impactanalysis("impactanalysis1")
            .rejectionreason("rejectionreason1");
    }

    public static PlanReturns getPlanReturnsSample2() {
        return new PlanReturns()
            .id("id2")
            .planreturnsname("planreturnsname2")
            .plantype(2)
            .description("description2")
            .deliverables("deliverables2")
            .progress(2)
            .impactanalysis("impactanalysis2")
            .rejectionreason("rejectionreason2");
    }

    public static PlanReturns getPlanReturnsRandomSampleGenerator() {
        return new PlanReturns()
            .id(UUID.randomUUID().toString())
            .planreturnsname(UUID.randomUUID().toString())
            .plantype(intCount.incrementAndGet())
            .description(UUID.randomUUID().toString())
            .deliverables(UUID.randomUUID().toString())
            .progress(intCount.incrementAndGet())
            .impactanalysis(UUID.randomUUID().toString())
            .rejectionreason(UUID.randomUUID().toString());
    }
}
