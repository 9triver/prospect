package com.cvicse.domain;

import java.util.UUID;

public class ProgressmanagementWbsTestSamples {

    public static ProgressmanagementWbs getProgressmanagementWbsSample1() {
        return new ProgressmanagementWbs()
            .id("id1")
            .wbsspare1("wbsspare11")
            .wbsspare2("wbsspare21")
            .wbsspare3("wbsspare31")
            .wbsspare4("wbsspare41")
            .wbsspare5("wbsspare51");
    }

    public static ProgressmanagementWbs getProgressmanagementWbsSample2() {
        return new ProgressmanagementWbs()
            .id("id2")
            .wbsspare1("wbsspare12")
            .wbsspare2("wbsspare22")
            .wbsspare3("wbsspare32")
            .wbsspare4("wbsspare42")
            .wbsspare5("wbsspare52");
    }

    public static ProgressmanagementWbs getProgressmanagementWbsRandomSampleGenerator() {
        return new ProgressmanagementWbs()
            .id(UUID.randomUUID().toString())
            .wbsspare1(UUID.randomUUID().toString())
            .wbsspare2(UUID.randomUUID().toString())
            .wbsspare3(UUID.randomUUID().toString())
            .wbsspare4(UUID.randomUUID().toString())
            .wbsspare5(UUID.randomUUID().toString());
    }
}
