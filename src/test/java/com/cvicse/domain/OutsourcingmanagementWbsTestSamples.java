package com.cvicse.domain;

import java.util.UUID;

public class OutsourcingmanagementWbsTestSamples {

    public static OutsourcingmanagementWbs getOutsourcingmanagementWbsSample1() {
        return new OutsourcingmanagementWbs()
            .id("id1")
            .wbsspare1("wbsspare11")
            .wbsspare2("wbsspare21")
            .wbsspare3("wbsspare31")
            .wbsspare4("wbsspare41")
            .wbsspare5("wbsspare51");
    }

    public static OutsourcingmanagementWbs getOutsourcingmanagementWbsSample2() {
        return new OutsourcingmanagementWbs()
            .id("id2")
            .wbsspare1("wbsspare12")
            .wbsspare2("wbsspare22")
            .wbsspare3("wbsspare32")
            .wbsspare4("wbsspare42")
            .wbsspare5("wbsspare52");
    }

    public static OutsourcingmanagementWbs getOutsourcingmanagementWbsRandomSampleGenerator() {
        return new OutsourcingmanagementWbs()
            .id(UUID.randomUUID().toString())
            .wbsspare1(UUID.randomUUID().toString())
            .wbsspare2(UUID.randomUUID().toString())
            .wbsspare3(UUID.randomUUID().toString())
            .wbsspare4(UUID.randomUUID().toString())
            .wbsspare5(UUID.randomUUID().toString());
    }
}
