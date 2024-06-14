package com.cvicse.domain;

import static com.cvicse.domain.AnnualplanTestSamples.*;
import static com.cvicse.domain.CycleplanTestSamples.*;
import static com.cvicse.domain.MonthplanTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PbsbaselineTestSamples.*;
import static com.cvicse.domain.ProjectchargeTestSamples.*;
import static com.cvicse.domain.WbsbaselineTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProjectchargeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectcharge.class);
        Projectcharge projectcharge1 = getProjectchargeSample1();
        Projectcharge projectcharge2 = new Projectcharge();
        assertThat(projectcharge1).isNotEqualTo(projectcharge2);

        projectcharge2.setId(projectcharge1.getId());
        assertThat(projectcharge1).isEqualTo(projectcharge2);

        projectcharge2 = getProjectchargeSample2();
        assertThat(projectcharge1).isNotEqualTo(projectcharge2);
    }

    @Test
    void creatoridTest() throws Exception {
        Projectcharge projectcharge = getProjectchargeRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectcharge.setCreatorid(officersBack);
        assertThat(projectcharge.getCreatorid()).isEqualTo(officersBack);

        projectcharge.creatorid(null);
        assertThat(projectcharge.getCreatorid()).isNull();
    }

    @Test
    void cycleplanTest() throws Exception {
        Projectcharge projectcharge = getProjectchargeRandomSampleGenerator();
        Cycleplan cycleplanBack = getCycleplanRandomSampleGenerator();

        projectcharge.setCycleplan(cycleplanBack);
        assertThat(projectcharge.getCycleplan()).isEqualTo(cycleplanBack);
        assertThat(cycleplanBack.getProjectcharge()).isEqualTo(projectcharge);

        projectcharge.cycleplan(null);
        assertThat(projectcharge.getCycleplan()).isNull();
        assertThat(cycleplanBack.getProjectcharge()).isNull();
    }

    @Test
    void annualplanTest() throws Exception {
        Projectcharge projectcharge = getProjectchargeRandomSampleGenerator();
        Annualplan annualplanBack = getAnnualplanRandomSampleGenerator();

        projectcharge.setAnnualplan(annualplanBack);
        assertThat(projectcharge.getAnnualplan()).isEqualTo(annualplanBack);
        assertThat(annualplanBack.getProjectcharge()).isEqualTo(projectcharge);

        projectcharge.annualplan(null);
        assertThat(projectcharge.getAnnualplan()).isNull();
        assertThat(annualplanBack.getProjectcharge()).isNull();
    }

    @Test
    void monthplanTest() throws Exception {
        Projectcharge projectcharge = getProjectchargeRandomSampleGenerator();
        Monthplan monthplanBack = getMonthplanRandomSampleGenerator();

        projectcharge.setMonthplan(monthplanBack);
        assertThat(projectcharge.getMonthplan()).isEqualTo(monthplanBack);
        assertThat(monthplanBack.getProjectcharge()).isEqualTo(projectcharge);

        projectcharge.monthplan(null);
        assertThat(projectcharge.getMonthplan()).isNull();
        assertThat(monthplanBack.getProjectcharge()).isNull();
    }

    @Test
    void pbsbaselineTest() throws Exception {
        Projectcharge projectcharge = getProjectchargeRandomSampleGenerator();
        Pbsbaseline pbsbaselineBack = getPbsbaselineRandomSampleGenerator();

        projectcharge.setPbsbaseline(pbsbaselineBack);
        assertThat(projectcharge.getPbsbaseline()).isEqualTo(pbsbaselineBack);
        assertThat(pbsbaselineBack.getProjectcharge()).isEqualTo(projectcharge);

        projectcharge.pbsbaseline(null);
        assertThat(projectcharge.getPbsbaseline()).isNull();
        assertThat(pbsbaselineBack.getProjectcharge()).isNull();
    }

    @Test
    void wbsbaselineTest() throws Exception {
        Projectcharge projectcharge = getProjectchargeRandomSampleGenerator();
        Wbsbaseline wbsbaselineBack = getWbsbaselineRandomSampleGenerator();

        projectcharge.setWbsbaseline(wbsbaselineBack);
        assertThat(projectcharge.getWbsbaseline()).isEqualTo(wbsbaselineBack);
        assertThat(wbsbaselineBack.getProjectcharge()).isEqualTo(projectcharge);

        projectcharge.wbsbaseline(null);
        assertThat(projectcharge.getWbsbaseline()).isNull();
        assertThat(wbsbaselineBack.getProjectcharge()).isNull();
    }
}
