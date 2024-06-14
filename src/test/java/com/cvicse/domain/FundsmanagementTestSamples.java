package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FundsmanagementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Fundsmanagement getFundsmanagementSample1() {
        return new Fundsmanagement()
            .id(1L)
            .fundsid(1L)
            .creatorname("creatorname1")
            .year(1L)
            .dapartmentid("dapartmentid1")
            .draftapproval(1L)
            .documentid(1L)
            .maintainerid(1L);
    }

    public static Fundsmanagement getFundsmanagementSample2() {
        return new Fundsmanagement()
            .id(2L)
            .fundsid(2L)
            .creatorname("creatorname2")
            .year(2L)
            .dapartmentid("dapartmentid2")
            .draftapproval(2L)
            .documentid(2L)
            .maintainerid(2L);
    }

    public static Fundsmanagement getFundsmanagementRandomSampleGenerator() {
        return new Fundsmanagement()
            .id(longCount.incrementAndGet())
            .fundsid(longCount.incrementAndGet())
            .creatorname(UUID.randomUUID().toString())
            .year(longCount.incrementAndGet())
            .dapartmentid(UUID.randomUUID().toString())
            .draftapproval(longCount.incrementAndGet())
            .documentid(longCount.incrementAndGet())
            .maintainerid(longCount.incrementAndGet());
    }
}
