package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class QualityObjectivesTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static QualityObjectives getQualityObjectivesSample1() {
        return new QualityObjectives()
            .id("id1")
            .name("name1")
            .objectives("objectives1")
            .target(1)
            .statisticalmethod("statisticalmethod1")
            .statisticalfrequency("statisticalfrequency1")
            .istarget(1)
            .progress(1)
            .description("description1")
            .problems("problems1")
            .improvementmeasures("improvementmeasures1");
    }

    public static QualityObjectives getQualityObjectivesSample2() {
        return new QualityObjectives()
            .id("id2")
            .name("name2")
            .objectives("objectives2")
            .target(2)
            .statisticalmethod("statisticalmethod2")
            .statisticalfrequency("statisticalfrequency2")
            .istarget(2)
            .progress(2)
            .description("description2")
            .problems("problems2")
            .improvementmeasures("improvementmeasures2");
    }

    public static QualityObjectives getQualityObjectivesRandomSampleGenerator() {
        return new QualityObjectives()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .objectives(UUID.randomUUID().toString())
            .target(intCount.incrementAndGet())
            .statisticalmethod(UUID.randomUUID().toString())
            .statisticalfrequency(UUID.randomUUID().toString())
            .istarget(intCount.incrementAndGet())
            .progress(intCount.incrementAndGet())
            .description(UUID.randomUUID().toString())
            .problems(UUID.randomUUID().toString())
            .improvementmeasures(UUID.randomUUID().toString());
    }
}
