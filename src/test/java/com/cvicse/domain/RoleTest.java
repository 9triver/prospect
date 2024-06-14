package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PermissionTestSamples.*;
import static com.cvicse.domain.RoleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Role.class);
        Role role1 = getRoleSample1();
        Role role2 = new Role();
        assertThat(role1).isNotEqualTo(role2);

        role2.setId(role1.getId());
        assertThat(role1).isEqualTo(role2);

        role2 = getRoleSample2();
        assertThat(role1).isNotEqualTo(role2);
    }

    @Test
    void permissionTest() throws Exception {
        Role role = getRoleRandomSampleGenerator();
        Permission permissionBack = getPermissionRandomSampleGenerator();

        role.setPermission(permissionBack);
        assertThat(role.getPermission()).isEqualTo(permissionBack);

        role.permission(null);
        assertThat(role.getPermission()).isNull();
    }

    @Test
    void officersTest() throws Exception {
        Role role = getRoleRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        role.setOfficers(officersBack);
        assertThat(role.getOfficers()).isEqualTo(officersBack);
        assertThat(officersBack.getRole()).isEqualTo(role);

        role.officers(null);
        assertThat(role.getOfficers()).isNull();
        assertThat(officersBack.getRole()).isNull();
    }
}
