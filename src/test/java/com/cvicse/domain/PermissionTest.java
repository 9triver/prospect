package com.cvicse.domain;

import static com.cvicse.domain.PermissionTestSamples.*;
import static com.cvicse.domain.RoleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PermissionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Permission.class);
        Permission permission1 = getPermissionSample1();
        Permission permission2 = new Permission();
        assertThat(permission1).isNotEqualTo(permission2);

        permission2.setId(permission1.getId());
        assertThat(permission1).isEqualTo(permission2);

        permission2 = getPermissionSample2();
        assertThat(permission1).isNotEqualTo(permission2);
    }

    @Test
    void roleTest() throws Exception {
        Permission permission = getPermissionRandomSampleGenerator();
        Role roleBack = getRoleRandomSampleGenerator();

        permission.setRole(roleBack);
        assertThat(permission.getRole()).isEqualTo(roleBack);
        assertThat(roleBack.getPermission()).isEqualTo(permission);

        permission.role(null);
        assertThat(permission.getRole()).isNull();
        assertThat(roleBack.getPermission()).isNull();
    }
}
