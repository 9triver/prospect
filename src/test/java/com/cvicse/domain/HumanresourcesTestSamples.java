package com.cvicse.domain;

import java.util.UUID;

public class HumanresourcesTestSamples {

    public static Humanresources getHumanresourcesSample1() {
        return new Humanresources()
            .id("id1")
            .name("name1")
            .outdeportment("outdeportment1")
            .indeportment("indeportment1")
            .projectname("projectname1")
            .deportment("deportment1")
            .projectleader("projectleader1");
    }

    public static Humanresources getHumanresourcesSample2() {
        return new Humanresources()
            .id("id2")
            .name("name2")
            .outdeportment("outdeportment2")
            .indeportment("indeportment2")
            .projectname("projectname2")
            .deportment("deportment2")
            .projectleader("projectleader2");
    }

    public static Humanresources getHumanresourcesRandomSampleGenerator() {
        return new Humanresources()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .outdeportment(UUID.randomUUID().toString())
            .indeportment(UUID.randomUUID().toString())
            .projectname(UUID.randomUUID().toString())
            .deportment(UUID.randomUUID().toString())
            .projectleader(UUID.randomUUID().toString());
    }
}
