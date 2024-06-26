package com.cvicse.domain;

import java.util.UUID;

public class PbssubmanageTestSamples {

    public static Pbssubmanage getPbssubmanageSample1() {
        return new Pbssubmanage()
            .id("id1")
            .pbssubname("pbssubname1")
            .responsiblename("responsiblename1")
            .responsibledepartment("responsibledepartment1")
            .relevantdepartment("relevantdepartment1")
            .type("type1");
    }

    public static Pbssubmanage getPbssubmanageSample2() {
        return new Pbssubmanage()
            .id("id2")
            .pbssubname("pbssubname2")
            .responsiblename("responsiblename2")
            .responsibledepartment("responsibledepartment2")
            .relevantdepartment("relevantdepartment2")
            .type("type2");
    }

    public static Pbssubmanage getPbssubmanageRandomSampleGenerator() {
        return new Pbssubmanage()
            .id(UUID.randomUUID().toString())
            .pbssubname(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString())
            .responsibledepartment(UUID.randomUUID().toString())
            .relevantdepartment(UUID.randomUUID().toString())
            .type(UUID.randomUUID().toString());
    }
}
