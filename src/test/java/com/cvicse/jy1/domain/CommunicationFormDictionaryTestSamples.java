package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CommunicationFormDictionaryTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CommunicationFormDictionary getCommunicationFormDictionarySample1() {
        return new CommunicationFormDictionary()
            .id(1)
            .communicationformname("communicationformname1")
            .communicationformtype("communicationformtype1")
            .status("status1");
    }

    public static CommunicationFormDictionary getCommunicationFormDictionarySample2() {
        return new CommunicationFormDictionary()
            .id(2)
            .communicationformname("communicationformname2")
            .communicationformtype("communicationformtype2")
            .status("status2");
    }

    public static CommunicationFormDictionary getCommunicationFormDictionaryRandomSampleGenerator() {
        return new CommunicationFormDictionary()
            .id(intCount.incrementAndGet())
            .communicationformname(UUID.randomUUID().toString())
            .communicationformtype(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString());
    }
}
