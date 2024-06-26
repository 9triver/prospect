package com.cvicse.domain;

import static com.cvicse.domain.AnnualplanTestSamples.*;
import static com.cvicse.domain.ComprehensivecontrolTestSamples.*;
import static com.cvicse.domain.ComprehensiveledgerTestSamples.*;
import static com.cvicse.domain.CycleplanTestSamples.*;
import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.IntegratedmanagementTestSamples.*;
import static com.cvicse.domain.IntegratedmanagementWbsTestSamples.*;
import static com.cvicse.domain.MonthplanTestSamples.*;
import static com.cvicse.domain.PlanexecuteTestSamples.*;
import static com.cvicse.domain.PlanmonitorTestSamples.*;
import static com.cvicse.domain.PlanreturnsTestSamples.*;
import static com.cvicse.domain.PlanstrategyTestSamples.*;
import static com.cvicse.domain.ProjectchargeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class IntegratedmanagementWbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IntegratedmanagementWbs.class);
        IntegratedmanagementWbs integratedmanagementWbs1 = getIntegratedmanagementWbsSample1();
        IntegratedmanagementWbs integratedmanagementWbs2 = new IntegratedmanagementWbs();
        assertThat(integratedmanagementWbs1).isNotEqualTo(integratedmanagementWbs2);

        integratedmanagementWbs2.setId(integratedmanagementWbs1.getId());
        assertThat(integratedmanagementWbs1).isEqualTo(integratedmanagementWbs2);

        integratedmanagementWbs2 = getIntegratedmanagementWbsSample2();
        assertThat(integratedmanagementWbs1).isNotEqualTo(integratedmanagementWbs2);
    }

    @Test
    void planstrategyTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Planstrategy planstrategyBack = getPlanstrategyRandomSampleGenerator();

        integratedmanagementWbs.setPlanstrategy(planstrategyBack);
        assertThat(integratedmanagementWbs.getPlanstrategy()).isEqualTo(planstrategyBack);

        integratedmanagementWbs.planstrategy(null);
        assertThat(integratedmanagementWbs.getPlanstrategy()).isNull();
    }

    @Test
    void comprehensivecontrolTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Comprehensivecontrol comprehensivecontrolBack = getComprehensivecontrolRandomSampleGenerator();

        integratedmanagementWbs.setComprehensivecontrol(comprehensivecontrolBack);
        assertThat(integratedmanagementWbs.getComprehensivecontrol()).isEqualTo(comprehensivecontrolBack);

        integratedmanagementWbs.comprehensivecontrol(null);
        assertThat(integratedmanagementWbs.getComprehensivecontrol()).isNull();
    }

    @Test
    void documentTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Document documentBack = getDocumentRandomSampleGenerator();

        integratedmanagementWbs.setDocument(documentBack);
        assertThat(integratedmanagementWbs.getDocument()).isEqualTo(documentBack);

        integratedmanagementWbs.document(null);
        assertThat(integratedmanagementWbs.getDocument()).isNull();
    }

    @Test
    void comprehensiveledgerTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Comprehensiveledger comprehensiveledgerBack = getComprehensiveledgerRandomSampleGenerator();

        integratedmanagementWbs.setComprehensiveledger(comprehensiveledgerBack);
        assertThat(integratedmanagementWbs.getComprehensiveledger()).isEqualTo(comprehensiveledgerBack);

        integratedmanagementWbs.comprehensiveledger(null);
        assertThat(integratedmanagementWbs.getComprehensiveledger()).isNull();
    }

    @Test
    void cycleplanTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Cycleplan cycleplanBack = getCycleplanRandomSampleGenerator();

        integratedmanagementWbs.setCycleplan(cycleplanBack);
        assertThat(integratedmanagementWbs.getCycleplan()).isEqualTo(cycleplanBack);

        integratedmanagementWbs.cycleplan(null);
        assertThat(integratedmanagementWbs.getCycleplan()).isNull();
    }

    @Test
    void annualplanTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Annualplan annualplanBack = getAnnualplanRandomSampleGenerator();

        integratedmanagementWbs.setAnnualplan(annualplanBack);
        assertThat(integratedmanagementWbs.getAnnualplan()).isEqualTo(annualplanBack);

        integratedmanagementWbs.annualplan(null);
        assertThat(integratedmanagementWbs.getAnnualplan()).isNull();
    }

    @Test
    void monthplanTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Monthplan monthplanBack = getMonthplanRandomSampleGenerator();

        integratedmanagementWbs.setMonthplan(monthplanBack);
        assertThat(integratedmanagementWbs.getMonthplan()).isEqualTo(monthplanBack);

        integratedmanagementWbs.monthplan(null);
        assertThat(integratedmanagementWbs.getMonthplan()).isNull();
    }

    @Test
    void planreturnsTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Planreturns planreturnsBack = getPlanreturnsRandomSampleGenerator();

        integratedmanagementWbs.setPlanreturns(planreturnsBack);
        assertThat(integratedmanagementWbs.getPlanreturns()).isEqualTo(planreturnsBack);

        integratedmanagementWbs.planreturns(null);
        assertThat(integratedmanagementWbs.getPlanreturns()).isNull();
    }

    @Test
    void planmonitorTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Planmonitor planmonitorBack = getPlanmonitorRandomSampleGenerator();

        integratedmanagementWbs.setPlanmonitor(planmonitorBack);
        assertThat(integratedmanagementWbs.getPlanmonitor()).isEqualTo(planmonitorBack);

        integratedmanagementWbs.planmonitor(null);
        assertThat(integratedmanagementWbs.getPlanmonitor()).isNull();
    }

    @Test
    void planexecuteTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Planexecute planexecuteBack = getPlanexecuteRandomSampleGenerator();

        integratedmanagementWbs.setPlanexecute(planexecuteBack);
        assertThat(integratedmanagementWbs.getPlanexecute()).isEqualTo(planexecuteBack);

        integratedmanagementWbs.planexecute(null);
        assertThat(integratedmanagementWbs.getPlanexecute()).isNull();
    }

    @Test
    void projectchargeTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Projectcharge projectchargeBack = getProjectchargeRandomSampleGenerator();

        integratedmanagementWbs.setProjectcharge(projectchargeBack);
        assertThat(integratedmanagementWbs.getProjectcharge()).isEqualTo(projectchargeBack);

        integratedmanagementWbs.projectcharge(null);
        assertThat(integratedmanagementWbs.getProjectcharge()).isNull();
    }

    @Test
    void integratedmanagementTest() throws Exception {
        IntegratedmanagementWbs integratedmanagementWbs = getIntegratedmanagementWbsRandomSampleGenerator();
        Integratedmanagement integratedmanagementBack = getIntegratedmanagementRandomSampleGenerator();

        integratedmanagementWbs.setIntegratedmanagement(integratedmanagementBack);
        assertThat(integratedmanagementWbs.getIntegratedmanagement()).isEqualTo(integratedmanagementBack);
        assertThat(integratedmanagementBack.getWbs()).isEqualTo(integratedmanagementWbs);

        integratedmanagementWbs.integratedmanagement(null);
        assertThat(integratedmanagementWbs.getIntegratedmanagement()).isNull();
        assertThat(integratedmanagementBack.getWbs()).isNull();
    }
}
