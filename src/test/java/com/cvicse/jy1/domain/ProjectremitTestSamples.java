package com.cvicse.jy1.domain;

import java.util.UUID;

public class ProjectremitTestSamples {

    public static Projectremit getProjectremitSample1() {
        return new Projectremit()
            .id("id1")
            .remit("remit1")
            .outdeportment("outdeportment1")
            .indeportment("indeportment1")
            .projectname("projectname1")
            .deportment("deportment1")
            .projectleader("projectleader1");
    }

    public static Projectremit getProjectremitSample2() {
        return new Projectremit()
            .id("id2")
            .remit("remit2")
            .outdeportment("outdeportment2")
            .indeportment("indeportment2")
            .projectname("projectname2")
            .deportment("deportment2")
            .projectleader("projectleader2");
    }

    public static Projectremit getProjectremitRandomSampleGenerator() {
        return new Projectremit()
            .id(UUID.randomUUID().toString())
            .remit(UUID.randomUUID().toString())
            .outdeportment(UUID.randomUUID().toString())
            .indeportment(UUID.randomUUID().toString())
            .projectname(UUID.randomUUID().toString())
            .deportment(UUID.randomUUID().toString())
            .projectleader(UUID.randomUUID().toString());
    }
}
