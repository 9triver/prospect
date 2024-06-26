package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DocumentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Document getDocumentSample1() {
        return new Document().id("id1").documentname("documentname1").documenttype(1).documentsize(1L).creatorname("creatorname1");
    }

    public static Document getDocumentSample2() {
        return new Document().id("id2").documentname("documentname2").documenttype(2).documentsize(2L).creatorname("creatorname2");
    }

    public static Document getDocumentRandomSampleGenerator() {
        return new Document()
            .id(UUID.randomUUID().toString())
            .documentname(UUID.randomUUID().toString())
            .documenttype(intCount.incrementAndGet())
            .documentsize(longCount.incrementAndGet())
            .creatorname(UUID.randomUUID().toString());
    }
}
