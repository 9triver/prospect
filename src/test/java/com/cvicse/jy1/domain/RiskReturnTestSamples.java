package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RiskReturnTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static RiskReturn getRiskReturnSample1() {
        return new RiskReturn().id(1).belongriskid(1).status("status1").closestatus("closestatus1").evidencefile("evidencefile1");
    }

    public static RiskReturn getRiskReturnSample2() {
        return new RiskReturn().id(2).belongriskid(2).status("status2").closestatus("closestatus2").evidencefile("evidencefile2");
    }

    public static RiskReturn getRiskReturnRandomSampleGenerator() {
        return new RiskReturn()
            .id(intCount.incrementAndGet())
            .belongriskid(intCount.incrementAndGet())
            .status(UUID.randomUUID().toString())
            .closestatus(UUID.randomUUID().toString())
            .evidencefile(UUID.randomUUID().toString());
    }
}
