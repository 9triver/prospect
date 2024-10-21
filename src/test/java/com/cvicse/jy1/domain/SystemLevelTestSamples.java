package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class SystemLevelTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static SystemLevel getSystemLevelSample1() {
        return new SystemLevel().id(1).name("name1");
    }

    public static SystemLevel getSystemLevelSample2() {
        return new SystemLevel().id(2).name("name2");
    }

    public static SystemLevel getSystemLevelRandomSampleGenerator() {
        return new SystemLevel().id(intCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
