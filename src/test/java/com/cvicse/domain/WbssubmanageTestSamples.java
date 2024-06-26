package com.cvicse.domain;

import java.util.UUID;

public class WbssubmanageTestSamples {

    public static Wbssubmanage getWbssubmanageSample1() {
        return new Wbssubmanage()
            .id("id1")
            .pbssubname("pbssubname1")
            .responsiblename("responsiblename1")
            .responsibledepartment("responsibledepartment1")
            .relevantdepartment("relevantdepartment1")
            .type("type1");
    }

    public static Wbssubmanage getWbssubmanageSample2() {
        return new Wbssubmanage()
            .id("id2")
            .pbssubname("pbssubname2")
            .responsiblename("responsiblename2")
            .responsibledepartment("responsibledepartment2")
            .relevantdepartment("relevantdepartment2")
            .type("type2");
    }

    public static Wbssubmanage getWbssubmanageRandomSampleGenerator() {
        return new Wbssubmanage()
            .id(UUID.randomUUID().toString())
            .pbssubname(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString())
            .responsibledepartment(UUID.randomUUID().toString())
            .relevantdepartment(UUID.randomUUID().toString())
            .type(UUID.randomUUID().toString());
    }
}
