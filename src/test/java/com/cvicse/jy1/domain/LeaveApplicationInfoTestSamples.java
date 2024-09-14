package com.cvicse.jy1.domain;

import java.util.UUID;

public class LeaveApplicationInfoTestSamples {

    public static LeaveApplicationInfo getLeaveApplicationInfoSample1() {
        return new LeaveApplicationInfo()
            .id("id1")
            .startDate("startDate1")
            .endDate("endDate1")
            .leaveType("leaveType1")
            .reason("reason1")
            .status("status1");
    }

    public static LeaveApplicationInfo getLeaveApplicationInfoSample2() {
        return new LeaveApplicationInfo()
            .id("id2")
            .startDate("startDate2")
            .endDate("endDate2")
            .leaveType("leaveType2")
            .reason("reason2")
            .status("status2");
    }

    public static LeaveApplicationInfo getLeaveApplicationInfoRandomSampleGenerator() {
        return new LeaveApplicationInfo()
            .id(UUID.randomUUID().toString())
            .startDate(UUID.randomUUID().toString())
            .endDate(UUID.randomUUID().toString())
            .leaveType(UUID.randomUUID().toString())
            .reason(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString());
    }
}
