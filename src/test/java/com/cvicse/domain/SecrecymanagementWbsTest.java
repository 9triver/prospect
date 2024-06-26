package com.cvicse.domain;

import static com.cvicse.domain.ProjectSecrecyTestSamples.*;
import static com.cvicse.domain.SecrecymanagementTestSamples.*;
import static com.cvicse.domain.SecrecymanagementWbsTestSamples.*;
import static com.cvicse.domain.SecrecysystemTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecrecymanagementWbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecrecymanagementWbs.class);
        SecrecymanagementWbs secrecymanagementWbs1 = getSecrecymanagementWbsSample1();
        SecrecymanagementWbs secrecymanagementWbs2 = new SecrecymanagementWbs();
        assertThat(secrecymanagementWbs1).isNotEqualTo(secrecymanagementWbs2);

        secrecymanagementWbs2.setId(secrecymanagementWbs1.getId());
        assertThat(secrecymanagementWbs1).isEqualTo(secrecymanagementWbs2);

        secrecymanagementWbs2 = getSecrecymanagementWbsSample2();
        assertThat(secrecymanagementWbs1).isNotEqualTo(secrecymanagementWbs2);
    }

    @Test
    void secrecysystemTest() throws Exception {
        SecrecymanagementWbs secrecymanagementWbs = getSecrecymanagementWbsRandomSampleGenerator();
        Secrecysystem secrecysystemBack = getSecrecysystemRandomSampleGenerator();

        secrecymanagementWbs.setSecrecysystem(secrecysystemBack);
        assertThat(secrecymanagementWbs.getSecrecysystem()).isEqualTo(secrecysystemBack);

        secrecymanagementWbs.secrecysystem(null);
        assertThat(secrecymanagementWbs.getSecrecysystem()).isNull();
    }

    @Test
    void projectSecrecyTest() throws Exception {
        SecrecymanagementWbs secrecymanagementWbs = getSecrecymanagementWbsRandomSampleGenerator();
        ProjectSecrecy projectSecrecyBack = getProjectSecrecyRandomSampleGenerator();

        secrecymanagementWbs.setProjectSecrecy(projectSecrecyBack);
        assertThat(secrecymanagementWbs.getProjectSecrecy()).isEqualTo(projectSecrecyBack);

        secrecymanagementWbs.projectSecrecy(null);
        assertThat(secrecymanagementWbs.getProjectSecrecy()).isNull();
    }

    @Test
    void secrecymanagementTest() throws Exception {
        SecrecymanagementWbs secrecymanagementWbs = getSecrecymanagementWbsRandomSampleGenerator();
        Secrecymanagement secrecymanagementBack = getSecrecymanagementRandomSampleGenerator();

        secrecymanagementWbs.setSecrecymanagement(secrecymanagementBack);
        assertThat(secrecymanagementWbs.getSecrecymanagement()).isEqualTo(secrecymanagementBack);
        assertThat(secrecymanagementBack.getWbs()).isEqualTo(secrecymanagementWbs);

        secrecymanagementWbs.secrecymanagement(null);
        assertThat(secrecymanagementWbs.getSecrecymanagement()).isNull();
        assertThat(secrecymanagementBack.getWbs()).isNull();
    }
}
