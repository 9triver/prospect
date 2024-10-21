package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerSatisfactionTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CustomerSatisfaction getCustomerSatisfactionSample1() {
        return new CustomerSatisfaction()
            .id(1)
            .year(1)
            .satisfactionitem("satisfactionitem1")
            .score(1)
            .opinion("opinion1")
            .totalscore(1)
            .customer("customer1")
            .plonenumber("plonenumber1")
            .filename("filename1");
    }

    public static CustomerSatisfaction getCustomerSatisfactionSample2() {
        return new CustomerSatisfaction()
            .id(2)
            .year(2)
            .satisfactionitem("satisfactionitem2")
            .score(2)
            .opinion("opinion2")
            .totalscore(2)
            .customer("customer2")
            .plonenumber("plonenumber2")
            .filename("filename2");
    }

    public static CustomerSatisfaction getCustomerSatisfactionRandomSampleGenerator() {
        return new CustomerSatisfaction()
            .id(intCount.incrementAndGet())
            .year(intCount.incrementAndGet())
            .satisfactionitem(UUID.randomUUID().toString())
            .score(intCount.incrementAndGet())
            .opinion(UUID.randomUUID().toString())
            .totalscore(intCount.incrementAndGet())
            .customer(UUID.randomUUID().toString())
            .plonenumber(UUID.randomUUID().toString())
            .filename(UUID.randomUUID().toString());
    }
}
