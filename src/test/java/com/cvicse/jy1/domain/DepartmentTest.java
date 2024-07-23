package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
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
    void officersTest() {
        Department department = getDepartmentRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        department.addOfficers(officersBack);
        assertThat(department.getOfficers()).containsOnly(officersBack);

        department.removeOfficers(officersBack);
        assertThat(department.getOfficers()).doesNotContain(officersBack);

        department.officers(new HashSet<>(Set.of(officersBack)));
        assertThat(department.getOfficers()).containsOnly(officersBack);

        department.setOfficers(new HashSet<>());
        assertThat(department.getOfficers()).doesNotContain(officersBack);
    }
}
