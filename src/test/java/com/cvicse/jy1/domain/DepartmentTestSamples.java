package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class DepartmentTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Department getDepartmentSample1() {
        return new Department().id("id1").name("name1").officersnum(1);
    }

    public static Department getDepartmentSample2() {
        return new Department().id("id2").name("name2").officersnum(2);
    }

    public static Department getDepartmentRandomSampleGenerator() {
        return new Department().id(UUID.randomUUID().toString()).name(UUID.randomUUID().toString()).officersnum(intCount.incrementAndGet());
    }
}
