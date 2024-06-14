package com.cvicse.domain;

import static com.cvicse.domain.AnnualplanTestSamples.*;
import static com.cvicse.domain.CycleplanTestSamples.*;
import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.MonthplanTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PlanexecuteTestSamples.*;
import static com.cvicse.domain.ProjectchargeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MonthplanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Monthplan.class);
        Monthplan monthplan1 = getMonthplanSample1();
        Monthplan monthplan2 = new Monthplan();
        assertThat(monthplan1).isNotEqualTo(monthplan2);

        monthplan2.setId(monthplan1.getId());
        assertThat(monthplan1).isEqualTo(monthplan2);

        monthplan2 = getMonthplanSample2();
        assertThat(monthplan1).isNotEqualTo(monthplan2);
    }

    @Test
    void documentTest() throws Exception {
        Monthplan monthplan = getMonthplanRandomSampleGenerator();
        Document documentBack = getDocumentRandomSampleGenerator();

        monthplan.setDocument(documentBack);
        assertThat(monthplan.getDocument()).isEqualTo(documentBack);

        monthplan.document(null);
        assertThat(monthplan.getDocument()).isNull();
    }

    @Test
    void planreturnsTest() throws Exception {
        Monthplan monthplan = getMonthplanRandomSampleGenerator();
        Planexecute planexecuteBack = getPlanexecuteRandomSampleGenerator();

        monthplan.setPlanreturns(planexecuteBack);
        assertThat(monthplan.getPlanreturns()).isEqualTo(planexecuteBack);

        monthplan.planreturns(null);
        assertThat(monthplan.getPlanreturns()).isNull();
    }

    @Test
    void projectchargeTest() throws Exception {
        Monthplan monthplan = getMonthplanRandomSampleGenerator();
        Projectcharge projectchargeBack = getProjectchargeRandomSampleGenerator();

        monthplan.setProjectcharge(projectchargeBack);
        assertThat(monthplan.getProjectcharge()).isEqualTo(projectchargeBack);

        monthplan.projectcharge(null);
        assertThat(monthplan.getProjectcharge()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        Monthplan monthplan = getMonthplanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        monthplan.setCreatorid(officersBack);
        assertThat(monthplan.getCreatorid()).isEqualTo(officersBack);

        monthplan.creatorid(null);
        assertThat(monthplan.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Monthplan monthplan = getMonthplanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        monthplan.setAuditorid(officersBack);
        assertThat(monthplan.getAuditorid()).isEqualTo(officersBack);

        monthplan.auditorid(null);
        assertThat(monthplan.getAuditorid()).isNull();
    }

    @Test
    void cycleplanTest() throws Exception {
        Monthplan monthplan = getMonthplanRandomSampleGenerator();
        Cycleplan cycleplanBack = getCycleplanRandomSampleGenerator();

        monthplan.setCycleplan(cycleplanBack);
        assertThat(monthplan.getCycleplan()).isEqualTo(cycleplanBack);
        assertThat(cycleplanBack.getMonthplan()).isEqualTo(monthplan);

        monthplan.cycleplan(null);
        assertThat(monthplan.getCycleplan()).isNull();
        assertThat(cycleplanBack.getMonthplan()).isNull();
    }

    @Test
    void annualplanTest() throws Exception {
        Monthplan monthplan = getMonthplanRandomSampleGenerator();
        Annualplan annualplanBack = getAnnualplanRandomSampleGenerator();

        monthplan.setAnnualplan(annualplanBack);
        assertThat(monthplan.getAnnualplan()).isEqualTo(annualplanBack);
        assertThat(annualplanBack.getMonthplan()).isEqualTo(monthplan);

        monthplan.annualplan(null);
        assertThat(monthplan.getAnnualplan()).isNull();
        assertThat(annualplanBack.getMonthplan()).isNull();
    }
}
