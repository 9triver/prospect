package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CommunicationPlanTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CommunicationPlan getCommunicationPlanSample1() {
        return new CommunicationPlan()
            .id(1)
            .wbsid("wbsid1")
            .communicationtopic("communicationtopic1")
            .worktarget("worktarget1")
            .workcontent("workcontent1")
            .remarks("remarks1");
    }

    public static CommunicationPlan getCommunicationPlanSample2() {
        return new CommunicationPlan()
            .id(2)
            .wbsid("wbsid2")
            .communicationtopic("communicationtopic2")
            .worktarget("worktarget2")
            .workcontent("workcontent2")
            .remarks("remarks2");
    }

    public static CommunicationPlan getCommunicationPlanRandomSampleGenerator() {
        return new CommunicationPlan()
            .id(intCount.incrementAndGet())
            .wbsid(UUID.randomUUID().toString())
            .communicationtopic(UUID.randomUUID().toString())
            .worktarget(UUID.randomUUID().toString())
            .workcontent(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString());
    }
}
