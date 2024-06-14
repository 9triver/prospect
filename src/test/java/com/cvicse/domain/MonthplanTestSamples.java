package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MonthplanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Monthplan getMonthplanSample1() {
        return new Monthplan().id(1L).monthplanid(1L).monthplanname("monthplanname1").creatorname("creatorname1");
    }

    public static Monthplan getMonthplanSample2() {
        return new Monthplan().id(2L).monthplanid(2L).monthplanname("monthplanname2").creatorname("creatorname2");
    }

    public static Monthplan getMonthplanRandomSampleGenerator() {
        return new Monthplan()
            .id(longCount.incrementAndGet())
            .monthplanid(longCount.incrementAndGet())
            .monthplanname(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString());
    }
}
