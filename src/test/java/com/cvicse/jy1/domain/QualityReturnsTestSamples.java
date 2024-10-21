package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class QualityReturnsTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static QualityReturns getQualityReturnsSample1() {
        return new QualityReturns()
            .id(1)
            .qualityplanid("qualityplanid1")
            .qualityobjectivesid("qualityobjectivesid1")
            .name("name1")
            .department("department1")
            .responsibleid("responsibleid1")
            .wbsid("wbsid1")
            .workbagid("workbagid1")
            .objectiveslevel("objectiveslevel1")
            .objectives("objectives1")
            .objectivesvalue("objectivesvalue1")
            .calculationmethod("calculationmethod1")
            .frequency("frequency1")
            .objectivescompletion("objectivescompletion1")
            .problem("problem1")
            .takeaction("takeaction1")
            .continuousimprovement("continuousimprovement1")
            .workevidence("workevidence1")
            .status("status1");
    }

    public static QualityReturns getQualityReturnsSample2() {
        return new QualityReturns()
            .id(2)
            .qualityplanid("qualityplanid2")
            .qualityobjectivesid("qualityobjectivesid2")
            .name("name2")
            .department("department2")
            .responsibleid("responsibleid2")
            .wbsid("wbsid2")
            .workbagid("workbagid2")
            .objectiveslevel("objectiveslevel2")
            .objectives("objectives2")
            .objectivesvalue("objectivesvalue2")
            .calculationmethod("calculationmethod2")
            .frequency("frequency2")
            .objectivescompletion("objectivescompletion2")
            .problem("problem2")
            .takeaction("takeaction2")
            .continuousimprovement("continuousimprovement2")
            .workevidence("workevidence2")
            .status("status2");
    }

    public static QualityReturns getQualityReturnsRandomSampleGenerator() {
        return new QualityReturns()
            .id(intCount.incrementAndGet())
            .qualityplanid(UUID.randomUUID().toString())
            .qualityobjectivesid(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .responsibleid(UUID.randomUUID().toString())
            .wbsid(UUID.randomUUID().toString())
            .workbagid(UUID.randomUUID().toString())
            .objectiveslevel(UUID.randomUUID().toString())
            .objectives(UUID.randomUUID().toString())
            .objectivesvalue(UUID.randomUUID().toString())
            .calculationmethod(UUID.randomUUID().toString())
            .frequency(UUID.randomUUID().toString())
            .objectivescompletion(UUID.randomUUID().toString())
            .problem(UUID.randomUUID().toString())
            .takeaction(UUID.randomUUID().toString())
            .continuousimprovement(UUID.randomUUID().toString())
            .workevidence(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString());
    }
}
