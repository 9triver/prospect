package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PbsmanageTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Pbsmanage getPbsmanageSample1() {
        return new Pbsmanage()
            .id(1L)
            .pbsid("pbsid1")
            .pbsname("pbsname1")
            .number(1)
            .type("type1")
            .administratorid("administratorid1")
            .administratorname("administratorname1")
            .responsiblename("responsiblename1")
            .department("department1")
            .auditUserid("auditUserid1");
    }

    public static Pbsmanage getPbsmanageSample2() {
        return new Pbsmanage()
            .id(2L)
            .pbsid("pbsid2")
            .pbsname("pbsname2")
            .number(2)
            .type("type2")
            .administratorid("administratorid2")
            .administratorname("administratorname2")
            .responsiblename("responsiblename2")
            .department("department2")
            .auditUserid("auditUserid2");
    }

    public static Pbsmanage getPbsmanageRandomSampleGenerator() {
        return new Pbsmanage()
            .id(longCount.incrementAndGet())
            .pbsid(UUID.randomUUID().toString())
            .pbsname(UUID.randomUUID().toString())
            .number(intCount.incrementAndGet())
            .type(UUID.randomUUID().toString())
            .administratorid(UUID.randomUUID().toString())
            .administratorname(UUID.randomUUID().toString())
            .responsiblename(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .auditUserid(UUID.randomUUID().toString());
    }
}
