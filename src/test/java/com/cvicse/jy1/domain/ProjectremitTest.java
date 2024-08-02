package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ProjectremitTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
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
}
