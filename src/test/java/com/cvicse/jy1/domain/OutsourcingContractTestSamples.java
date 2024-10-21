package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class OutsourcingContractTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OutsourcingContract getOutsourcingContractSample1() {
        return new OutsourcingContract()
            .id(1)
            .contractid("contractid1")
            .contractcode("contractcode1")
            .contractname("contractname1")
            .contractqualityid("contractqualityid1")
            .contractcostid("contractcostid1")
            .contractfinanceid("contractfinanceid1")
            .projectid("projectid1")
            .projectsecretlevel("projectsecretlevel1")
            .counterpartyunit("counterpartyunit1")
            .negotiationlocation("negotiationlocation1")
            .negotiator("negotiator1")
            .approver("approver1")
            .contractsecretlevel("contractsecretlevel1");
    }

    public static OutsourcingContract getOutsourcingContractSample2() {
        return new OutsourcingContract()
            .id(2)
            .contractid("contractid2")
            .contractcode("contractcode2")
            .contractname("contractname2")
            .contractqualityid("contractqualityid2")
            .contractcostid("contractcostid2")
            .contractfinanceid("contractfinanceid2")
            .projectid("projectid2")
            .projectsecretlevel("projectsecretlevel2")
            .counterpartyunit("counterpartyunit2")
            .negotiationlocation("negotiationlocation2")
            .negotiator("negotiator2")
            .approver("approver2")
            .contractsecretlevel("contractsecretlevel2");
    }

    public static OutsourcingContract getOutsourcingContractRandomSampleGenerator() {
        return new OutsourcingContract()
            .id(intCount.incrementAndGet())
            .contractid(UUID.randomUUID().toString())
            .contractcode(UUID.randomUUID().toString())
            .contractname(UUID.randomUUID().toString())
            .contractqualityid(UUID.randomUUID().toString())
            .contractcostid(UUID.randomUUID().toString())
            .contractfinanceid(UUID.randomUUID().toString())
            .projectid(UUID.randomUUID().toString())
            .projectsecretlevel(UUID.randomUUID().toString())
            .counterpartyunit(UUID.randomUUID().toString())
            .negotiationlocation(UUID.randomUUID().toString())
            .negotiator(UUID.randomUUID().toString())
            .approver(UUID.randomUUID().toString())
            .contractsecretlevel(UUID.randomUUID().toString());
    }
}
