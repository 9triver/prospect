package com.cvicse.domain;

import java.util.UUID;

public class ProgressplanTestSamples {

    public static Progressplan getProgressplanSample1() {
        return new Progressplan()
            .id("id1")
            .progressname("progressname1")
            .workfocus("workfocus1")
            .creatorname("creatorname1")
            .responsiblename("responsiblename1");
    }

    public static Progressplan getProgressplanSample2() {
        return new Progressplan()
            .id("id2")
            .progressname("progressname2")
            .workfocus("workfocus2")
            .creatorname("creatorname2")
            .responsiblename("responsiblename2");
    }

    public static Progressplan getProgressplanRandomSampleGenerator() {
        return new Progressplan()
            .id(UUID.randomUUID().toString())
            .progressname(UUID.randomUUID().toString())
            .workfocus(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString());
    }
}
