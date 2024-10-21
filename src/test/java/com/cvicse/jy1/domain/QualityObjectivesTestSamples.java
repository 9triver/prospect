package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class QualityObjectivesTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static QualityObjectives getQualityObjectivesSample1() {
        return new QualityObjectives()
            .id(1)
            .name("name1")
            .objectiveslevel("objectiveslevel1")
            .objectives("objectives1")
            .objectivesvalue("objectivesvalue1")
            .calculationmethod("calculationmethod1")
            .frequency("frequency1")
            .takeaction("takeaction1")
            .needresource("needresource1")
            .status("status1");
    }

    public static QualityObjectives getQualityObjectivesSample2() {
        return new QualityObjectives()
            .id(2)
            .name("name2")
            .objectiveslevel("objectiveslevel2")
            .objectives("objectives2")
            .objectivesvalue("objectivesvalue2")
            .calculationmethod("calculationmethod2")
            .frequency("frequency2")
            .takeaction("takeaction2")
            .needresource("needresource2")
            .status("status2");
    }

    public static QualityObjectives getQualityObjectivesRandomSampleGenerator() {
        return new QualityObjectives()
            .id(intCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .objectiveslevel(UUID.randomUUID().toString())
            .objectives(UUID.randomUUID().toString())
            .objectivesvalue(UUID.randomUUID().toString())
            .calculationmethod(UUID.randomUUID().toString())
            .frequency(UUID.randomUUID().toString())
            .takeaction(UUID.randomUUID().toString())
            .needresource(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString());
    }
}
