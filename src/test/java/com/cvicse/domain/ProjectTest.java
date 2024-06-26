package com.cvicse.domain;

import static com.cvicse.domain.AnnualSecurityPlanTestSamples.*;
import static com.cvicse.domain.ComprehensivecontrolTestSamples.*;
import static com.cvicse.domain.HumanresourcesTestSamples.*;
import static com.cvicse.domain.ManagementCapacityEvaluationTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.OutsourcingPurchasePlanTestSamples.*;
import static com.cvicse.domain.ProjectHumanresourcesplanTestSamples.*;
import static com.cvicse.domain.ProjectSecrecyTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.ProjectremitTestSamples.*;
import static com.cvicse.domain.ProjectwbsTestSamples.*;
import static com.cvicse.domain.WbsmanageTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProjectTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project.class);
        Project project1 = getProjectSample1();
        Project project2 = new Project();
        assertThat(project1).isNotEqualTo(project2);

        project2.setId(project1.getId());
        assertThat(project1).isEqualTo(project2);

        project2 = getProjectSample2();
        assertThat(project1).isNotEqualTo(project2);
    }

    @Test
    void projectwbsTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        project.setProjectwbs(projectwbsBack);
        assertThat(project.getProjectwbs()).isEqualTo(projectwbsBack);

        project.projectwbs(null);
        assertThat(project.getProjectwbs()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        project.setResponsibleid(officersBack);
        assertThat(project.getResponsibleid()).isEqualTo(officersBack);

        project.responsibleid(null);
        assertThat(project.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        project.setAuditorid(officersBack);
        assertThat(project.getAuditorid()).isEqualTo(officersBack);

        project.auditorid(null);
        assertThat(project.getAuditorid()).isNull();
    }

    @Test
    void projectSecrecyTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        ProjectSecrecy projectSecrecyBack = getProjectSecrecyRandomSampleGenerator();

        project.setProjectSecrecy(projectSecrecyBack);
        assertThat(project.getProjectSecrecy()).isEqualTo(projectSecrecyBack);

        project.projectSecrecy(null);
        assertThat(project.getProjectSecrecy()).isNull();
    }

    @Test
    void comprehensivecontrolTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Comprehensivecontrol comprehensivecontrolBack = getComprehensivecontrolRandomSampleGenerator();

        project.setComprehensivecontrol(comprehensivecontrolBack);
        assertThat(project.getComprehensivecontrol()).isEqualTo(comprehensivecontrolBack);
        assertThat(comprehensivecontrolBack.getProject()).isEqualTo(project);

        project.comprehensivecontrol(null);
        assertThat(project.getComprehensivecontrol()).isNull();
        assertThat(comprehensivecontrolBack.getProject()).isNull();
    }

    @Test
    void wbsmanageTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Wbsmanage wbsmanageBack = getWbsmanageRandomSampleGenerator();

        project.setWbsmanage(wbsmanageBack);
        assertThat(project.getWbsmanage()).isEqualTo(wbsmanageBack);
        assertThat(wbsmanageBack.getProject()).isEqualTo(project);

        project.wbsmanage(null);
        assertThat(project.getWbsmanage()).isNull();
        assertThat(wbsmanageBack.getProject()).isNull();
    }

    @Test
    void outsourcingPurchasePlanTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        OutsourcingPurchasePlan outsourcingPurchasePlanBack = getOutsourcingPurchasePlanRandomSampleGenerator();

        project.setOutsourcingPurchasePlan(outsourcingPurchasePlanBack);
        assertThat(project.getOutsourcingPurchasePlan()).isEqualTo(outsourcingPurchasePlanBack);
        assertThat(outsourcingPurchasePlanBack.getProject()).isEqualTo(project);

        project.outsourcingPurchasePlan(null);
        assertThat(project.getOutsourcingPurchasePlan()).isNull();
        assertThat(outsourcingPurchasePlanBack.getProject()).isNull();
    }

    @Test
    void projectHumanresourcesplanTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        ProjectHumanresourcesplan projectHumanresourcesplanBack = getProjectHumanresourcesplanRandomSampleGenerator();

        project.setProjectHumanresourcesplan(projectHumanresourcesplanBack);
        assertThat(project.getProjectHumanresourcesplan()).isEqualTo(projectHumanresourcesplanBack);
        assertThat(projectHumanresourcesplanBack.getProject()).isEqualTo(project);

        project.projectHumanresourcesplan(null);
        assertThat(project.getProjectHumanresourcesplan()).isNull();
        assertThat(projectHumanresourcesplanBack.getProject()).isNull();
    }

    @Test
    void projectremitTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Projectremit projectremitBack = getProjectremitRandomSampleGenerator();

        project.setProjectremit(projectremitBack);
        assertThat(project.getProjectremit()).isEqualTo(projectremitBack);
        assertThat(projectremitBack.getProject()).isEqualTo(project);

        project.projectremit(null);
        assertThat(project.getProjectremit()).isNull();
        assertThat(projectremitBack.getProject()).isNull();
    }

    @Test
    void humanresourcesTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Humanresources humanresourcesBack = getHumanresourcesRandomSampleGenerator();

        project.setHumanresources(humanresourcesBack);
        assertThat(project.getHumanresources()).isEqualTo(humanresourcesBack);
        assertThat(humanresourcesBack.getProject()).isEqualTo(project);

        project.humanresources(null);
        assertThat(project.getHumanresources()).isNull();
        assertThat(humanresourcesBack.getProject()).isNull();
    }

    @Test
    void annualSecurityPlanTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        AnnualSecurityPlan annualSecurityPlanBack = getAnnualSecurityPlanRandomSampleGenerator();

        project.setAnnualSecurityPlan(annualSecurityPlanBack);
        assertThat(project.getAnnualSecurityPlan()).isEqualTo(annualSecurityPlanBack);
        assertThat(annualSecurityPlanBack.getProject()).isEqualTo(project);

        project.annualSecurityPlan(null);
        assertThat(project.getAnnualSecurityPlan()).isNull();
        assertThat(annualSecurityPlanBack.getProject()).isNull();
    }

    @Test
    void managementCapacityEvaluationTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        ManagementCapacityEvaluation managementCapacityEvaluationBack = getManagementCapacityEvaluationRandomSampleGenerator();

        project.setManagementCapacityEvaluation(managementCapacityEvaluationBack);
        assertThat(project.getManagementCapacityEvaluation()).isEqualTo(managementCapacityEvaluationBack);
        assertThat(managementCapacityEvaluationBack.getProject()).isEqualTo(project);

        project.managementCapacityEvaluation(null);
        assertThat(project.getManagementCapacityEvaluation()).isNull();
        assertThat(managementCapacityEvaluationBack.getProject()).isNull();
    }
}
