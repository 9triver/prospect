package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.RoleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OfficersTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Officers.class);
        Officers officers1 = getOfficersSample1();
        Officers officers2 = new Officers();
        assertThat(officers1).isNotEqualTo(officers2);

        officers2.setId(officers1.getId());
        assertThat(officers1).isEqualTo(officers2);

        officers2 = getOfficersSample2();
        assertThat(officers1).isNotEqualTo(officers2);
    }

    @Test
    void departmentsTest() {
        Officers officers = getOfficersRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        officers.addDepartments(departmentBack);
        assertThat(officers.getDepartments()).containsOnly(departmentBack);

        officers.removeDepartments(departmentBack);
        assertThat(officers.getDepartments()).doesNotContain(departmentBack);

        officers.departments(new HashSet<>(Set.of(departmentBack)));
        assertThat(officers.getDepartments()).containsOnly(departmentBack);

        officers.setDepartments(new HashSet<>());
        assertThat(officers.getDepartments()).doesNotContain(departmentBack);
    }

    @Test
    void roleTest() {
        Officers officers = getOfficersRandomSampleGenerator();
        Role roleBack = getRoleRandomSampleGenerator();

        officers.addRole(roleBack);
        assertThat(officers.getRoles()).containsOnly(roleBack);

        officers.removeRole(roleBack);
        assertThat(officers.getRoles()).doesNotContain(roleBack);

        officers.roles(new HashSet<>(Set.of(roleBack)));
        assertThat(officers.getRoles()).containsOnly(roleBack);

        officers.setRoles(new HashSet<>());
        assertThat(officers.getRoles()).doesNotContain(roleBack);
    }
}
