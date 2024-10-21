package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractCostBudgetTestSamples.*;
import static com.cvicse.jy1.domain.CostControlSystemTestSamples.*;
import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.FundsEstimationTestSamples.*;
import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingContractualTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingPurchasePlanTestSamples.*;
import static com.cvicse.jy1.domain.ProgressPlanTestSamples.*;
import static com.cvicse.jy1.domain.ProjectBudgetTestSamples.*;
import static com.cvicse.jy1.domain.ProjectTestSamples.*;
import static com.cvicse.jy1.domain.ProjectTotalwbsTestSamples.*;
import static com.cvicse.jy1.domain.ProjectdeliverablesTestSamples.*;
import static com.cvicse.jy1.domain.ProjectpbsTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.TechnicalTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
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
    void projectpbsTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Projectpbs projectpbsBack = getProjectpbsRandomSampleGenerator();

        projectwbs.setProjectpbs(projectpbsBack);
        assertThat(projectwbs.getProjectpbs()).isEqualTo(projectpbsBack);

        projectwbs.projectpbs(null);
        assertThat(projectwbs.getProjectpbs()).isNull();
    }

    @Test
    void responsiblepersonTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        projectwbs.setResponsibleperson(hrManagementBack);
        assertThat(projectwbs.getResponsibleperson()).isEqualTo(hrManagementBack);

        projectwbs.responsibleperson(null);
        assertThat(projectwbs.getResponsibleperson()).isNull();
    }

    @Test
    void technicaldirectorTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        projectwbs.setTechnicaldirector(hrManagementBack);
        assertThat(projectwbs.getTechnicaldirector()).isEqualTo(hrManagementBack);

        projectwbs.technicaldirector(null);
        assertThat(projectwbs.getTechnicaldirector()).isNull();
    }

    @Test
    void knowingpeopleTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        projectwbs.setKnowingpeople(hrManagementBack);
        assertThat(projectwbs.getKnowingpeople()).isEqualTo(hrManagementBack);

        projectwbs.knowingpeople(null);
        assertThat(projectwbs.getKnowingpeople()).isNull();
    }

    @Test
    void auditoridTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        projectwbs.setAuditorid(hrManagementBack);
        assertThat(projectwbs.getAuditorid()).isEqualTo(hrManagementBack);

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
    void projectdeliverablesTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Projectdeliverables projectdeliverablesBack = getProjectdeliverablesRandomSampleGenerator();

        projectwbs.addProjectdeliverables(projectdeliverablesBack);
        assertThat(projectwbs.getProjectdeliverables()).containsOnly(projectdeliverablesBack);

        projectwbs.removeProjectdeliverables(projectdeliverablesBack);
        assertThat(projectwbs.getProjectdeliverables()).doesNotContain(projectdeliverablesBack);

        projectwbs.projectdeliverables(new HashSet<>(Set.of(projectdeliverablesBack)));
        assertThat(projectwbs.getProjectdeliverables()).containsOnly(projectdeliverablesBack);

        projectwbs.setProjectdeliverables(new HashSet<>());
        assertThat(projectwbs.getProjectdeliverables()).doesNotContain(projectdeliverablesBack);
    }

    @Test
    void relevantdepartmentTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectwbs.addRelevantdepartment(departmentBack);
        assertThat(projectwbs.getRelevantdepartments()).containsOnly(departmentBack);

        projectwbs.removeRelevantdepartment(departmentBack);
        assertThat(projectwbs.getRelevantdepartments()).doesNotContain(departmentBack);

        projectwbs.relevantdepartments(new HashSet<>(Set.of(departmentBack)));
        assertThat(projectwbs.getRelevantdepartments()).containsOnly(departmentBack);

        projectwbs.setRelevantdepartments(new HashSet<>());
        assertThat(projectwbs.getRelevantdepartments()).doesNotContain(departmentBack);
    }

    @Test
    void workbagTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        projectwbs.addWorkbag(workbagBack);
        assertThat(projectwbs.getWorkbags()).containsOnly(workbagBack);
        assertThat(workbagBack.getWbsids()).containsOnly(projectwbs);

        projectwbs.removeWorkbag(workbagBack);
        assertThat(projectwbs.getWorkbags()).doesNotContain(workbagBack);
        assertThat(workbagBack.getWbsids()).doesNotContain(projectwbs);

        projectwbs.workbags(new HashSet<>(Set.of(workbagBack)));
        assertThat(projectwbs.getWorkbags()).containsOnly(workbagBack);
        assertThat(workbagBack.getWbsids()).containsOnly(projectwbs);

        projectwbs.setWorkbags(new HashSet<>());
        assertThat(projectwbs.getWorkbags()).doesNotContain(workbagBack);
        assertThat(workbagBack.getWbsids()).doesNotContain(projectwbs);
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
    void projectBudgetTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        ProjectBudget projectBudgetBack = getProjectBudgetRandomSampleGenerator();

        projectwbs.addProjectBudget(projectBudgetBack);
        assertThat(projectwbs.getProjectBudgets()).containsOnly(projectBudgetBack);
        assertThat(projectBudgetBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeProjectBudget(projectBudgetBack);
        assertThat(projectwbs.getProjectBudgets()).doesNotContain(projectBudgetBack);
        assertThat(projectBudgetBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.projectBudgets(new HashSet<>(Set.of(projectBudgetBack)));
        assertThat(projectwbs.getProjectBudgets()).containsOnly(projectBudgetBack);
        assertThat(projectBudgetBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setProjectBudgets(new HashSet<>());
        assertThat(projectwbs.getProjectBudgets()).doesNotContain(projectBudgetBack);
        assertThat(projectBudgetBack.getProjectwbs()).doesNotContain(projectwbs);
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
    void projectTotalwbsTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        ProjectTotalwbs projectTotalwbsBack = getProjectTotalwbsRandomSampleGenerator();

        projectwbs.setProjectTotalwbs(projectTotalwbsBack);
        assertThat(projectwbs.getProjectTotalwbs()).isEqualTo(projectTotalwbsBack);
        assertThat(projectTotalwbsBack.getProjectwbs()).isEqualTo(projectwbs);

        projectwbs.projectTotalwbs(null);
        assertThat(projectwbs.getProjectTotalwbs()).isNull();
        assertThat(projectTotalwbsBack.getProjectwbs()).isNull();
    }
}
