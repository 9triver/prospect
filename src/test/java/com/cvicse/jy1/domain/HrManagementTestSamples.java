package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class HrManagementTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static HrManagement getHrManagementSample1() {
        return new HrManagement()
            .id(1)
            .officersid("officersid1")
            .officersname("officersname1")
            .projectid(1)
            .projectname("projectname1")
            .projectrole("projectrole1")
            .jobgrade("jobgrade1")
            .departmentid("departmentid1")
            .departmentname("departmentname1")
            .frontlineid("frontlineid1")
            .frontlinename("frontlinename1")
            .jobduty("jobduty1")
            .annualworktime(1)
            .annualtasktarget("annualtasktarget1");
    }

    public static HrManagement getHrManagementSample2() {
        return new HrManagement()
            .id(2)
            .officersid("officersid2")
            .officersname("officersname2")
            .projectid(2)
            .projectname("projectname2")
            .projectrole("projectrole2")
            .jobgrade("jobgrade2")
            .departmentid("departmentid2")
            .departmentname("departmentname2")
            .frontlineid("frontlineid2")
            .frontlinename("frontlinename2")
            .jobduty("jobduty2")
            .annualworktime(2)
            .annualtasktarget("annualtasktarget2");
    }

    public static HrManagement getHrManagementRandomSampleGenerator() {
        return new HrManagement()
            .id(intCount.incrementAndGet())
            .officersid(UUID.randomUUID().toString())
            .officersname(UUID.randomUUID().toString())
            .projectid(intCount.incrementAndGet())
            .projectname(UUID.randomUUID().toString())
            .projectrole(UUID.randomUUID().toString())
            .jobgrade(UUID.randomUUID().toString())
            .departmentid(UUID.randomUUID().toString())
            .departmentname(UUID.randomUUID().toString())
            .frontlineid(UUID.randomUUID().toString())
            .frontlinename(UUID.randomUUID().toString())
            .jobduty(UUID.randomUUID().toString())
            .annualworktime(intCount.incrementAndGet())
            .annualtasktarget(UUID.randomUUID().toString());
    }
}
