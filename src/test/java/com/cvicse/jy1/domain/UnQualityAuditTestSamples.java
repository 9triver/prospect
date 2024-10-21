package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class UnQualityAuditTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static UnQualityAudit getUnQualityAuditSample1() {
        return new UnQualityAudit()
            .id(1)
            .workbagid("workbagid1")
            .belongwbsid("belongwbsid1")
            .outsourcingcontractid("outsourcingcontractid1")
            .unqualityid("unqualityid1")
            .unqualityname("unqualityname1")
            .unqualityunit("unqualityunit1")
            .unqualitytrialgroup("unqualitytrialgroup1")
            .inspector("inspector1")
            .unqualitystage("unqualitystage1")
            .unqualitynumber(1)
            .unqualityintroduction("unqualityintroduction1")
            .unqualitycategory("unqualitycategory1")
            .handlingopinion("handlingopinion1")
            .applicant("applicant1")
            .applicationdate("applicationdate1")
            .attachment("attachment1")
            .disposalmethod("disposalmethod1")
            .causeanalysis("causeanalysis1")
            .correctivemeasures("correctivemeasures1")
            .remarks("remarks1");
    }

    public static UnQualityAudit getUnQualityAuditSample2() {
        return new UnQualityAudit()
            .id(2)
            .workbagid("workbagid2")
            .belongwbsid("belongwbsid2")
            .outsourcingcontractid("outsourcingcontractid2")
            .unqualityid("unqualityid2")
            .unqualityname("unqualityname2")
            .unqualityunit("unqualityunit2")
            .unqualitytrialgroup("unqualitytrialgroup2")
            .inspector("inspector2")
            .unqualitystage("unqualitystage2")
            .unqualitynumber(2)
            .unqualityintroduction("unqualityintroduction2")
            .unqualitycategory("unqualitycategory2")
            .handlingopinion("handlingopinion2")
            .applicant("applicant2")
            .applicationdate("applicationdate2")
            .attachment("attachment2")
            .disposalmethod("disposalmethod2")
            .causeanalysis("causeanalysis2")
            .correctivemeasures("correctivemeasures2")
            .remarks("remarks2");
    }

    public static UnQualityAudit getUnQualityAuditRandomSampleGenerator() {
        return new UnQualityAudit()
            .id(intCount.incrementAndGet())
            .workbagid(UUID.randomUUID().toString())
            .belongwbsid(UUID.randomUUID().toString())
            .outsourcingcontractid(UUID.randomUUID().toString())
            .unqualityid(UUID.randomUUID().toString())
            .unqualityname(UUID.randomUUID().toString())
            .unqualityunit(UUID.randomUUID().toString())
            .unqualitytrialgroup(UUID.randomUUID().toString())
            .inspector(UUID.randomUUID().toString())
            .unqualitystage(UUID.randomUUID().toString())
            .unqualitynumber(intCount.incrementAndGet())
            .unqualityintroduction(UUID.randomUUID().toString())
            .unqualitycategory(UUID.randomUUID().toString())
            .handlingopinion(UUID.randomUUID().toString())
            .applicant(UUID.randomUUID().toString())
            .applicationdate(UUID.randomUUID().toString())
            .attachment(UUID.randomUUID().toString())
            .disposalmethod(UUID.randomUUID().toString())
            .causeanalysis(UUID.randomUUID().toString())
            .correctivemeasures(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString());
    }
}
