package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TechnicalConditionTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static TechnicalCondition getTechnicalConditionSample1() {
        return new TechnicalCondition()
            .id(1)
            .workbagid("workbagid1")
            .belongwbsid("belongwbsid1")
            .outsourcingcontractid("outsourcingcontractid1")
            .technicalid("technicalid1")
            .technicalname("technicalname1")
            .changedfilename("changedfilename1")
            .applicant("applicant1")
            .changedreason("changedreason1")
            .changedbefore("changedbefore1")
            .changedafter("changedafter1")
            .distributionrange("distributionrange1")
            .remarks("remarks1");
    }

    public static TechnicalCondition getTechnicalConditionSample2() {
        return new TechnicalCondition()
            .id(2)
            .workbagid("workbagid2")
            .belongwbsid("belongwbsid2")
            .outsourcingcontractid("outsourcingcontractid2")
            .technicalid("technicalid2")
            .technicalname("technicalname2")
            .changedfilename("changedfilename2")
            .applicant("applicant2")
            .changedreason("changedreason2")
            .changedbefore("changedbefore2")
            .changedafter("changedafter2")
            .distributionrange("distributionrange2")
            .remarks("remarks2");
    }

    public static TechnicalCondition getTechnicalConditionRandomSampleGenerator() {
        return new TechnicalCondition()
            .id(intCount.incrementAndGet())
            .workbagid(UUID.randomUUID().toString())
            .belongwbsid(UUID.randomUUID().toString())
            .outsourcingcontractid(UUID.randomUUID().toString())
            .technicalid(UUID.randomUUID().toString())
            .technicalname(UUID.randomUUID().toString())
            .changedfilename(UUID.randomUUID().toString())
            .applicant(UUID.randomUUID().toString())
            .changedreason(UUID.randomUUID().toString())
            .changedbefore(UUID.randomUUID().toString())
            .changedafter(UUID.randomUUID().toString())
            .distributionrange(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString());
    }
}
