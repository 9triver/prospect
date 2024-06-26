package com.cvicse.domain;

import java.util.UUID;

public class ProgressplanreturnsTestSamples {

    public static Progressplanreturns getProgressplanreturnsSample1() {
        return new Progressplanreturns().id("id1");
    }

    public static Progressplanreturns getProgressplanreturnsSample2() {
        return new Progressplanreturns().id("id2");
    }

    public static Progressplanreturns getProgressplanreturnsRandomSampleGenerator() {
        return new Progressplanreturns().id(UUID.randomUUID().toString());
    }
}
