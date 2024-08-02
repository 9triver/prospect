package com.cvicse.jy1.domain;

import java.util.UUID;

public class FundsEstimationTestSamples {

    public static FundsEstimation getFundsEstimationSample1() {
        return new FundsEstimation().id("id1").name("name1").source("source1").unit("unit1").number("number1");
    }

    public static FundsEstimation getFundsEstimationSample2() {
        return new FundsEstimation().id("id2").name("name2").source("source2").unit("unit2").number("number2");
    }

    public static FundsEstimation getFundsEstimationRandomSampleGenerator() {
        return new FundsEstimation()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .source(UUID.randomUUID().toString())
            .unit(UUID.randomUUID().toString())
            .number(UUID.randomUUID().toString());
    }
}
