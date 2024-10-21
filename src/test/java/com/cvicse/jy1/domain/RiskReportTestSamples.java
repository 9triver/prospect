package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RiskReportTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static RiskReport getRiskReportSample1() {
        return new RiskReport().id(1).type("type1").year(1).riskreportname("riskreportname1");
    }

    public static RiskReport getRiskReportSample2() {
        return new RiskReport().id(2).type("type2").year(2).riskreportname("riskreportname2");
    }

    public static RiskReport getRiskReportRandomSampleGenerator() {
        return new RiskReport()
            .id(intCount.incrementAndGet())
            .type(UUID.randomUUID().toString())
            .year(intCount.incrementAndGet())
            .riskreportname(UUID.randomUUID().toString());
    }
}
