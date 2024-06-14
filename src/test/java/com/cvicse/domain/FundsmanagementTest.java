package com.cvicse.domain;

import static com.cvicse.domain.ComprehensivecontrolTestSamples.*;
import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.FundsavailabilityTestSamples.*;
import static com.cvicse.domain.FundsmanagementTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.TotalbudgetTestSamples.*;
import static com.cvicse.domain.UnitbudgetTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FundsmanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fundsmanagement.class);
        Fundsmanagement fundsmanagement1 = getFundsmanagementSample1();
        Fundsmanagement fundsmanagement2 = new Fundsmanagement();
        assertThat(fundsmanagement1).isNotEqualTo(fundsmanagement2);

        fundsmanagement2.setId(fundsmanagement1.getId());
        assertThat(fundsmanagement1).isEqualTo(fundsmanagement2);

        fundsmanagement2 = getFundsmanagementSample2();
        assertThat(fundsmanagement1).isNotEqualTo(fundsmanagement2);
    }

    @Test
    void totalbudgetTest() throws Exception {
        Fundsmanagement fundsmanagement = getFundsmanagementRandomSampleGenerator();
        Totalbudget totalbudgetBack = getTotalbudgetRandomSampleGenerator();

        fundsmanagement.setTotalbudget(totalbudgetBack);
        assertThat(fundsmanagement.getTotalbudget()).isEqualTo(totalbudgetBack);

        fundsmanagement.totalbudget(null);
        assertThat(fundsmanagement.getTotalbudget()).isNull();
    }

    @Test
    void unitbudgetTest() throws Exception {
        Fundsmanagement fundsmanagement = getFundsmanagementRandomSampleGenerator();
        Unitbudget unitbudgetBack = getUnitbudgetRandomSampleGenerator();

        fundsmanagement.setUnitbudget(unitbudgetBack);
        assertThat(fundsmanagement.getUnitbudget()).isEqualTo(unitbudgetBack);

        fundsmanagement.unitbudget(null);
        assertThat(fundsmanagement.getUnitbudget()).isNull();
    }

    @Test
    void documentTest() throws Exception {
        Fundsmanagement fundsmanagement = getFundsmanagementRandomSampleGenerator();
        Document documentBack = getDocumentRandomSampleGenerator();

        fundsmanagement.setDocument(documentBack);
        assertThat(fundsmanagement.getDocument()).isEqualTo(documentBack);

        fundsmanagement.document(null);
        assertThat(fundsmanagement.getDocument()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        Fundsmanagement fundsmanagement = getFundsmanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        fundsmanagement.setCreatorid(officersBack);
        assertThat(fundsmanagement.getCreatorid()).isEqualTo(officersBack);

        fundsmanagement.creatorid(null);
        assertThat(fundsmanagement.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Fundsmanagement fundsmanagement = getFundsmanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        fundsmanagement.setAuditorid(officersBack);
        assertThat(fundsmanagement.getAuditorid()).isEqualTo(officersBack);

        fundsmanagement.auditorid(null);
        assertThat(fundsmanagement.getAuditorid()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        Fundsmanagement fundsmanagement = getFundsmanagementRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        fundsmanagement.setProject(projectBack);
        assertThat(fundsmanagement.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getFundsmanagement()).isEqualTo(fundsmanagement);

        fundsmanagement.project(null);
        assertThat(fundsmanagement.getProject()).isNull();
        assertThat(projectBack.getFundsmanagement()).isNull();
    }

    @Test
    void comprehensivecontrolTest() throws Exception {
        Fundsmanagement fundsmanagement = getFundsmanagementRandomSampleGenerator();
        Comprehensivecontrol comprehensivecontrolBack = getComprehensivecontrolRandomSampleGenerator();

        fundsmanagement.setComprehensivecontrol(comprehensivecontrolBack);
        assertThat(fundsmanagement.getComprehensivecontrol()).isEqualTo(comprehensivecontrolBack);
        assertThat(comprehensivecontrolBack.getFunds()).isEqualTo(fundsmanagement);

        fundsmanagement.comprehensivecontrol(null);
        assertThat(fundsmanagement.getComprehensivecontrol()).isNull();
        assertThat(comprehensivecontrolBack.getFunds()).isNull();
    }

    @Test
    void fundsavailabilityTest() throws Exception {
        Fundsmanagement fundsmanagement = getFundsmanagementRandomSampleGenerator();
        Fundsavailability fundsavailabilityBack = getFundsavailabilityRandomSampleGenerator();

        fundsmanagement.setFundsavailability(fundsavailabilityBack);
        assertThat(fundsmanagement.getFundsavailability()).isEqualTo(fundsavailabilityBack);
        assertThat(fundsavailabilityBack.getFundsmanagement()).isEqualTo(fundsmanagement);

        fundsmanagement.fundsavailability(null);
        assertThat(fundsmanagement.getFundsavailability()).isNull();
        assertThat(fundsavailabilityBack.getFundsmanagement()).isNull();
    }
}
