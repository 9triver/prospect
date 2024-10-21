package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HrManagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HrManagement.class);
        HrManagement hrManagement1 = getHrManagementSample1();
        HrManagement hrManagement2 = new HrManagement();
        assertThat(hrManagement1).isNotEqualTo(hrManagement2);

        hrManagement2.setId(hrManagement1.getId());
        assertThat(hrManagement1).isEqualTo(hrManagement2);

        hrManagement2 = getHrManagementSample2();
        assertThat(hrManagement1).isNotEqualTo(hrManagement2);
    }

    @Test
    void officersTest() {
        HrManagement hrManagement = getHrManagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        hrManagement.setOfficers(officersBack);
        assertThat(hrManagement.getOfficers()).isEqualTo(officersBack);

        hrManagement.officers(null);
        assertThat(hrManagement.getOfficers()).isNull();
    }
}
