package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OfficersTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Officers getOfficersSample1() {
        return new Officers().id("id1").name("name1").password("password1").email("email1").phone(1L).years(1);
    }

    public static Officers getOfficersSample2() {
        return new Officers().id("id2").name("name2").password("password2").email("email2").phone(2L).years(2);
    }

    public static Officers getOfficersRandomSampleGenerator() {
        return new Officers()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .password(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .phone(longCount.incrementAndGet())
            .years(intCount.incrementAndGet());
    }
}
