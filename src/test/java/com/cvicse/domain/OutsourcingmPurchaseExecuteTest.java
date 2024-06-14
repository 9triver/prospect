package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.OutsourcingmPurchaseExecuteTestSamples.*;
import static com.cvicse.domain.OutsourcingmPurchasePlanTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OutsourcingmPurchaseExecuteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutsourcingmPurchaseExecute.class);
        OutsourcingmPurchaseExecute outsourcingmPurchaseExecute1 = getOutsourcingmPurchaseExecuteSample1();
        OutsourcingmPurchaseExecute outsourcingmPurchaseExecute2 = new OutsourcingmPurchaseExecute();
        assertThat(outsourcingmPurchaseExecute1).isNotEqualTo(outsourcingmPurchaseExecute2);

        outsourcingmPurchaseExecute2.setId(outsourcingmPurchaseExecute1.getId());
        assertThat(outsourcingmPurchaseExecute1).isEqualTo(outsourcingmPurchaseExecute2);

        outsourcingmPurchaseExecute2 = getOutsourcingmPurchaseExecuteSample2();
        assertThat(outsourcingmPurchaseExecute1).isNotEqualTo(outsourcingmPurchaseExecute2);
    }

    @Test
    void outsourcingplanidTest() throws Exception {
        OutsourcingmPurchaseExecute outsourcingmPurchaseExecute = getOutsourcingmPurchaseExecuteRandomSampleGenerator();
        OutsourcingmPurchasePlan outsourcingmPurchasePlanBack = getOutsourcingmPurchasePlanRandomSampleGenerator();

        outsourcingmPurchaseExecute.setOutsourcingplanid(outsourcingmPurchasePlanBack);
        assertThat(outsourcingmPurchaseExecute.getOutsourcingplanid()).isEqualTo(outsourcingmPurchasePlanBack);

        outsourcingmPurchaseExecute.outsourcingplanid(null);
        assertThat(outsourcingmPurchaseExecute.getOutsourcingplanid()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        OutsourcingmPurchaseExecute outsourcingmPurchaseExecute = getOutsourcingmPurchaseExecuteRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingmPurchaseExecute.setResponsibleid(officersBack);
        assertThat(outsourcingmPurchaseExecute.getResponsibleid()).isEqualTo(officersBack);

        outsourcingmPurchaseExecute.responsibleid(null);
        assertThat(outsourcingmPurchaseExecute.getResponsibleid()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        OutsourcingmPurchaseExecute outsourcingmPurchaseExecute = getOutsourcingmPurchaseExecuteRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        outsourcingmPurchaseExecute.setProject(projectBack);
        assertThat(outsourcingmPurchaseExecute.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getOutsourcingmPurchaseExecute()).isEqualTo(outsourcingmPurchaseExecute);

        outsourcingmPurchaseExecute.project(null);
        assertThat(outsourcingmPurchaseExecute.getProject()).isNull();
        assertThat(projectBack.getOutsourcingmPurchaseExecute()).isNull();
    }
}
