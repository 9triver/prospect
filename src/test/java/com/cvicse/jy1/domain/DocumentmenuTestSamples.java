package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class DocumentmenuTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Documentmenu getDocumentmenuSample1() {
        return new Documentmenu()
            .id(1)
            .menuid("menuid1")
            .belongtype("belongtype1")
            .menuname("menuname1")
            .parentmenuid("parentmenuid1")
            .creatorid("creatorid1")
            .creatorname("creatorname1")
            .type(1)
            .filenum(1)
            .departmentid("departmentid1")
            .departmentname("departmentname1")
            .fileurl("fileurl")
            .spare2(1)
            .spare3("spare31");
    }

    public static Documentmenu getDocumentmenuSample2() {
        return new Documentmenu()
            .id(2)
            .menuid("menuid2")
            .belongtype("belongtype2")
            .menuname("menuname2")
            .parentmenuid("parentmenuid2")
            .creatorid("creatorid2")
            .creatorname("creatorname2")
            .type(2)
            .filenum(2)
            .departmentid("departmentid2")
            .departmentname("departmentname2")
            .fileurl("fileurl2")
            .spare2(2)
            .spare3("spare32");
    }

    public static Documentmenu getDocumentmenuRandomSampleGenerator() {
        return new Documentmenu()
            .id(intCount.incrementAndGet())
            .menuid(UUID.randomUUID().toString())
            .belongtype(UUID.randomUUID().toString())
            .menuname(UUID.randomUUID().toString())
            .parentmenuid(UUID.randomUUID().toString())
            .creatorid(UUID.randomUUID().toString())
            .creatorname(UUID.randomUUID().toString())
            .type(intCount.incrementAndGet())
            .filenum(intCount.incrementAndGet())
            .departmentid(UUID.randomUUID().toString())
            .departmentname(UUID.randomUUID().toString())
            .fileurl(UUID.randomUUID().toString())
            .spare2(intCount.incrementAndGet())
            .spare3(UUID.randomUUID().toString());
    }
}
