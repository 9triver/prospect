package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkbagTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Workbag getWorkbagSample1() {
        return new Workbag()
            .id("id1")
            .name("name1")
            .pbsid("pbsid1")
            .workbagtype(1)
            .supplier("supplier1")
            .iskeyimportant(1)
            .keypbsname("keypbsname1")
            .importantpbsname("importantpbsname1")
            .description("description1")
            .progress(1)
            .issafetywork(1)
            .remark("remark1");
    }

    public static Workbag getWorkbagSample2() {
        return new Workbag()
            .id("id2")
            .name("name2")
            .pbsid("pbsid2")
            .workbagtype(2)
            .supplier("supplier2")
            .iskeyimportant(2)
            .keypbsname("keypbsname2")
            .importantpbsname("importantpbsname2")
            .description("description2")
            .progress(2)
            .issafetywork(2)
            .remark("remark2");
    }

    public static Workbag getWorkbagRandomSampleGenerator() {
        return new Workbag()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .pbsid(UUID.randomUUID().toString())
            .workbagtype(intCount.incrementAndGet())
            .supplier(UUID.randomUUID().toString())
            .iskeyimportant(intCount.incrementAndGet())
            .keypbsname(UUID.randomUUID().toString())
            .importantpbsname(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .progress(intCount.incrementAndGet())
            .issafetywork(intCount.incrementAndGet())
            .remark(UUID.randomUUID().toString());
    }
}
