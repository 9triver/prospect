package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingContractTestSamples.*;
import static com.cvicse.jy1.domain.ProjectdeliverablesTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.WorkTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class WorkbagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Workbag.class);
        Workbag workbag1 = getWorkbagSample1();
        Workbag workbag2 = new Workbag();
        assertThat(workbag1).isNotEqualTo(workbag2);

        workbag2.setId(workbag1.getId());
        assertThat(workbag1).isEqualTo(workbag2);

        workbag2 = getWorkbagSample2();
        assertThat(workbag1).isNotEqualTo(workbag2);
    }

    @Test
    void responsiblepersonTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        workbag.setResponsibleperson(hrManagementBack);
        assertThat(workbag.getResponsibleperson()).isEqualTo(hrManagementBack);

        workbag.responsibleperson(null);
        assertThat(workbag.getResponsibleperson()).isNull();
    }

    @Test
    void projectmanagerTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        workbag.setProjectmanager(hrManagementBack);
        assertThat(workbag.getProjectmanager()).isEqualTo(hrManagementBack);

        workbag.projectmanager(null);
        assertThat(workbag.getProjectmanager()).isNull();
    }

    @Test
    void knowingpeopleTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        workbag.setKnowingpeople(hrManagementBack);
        assertThat(workbag.getKnowingpeople()).isEqualTo(hrManagementBack);

        workbag.knowingpeople(null);
        assertThat(workbag.getKnowingpeople()).isNull();
    }

    @Test
    void auditoridTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        workbag.setAuditorid(hrManagementBack);
        assertThat(workbag.getAuditorid()).isEqualTo(hrManagementBack);

        workbag.auditorid(null);
        assertThat(workbag.getAuditorid()).isNull();
    }

    @Test
    void responsibledepartmentTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        workbag.setResponsibledepartment(departmentBack);
        assertThat(workbag.getResponsibledepartment()).isEqualTo(departmentBack);

        workbag.responsibledepartment(null);
        assertThat(workbag.getResponsibledepartment()).isNull();
    }

    @Test
    void departmentTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        workbag.setDepartment(departmentBack);
        assertThat(workbag.getDepartment()).isEqualTo(departmentBack);

        workbag.department(null);
        assertThat(workbag.getDepartment()).isNull();
    }

    @Test
    void projectdeliverablesTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        Projectdeliverables projectdeliverablesBack = getProjectdeliverablesRandomSampleGenerator();

        workbag.addProjectdeliverables(projectdeliverablesBack);
        assertThat(workbag.getProjectdeliverables()).containsOnly(projectdeliverablesBack);

        workbag.removeProjectdeliverables(projectdeliverablesBack);
        assertThat(workbag.getProjectdeliverables()).doesNotContain(projectdeliverablesBack);

        workbag.projectdeliverables(new HashSet<>(Set.of(projectdeliverablesBack)));
        assertThat(workbag.getProjectdeliverables()).containsOnly(projectdeliverablesBack);

        workbag.setProjectdeliverables(new HashSet<>());
        assertThat(workbag.getProjectdeliverables()).doesNotContain(projectdeliverablesBack);
    }

    @Test
    void relevantdepartmentTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        workbag.addRelevantdepartment(departmentBack);
        assertThat(workbag.getRelevantdepartments()).containsOnly(departmentBack);

        workbag.removeRelevantdepartment(departmentBack);
        assertThat(workbag.getRelevantdepartments()).doesNotContain(departmentBack);

        workbag.relevantdepartments(new HashSet<>(Set.of(departmentBack)));
        assertThat(workbag.getRelevantdepartments()).containsOnly(departmentBack);

        workbag.setRelevantdepartments(new HashSet<>());
        assertThat(workbag.getRelevantdepartments()).doesNotContain(departmentBack);
    }

    @Test
    void wbsidTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        workbag.addWbsid(projectwbsBack);
        assertThat(workbag.getWbsids()).containsOnly(projectwbsBack);

        workbag.removeWbsid(projectwbsBack);
        assertThat(workbag.getWbsids()).doesNotContain(projectwbsBack);

        workbag.wbsids(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(workbag.getWbsids()).containsOnly(projectwbsBack);

        workbag.setWbsids(new HashSet<>());
        assertThat(workbag.getWbsids()).doesNotContain(projectwbsBack);
    }

    @Test
    void workTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        Work workBack = getWorkRandomSampleGenerator();

        workbag.addWork(workBack);
        assertThat(workbag.getWorks()).containsOnly(workBack);

        workbag.removeWork(workBack);
        assertThat(workbag.getWorks()).doesNotContain(workBack);

        workbag.works(new HashSet<>(Set.of(workBack)));
        assertThat(workbag.getWorks()).containsOnly(workBack);

        workbag.setWorks(new HashSet<>());
        assertThat(workbag.getWorks()).doesNotContain(workBack);
    }

    @Test
    void outsourcingContractTest() {
        Workbag workbag = getWorkbagRandomSampleGenerator();
        OutsourcingContract outsourcingContractBack = getOutsourcingContractRandomSampleGenerator();

        workbag.setOutsourcingContract(outsourcingContractBack);
        assertThat(workbag.getOutsourcingContract()).isEqualTo(outsourcingContractBack);
        assertThat(outsourcingContractBack.getWorkbag()).isEqualTo(workbag);

        workbag.outsourcingContract(null);
        assertThat(workbag.getOutsourcingContract()).isNull();
        assertThat(outsourcingContractBack.getWorkbag()).isNull();
    }
}
