package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.ProjectremitTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProjectremitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectremit.class);
        Projectremit projectremit1 = getProjectremitSample1();
        Projectremit projectremit2 = new Projectremit();
        assertThat(projectremit1).isNotEqualTo(projectremit2);

        projectremit2.setId(projectremit1.getId());
        assertThat(projectremit1).isEqualTo(projectremit2);

        projectremit2 = getProjectremitSample2();
        assertThat(projectremit1).isNotEqualTo(projectremit2);
    }

    @Test
    void projectTest() throws Exception {
        Projectremit projectremit = getProjectremitRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        projectremit.setProject(projectBack);
        assertThat(projectremit.getProject()).isEqualTo(projectBack);

        projectremit.project(null);
        assertThat(projectremit.getProject()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        Projectremit projectremit = getProjectremitRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectremit.setCreatorid(officersBack);
        assertThat(projectremit.getCreatorid()).isEqualTo(officersBack);

        projectremit.creatorid(null);
        assertThat(projectremit.getCreatorid()).isNull();
    }
}
