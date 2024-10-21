package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DeliverablesTestSamples.*;
import static com.cvicse.jy1.domain.ProjectdeliverablesTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProjectdeliverablesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectdeliverables.class);
        Projectdeliverables projectdeliverables1 = getProjectdeliverablesSample1();
        Projectdeliverables projectdeliverables2 = new Projectdeliverables();
        assertThat(projectdeliverables1).isNotEqualTo(projectdeliverables2);

        projectdeliverables2.setId(projectdeliverables1.getId());
        assertThat(projectdeliverables1).isEqualTo(projectdeliverables2);

        projectdeliverables2 = getProjectdeliverablesSample2();
        assertThat(projectdeliverables1).isNotEqualTo(projectdeliverables2);
    }

    @Test
    void codeTest() {
        Projectdeliverables projectdeliverables = getProjectdeliverablesRandomSampleGenerator();
        Deliverables deliverablesBack = getDeliverablesRandomSampleGenerator();

        projectdeliverables.setCode(deliverablesBack);
        assertThat(projectdeliverables.getCode()).isEqualTo(deliverablesBack);

        projectdeliverables.code(null);
        assertThat(projectdeliverables.getCode()).isNull();
    }

    @Test
    void belongwbsidTest() {
        Projectdeliverables projectdeliverables = getProjectdeliverablesRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        projectdeliverables.addBelongwbsid(projectwbsBack);
        assertThat(projectdeliverables.getBelongwbsids()).containsOnly(projectwbsBack);
        assertThat(projectwbsBack.getProjectdeliverables()).containsOnly(projectdeliverables);

        projectdeliverables.removeBelongwbsid(projectwbsBack);
        assertThat(projectdeliverables.getBelongwbsids()).doesNotContain(projectwbsBack);
        assertThat(projectwbsBack.getProjectdeliverables()).doesNotContain(projectdeliverables);

        projectdeliverables.belongwbsids(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(projectdeliverables.getBelongwbsids()).containsOnly(projectwbsBack);
        assertThat(projectwbsBack.getProjectdeliverables()).containsOnly(projectdeliverables);

        projectdeliverables.setBelongwbsids(new HashSet<>());
        assertThat(projectdeliverables.getBelongwbsids()).doesNotContain(projectwbsBack);
        assertThat(projectwbsBack.getProjectdeliverables()).doesNotContain(projectdeliverables);
    }

    @Test
    void belongworkbagidTest() {
        Projectdeliverables projectdeliverables = getProjectdeliverablesRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        projectdeliverables.addBelongworkbagid(workbagBack);
        assertThat(projectdeliverables.getBelongworkbagids()).containsOnly(workbagBack);
        assertThat(workbagBack.getProjectdeliverables()).containsOnly(projectdeliverables);

        projectdeliverables.removeBelongworkbagid(workbagBack);
        assertThat(projectdeliverables.getBelongworkbagids()).doesNotContain(workbagBack);
        assertThat(workbagBack.getProjectdeliverables()).doesNotContain(projectdeliverables);

        projectdeliverables.belongworkbagids(new HashSet<>(Set.of(workbagBack)));
        assertThat(projectdeliverables.getBelongworkbagids()).containsOnly(workbagBack);
        assertThat(workbagBack.getProjectdeliverables()).containsOnly(projectdeliverables);

        projectdeliverables.setBelongworkbagids(new HashSet<>());
        assertThat(projectdeliverables.getBelongworkbagids()).doesNotContain(workbagBack);
        assertThat(workbagBack.getProjectdeliverables()).doesNotContain(projectdeliverables);
    }
}
