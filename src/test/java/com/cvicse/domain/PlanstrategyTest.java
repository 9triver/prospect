package com.cvicse.domain;

import static com.cvicse.domain.DepartmentTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PlanstrategyTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PlanstrategyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Planstrategy.class);
        Planstrategy planstrategy1 = getPlanstrategySample1();
        Planstrategy planstrategy2 = new Planstrategy();
        assertThat(planstrategy1).isNotEqualTo(planstrategy2);

        planstrategy2.setId(planstrategy1.getId());
        assertThat(planstrategy1).isEqualTo(planstrategy2);

        planstrategy2 = getPlanstrategySample2();
        assertThat(planstrategy1).isNotEqualTo(planstrategy2);
    }

    @Test
    void decumentTest() throws Exception {
        Planstrategy planstrategy = getPlanstrategyRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        planstrategy.setDecument(departmentBack);
        assertThat(planstrategy.getDecument()).isEqualTo(departmentBack);

        planstrategy.decument(null);
        assertThat(planstrategy.getDecument()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Planstrategy planstrategy = getPlanstrategyRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        planstrategy.setResponsibleid(officersBack);
        assertThat(planstrategy.getResponsibleid()).isEqualTo(officersBack);

        planstrategy.responsibleid(null);
        assertThat(planstrategy.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Planstrategy planstrategy = getPlanstrategyRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        planstrategy.setAuditorid(officersBack);
        assertThat(planstrategy.getAuditorid()).isEqualTo(officersBack);

        planstrategy.auditorid(null);
        assertThat(planstrategy.getAuditorid()).isNull();
    }
}
