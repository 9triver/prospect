package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class DeliveryContentTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DeliveryContent getDeliveryContentSample1() {
        return new DeliveryContent()
            .id(1)
            .warrantyrequirement("warrantyrequirement1")
            .purchaseplanno("purchaseplanno1")
            .purchasemethod("purchasemethod1")
            .purchasesecretlevel("purchasesecretlevel1")
            .reviewmethod("reviewmethod1")
            .requirementdepartment("requirementdepartment1")
            .requirementperson("requirementperson1")
            .undertaker("undertaker1")
            .undertakingdepartment("undertakingdepartment1")
            .workbagid("workbagid1")
            .projectmanager("projectmanager1")
            .fundsource("fundsource1")
            .thesisname("thesisname1")
            .contractauxiliaryno("contractauxiliaryno1")
            .reasonfornosuppliers("reasonfornosuppliers1")
            .reasonforchange("reasonforchange1")
            .judges("judges1")
            .responsevendorname("responsevendorname1")
            .finalquoteandscore("finalquoteandscore1")
            .issubmitsecrecyagreement("issubmitsecrecyagreement1")
            .issubmitsecurityagreement("issubmitsecurityagreement1")
            .remark("remark1");
    }

    public static DeliveryContent getDeliveryContentSample2() {
        return new DeliveryContent()
            .id(2)
            .warrantyrequirement("warrantyrequirement2")
            .purchaseplanno("purchaseplanno2")
            .purchasemethod("purchasemethod2")
            .purchasesecretlevel("purchasesecretlevel2")
            .reviewmethod("reviewmethod2")
            .requirementdepartment("requirementdepartment2")
            .requirementperson("requirementperson2")
            .undertaker("undertaker2")
            .undertakingdepartment("undertakingdepartment2")
            .workbagid("workbagid2")
            .projectmanager("projectmanager2")
            .fundsource("fundsource2")
            .thesisname("thesisname2")
            .contractauxiliaryno("contractauxiliaryno2")
            .reasonfornosuppliers("reasonfornosuppliers2")
            .reasonforchange("reasonforchange2")
            .judges("judges2")
            .responsevendorname("responsevendorname2")
            .finalquoteandscore("finalquoteandscore2")
            .issubmitsecrecyagreement("issubmitsecrecyagreement2")
            .issubmitsecurityagreement("issubmitsecurityagreement2")
            .remark("remark2");
    }

    public static DeliveryContent getDeliveryContentRandomSampleGenerator() {
        return new DeliveryContent()
            .id(intCount.incrementAndGet())
            .warrantyrequirement(UUID.randomUUID().toString())
            .purchaseplanno(UUID.randomUUID().toString())
            .purchasemethod(UUID.randomUUID().toString())
            .purchasesecretlevel(UUID.randomUUID().toString())
            .reviewmethod(UUID.randomUUID().toString())
            .requirementdepartment(UUID.randomUUID().toString())
            .requirementperson(UUID.randomUUID().toString())
            .undertaker(UUID.randomUUID().toString())
            .undertakingdepartment(UUID.randomUUID().toString())
            .workbagid(UUID.randomUUID().toString())
            .projectmanager(UUID.randomUUID().toString())
            .fundsource(UUID.randomUUID().toString())
            .thesisname(UUID.randomUUID().toString())
            .contractauxiliaryno(UUID.randomUUID().toString())
            .reasonfornosuppliers(UUID.randomUUID().toString())
            .reasonforchange(UUID.randomUUID().toString())
            .judges(UUID.randomUUID().toString())
            .responsevendorname(UUID.randomUUID().toString())
            .finalquoteandscore(UUID.randomUUID().toString())
            .issubmitsecrecyagreement(UUID.randomUUID().toString())
            .issubmitsecurityagreement(UUID.randomUUID().toString())
            .remark(UUID.randomUUID().toString());
    }
}
