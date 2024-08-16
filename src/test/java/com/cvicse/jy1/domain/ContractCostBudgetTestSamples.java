package com.cvicse.jy1.domain;

import java.util.UUID;

public class ContractCostBudgetTestSamples {

    public static ContractCostBudget getContractCostBudgetSample1() {
        return new ContractCostBudget().id("id1").subject("subject1").unit("unit1").number("number1");
    }

    public static ContractCostBudget getContractCostBudgetSample2() {
        return new ContractCostBudget().id("id2").subject("subject2").unit("unit2").number("number2");
    }

    public static ContractCostBudget getContractCostBudgetRandomSampleGenerator() {
        return new ContractCostBudget()
            .id(UUID.randomUUID().toString())
            .subject(UUID.randomUUID().toString())
            .unit(UUID.randomUUID().toString())
            .number(UUID.randomUUID().toString());
    }
}
