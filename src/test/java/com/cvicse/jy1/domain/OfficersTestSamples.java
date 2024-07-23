package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OfficersTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Officers getOfficersSample1() {
        return new Officers().id("id1").officersname("officersname1").password("password1").email("email1").phone(1L);
    }

    public static Officers getOfficersSample2() {
        return new Officers().id("id2").officersname("officersname2").password("password2").email("email2").phone(2L);
    }

    public static Officers getOfficersRandomSampleGenerator() {
        return new Officers()
            .id(UUID.randomUUID().toString())
            .officersname(UUID.randomUUID().toString())
            .password(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .phone(longCount.incrementAndGet());
    }
}
