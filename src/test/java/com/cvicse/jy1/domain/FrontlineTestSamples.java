package com.cvicse.jy1.domain;

import java.util.UUID;

public class FrontlineTestSamples {

    public static Frontline getFrontlineSample1() {
        return new Frontline().id("id1").name("name1").description("description1");
    }

    public static Frontline getFrontlineSample2() {
        return new Frontline().id("id2").name("name2").description("description2");
    }

    public static Frontline getFrontlineRandomSampleGenerator() {
        return new Frontline()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
