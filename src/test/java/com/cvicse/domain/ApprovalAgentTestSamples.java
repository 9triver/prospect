package com.cvicse.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ApprovalAgentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ApprovalAgent getApprovalAgentSample1() {
        return new ApprovalAgent()
            .id("id1")
            .agentid(1L)
            .agentname("agentname1")
            .agentdepartment("agentdepartment1")
            .originalapprovalname("originalapprovalname1")
            .originaldepartment("originaldepartment1")
            .secrecylevel(1);
    }

    public static ApprovalAgent getApprovalAgentSample2() {
        return new ApprovalAgent()
            .id("id2")
            .agentid(2L)
            .agentname("agentname2")
            .agentdepartment("agentdepartment2")
            .originalapprovalname("originalapprovalname2")
            .originaldepartment("originaldepartment2")
            .secrecylevel(2);
    }

    public static ApprovalAgent getApprovalAgentRandomSampleGenerator() {
        return new ApprovalAgent()
            .id(UUID.randomUUID().toString())
            .agentid(longCount.incrementAndGet())
            .agentname(UUID.randomUUID().toString())
            .agentdepartment(UUID.randomUUID().toString())
            .originalapprovalname(UUID.randomUUID().toString())
            .originaldepartment(UUID.randomUUID().toString())
            .secrecylevel(intCount.incrementAndGet());
    }
}
