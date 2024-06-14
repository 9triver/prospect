package com.cvicse.domain;

import static com.cvicse.domain.ComprehensivecontrolTestSamples.*;
import static com.cvicse.domain.FundsmanagementTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.UnitbudgetTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UnitbudgetTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Unitbudget.class);
        Unitbudget unitbudget1 = getUnitbudgetSample1();
        Unitbudget unitbudget2 = new Unitbudget();
        assertThat(unitbudget1).isNotEqualTo(unitbudget2);

        unitbudget2.setId(unitbudget1.getId());
        assertThat(unitbudget1).isEqualTo(unitbudget2);

        unitbudget2 = getUnitbudgetSample2();
        assertThat(unitbudget1).isNotEqualTo(unitbudget2);
    }

    @Test
    void creatoridTest() throws Exception {
        Unitbudget unitbudget = getUnitbudgetRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        unitbudget.setCreatorid(officersBack);
        assertThat(unitbudget.getCreatorid()).isEqualTo(officersBack);

        unitbudget.creatorid(null);
        assertThat(unitbudget.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Unitbudget unitbudget = getUnitbudgetRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        unitbudget.setAuditorid(officersBack);
        assertThat(unitbudget.getAuditorid()).isEqualTo(officersBack);

        unitbudget.auditorid(null);
        assertThat(unitbudget.getAuditorid()).isNull();
    }

    @Test
    void comprehensivecontrolTest() throws Exception {
        Unitbudget unitbudget = getUnitbudgetRandomSampleGenerator();
        Comprehensivecontrol comprehensivecontrolBack = getComprehensivecontrolRandomSampleGenerator();

        unitbudget.setComprehensivecontrol(comprehensivecontrolBack);
        assertThat(unitbudget.getComprehensivecontrol()).isEqualTo(comprehensivecontrolBack);
        assertThat(comprehensivecontrolBack.getUnitbudget()).isEqualTo(unitbudget);

        unitbudget.comprehensivecontrol(null);
        assertThat(unitbudget.getComprehensivecontrol()).isNull();
        assertThat(comprehensivecontrolBack.getUnitbudget()).isNull();
    }

    @Test
    void fundsmanagementTest() throws Exception {
        Unitbudget unitbudget = getUnitbudgetRandomSampleGenerator();
        Fundsmanagement fundsmanagementBack = getFundsmanagementRandomSampleGenerator();

        unitbudget.setFundsmanagement(fundsmanagementBack);
        assertThat(unitbudget.getFundsmanagement()).isEqualTo(fundsmanagementBack);
        assertThat(fundsmanagementBack.getUnitbudget()).isEqualTo(unitbudget);

        unitbudget.fundsmanagement(null);
        assertThat(unitbudget.getFundsmanagement()).isNull();
        assertThat(fundsmanagementBack.getUnitbudget()).isNull();
    }
}
