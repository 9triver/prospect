package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class LetterTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Letter getLetterSample1() {
        return new Letter()
            .id(1)
            .lettername("lettername1")
            .letternumber("letternumber1")
            .lettertype("lettertype1")
            .lettercontent("lettercontent1")
            .letterstatus("letterstatus1")
            .previousfile("previousfile1")
            .datarecordstatus("datarecordstatus1");
    }

    public static Letter getLetterSample2() {
        return new Letter()
            .id(2)
            .lettername("lettername2")
            .letternumber("letternumber2")
            .lettertype("lettertype2")
            .lettercontent("lettercontent2")
            .letterstatus("letterstatus2")
            .previousfile("previousfile2")
            .datarecordstatus("datarecordstatus2");
    }

    public static Letter getLetterRandomSampleGenerator() {
        return new Letter()
            .id(intCount.incrementAndGet())
            .lettername(UUID.randomUUID().toString())
            .letternumber(UUID.randomUUID().toString())
            .lettertype(UUID.randomUUID().toString())
            .lettercontent(UUID.randomUUID().toString())
            .letterstatus(UUID.randomUUID().toString())
            .previousfile(UUID.randomUUID().toString())
            .datarecordstatus(UUID.randomUUID().toString());
    }
}
