package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UnitbudgetTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Unitbudget getUnitbudgetSample1() {
        return new Unitbudget().id(1L).unitbudgetid(1L).subprojectname("subprojectname1").unitbudgername("unitbudgername1").billingunit(1);
    }

    public static Unitbudget getUnitbudgetSample2() {
        return new Unitbudget().id(2L).unitbudgetid(2L).subprojectname("subprojectname2").unitbudgername("unitbudgername2").billingunit(2);
    }

    public static Unitbudget getUnitbudgetRandomSampleGenerator() {
        return new Unitbudget()
            .id(longCount.incrementAndGet())
            .unitbudgetid(longCount.incrementAndGet())
            .subprojectname(UUID.randomUUID().toString())
            .unitbudgername(UUID.randomUUID().toString())
            .billingunit(intCount.incrementAndGet());
    }
}
