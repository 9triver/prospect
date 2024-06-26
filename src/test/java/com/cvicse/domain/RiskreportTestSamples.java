package com.cvicse.domain;

import java.util.UUID;

public class RiskreportTestSamples {

    public static Riskreport getRiskreportSample1() {
        return new Riskreport().id("id1").type("type1").riskreportname("riskreportname1");
    }

    public static Riskreport getRiskreportSample2() {
        return new Riskreport().id("id2").type("type2").riskreportname("riskreportname2");
    }

    public static Riskreport getRiskreportRandomSampleGenerator() {
        return new Riskreport()
            .id(UUID.randomUUID().toString())
            .type(UUID.randomUUID().toString())
            .riskreportname(UUID.randomUUID().toString());
    }
}
