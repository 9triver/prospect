package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class UnQualityAuditTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static UnQualityAudit getUnQualityAuditSample1() {
        return new UnQualityAudit()
            .id("id1")
            .unqualityname("unqualityname1")
            .unqualitytype(1)
            .belongunitid("belongunitid1")
            .belongunitname("belongunitname1")
            .auditteam("auditteam1")
            .auditperson("auditperson1")
            .creatorname("creatorname1");
    }

    public static UnQualityAudit getUnQualityAuditSample2() {
        return new UnQualityAudit()
            .id("id2")
            .unqualityname("unqualityname2")
            .unqualitytype(2)
            .belongunitid("belongunitid2")
            .belongunitname("belongunitname2")
            .auditteam("auditteam2")
            .auditperson("auditperson2")
            .creatorname("creatorname2");
    }

    public static UnQualityAudit getUnQualityAuditRandomSampleGenerator() {
        return new UnQualityAudit()
            .id(UUID.randomUUID().toString())
            .unqualityname(UUID.randomUUID().toString())
            .unqualitytype(intCount.incrementAndGet())
            .belongunitid(UUID.randomUUID().toString())
            .belongunitname(UUID.randomUUID().toString())
            .auditteam(UUID.randomUUID().toString())
            .auditperson(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString());
    }
}
