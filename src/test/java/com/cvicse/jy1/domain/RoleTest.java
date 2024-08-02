package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.PermissionTestSamples.*;
import static com.cvicse.jy1.domain.RoleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
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
    void permissionTest() {
        Role role = getRoleRandomSampleGenerator();
        Permission permissionBack = getPermissionRandomSampleGenerator();

        role.addPermission(permissionBack);
        assertThat(role.getPermissions()).containsOnly(permissionBack);

        role.removePermission(permissionBack);
        assertThat(role.getPermissions()).doesNotContain(permissionBack);

        role.permissions(new HashSet<>(Set.of(permissionBack)));
        assertThat(role.getPermissions()).containsOnly(permissionBack);

        role.setPermissions(new HashSet<>());
        assertThat(role.getPermissions()).doesNotContain(permissionBack);
    }

    @Test
    void officersTest() {
        Role role = getRoleRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        role.addOfficers(officersBack);
        assertThat(role.getOfficers()).containsOnly(officersBack);
        assertThat(officersBack.getRoles()).containsOnly(role);

        role.removeOfficers(officersBack);
        assertThat(role.getOfficers()).doesNotContain(officersBack);
        assertThat(officersBack.getRoles()).doesNotContain(role);

        role.officers(new HashSet<>(Set.of(officersBack)));
        assertThat(role.getOfficers()).containsOnly(officersBack);
        assertThat(officersBack.getRoles()).containsOnly(role);

        role.setOfficers(new HashSet<>());
        assertThat(role.getOfficers()).doesNotContain(officersBack);
        assertThat(officersBack.getRoles()).doesNotContain(role);
    }
}
