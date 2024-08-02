package com.cvicse.jy1.domain;

import java.util.UUID;

public class TechnicalTestSamples {

    public static Technical getTechnicalSample1() {
        return new Technical().id("id1").name("name1").description("description1");
    }

    public static Technical getTechnicalSample2() {
        return new Technical().id("id2").name("name2").description("description2");
    }

    public static Technical getTechnicalRandomSampleGenerator() {
        return new Technical()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
