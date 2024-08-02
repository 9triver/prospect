package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractCostBudgetTestSamples.*;
import static com.cvicse.jy1.domain.CostControlSystemTestSamples.*;
import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.FundsEstimationTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingContractualTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingPurchasePlanTestSamples.*;
import static com.cvicse.jy1.domain.ProgressPlanTestSamples.*;
import static com.cvicse.jy1.domain.ProjectRiskTestSamples.*;
import static com.cvicse.jy1.domain.ProjectTestSamples.*;
import static com.cvicse.jy1.domain.ProjectpbsTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.QualityObjectivesTestSamples.*;
import static com.cvicse.jy1.domain.TechnicalConditionTestSamples.*;
import static com.cvicse.jy1.domain.TechnicalTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProjectwbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectwbs.class);
        Projectwbs projectwbs1 = getProjectwbsSample1();
        Projectwbs projectwbs2 = new Projectwbs();
        assertThat(projectwbs1).isNotEqualTo(projectwbs2);

        projectwbs2.setId(projectwbs1.getId());
        assertThat(projectwbs1).isEqualTo(projectwbs2);

        projectwbs2 = getProjectwbsSample2();
        assertThat(projectwbs1).isNotEqualTo(projectwbs2);
    }

    @Test
    void responsiblepersonTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectwbs.setResponsibleperson(officersBack);
        assertThat(projectwbs.getResponsibleperson()).isEqualTo(officersBack);

        projectwbs.responsibleperson(null);
        assertThat(projectwbs.getResponsibleperson()).isNull();
    }

    @Test
    void technicaldirectorTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectwbs.setTechnicaldirector(officersBack);
        assertThat(projectwbs.getTechnicaldirector()).isEqualTo(officersBack);

        projectwbs.technicaldirector(null);
        assertThat(projectwbs.getTechnicaldirector()).isNull();
    }

    @Test
    void administrativedirectorTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectwbs.setAdministrativedirector(officersBack);
        assertThat(projectwbs.getAdministrativedirector()).isEqualTo(officersBack);

        projectwbs.administrativedirector(null);
        assertThat(projectwbs.getAdministrativedirector()).isNull();
    }

    @Test
    void knowingpeopleTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectwbs.setKnowingpeople(officersBack);
        assertThat(projectwbs.getKnowingpeople()).isEqualTo(officersBack);

        projectwbs.knowingpeople(null);
        assertThat(projectwbs.getKnowingpeople()).isNull();
    }

    @Test
    void auditoridTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectwbs.setAuditorid(officersBack);
        assertThat(projectwbs.getAuditorid()).isEqualTo(officersBack);

        projectwbs.auditorid(null);
        assertThat(projectwbs.getAuditorid()).isNull();
    }

    @Test
    void responsibledepartmentTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectwbs.setResponsibledepartment(departmentBack);
        assertThat(projectwbs.getResponsibledepartment()).isEqualTo(departmentBack);

        projectwbs.responsibledepartment(null);
        assertThat(projectwbs.getResponsibledepartment()).isNull();
    }

    @Test
    void relevantdepartmentTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectwbs.setRelevantdepartment(departmentBack);
        assertThat(projectwbs.getRelevantdepartment()).isEqualTo(departmentBack);

        projectwbs.relevantdepartment(null);
        assertThat(projectwbs.getRelevantdepartment()).isNull();
    }

    @Test
    void departmentTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectwbs.setDepartment(departmentBack);
        assertThat(projectwbs.getDepartment()).isEqualTo(departmentBack);

        projectwbs.department(null);
        assertThat(projectwbs.getDepartment()).isNull();
    }

    @Test
    void projectTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        projectwbs.addProject(projectBack);
        assertThat(projectwbs.getProjects()).containsOnly(projectBack);
        assertThat(projectBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeProject(projectBack);
        assertThat(projectwbs.getProjects()).doesNotContain(projectBack);
        assertThat(projectBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.projects(new HashSet<>(Set.of(projectBack)));
        assertThat(projectwbs.getProjects()).containsOnly(projectBack);
        assertThat(projectBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setProjects(new HashSet<>());
        assertThat(projectwbs.getProjects()).doesNotContain(projectBack);
        assertThat(projectBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void projectpbsTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Projectpbs projectpbsBack = getProjectpbsRandomSampleGenerator();

        projectwbs.addProjectpbs(projectpbsBack);
        assertThat(projectwbs.getProjectpbs()).containsOnly(projectpbsBack);
        assertThat(projectpbsBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeProjectpbs(projectpbsBack);
        assertThat(projectwbs.getProjectpbs()).doesNotContain(projectpbsBack);
        assertThat(projectpbsBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.projectpbs(new HashSet<>(Set.of(projectpbsBack)));
        assertThat(projectwbs.getProjectpbs()).containsOnly(projectpbsBack);
        assertThat(projectpbsBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setProjectpbs(new HashSet<>());
        assertThat(projectwbs.getProjectpbs()).doesNotContain(projectpbsBack);
        assertThat(projectpbsBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void progressPlanTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        ProgressPlan progressPlanBack = getProgressPlanRandomSampleGenerator();

        projectwbs.addProgressPlan(progressPlanBack);
        assertThat(projectwbs.getProgressPlans()).containsOnly(progressPlanBack);
        assertThat(progressPlanBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeProgressPlan(progressPlanBack);
        assertThat(projectwbs.getProgressPlans()).doesNotContain(progressPlanBack);
        assertThat(progressPlanBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.progressPlans(new HashSet<>(Set.of(progressPlanBack)));
        assertThat(projectwbs.getProgressPlans()).containsOnly(progressPlanBack);
        assertThat(progressPlanBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setProgressPlans(new HashSet<>());
        assertThat(projectwbs.getProgressPlans()).doesNotContain(progressPlanBack);
        assertThat(progressPlanBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void fundsEstimationTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        FundsEstimation fundsEstimationBack = getFundsEstimationRandomSampleGenerator();

        projectwbs.addFundsEstimation(fundsEstimationBack);
        assertThat(projectwbs.getFundsEstimations()).containsOnly(fundsEstimationBack);
        assertThat(fundsEstimationBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeFundsEstimation(fundsEstimationBack);
        assertThat(projectwbs.getFundsEstimations()).doesNotContain(fundsEstimationBack);
        assertThat(fundsEstimationBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.fundsEstimations(new HashSet<>(Set.of(fundsEstimationBack)));
        assertThat(projectwbs.getFundsEstimations()).containsOnly(fundsEstimationBack);
        assertThat(fundsEstimationBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setFundsEstimations(new HashSet<>());
        assertThat(projectwbs.getFundsEstimations()).doesNotContain(fundsEstimationBack);
        assertThat(fundsEstimationBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void contractCostBudgetTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        ContractCostBudget contractCostBudgetBack = getContractCostBudgetRandomSampleGenerator();

        projectwbs.addContractCostBudget(contractCostBudgetBack);
        assertThat(projectwbs.getContractCostBudgets()).containsOnly(contractCostBudgetBack);
        assertThat(contractCostBudgetBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeContractCostBudget(contractCostBudgetBack);
        assertThat(projectwbs.getContractCostBudgets()).doesNotContain(contractCostBudgetBack);
        assertThat(contractCostBudgetBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.contractCostBudgets(new HashSet<>(Set.of(contractCostBudgetBack)));
        assertThat(projectwbs.getContractCostBudgets()).containsOnly(contractCostBudgetBack);
        assertThat(contractCostBudgetBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setContractCostBudgets(new HashSet<>());
        assertThat(projectwbs.getContractCostBudgets()).doesNotContain(contractCostBudgetBack);
        assertThat(contractCostBudgetBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void costControlSystemTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        CostControlSystem costControlSystemBack = getCostControlSystemRandomSampleGenerator();

        projectwbs.addCostControlSystem(costControlSystemBack);
        assertThat(projectwbs.getCostControlSystems()).containsOnly(costControlSystemBack);
        assertThat(costControlSystemBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeCostControlSystem(costControlSystemBack);
        assertThat(projectwbs.getCostControlSystems()).doesNotContain(costControlSystemBack);
        assertThat(costControlSystemBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.costControlSystems(new HashSet<>(Set.of(costControlSystemBack)));
        assertThat(projectwbs.getCostControlSystems()).containsOnly(costControlSystemBack);
        assertThat(costControlSystemBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setCostControlSystems(new HashSet<>());
        assertThat(projectwbs.getCostControlSystems()).doesNotContain(costControlSystemBack);
        assertThat(costControlSystemBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void qualityObjectivesTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        QualityObjectives qualityObjectivesBack = getQualityObjectivesRandomSampleGenerator();

        projectwbs.addQualityObjectives(qualityObjectivesBack);
        assertThat(projectwbs.getQualityObjectives()).containsOnly(qualityObjectivesBack);
        assertThat(qualityObjectivesBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeQualityObjectives(qualityObjectivesBack);
        assertThat(projectwbs.getQualityObjectives()).doesNotContain(qualityObjectivesBack);
        assertThat(qualityObjectivesBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.qualityObjectives(new HashSet<>(Set.of(qualityObjectivesBack)));
        assertThat(projectwbs.getQualityObjectives()).containsOnly(qualityObjectivesBack);
        assertThat(qualityObjectivesBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setQualityObjectives(new HashSet<>());
        assertThat(projectwbs.getQualityObjectives()).doesNotContain(qualityObjectivesBack);
        assertThat(qualityObjectivesBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void outsourcingContractualTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        OutsourcingContractual outsourcingContractualBack = getOutsourcingContractualRandomSampleGenerator();

        projectwbs.addOutsourcingContractual(outsourcingContractualBack);
        assertThat(projectwbs.getOutsourcingContractuals()).containsOnly(outsourcingContractualBack);
        assertThat(outsourcingContractualBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeOutsourcingContractual(outsourcingContractualBack);
        assertThat(projectwbs.getOutsourcingContractuals()).doesNotContain(outsourcingContractualBack);
        assertThat(outsourcingContractualBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.outsourcingContractuals(new HashSet<>(Set.of(outsourcingContractualBack)));
        assertThat(projectwbs.getOutsourcingContractuals()).containsOnly(outsourcingContractualBack);
        assertThat(outsourcingContractualBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setOutsourcingContractuals(new HashSet<>());
        assertThat(projectwbs.getOutsourcingContractuals()).doesNotContain(outsourcingContractualBack);
        assertThat(outsourcingContractualBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void outsourcingPurchasePlanTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        OutsourcingPurchasePlan outsourcingPurchasePlanBack = getOutsourcingPurchasePlanRandomSampleGenerator();

        projectwbs.addOutsourcingPurchasePlan(outsourcingPurchasePlanBack);
        assertThat(projectwbs.getOutsourcingPurchasePlans()).containsOnly(outsourcingPurchasePlanBack);
        assertThat(outsourcingPurchasePlanBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeOutsourcingPurchasePlan(outsourcingPurchasePlanBack);
        assertThat(projectwbs.getOutsourcingPurchasePlans()).doesNotContain(outsourcingPurchasePlanBack);
        assertThat(outsourcingPurchasePlanBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.outsourcingPurchasePlans(new HashSet<>(Set.of(outsourcingPurchasePlanBack)));
        assertThat(projectwbs.getOutsourcingPurchasePlans()).containsOnly(outsourcingPurchasePlanBack);
        assertThat(outsourcingPurchasePlanBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setOutsourcingPurchasePlans(new HashSet<>());
        assertThat(projectwbs.getOutsourcingPurchasePlans()).doesNotContain(outsourcingPurchasePlanBack);
        assertThat(outsourcingPurchasePlanBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void technicalTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Technical technicalBack = getTechnicalRandomSampleGenerator();

        projectwbs.addTechnical(technicalBack);
        assertThat(projectwbs.getTechnicals()).containsOnly(technicalBack);
        assertThat(technicalBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeTechnical(technicalBack);
        assertThat(projectwbs.getTechnicals()).doesNotContain(technicalBack);
        assertThat(technicalBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.technicals(new HashSet<>(Set.of(technicalBack)));
        assertThat(projectwbs.getTechnicals()).containsOnly(technicalBack);
        assertThat(technicalBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setTechnicals(new HashSet<>());
        assertThat(projectwbs.getTechnicals()).doesNotContain(technicalBack);
        assertThat(technicalBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void technicalConditionTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        TechnicalCondition technicalConditionBack = getTechnicalConditionRandomSampleGenerator();

        projectwbs.addTechnicalCondition(technicalConditionBack);
        assertThat(projectwbs.getTechnicalConditions()).containsOnly(technicalConditionBack);
        assertThat(technicalConditionBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeTechnicalCondition(technicalConditionBack);
        assertThat(projectwbs.getTechnicalConditions()).doesNotContain(technicalConditionBack);
        assertThat(technicalConditionBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.technicalConditions(new HashSet<>(Set.of(technicalConditionBack)));
        assertThat(projectwbs.getTechnicalConditions()).containsOnly(technicalConditionBack);
        assertThat(technicalConditionBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setTechnicalConditions(new HashSet<>());
        assertThat(projectwbs.getTechnicalConditions()).doesNotContain(technicalConditionBack);
        assertThat(technicalConditionBack.getProjectwbs()).doesNotContain(projectwbs);
    }

    @Test
    void projectRiskTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        ProjectRisk projectRiskBack = getProjectRiskRandomSampleGenerator();

        projectwbs.addProjectRisk(projectRiskBack);
        assertThat(projectwbs.getProjectRisks()).containsOnly(projectRiskBack);
        assertThat(projectRiskBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeProjectRisk(projectRiskBack);
        assertThat(projectwbs.getProjectRisks()).doesNotContain(projectRiskBack);
        assertThat(projectRiskBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.projectRisks(new HashSet<>(Set.of(projectRiskBack)));
        assertThat(projectwbs.getProjectRisks()).containsOnly(projectRiskBack);
        assertThat(projectRiskBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setProjectRisks(new HashSet<>());
        assertThat(projectwbs.getProjectRisks()).doesNotContain(projectRiskBack);
        assertThat(projectRiskBack.getProjectwbs()).doesNotContain(projectwbs);
    }
}
