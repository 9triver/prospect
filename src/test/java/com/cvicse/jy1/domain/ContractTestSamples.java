package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ContractTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Contract getContractSample1() {
        return new Contract()
            .id(1)
            .contractcode("contractcode1")
            .contractname("contractname1")
            .projectwbsname("projectwbsname1")
            .year(1)
            .fileurl("fileurl1")
            .remark("remark1");
    }

    public static Contract getContractSample2() {
        return new Contract()
            .id(2)
            .contractcode("contractcode2")
            .contractname("contractname2")
            .projectwbsname("projectwbsname2")
            .year(2)
            .fileurl("fileurl2")
            .remark("remark2");
    }

    public static Contract getContractRandomSampleGenerator() {
        return new Contract()
            .id(intCount.incrementAndGet())
            .contractcode(UUID.randomUUID().toString())
            .contractname(UUID.randomUUID().toString())
            .projectwbsname(UUID.randomUUID().toString())
            .year(intCount.incrementAndGet())
            .fileurl(UUID.randomUUID().toString())
            .remark(UUID.randomUUID().toString());
    }
}
