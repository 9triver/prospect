package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OfficersTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Officers getOfficersSample1() {
        return new Officers().id(1L).years(1);
    }

    public static Officers getOfficersSample2() {
        return new Officers().id(2L).years(2);
    }

    public static Officers getOfficersRandomSampleGenerator() {
        return new Officers().id(longCount.incrementAndGet()).years(intCount.incrementAndGet());
    }
}
