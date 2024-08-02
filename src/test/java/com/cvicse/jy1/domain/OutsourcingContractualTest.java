package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingContractualTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OutsourcingContractualTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutsourcingContractual.class);
        OutsourcingContractual outsourcingContractual1 = getOutsourcingContractualSample1();
        OutsourcingContractual outsourcingContractual2 = new OutsourcingContractual();
        assertThat(outsourcingContractual1).isNotEqualTo(outsourcingContractual2);

        outsourcingContractual2.setId(outsourcingContractual1.getId());
        assertThat(outsourcingContractual1).isEqualTo(outsourcingContractual2);

        outsourcingContractual2 = getOutsourcingContractualSample2();
        assertThat(outsourcingContractual1).isNotEqualTo(outsourcingContractual2);
    }

    @Test
    void creatoridTest() {
        OutsourcingContractual outsourcingContractual = getOutsourcingContractualRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingContractual.setCreatorid(officersBack);
        assertThat(outsourcingContractual.getCreatorid()).isEqualTo(officersBack);

        outsourcingContractual.creatorid(null);
        assertThat(outsourcingContractual.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() {
        OutsourcingContractual outsourcingContractual = getOutsourcingContractualRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingContractual.setAuditorid(officersBack);
        assertThat(outsourcingContractual.getAuditorid()).isEqualTo(officersBack);

        outsourcingContractual.auditorid(null);
        assertThat(outsourcingContractual.getAuditorid()).isNull();
    }

    @Test
    void projectwbsTest() {
        OutsourcingContractual outsourcingContractual = getOutsourcingContractualRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        outsourcingContractual.addProjectwbs(projectwbsBack);
        assertThat(outsourcingContractual.getProjectwbs()).containsOnly(projectwbsBack);

        outsourcingContractual.removeProjectwbs(projectwbsBack);
        assertThat(outsourcingContractual.getProjectwbs()).doesNotContain(projectwbsBack);

        outsourcingContractual.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(outsourcingContractual.getProjectwbs()).containsOnly(projectwbsBack);

        outsourcingContractual.setProjectwbs(new HashSet<>());
        assertThat(outsourcingContractual.getProjectwbs()).doesNotContain(projectwbsBack);
    }
}
