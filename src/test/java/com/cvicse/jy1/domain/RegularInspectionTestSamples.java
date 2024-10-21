package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RegularInspectionTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static RegularInspection getRegularInspectionSample1() {
        return new RegularInspection()
            .id(1)
            .name("name1")
            .workbagid("workbagid1")
            .workbagname("workbagname1")
            .type("type1")
            .standard("standard1")
            .measurementmethod("measurementmethod1")
            .checkresult("checkresult1")
            .checktarget("checktarget1")
            .checkcompletion("checkcompletion1")
            .checkstatus("checkstatus1");
    }

    public static RegularInspection getRegularInspectionSample2() {
        return new RegularInspection()
            .id(2)
            .name("name2")
            .workbagid("workbagid2")
            .workbagname("workbagname2")
            .type("type2")
            .standard("standard2")
            .measurementmethod("measurementmethod2")
            .checkresult("checkresult2")
            .checktarget("checktarget2")
            .checkcompletion("checkcompletion2")
            .checkstatus("checkstatus2");
    }

    public static RegularInspection getRegularInspectionRandomSampleGenerator() {
        return new RegularInspection()
            .id(intCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .workbagid(UUID.randomUUID().toString())
            .workbagname(UUID.randomUUID().toString())
            .type(UUID.randomUUID().toString())
            .standard(UUID.randomUUID().toString())
            .measurementmethod(UUID.randomUUID().toString())
            .checkresult(UUID.randomUUID().toString())
            .checktarget(UUID.randomUUID().toString())
            .checkcompletion(UUID.randomUUID().toString())
            .checkstatus(UUID.randomUUID().toString());
    }
}
