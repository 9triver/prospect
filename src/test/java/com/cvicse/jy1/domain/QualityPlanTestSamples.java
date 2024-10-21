package com.cvicse.jy1.domain;

import java.util.UUID;

public class QualityPlanTestSamples {

    public static QualityPlan getQualityPlanSample1() {
        return new QualityPlan()
            .id("id1")
            .name("name1")
            .wbsid("wbsid1")
            .workbagid("workbagid1")
            .fileversion("fileversion1")
            .author("author1")
            .attachment("attachment1");
    }

    public static QualityPlan getQualityPlanSample2() {
        return new QualityPlan()
            .id("id2")
            .name("name2")
            .wbsid("wbsid2")
            .workbagid("workbagid2")
            .fileversion("fileversion2")
            .author("author2")
            .attachment("attachment2");
    }

    public static QualityPlan getQualityPlanRandomSampleGenerator() {
        return new QualityPlan()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .wbsid(UUID.randomUUID().toString())
            .workbagid(UUID.randomUUID().toString())
            .fileversion(UUID.randomUUID().toString())
            .author(UUID.randomUUID().toString())
            .attachment(UUID.randomUUID().toString());
    }
}
