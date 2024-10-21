package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class DeviationPermitApplicationTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DeviationPermitApplication getDeviationPermitApplicationSample1() {
        return new DeviationPermitApplication()
            .id(1)
            .wbsid("wbsid1")
            .technicalfileid("technicalfileid1")
            .applicationunit("applicationunit1")
            .applicant("applicant1")
            .permitcontent("permitcontent1")
            .permitreason("permitreason1")
            .projectinfluence("projectinfluence1")
            .contractinfluence("contractinfluence1")
            .permitrange("permitrange1")
            .remarks("remarks1");
    }

    public static DeviationPermitApplication getDeviationPermitApplicationSample2() {
        return new DeviationPermitApplication()
            .id(2)
            .wbsid("wbsid2")
            .technicalfileid("technicalfileid2")
            .applicationunit("applicationunit2")
            .applicant("applicant2")
            .permitcontent("permitcontent2")
            .permitreason("permitreason2")
            .projectinfluence("projectinfluence2")
            .contractinfluence("contractinfluence2")
            .permitrange("permitrange2")
            .remarks("remarks2");
    }

    public static DeviationPermitApplication getDeviationPermitApplicationRandomSampleGenerator() {
        return new DeviationPermitApplication()
            .id(intCount.incrementAndGet())
            .wbsid(UUID.randomUUID().toString())
            .technicalfileid(UUID.randomUUID().toString())
            .applicationunit(UUID.randomUUID().toString())
            .applicant(UUID.randomUUID().toString())
            .permitcontent(UUID.randomUUID().toString())
            .permitreason(UUID.randomUUID().toString())
            .projectinfluence(UUID.randomUUID().toString())
            .contractinfluence(UUID.randomUUID().toString())
            .permitrange(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString());
    }
}
