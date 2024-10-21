package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class QualitytozeroTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Qualitytozero getQualitytozeroSample1() {
        return new Qualitytozero()
            .id(1)
            .workbagid("workbagid1")
            .belongwbsid("belongwbsid1")
            .outsourcingcontractid("outsourcingcontractid1")
            .qualityproblemid("qualityproblemid1")
            .qualityproblemname("qualityproblemname1")
            .problemresponsibleperson("problemresponsibleperson1")
            .problemresponsibleunit("problemresponsibleunit1")
            .producttype("producttype1")
            .productname("productname1")
            .problemphenomenon("problemphenomenon1")
            .problemtype("problemtype1")
            .qualitylevel("qualitylevel1")
            .zerotype("zerotype1")
            .problemreasonanalysis("problemreasonanalysis1")
            .problemreasoncategory("problemreasoncategory1")
            .takemeasures("takemeasures1")
            .onebyonecategory("onebyonecategory1")
            .verificationeffect("verificationeffect1")
            .qualityprojectreport("qualityprojectreport1")
            .qualitytozeroreport("qualitytozeroreport1")
            .reviewopinion("reviewopinion1")
            .implementationverificationtable("implementationverificationtable1");
    }

    public static Qualitytozero getQualitytozeroSample2() {
        return new Qualitytozero()
            .id(2)
            .workbagid("workbagid2")
            .belongwbsid("belongwbsid2")
            .outsourcingcontractid("outsourcingcontractid2")
            .qualityproblemid("qualityproblemid2")
            .qualityproblemname("qualityproblemname2")
            .problemresponsibleperson("problemresponsibleperson2")
            .problemresponsibleunit("problemresponsibleunit2")
            .producttype("producttype2")
            .productname("productname2")
            .problemphenomenon("problemphenomenon2")
            .problemtype("problemtype2")
            .qualitylevel("qualitylevel2")
            .zerotype("zerotype2")
            .problemreasonanalysis("problemreasonanalysis2")
            .problemreasoncategory("problemreasoncategory2")
            .takemeasures("takemeasures2")
            .onebyonecategory("onebyonecategory2")
            .verificationeffect("verificationeffect2")
            .qualityprojectreport("qualityprojectreport2")
            .qualitytozeroreport("qualitytozeroreport2")
            .reviewopinion("reviewopinion2")
            .implementationverificationtable("implementationverificationtable2");
    }

    public static Qualitytozero getQualitytozeroRandomSampleGenerator() {
        return new Qualitytozero()
            .id(intCount.incrementAndGet())
            .workbagid(UUID.randomUUID().toString())
            .belongwbsid(UUID.randomUUID().toString())
            .outsourcingcontractid(UUID.randomUUID().toString())
            .qualityproblemid(UUID.randomUUID().toString())
            .qualityproblemname(UUID.randomUUID().toString())
            .problemresponsibleperson(UUID.randomUUID().toString())
            .problemresponsibleunit(UUID.randomUUID().toString())
            .producttype(UUID.randomUUID().toString())
            .productname(UUID.randomUUID().toString())
            .problemphenomenon(UUID.randomUUID().toString())
            .problemtype(UUID.randomUUID().toString())
            .qualitylevel(UUID.randomUUID().toString())
            .zerotype(UUID.randomUUID().toString())
            .problemreasonanalysis(UUID.randomUUID().toString())
            .problemreasoncategory(UUID.randomUUID().toString())
            .takemeasures(UUID.randomUUID().toString())
            .onebyonecategory(UUID.randomUUID().toString())
            .verificationeffect(UUID.randomUUID().toString())
            .qualityprojectreport(UUID.randomUUID().toString())
            .qualitytozeroreport(UUID.randomUUID().toString())
            .reviewopinion(UUID.randomUUID().toString())
            .implementationverificationtable(UUID.randomUUID().toString());
    }
}
