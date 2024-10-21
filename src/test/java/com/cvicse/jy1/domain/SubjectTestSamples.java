package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class SubjectTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Subject getSubjectSample1() {
        return new Subject().id(1).name("name1").type("type1").parentid("parentid1").remark("remark1");
    }

    public static Subject getSubjectSample2() {
        return new Subject().id(2).name("name2").type("type2").parentid("parentid2").remark("remark2");
    }

    public static Subject getSubjectRandomSampleGenerator() {
        return new Subject()
            .id(intCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .type(UUID.randomUUID().toString())
            .parentid(UUID.randomUUID().toString())
            .remark(UUID.randomUUID().toString());
    }
}
