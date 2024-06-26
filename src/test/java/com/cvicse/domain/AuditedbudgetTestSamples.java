package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AuditedbudgetTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Auditedbudget getAuditedbudgetSample1() {
        return new Auditedbudget()
            .id("id1")
            .creatorname("creatorname1")
            .year(1L)
            .dapartmentid("dapartmentid1")
            .draftapproval(1L)
            .documentid("documentid1")
            .maintainerid("maintainerid1");
    }

    public static Auditedbudget getAuditedbudgetSample2() {
        return new Auditedbudget()
            .id("id2")
            .creatorname("creatorname2")
            .year(2L)
            .dapartmentid("dapartmentid2")
            .draftapproval(2L)
            .documentid("documentid2")
            .maintainerid("maintainerid2");
    }

    public static Auditedbudget getAuditedbudgetRandomSampleGenerator() {
        return new Auditedbudget()
            .id(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString())
            .year(longCount.incrementAndGet())
            .dapartmentid(UUID.randomUUID().toString())
            .draftapproval(longCount.incrementAndGet())
            .documentid(UUID.randomUUID().toString())
            .maintainerid(UUID.randomUUID().toString());
    }
}
