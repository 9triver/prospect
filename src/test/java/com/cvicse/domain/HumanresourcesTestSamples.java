package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class HumanresourcesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Humanresources getHumanresourcesSample1() {
        return new Humanresources()
            .id(1L)
            .humanresourcesid(1L)
            .name("name1")
            .outdeportment("outdeportment1")
            .indeportment("indeportment1")
            .projectname("projectname1")
            .deportment("deportment1")
            .projectleader("projectleader1");
    }

    public static Humanresources getHumanresourcesSample2() {
        return new Humanresources()
            .id(2L)
            .humanresourcesid(2L)
            .name("name2")
            .outdeportment("outdeportment2")
            .indeportment("indeportment2")
            .projectname("projectname2")
            .deportment("deportment2")
            .projectleader("projectleader2");
    }

    public static Humanresources getHumanresourcesRandomSampleGenerator() {
        return new Humanresources()
            .id(longCount.incrementAndGet())
            .humanresourcesid(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .outdeportment(UUID.randomUUID().toString())
            .indeportment(UUID.randomUUID().toString())
            .projectname(UUID.randomUUID().toString())
            .deportment(UUID.randomUUID().toString())
            .projectleader(UUID.randomUUID().toString());
    }
}
