package com.cvicse.domain;

import static com.cvicse.domain.AnnualplanTestSamples.*;
import static com.cvicse.domain.CycleplanTestSamples.*;
import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.MonthplanTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectchargeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CycleplanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cycleplan.class);
        Cycleplan cycleplan1 = getCycleplanSample1();
        Cycleplan cycleplan2 = new Cycleplan();
        assertThat(cycleplan1).isNotEqualTo(cycleplan2);

        cycleplan2.setId(cycleplan1.getId());
        assertThat(cycleplan1).isEqualTo(cycleplan2);

        cycleplan2 = getCycleplanSample2();
        assertThat(cycleplan1).isNotEqualTo(cycleplan2);
    }

    @Test
    void documentTest() throws Exception {
        Cycleplan cycleplan = getCycleplanRandomSampleGenerator();
        Document documentBack = getDocumentRandomSampleGenerator();

        cycleplan.setDocument(documentBack);
        assertThat(cycleplan.getDocument()).isEqualTo(documentBack);

        cycleplan.document(null);
        assertThat(cycleplan.getDocument()).isNull();
    }

    @Test
    void annualplanTest() throws Exception {
        Cycleplan cycleplan = getCycleplanRandomSampleGenerator();
        Annualplan annualplanBack = getAnnualplanRandomSampleGenerator();

        cycleplan.setAnnualplan(annualplanBack);
        assertThat(cycleplan.getAnnualplan()).isEqualTo(annualplanBack);

        cycleplan.annualplan(null);
        assertThat(cycleplan.getAnnualplan()).isNull();
    }

    @Test
    void monthplanTest() throws Exception {
        Cycleplan cycleplan = getCycleplanRandomSampleGenerator();
        Monthplan monthplanBack = getMonthplanRandomSampleGenerator();

        cycleplan.setMonthplan(monthplanBack);
        assertThat(cycleplan.getMonthplan()).isEqualTo(monthplanBack);

        cycleplan.monthplan(null);
        assertThat(cycleplan.getMonthplan()).isNull();
    }

    @Test
    void projectchargeTest() throws Exception {
        Cycleplan cycleplan = getCycleplanRandomSampleGenerator();
        Projectcharge projectchargeBack = getProjectchargeRandomSampleGenerator();

        cycleplan.setProjectcharge(projectchargeBack);
        assertThat(cycleplan.getProjectcharge()).isEqualTo(projectchargeBack);

        cycleplan.projectcharge(null);
        assertThat(cycleplan.getProjectcharge()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Cycleplan cycleplan = getCycleplanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        cycleplan.setResponsibleid(officersBack);
        assertThat(cycleplan.getResponsibleid()).isEqualTo(officersBack);

        cycleplan.responsibleid(null);
        assertThat(cycleplan.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Cycleplan cycleplan = getCycleplanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        cycleplan.setAuditorid(officersBack);
        assertThat(cycleplan.getAuditorid()).isEqualTo(officersBack);

        cycleplan.auditorid(null);
        assertThat(cycleplan.getAuditorid()).isNull();
    }
}
