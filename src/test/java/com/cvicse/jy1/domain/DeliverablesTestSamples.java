package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DeliverablesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Deliverables getDeliverablesSample1() {
        return new Deliverables()
            .id(1L)
            .code("code1")
            .name("name1")
            .parentcode("parentcode1")
            .level("level1")
            .status("status1")
            .description("description1");
    }

    public static Deliverables getDeliverablesSample2() {
        return new Deliverables()
            .id(2L)
            .code("code2")
            .name("name2")
            .parentcode("parentcode2")
            .level("level2")
            .status("status2")
            .description("description2");
    }

    public static Deliverables getDeliverablesRandomSampleGenerator() {
        return new Deliverables()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .parentcode(UUID.randomUUID().toString())
            .level(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
