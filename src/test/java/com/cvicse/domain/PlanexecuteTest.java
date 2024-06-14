package com.cvicse.domain;

import static com.cvicse.domain.MonthplanTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PlanexecuteTestSamples.*;
import static com.cvicse.domain.PlanreturnsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PlanexecuteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Planexecute.class);
        Planexecute planexecute1 = getPlanexecuteSample1();
        Planexecute planexecute2 = new Planexecute();
        assertThat(planexecute1).isNotEqualTo(planexecute2);

        planexecute2.setId(planexecute1.getId());
        assertThat(planexecute1).isEqualTo(planexecute2);

        planexecute2 = getPlanexecuteSample2();
        assertThat(planexecute1).isNotEqualTo(planexecute2);
    }

    @Test
    void planreturnsTest() throws Exception {
        Planexecute planexecute = getPlanexecuteRandomSampleGenerator();
        Planreturns planreturnsBack = getPlanreturnsRandomSampleGenerator();

        planexecute.setPlanreturns(planreturnsBack);
        assertThat(planexecute.getPlanreturns()).isEqualTo(planreturnsBack);

        planexecute.planreturns(null);
        assertThat(planexecute.getPlanreturns()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Planexecute planexecute = getPlanexecuteRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        planexecute.setResponsibleid(officersBack);
        assertThat(planexecute.getResponsibleid()).isEqualTo(officersBack);

        planexecute.responsibleid(null);
        assertThat(planexecute.getResponsibleid()).isNull();
    }

    @Test
    void monthplanTest() throws Exception {
        Planexecute planexecute = getPlanexecuteRandomSampleGenerator();
        Monthplan monthplanBack = getMonthplanRandomSampleGenerator();

        planexecute.setMonthplan(monthplanBack);
        assertThat(planexecute.getMonthplan()).isEqualTo(monthplanBack);
        assertThat(monthplanBack.getPlanreturns()).isEqualTo(planexecute);

        planexecute.monthplan(null);
        assertThat(planexecute.getMonthplan()).isNull();
        assertThat(monthplanBack.getPlanreturns()).isNull();
    }
}
