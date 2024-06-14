package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.OutsourcingmPurchaseExecuteTestSamples.*;
import static com.cvicse.domain.OutsourcingmPurchasePlanTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OutsourcingmPurchasePlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutsourcingmPurchasePlan.class);
        OutsourcingmPurchasePlan outsourcingmPurchasePlan1 = getOutsourcingmPurchasePlanSample1();
        OutsourcingmPurchasePlan outsourcingmPurchasePlan2 = new OutsourcingmPurchasePlan();
        assertThat(outsourcingmPurchasePlan1).isNotEqualTo(outsourcingmPurchasePlan2);

        outsourcingmPurchasePlan2.setId(outsourcingmPurchasePlan1.getId());
        assertThat(outsourcingmPurchasePlan1).isEqualTo(outsourcingmPurchasePlan2);

        outsourcingmPurchasePlan2 = getOutsourcingmPurchasePlanSample2();
        assertThat(outsourcingmPurchasePlan1).isNotEqualTo(outsourcingmPurchasePlan2);
    }

    @Test
    void projectTest() throws Exception {
        OutsourcingmPurchasePlan outsourcingmPurchasePlan = getOutsourcingmPurchasePlanRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        outsourcingmPurchasePlan.setProject(projectBack);
        assertThat(outsourcingmPurchasePlan.getProject()).isEqualTo(projectBack);

        outsourcingmPurchasePlan.project(null);
        assertThat(outsourcingmPurchasePlan.getProject()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        OutsourcingmPurchasePlan outsourcingmPurchasePlan = getOutsourcingmPurchasePlanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingmPurchasePlan.setResponsibleid(officersBack);
        assertThat(outsourcingmPurchasePlan.getResponsibleid()).isEqualTo(officersBack);

        outsourcingmPurchasePlan.responsibleid(null);
        assertThat(outsourcingmPurchasePlan.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        OutsourcingmPurchasePlan outsourcingmPurchasePlan = getOutsourcingmPurchasePlanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingmPurchasePlan.setAuditorid(officersBack);
        assertThat(outsourcingmPurchasePlan.getAuditorid()).isEqualTo(officersBack);

        outsourcingmPurchasePlan.auditorid(null);
        assertThat(outsourcingmPurchasePlan.getAuditorid()).isNull();
    }

    @Test
    void outsourcingmPurchaseExecuteTest() throws Exception {
        OutsourcingmPurchasePlan outsourcingmPurchasePlan = getOutsourcingmPurchasePlanRandomSampleGenerator();
        OutsourcingmPurchaseExecute outsourcingmPurchaseExecuteBack = getOutsourcingmPurchaseExecuteRandomSampleGenerator();

        outsourcingmPurchasePlan.setOutsourcingmPurchaseExecute(outsourcingmPurchaseExecuteBack);
        assertThat(outsourcingmPurchasePlan.getOutsourcingmPurchaseExecute()).isEqualTo(outsourcingmPurchaseExecuteBack);
        assertThat(outsourcingmPurchaseExecuteBack.getOutsourcingplanid()).isEqualTo(outsourcingmPurchasePlan);

        outsourcingmPurchasePlan.outsourcingmPurchaseExecute(null);
        assertThat(outsourcingmPurchasePlan.getOutsourcingmPurchaseExecute()).isNull();
        assertThat(outsourcingmPurchaseExecuteBack.getOutsourcingplanid()).isNull();
    }
}
