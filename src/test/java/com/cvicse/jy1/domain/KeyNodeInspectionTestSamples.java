package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class KeyNodeInspectionTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static KeyNodeInspection getKeyNodeInspectionSample1() {
        return new KeyNodeInspection()
            .id(1)
            .name("name1")
            .workbagid("workbagid1")
            .workbagname("workbagname1")
            .belongwbsid("belongwbsid1")
            .projectlevel("projectlevel1")
            .iskey("iskey1")
            .isimplementationplan("isimplementationplan1")
            .isqualityplan("isqualityplan1")
            .istechniqueplan("istechniqueplan1")
            .implementationplanstatus("implementationplanstatus1")
            .isimplementationplanmaterial("isimplementationplanmaterial1")
            .technologyplanstatus("technologyplanstatus1")
            .istechnologymaterial("istechnologymaterial1")
            .firstcheckstatus("firstcheckstatus1")
            .isfirstcheckmaterial("isfirstcheckmaterial1")
            .productioncheckstatus("productioncheckstatus1")
            .isproductioncheckmaterial("isproductioncheckmaterial1")
            .status("status1");
    }

    public static KeyNodeInspection getKeyNodeInspectionSample2() {
        return new KeyNodeInspection()
            .id(2)
            .name("name2")
            .workbagid("workbagid2")
            .workbagname("workbagname2")
            .belongwbsid("belongwbsid2")
            .projectlevel("projectlevel2")
            .iskey("iskey2")
            .isimplementationplan("isimplementationplan2")
            .isqualityplan("isqualityplan2")
            .istechniqueplan("istechniqueplan2")
            .implementationplanstatus("implementationplanstatus2")
            .isimplementationplanmaterial("isimplementationplanmaterial2")
            .technologyplanstatus("technologyplanstatus2")
            .istechnologymaterial("istechnologymaterial2")
            .firstcheckstatus("firstcheckstatus2")
            .isfirstcheckmaterial("isfirstcheckmaterial2")
            .productioncheckstatus("productioncheckstatus2")
            .isproductioncheckmaterial("isproductioncheckmaterial2")
            .status("status2");
    }

    public static KeyNodeInspection getKeyNodeInspectionRandomSampleGenerator() {
        return new KeyNodeInspection()
            .id(intCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .workbagid(UUID.randomUUID().toString())
            .workbagname(UUID.randomUUID().toString())
            .belongwbsid(UUID.randomUUID().toString())
            .projectlevel(UUID.randomUUID().toString())
            .iskey(UUID.randomUUID().toString())
            .isimplementationplan(UUID.randomUUID().toString())
            .isqualityplan(UUID.randomUUID().toString())
            .istechniqueplan(UUID.randomUUID().toString())
            .implementationplanstatus(UUID.randomUUID().toString())
            .isimplementationplanmaterial(UUID.randomUUID().toString())
            .technologyplanstatus(UUID.randomUUID().toString())
            .istechnologymaterial(UUID.randomUUID().toString())
            .firstcheckstatus(UUID.randomUUID().toString())
            .isfirstcheckmaterial(UUID.randomUUID().toString())
            .productioncheckstatus(UUID.randomUUID().toString())
            .isproductioncheckmaterial(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString());
    }
}
