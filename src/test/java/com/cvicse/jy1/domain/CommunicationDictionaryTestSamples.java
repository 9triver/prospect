package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CommunicationDictionaryTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CommunicationDictionary getCommunicationDictionarySample1() {
        return new CommunicationDictionary().id(1).partiesname("partiesname1").partiestype("partiestype1").partiesduty("partiesduty1");
    }

    public static CommunicationDictionary getCommunicationDictionarySample2() {
        return new CommunicationDictionary().id(2).partiesname("partiesname2").partiestype("partiestype2").partiesduty("partiesduty2");
    }

    public static CommunicationDictionary getCommunicationDictionaryRandomSampleGenerator() {
        return new CommunicationDictionary()
            .id(intCount.incrementAndGet())
            .partiesname(UUID.randomUUID().toString())
            .partiestype(UUID.randomUUID().toString())
            .partiesduty(UUID.randomUUID().toString());
    }
}
