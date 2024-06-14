package com.cvicse.domain;

import static com.cvicse.domain.ComprehensivecontrolTestSamples.*;
import static com.cvicse.domain.DepartmentTestSamples.*;
import static com.cvicse.domain.FundsmanagementTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProgressmanagementTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.TotalbudgetTestSamples.*;
import static com.cvicse.domain.UnitbudgetTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ComprehensivecontrolTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comprehensivecontrol.class);
        Comprehensivecontrol comprehensivecontrol1 = getComprehensivecontrolSample1();
        Comprehensivecontrol comprehensivecontrol2 = new Comprehensivecontrol();
        assertThat(comprehensivecontrol1).isNotEqualTo(comprehensivecontrol2);

        comprehensivecontrol2.setId(comprehensivecontrol1.getId());
        assertThat(comprehensivecontrol1).isEqualTo(comprehensivecontrol2);

        comprehensivecontrol2 = getComprehensivecontrolSample2();
        assertThat(comprehensivecontrol1).isNotEqualTo(comprehensivecontrol2);
    }

    @Test
    void progressTest() throws Exception {
        Comprehensivecontrol comprehensivecontrol = getComprehensivecontrolRandomSampleGenerator();
        Progressmanagement progressmanagementBack = getProgressmanagementRandomSampleGenerator();

        comprehensivecontrol.setProgress(progressmanagementBack);
        assertThat(comprehensivecontrol.getProgress()).isEqualTo(progressmanagementBack);

        comprehensivecontrol.progress(null);
        assertThat(comprehensivecontrol.getProgress()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        Comprehensivecontrol comprehensivecontrol = getComprehensivecontrolRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        comprehensivecontrol.setProject(projectBack);
        assertThat(comprehensivecontrol.getProject()).isEqualTo(projectBack);

        comprehensivecontrol.project(null);
        assertThat(comprehensivecontrol.getProject()).isNull();
    }

    @Test
    void fundsTest() throws Exception {
        Comprehensivecontrol comprehensivecontrol = getComprehensivecontrolRandomSampleGenerator();
        Fundsmanagement fundsmanagementBack = getFundsmanagementRandomSampleGenerator();

        comprehensivecontrol.setFunds(fundsmanagementBack);
        assertThat(comprehensivecontrol.getFunds()).isEqualTo(fundsmanagementBack);

        comprehensivecontrol.funds(null);
        assertThat(comprehensivecontrol.getFunds()).isNull();
    }

    @Test
    void totalbudgetTest() throws Exception {
        Comprehensivecontrol comprehensivecontrol = getComprehensivecontrolRandomSampleGenerator();
        Totalbudget totalbudgetBack = getTotalbudgetRandomSampleGenerator();

        comprehensivecontrol.setTotalbudget(totalbudgetBack);
        assertThat(comprehensivecontrol.getTotalbudget()).isEqualTo(totalbudgetBack);

        comprehensivecontrol.totalbudget(null);
        assertThat(comprehensivecontrol.getTotalbudget()).isNull();
    }

    @Test
    void unitbudgetTest() throws Exception {
        Comprehensivecontrol comprehensivecontrol = getComprehensivecontrolRandomSampleGenerator();
        Unitbudget unitbudgetBack = getUnitbudgetRandomSampleGenerator();

        comprehensivecontrol.setUnitbudget(unitbudgetBack);
        assertThat(comprehensivecontrol.getUnitbudget()).isEqualTo(unitbudgetBack);

        comprehensivecontrol.unitbudget(null);
        assertThat(comprehensivecontrol.getUnitbudget()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Comprehensivecontrol comprehensivecontrol = getComprehensivecontrolRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        comprehensivecontrol.setResponsibleid(officersBack);
        assertThat(comprehensivecontrol.getResponsibleid()).isEqualTo(officersBack);

        comprehensivecontrol.responsibleid(null);
        assertThat(comprehensivecontrol.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Comprehensivecontrol comprehensivecontrol = getComprehensivecontrolRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        comprehensivecontrol.setAuditorid(officersBack);
        assertThat(comprehensivecontrol.getAuditorid()).isEqualTo(officersBack);

        comprehensivecontrol.auditorid(null);
        assertThat(comprehensivecontrol.getAuditorid()).isNull();
    }

    @Test
    void decumentTest() throws Exception {
        Comprehensivecontrol comprehensivecontrol = getComprehensivecontrolRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        comprehensivecontrol.setDecument(departmentBack);
        assertThat(comprehensivecontrol.getDecument()).isEqualTo(departmentBack);

        comprehensivecontrol.decument(null);
        assertThat(comprehensivecontrol.getDecument()).isNull();
    }

    @Test
    void coordinationdepartmentTest() throws Exception {
        Comprehensivecontrol comprehensivecontrol = getComprehensivecontrolRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        comprehensivecontrol.setCoordinationdepartment(departmentBack);
        assertThat(comprehensivecontrol.getCoordinationdepartment()).isEqualTo(departmentBack);

        comprehensivecontrol.coordinationdepartment(null);
        assertThat(comprehensivecontrol.getCoordinationdepartment()).isNull();
    }
}
