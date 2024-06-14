package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RiskreportTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Riskreport getRiskreportSample1() {
        return new Riskreport().id(1L).riskid(1L).type("type1").riskreportname("riskreportname1");
    }

    public static Riskreport getRiskreportSample2() {
        return new Riskreport().id(2L).riskid(2L).type("type2").riskreportname("riskreportname2");
    }

    public static Riskreport getRiskreportRandomSampleGenerator() {
        return new Riskreport()
            .id(longCount.incrementAndGet())
            .riskid(longCount.incrementAndGet())
            .type(UUID.randomUUID().toString())
            .riskreportname(UUID.randomUUID().toString());
    }
}
