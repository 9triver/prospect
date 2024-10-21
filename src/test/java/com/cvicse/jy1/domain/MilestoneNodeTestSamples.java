package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class MilestoneNodeTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static MilestoneNode getMilestoneNodeSample1() {
        return new MilestoneNode().id(1).name("name1");
    }

    public static MilestoneNode getMilestoneNodeSample2() {
        return new MilestoneNode().id(2).name("name2");
    }

    public static MilestoneNode getMilestoneNodeRandomSampleGenerator() {
        return new MilestoneNode().id(intCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
