package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ContractPaymentTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ContractPayment getContractPaymentSample1() {
        return new ContractPayment()
            .id(1)
            .workbagid("workbagid1")
            .workbagname("workbagname1")
            .contractcode("contractcode1")
            .contractname("contractname1")
            .planpaymentnode("planpaymentnode1")
            .financialvoucherid("financialvoucherid1");
    }

    public static ContractPayment getContractPaymentSample2() {
        return new ContractPayment()
            .id(2)
            .workbagid("workbagid2")
            .workbagname("workbagname2")
            .contractcode("contractcode2")
            .contractname("contractname2")
            .planpaymentnode("planpaymentnode2")
            .financialvoucherid("financialvoucherid2");
    }

    public static ContractPayment getContractPaymentRandomSampleGenerator() {
        return new ContractPayment()
            .id(intCount.incrementAndGet())
            .workbagid(UUID.randomUUID().toString())
            .workbagname(UUID.randomUUID().toString())
            .contractcode(UUID.randomUUID().toString())
            .contractname(UUID.randomUUID().toString())
            .planpaymentnode(UUID.randomUUID().toString())
            .financialvoucherid(UUID.randomUUID().toString());
    }
}
