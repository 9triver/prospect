package com.cvicse.jy1.domain;

import java.util.UUID;

public class RiskReportTestSamples {

    public static RiskReport getRiskReportSample1() {
        return new RiskReport().id("id1").type("type1").riskreportname("riskreportname1");
    }

    public static RiskReport getRiskReportSample2() {
        return new RiskReport().id("id2").type("type2").riskreportname("riskreportname2");
    }

    public static RiskReport getRiskReportRandomSampleGenerator() {
        return new RiskReport()
            .id(UUID.randomUUID().toString())
            .type(UUID.randomUUID().toString())
            .riskreportname(UUID.randomUUID().toString());
    }
}
