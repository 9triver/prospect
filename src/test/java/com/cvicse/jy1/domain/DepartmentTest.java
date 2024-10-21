package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProjectpbsTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DepartmentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Department.class);
        Department department1 = getDepartmentSample1();
        Department department2 = new Department();
        assertThat(department1).isNotEqualTo(department2);

        department2.setId(department1.getId());
        assertThat(department1).isEqualTo(department2);

        department2 = getDepartmentSample2();
        assertThat(department1).isNotEqualTo(department2);
    }

    @Test
    void superiorTest() {
        Department department = getDepartmentRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        department.setSuperior(departmentBack);
        assertThat(department.getSuperior()).isEqualTo(departmentBack);

        department.superior(null);
        assertThat(department.getSuperior()).isNull();
    }

    @Test
    void officersTest() {
        Department department = getDepartmentRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        department.addOfficers(officersBack);
        assertThat(department.getOfficers()).containsOnly(officersBack);
        assertThat(officersBack.getDepartments()).containsOnly(department);

        department.removeOfficers(officersBack);
        assertThat(department.getOfficers()).doesNotContain(officersBack);
        assertThat(officersBack.getDepartments()).doesNotContain(department);

        department.officers(new HashSet<>(Set.of(officersBack)));
        assertThat(department.getOfficers()).containsOnly(officersBack);
        assertThat(officersBack.getDepartments()).containsOnly(department);

        department.setOfficers(new HashSet<>());
        assertThat(department.getOfficers()).doesNotContain(officersBack);
        assertThat(officersBack.getDepartments()).doesNotContain(department);
    }

    @Test
    void pbsTest() {
        Department department = getDepartmentRandomSampleGenerator();
        Projectpbs projectpbsBack = getProjectpbsRandomSampleGenerator();

        department.addPbs(projectpbsBack);
        assertThat(department.getPbs()).containsOnly(projectpbsBack);
        assertThat(projectpbsBack.getRelevantdepartments()).containsOnly(department);

        department.removePbs(projectpbsBack);
        assertThat(department.getPbs()).doesNotContain(projectpbsBack);
        assertThat(projectpbsBack.getRelevantdepartments()).doesNotContain(department);

        department.pbs(new HashSet<>(Set.of(projectpbsBack)));
        assertThat(department.getPbs()).containsOnly(projectpbsBack);
        assertThat(projectpbsBack.getRelevantdepartments()).containsOnly(department);

        department.setPbs(new HashSet<>());
        assertThat(department.getPbs()).doesNotContain(projectpbsBack);
        assertThat(projectpbsBack.getRelevantdepartments()).doesNotContain(department);
    }

    @Test
    void wbsTest() {
        Department department = getDepartmentRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        department.addWbs(projectwbsBack);
        assertThat(department.getWbs()).containsOnly(projectwbsBack);
        assertThat(projectwbsBack.getRelevantdepartments()).containsOnly(department);

        department.removeWbs(projectwbsBack);
        assertThat(department.getWbs()).doesNotContain(projectwbsBack);
        assertThat(projectwbsBack.getRelevantdepartments()).doesNotContain(department);

        department.wbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(department.getWbs()).containsOnly(projectwbsBack);
        assertThat(projectwbsBack.getRelevantdepartments()).containsOnly(department);

        department.setWbs(new HashSet<>());
        assertThat(department.getWbs()).doesNotContain(projectwbsBack);
        assertThat(projectwbsBack.getRelevantdepartments()).doesNotContain(department);
    }

    @Test
    void workbagTest() {
        Department department = getDepartmentRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        department.addWorkbag(workbagBack);
        assertThat(department.getWorkbags()).containsOnly(workbagBack);
        assertThat(workbagBack.getRelevantdepartments()).containsOnly(department);

        department.removeWorkbag(workbagBack);
        assertThat(department.getWorkbags()).doesNotContain(workbagBack);
        assertThat(workbagBack.getRelevantdepartments()).doesNotContain(department);

        department.workbags(new HashSet<>(Set.of(workbagBack)));
        assertThat(department.getWorkbags()).containsOnly(workbagBack);
        assertThat(workbagBack.getRelevantdepartments()).containsOnly(department);

        department.setWorkbags(new HashSet<>());
        assertThat(department.getWorkbags()).doesNotContain(workbagBack);
        assertThat(workbagBack.getRelevantdepartments()).doesNotContain(department);
    }
}
