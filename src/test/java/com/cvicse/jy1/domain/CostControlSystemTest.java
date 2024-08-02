package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractTestSamples.*;
import static com.cvicse.jy1.domain.CostControlSystemTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CostControlSystemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CostControlSystem.class);
        CostControlSystem costControlSystem1 = getCostControlSystemSample1();
        CostControlSystem costControlSystem2 = new CostControlSystem();
        assertThat(costControlSystem1).isNotEqualTo(costControlSystem2);

        costControlSystem2.setId(costControlSystem1.getId());
        assertThat(costControlSystem1).isEqualTo(costControlSystem2);

        costControlSystem2 = getCostControlSystemSample2();
        assertThat(costControlSystem1).isNotEqualTo(costControlSystem2);
    }

    @Test
    void responsiblepersonTest() {
        CostControlSystem costControlSystem = getCostControlSystemRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        costControlSystem.setResponsibleperson(officersBack);
        assertThat(costControlSystem.getResponsibleperson()).isEqualTo(officersBack);

        costControlSystem.responsibleperson(null);
        assertThat(costControlSystem.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        CostControlSystem costControlSystem = getCostControlSystemRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        costControlSystem.setAuditorid(officersBack);
        assertThat(costControlSystem.getAuditorid()).isEqualTo(officersBack);

        costControlSystem.auditorid(null);
        assertThat(costControlSystem.getAuditorid()).isNull();
    }

    @Test
    void projectwbsTest() {
        CostControlSystem costControlSystem = getCostControlSystemRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        costControlSystem.addProjectwbs(projectwbsBack);
        assertThat(costControlSystem.getProjectwbs()).containsOnly(projectwbsBack);

        costControlSystem.removeProjectwbs(projectwbsBack);
        assertThat(costControlSystem.getProjectwbs()).doesNotContain(projectwbsBack);

        costControlSystem.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(costControlSystem.getProjectwbs()).containsOnly(projectwbsBack);

        costControlSystem.setProjectwbs(new HashSet<>());
        assertThat(costControlSystem.getProjectwbs()).doesNotContain(projectwbsBack);
    }

    @Test
    void contractTest() {
        CostControlSystem costControlSystem = getCostControlSystemRandomSampleGenerator();
        Contract contractBack = getContractRandomSampleGenerator();

        costControlSystem.addContract(contractBack);
        assertThat(costControlSystem.getContracts()).containsOnly(contractBack);

        costControlSystem.removeContract(contractBack);
        assertThat(costControlSystem.getContracts()).doesNotContain(contractBack);

        costControlSystem.contracts(new HashSet<>(Set.of(contractBack)));
        assertThat(costControlSystem.getContracts()).containsOnly(contractBack);

        costControlSystem.setContracts(new HashSet<>());
        assertThat(costControlSystem.getContracts()).doesNotContain(contractBack);
    }
}
