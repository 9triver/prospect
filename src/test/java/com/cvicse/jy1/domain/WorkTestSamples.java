package com.cvicse.jy1.domain;

import java.util.UUID;

public class WorkTestSamples {

    public static Work getWorkSample1() {
        return new Work().id("id1").name("name1").description("description1").workbagid("workbagid1");
    }

    public static Work getWorkSample2() {
        return new Work().id("id2").name("name2").description("description2").workbagid("workbagid2");
    }

    public static Work getWorkRandomSampleGenerator() {
        return new Work()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .workbagid(UUID.randomUUID().toString());
    }
}
