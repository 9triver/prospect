package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DeliverablesTestSamples.*;
import static com.cvicse.jy1.domain.ProjectdeliverablesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliverablesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Deliverables.class);
        Deliverables deliverables1 = getDeliverablesSample1();
        Deliverables deliverables2 = new Deliverables();
        assertThat(deliverables1).isNotEqualTo(deliverables2);

        deliverables2.setId(deliverables1.getId());
        assertThat(deliverables1).isEqualTo(deliverables2);

        deliverables2 = getDeliverablesSample2();
        assertThat(deliverables1).isNotEqualTo(deliverables2);
    }

    @Test
    void projectdeliverablesTest() {
        Deliverables deliverables = getDeliverablesRandomSampleGenerator();
        Projectdeliverables projectdeliverablesBack = getProjectdeliverablesRandomSampleGenerator();

        deliverables.setProjectdeliverables(projectdeliverablesBack);
        assertThat(deliverables.getProjectdeliverables()).isEqualTo(projectdeliverablesBack);
        assertThat(projectdeliverablesBack.getCode()).isEqualTo(deliverables);

        deliverables.projectdeliverables(null);
        assertThat(deliverables.getProjectdeliverables()).isNull();
        assertThat(projectdeliverablesBack.getCode()).isNull();
    }
}
