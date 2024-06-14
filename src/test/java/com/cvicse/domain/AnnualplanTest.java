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

class AnnualplanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Annualplan.class);
        Annualplan annualplan1 = getAnnualplanSample1();
        Annualplan annualplan2 = new Annualplan();
        assertThat(annualplan1).isNotEqualTo(annualplan2);

        annualplan2.setId(annualplan1.getId());
        assertThat(annualplan1).isEqualTo(annualplan2);

        annualplan2 = getAnnualplanSample2();
        assertThat(annualplan1).isNotEqualTo(annualplan2);
    }

    @Test
    void documentTest() throws Exception {
        Annualplan annualplan = getAnnualplanRandomSampleGenerator();
        Document documentBack = getDocumentRandomSampleGenerator();

        annualplan.setDocument(documentBack);
        assertThat(annualplan.getDocument()).isEqualTo(documentBack);

        annualplan.document(null);
        assertThat(annualplan.getDocument()).isNull();
    }

    @Test
    void monthplanTest() throws Exception {
        Annualplan annualplan = getAnnualplanRandomSampleGenerator();
        Monthplan monthplanBack = getMonthplanRandomSampleGenerator();

        annualplan.setMonthplan(monthplanBack);
        assertThat(annualplan.getMonthplan()).isEqualTo(monthplanBack);

        annualplan.monthplan(null);
        assertThat(annualplan.getMonthplan()).isNull();
    }

    @Test
    void projectchargeTest() throws Exception {
        Annualplan annualplan = getAnnualplanRandomSampleGenerator();
        Projectcharge projectchargeBack = getProjectchargeRandomSampleGenerator();

        annualplan.setProjectcharge(projectchargeBack);
        assertThat(annualplan.getProjectcharge()).isEqualTo(projectchargeBack);

        annualplan.projectcharge(null);
        assertThat(annualplan.getProjectcharge()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        Annualplan annualplan = getAnnualplanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        annualplan.setCreatorid(officersBack);
        assertThat(annualplan.getCreatorid()).isEqualTo(officersBack);

        annualplan.creatorid(null);
        assertThat(annualplan.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Annualplan annualplan = getAnnualplanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        annualplan.setAuditorid(officersBack);
        assertThat(annualplan.getAuditorid()).isEqualTo(officersBack);

        annualplan.auditorid(null);
        assertThat(annualplan.getAuditorid()).isNull();
    }

    @Test
    void cycleplanTest() throws Exception {
        Annualplan annualplan = getAnnualplanRandomSampleGenerator();
        Cycleplan cycleplanBack = getCycleplanRandomSampleGenerator();

        annualplan.setCycleplan(cycleplanBack);
        assertThat(annualplan.getCycleplan()).isEqualTo(cycleplanBack);
        assertThat(cycleplanBack.getAnnualplan()).isEqualTo(annualplan);

        annualplan.cycleplan(null);
        assertThat(annualplan.getCycleplan()).isNull();
        assertThat(cycleplanBack.getAnnualplan()).isNull();
    }
}
