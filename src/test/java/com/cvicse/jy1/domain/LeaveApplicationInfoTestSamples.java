package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class LeaveApplicationInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static LeaveApplicationInfo getLeaveApplicationInfoSample1() {
        return new LeaveApplicationInfo()
            .id(1)
            .startDate("startDate1")
            .endDate("endDate1")
            .leaveType("leaveType1")
            .reason("reason1")
            .status("status1");
    }

    public static LeaveApplicationInfo getLeaveApplicationInfoSample2() {
        return new LeaveApplicationInfo()
            .id(2)
            .startDate("startDate2")
            .endDate("endDate2")
            .leaveType("leaveType2")
            .reason("reason2")
            .status("status2");
    }

    public static LeaveApplicationInfo getLeaveApplicationInfoRandomSampleGenerator() {
        return new LeaveApplicationInfo()
            .id(intCount.incrementAndGet())
            .startDate(UUID.randomUUID().toString())
            .endDate(UUID.randomUUID().toString())
            .leaveType(UUID.randomUUID().toString())
            .reason(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString());
    }
}
