package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UnQualityAuditTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static UnQualityAudit getUnQualityAuditSample1() {
        return new UnQualityAudit()
            .id(1L)
            .unqualityid(1L)
            .unqualityname("unqualityname1")
            .unqualitytype(1)
            .belongunitid(1L)
            .belongunitname("belongunitname1")
            .auditteam("auditteam1")
            .auditperson("auditperson1")
            .creatorname("creatorname1");
    }

    public static UnQualityAudit getUnQualityAuditSample2() {
        return new UnQualityAudit()
            .id(2L)
            .unqualityid(2L)
            .unqualityname("unqualityname2")
            .unqualitytype(2)
            .belongunitid(2L)
            .belongunitname("belongunitname2")
            .auditteam("auditteam2")
            .auditperson("auditperson2")
            .creatorname("creatorname2");
    }

    public static UnQualityAudit getUnQualityAuditRandomSampleGenerator() {
        return new UnQualityAudit()
            .id(longCount.incrementAndGet())
            .unqualityid(longCount.incrementAndGet())
            .unqualityname(UUID.randomUUID().toString())
            .unqualitytype(intCount.incrementAndGet())
            .belongunitid(longCount.incrementAndGet())
            .belongunitname(UUID.randomUUID().toString())
            .auditteam(UUID.randomUUID().toString())
            .auditperson(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString());
    }
}
