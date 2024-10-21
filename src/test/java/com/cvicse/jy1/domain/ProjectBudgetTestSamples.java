package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ProjectBudgetTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ProjectBudget getProjectBudgetSample1() {
        return new ProjectBudget()
            .id(1L)
            .wbsid("wbsid1")
            .wbsname("wbsname1")
            .parentwbsid("parentwbsid1")
            .subjectid(1)
            .subjectname("subjectname1")
            .contractid("contractid1")
            .contractname("contractname1")
            .year(1)
            .auxiliaryitem("auxiliaryitem1")
            .unit("unit1")
            .number("number1")
            .remark("remark1");
    }

    public static ProjectBudget getProjectBudgetSample2() {
        return new ProjectBudget()
            .id(2L)
            .wbsid("wbsid2")
            .wbsname("wbsname2")
            .parentwbsid("parentwbsid2")
            .subjectid(2)
            .subjectname("subjectname2")
            .contractid("contractid2")
            .contractname("contractname2")
            .year(2)
            .auxiliaryitem("auxiliaryitem2")
            .unit("unit2")
            .number("number2")
            .remark("remark2");
    }

    public static ProjectBudget getProjectBudgetRandomSampleGenerator() {
        return new ProjectBudget()
            .id(longCount.incrementAndGet())
            .wbsid(UUID.randomUUID().toString())
            .wbsname(UUID.randomUUID().toString())
            .parentwbsid(UUID.randomUUID().toString())
            .subjectid(intCount.incrementAndGet())
            .subjectname(UUID.randomUUID().toString())
            .contractid(UUID.randomUUID().toString())
            .contractname(UUID.randomUUID().toString())
            .year(intCount.incrementAndGet())
            .auxiliaryitem(UUID.randomUUID().toString())
            .unit(UUID.randomUUID().toString())
            .number(UUID.randomUUID().toString())
            .remark(UUID.randomUUID().toString());
    }
}
