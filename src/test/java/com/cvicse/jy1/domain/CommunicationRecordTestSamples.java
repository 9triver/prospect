package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CommunicationRecordTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CommunicationRecord getCommunicationRecordSample1() {
        return new CommunicationRecord()
            .id(1)
            .wbsid("wbsid1")
            .wbsname("wbsname1")
            .workbagid("workbagid1")
            .associationmeetingname("associationmeetingname1")
            .communicationlocation("communicationlocation1")
            .communicationcontent("communicationcontent1")
            .auditorid("auditorid1")
            .auditorname("auditorname1")
            .remarks("remarks1");
    }

    public static CommunicationRecord getCommunicationRecordSample2() {
        return new CommunicationRecord()
            .id(2)
            .wbsid("wbsid2")
            .wbsname("wbsname2")
            .workbagid("workbagid2")
            .associationmeetingname("associationmeetingname2")
            .communicationlocation("communicationlocation2")
            .communicationcontent("communicationcontent2")
            .auditorid("auditorid2")
            .auditorname("auditorname2")
            .remarks("remarks2");
    }

    public static CommunicationRecord getCommunicationRecordRandomSampleGenerator() {
        return new CommunicationRecord()
            .id(intCount.incrementAndGet())
            .wbsid(UUID.randomUUID().toString())
            .wbsname(UUID.randomUUID().toString())
            .workbagid(UUID.randomUUID().toString())
            .associationmeetingname(UUID.randomUUID().toString())
            .communicationlocation(UUID.randomUUID().toString())
            .communicationcontent(UUID.randomUUID().toString())
            .auditorid(UUID.randomUUID().toString())
            .auditorname(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString());
    }
}
