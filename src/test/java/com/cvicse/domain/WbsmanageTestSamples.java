package com.cvicse.domain;

import java.util.UUID;

public class WbsmanageTestSamples {

    public static Wbsmanage getWbsmanageSample1() {
        return new Wbsmanage()
            .id("id1")
            .wbsname("wbsname1")
            .description("description1")
            .result("result1")
            .administratorname("administratorname1")
            .responsiblename("responsiblename1")
            .department("department1");
    }

    public static Wbsmanage getWbsmanageSample2() {
        return new Wbsmanage()
            .id("id2")
            .wbsname("wbsname2")
            .description("description2")
            .result("result2")
            .administratorname("administratorname2")
            .responsiblename("responsiblename2")
            .department("department2");
    }

    public static Wbsmanage getWbsmanageRandomSampleGenerator() {
        return new Wbsmanage()
            .id(UUID.randomUUID().toString())
            .wbsname(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .result(UUID.randomUUID().toString())
            .administratorname(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString());
    }
}
