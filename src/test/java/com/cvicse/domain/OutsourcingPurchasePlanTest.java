package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.OutsourcingPurchaseExecuteTestSamples.*;
import static com.cvicse.domain.OutsourcingPurchasePlanTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OutsourcingPurchasePlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutsourcingPurchasePlan.class);
        OutsourcingPurchasePlan outsourcingPurchasePlan1 = getOutsourcingPurchasePlanSample1();
        OutsourcingPurchasePlan outsourcingPurchasePlan2 = new OutsourcingPurchasePlan();
        assertThat(outsourcingPurchasePlan1).isNotEqualTo(outsourcingPurchasePlan2);

        outsourcingPurchasePlan2.setId(outsourcingPurchasePlan1.getId());
        assertThat(outsourcingPurchasePlan1).isEqualTo(outsourcingPurchasePlan2);

        outsourcingPurchasePlan2 = getOutsourcingPurchasePlanSample2();
        assertThat(outsourcingPurchasePlan1).isNotEqualTo(outsourcingPurchasePlan2);
    }

    @Test
    void projectTest() throws Exception {
        OutsourcingPurchasePlan outsourcingPurchasePlan = getOutsourcingPurchasePlanRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        outsourcingPurchasePlan.setProject(projectBack);
        assertThat(outsourcingPurchasePlan.getProject()).isEqualTo(projectBack);

        outsourcingPurchasePlan.project(null);
        assertThat(outsourcingPurchasePlan.getProject()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        OutsourcingPurchasePlan outsourcingPurchasePlan = getOutsourcingPurchasePlanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingPurchasePlan.setResponsibleid(officersBack);
        assertThat(outsourcingPurchasePlan.getResponsibleid()).isEqualTo(officersBack);

        outsourcingPurchasePlan.responsibleid(null);
        assertThat(outsourcingPurchasePlan.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        OutsourcingPurchasePlan outsourcingPurchasePlan = getOutsourcingPurchasePlanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingPurchasePlan.setAuditorid(officersBack);
        assertThat(outsourcingPurchasePlan.getAuditorid()).isEqualTo(officersBack);

        outsourcingPurchasePlan.auditorid(null);
        assertThat(outsourcingPurchasePlan.getAuditorid()).isNull();
    }

    @Test
    void outsourcingPurchaseExecuteTest() throws Exception {
        OutsourcingPurchasePlan outsourcingPurchasePlan = getOutsourcingPurchasePlanRandomSampleGenerator();
        OutsourcingPurchaseExecute outsourcingPurchaseExecuteBack = getOutsourcingPurchaseExecuteRandomSampleGenerator();

        outsourcingPurchasePlan.setOutsourcingPurchaseExecute(outsourcingPurchaseExecuteBack);
        assertThat(outsourcingPurchasePlan.getOutsourcingPurchaseExecute()).isEqualTo(outsourcingPurchaseExecuteBack);
        assertThat(outsourcingPurchaseExecuteBack.getOutsourcingplanid()).isEqualTo(outsourcingPurchasePlan);

        outsourcingPurchasePlan.outsourcingPurchaseExecute(null);
        assertThat(outsourcingPurchasePlan.getOutsourcingPurchaseExecute()).isNull();
        assertThat(outsourcingPurchaseExecuteBack.getOutsourcingplanid()).isNull();
    }
}
