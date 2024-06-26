package com.cvicse.domain;

import static com.cvicse.domain.HumanresourcesTestSamples.*;
import static com.cvicse.domain.ProjectHumanresourcesplanTestSamples.*;
import static com.cvicse.domain.ProjectremitTestSamples.*;
import static com.cvicse.domain.ResourcemanagementTestSamples.*;
import static com.cvicse.domain.ResourcemanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourcemanagementWbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourcemanagementWbs.class);
        ResourcemanagementWbs resourcemanagementWbs1 = getResourcemanagementWbsSample1();
        ResourcemanagementWbs resourcemanagementWbs2 = new ResourcemanagementWbs();
        assertThat(resourcemanagementWbs1).isNotEqualTo(resourcemanagementWbs2);

        resourcemanagementWbs2.setId(resourcemanagementWbs1.getId());
        assertThat(resourcemanagementWbs1).isEqualTo(resourcemanagementWbs2);

        resourcemanagementWbs2 = getResourcemanagementWbsSample2();
        assertThat(resourcemanagementWbs1).isNotEqualTo(resourcemanagementWbs2);
    }

    @Test
    void projectHumanresourcesplanTest() throws Exception {
        ResourcemanagementWbs resourcemanagementWbs = getResourcemanagementWbsRandomSampleGenerator();
        ProjectHumanresourcesplan projectHumanresourcesplanBack = getProjectHumanresourcesplanRandomSampleGenerator();

        resourcemanagementWbs.setProjectHumanresourcesplan(projectHumanresourcesplanBack);
        assertThat(resourcemanagementWbs.getProjectHumanresourcesplan()).isEqualTo(projectHumanresourcesplanBack);

        resourcemanagementWbs.projectHumanresourcesplan(null);
        assertThat(resourcemanagementWbs.getProjectHumanresourcesplan()).isNull();
    }

    @Test
    void projectremitTest() throws Exception {
        ResourcemanagementWbs resourcemanagementWbs = getResourcemanagementWbsRandomSampleGenerator();
        Projectremit projectremitBack = getProjectremitRandomSampleGenerator();

        resourcemanagementWbs.setProjectremit(projectremitBack);
        assertThat(resourcemanagementWbs.getProjectremit()).isEqualTo(projectremitBack);

        resourcemanagementWbs.projectremit(null);
        assertThat(resourcemanagementWbs.getProjectremit()).isNull();
    }

    @Test
    void humanresourcesTest() throws Exception {
        ResourcemanagementWbs resourcemanagementWbs = getResourcemanagementWbsRandomSampleGenerator();
        Humanresources humanresourcesBack = getHumanresourcesRandomSampleGenerator();

        resourcemanagementWbs.setHumanresources(humanresourcesBack);
        assertThat(resourcemanagementWbs.getHumanresources()).isEqualTo(humanresourcesBack);

        resourcemanagementWbs.humanresources(null);
        assertThat(resourcemanagementWbs.getHumanresources()).isNull();
    }

    @Test
    void resourcemanagementTest() throws Exception {
        ResourcemanagementWbs resourcemanagementWbs = getResourcemanagementWbsRandomSampleGenerator();
        Resourcemanagement resourcemanagementBack = getResourcemanagementRandomSampleGenerator();

        resourcemanagementWbs.setResourcemanagement(resourcemanagementBack);
        assertThat(resourcemanagementWbs.getResourcemanagement()).isEqualTo(resourcemanagementBack);
        assertThat(resourcemanagementBack.getWbs()).isEqualTo(resourcemanagementWbs);

        resourcemanagementWbs.resourcemanagement(null);
        assertThat(resourcemanagementWbs.getResourcemanagement()).isNull();
        assertThat(resourcemanagementBack.getWbs()).isNull();
    }
}
