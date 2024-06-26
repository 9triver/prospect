package com.cvicse.domain;

import static com.cvicse.domain.ResourcemanagementTestSamples.*;
import static com.cvicse.domain.ResourcemanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourcemanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Resourcemanagement.class);
        Resourcemanagement resourcemanagement1 = getResourcemanagementSample1();
        Resourcemanagement resourcemanagement2 = new Resourcemanagement();
        assertThat(resourcemanagement1).isNotEqualTo(resourcemanagement2);

        resourcemanagement2.setId(resourcemanagement1.getId());
        assertThat(resourcemanagement1).isEqualTo(resourcemanagement2);

        resourcemanagement2 = getResourcemanagementSample2();
        assertThat(resourcemanagement1).isNotEqualTo(resourcemanagement2);
    }

    @Test
    void wbsTest() throws Exception {
        Resourcemanagement resourcemanagement = getResourcemanagementRandomSampleGenerator();
        ResourcemanagementWbs resourcemanagementWbsBack = getResourcemanagementWbsRandomSampleGenerator();

        resourcemanagement.setWbs(resourcemanagementWbsBack);
        assertThat(resourcemanagement.getWbs()).isEqualTo(resourcemanagementWbsBack);

        resourcemanagement.wbs(null);
        assertThat(resourcemanagement.getWbs()).isNull();
    }
}
