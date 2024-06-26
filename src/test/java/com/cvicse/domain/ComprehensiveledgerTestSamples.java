package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ComprehensiveledgerTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Comprehensiveledger getComprehensiveledgerSample1() {
        return new Comprehensiveledger()
            .id("id1")
            .fundsname("fundsname1")
            .wbsname("wbsname1")
            .unitname("unitname1")
            .budgetsection("budgetsection1")
            .purpose("purpose1")
            .unit("unit1")
            .number(1);
    }

    public static Comprehensiveledger getComprehensiveledgerSample2() {
        return new Comprehensiveledger()
            .id("id2")
            .fundsname("fundsname2")
            .wbsname("wbsname2")
            .unitname("unitname2")
            .budgetsection("budgetsection2")
            .purpose("purpose2")
            .unit("unit2")
            .number(2);
    }

    public static Comprehensiveledger getComprehensiveledgerRandomSampleGenerator() {
        return new Comprehensiveledger()
            .id(UUID.randomUUID().toString())
            .fundsname(UUID.randomUUID().toString())
            .wbsname(UUID.randomUUID().toString())
            .unitname(UUID.randomUUID().toString())
            .budgetsection(UUID.randomUUID().toString())
            .purpose(UUID.randomUUID().toString())
            .unit(UUID.randomUUID().toString())
            .number(intCount.incrementAndGet());
    }
}
