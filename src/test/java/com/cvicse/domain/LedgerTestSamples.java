package com.cvicse.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class LedgerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Ledger getLedgerSample1() {
        return new Ledger().id(1L);
    }

    public static Ledger getLedgerSample2() {
        return new Ledger().id(2L);
    }

    public static Ledger getLedgerRandomSampleGenerator() {
        return new Ledger().id(longCount.incrementAndGet());
    }
}
