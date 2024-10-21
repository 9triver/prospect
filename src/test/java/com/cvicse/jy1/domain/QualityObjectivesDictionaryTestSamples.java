package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class QualityObjectivesDictionaryTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static QualityObjectivesDictionary getQualityObjectivesDictionarySample1() {
        return new QualityObjectivesDictionary()
            .id(1)
            .objectiveslevel("objectiveslevel1")
            .objectivestype("objectivestype1")
            .objectivesname("objectivesname1")
            .objectivescontent("objectivescontent1")
            .calculationmethod("calculationmethod1")
            .frequency("frequency1")
            .evaluationcriteria("evaluationcriteria1");
    }

    public static QualityObjectivesDictionary getQualityObjectivesDictionarySample2() {
        return new QualityObjectivesDictionary()
            .id(2)
            .objectiveslevel("objectiveslevel2")
            .objectivestype("objectivestype2")
            .objectivesname("objectivesname2")
            .objectivescontent("objectivescontent2")
            .calculationmethod("calculationmethod2")
            .frequency("frequency2")
            .evaluationcriteria("evaluationcriteria2");
    }

    public static QualityObjectivesDictionary getQualityObjectivesDictionaryRandomSampleGenerator() {
        return new QualityObjectivesDictionary()
            .id(intCount.incrementAndGet())
            .objectiveslevel(UUID.randomUUID().toString())
            .objectivestype(UUID.randomUUID().toString())
            .objectivesname(UUID.randomUUID().toString())
            .objectivescontent(UUID.randomUUID().toString())
            .calculationmethod(UUID.randomUUID().toString())
            .frequency(UUID.randomUUID().toString())
            .evaluationcriteria(UUID.randomUUID().toString());
    }
}
