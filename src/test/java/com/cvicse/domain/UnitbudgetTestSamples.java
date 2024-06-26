package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class UnitbudgetTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Unitbudget getUnitbudgetSample1() {
        return new Unitbudget().id("id1").subprojectname("subprojectname1").unitbudgername("unitbudgername1").billingunit(1);
    }

    public static Unitbudget getUnitbudgetSample2() {
        return new Unitbudget().id("id2").subprojectname("subprojectname2").unitbudgername("unitbudgername2").billingunit(2);
    }

    public static Unitbudget getUnitbudgetRandomSampleGenerator() {
        return new Unitbudget()
            .id(UUID.randomUUID().toString())
            .subprojectname(UUID.randomUUID().toString())
            .unitbudgername(UUID.randomUUID().toString())
            .billingunit(intCount.incrementAndGet());
    }
}
