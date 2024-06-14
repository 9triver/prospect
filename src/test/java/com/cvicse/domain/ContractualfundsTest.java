package com.cvicse.domain;

import static com.cvicse.domain.ContractualfundsTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractualfundsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contractualfunds.class);
        Contractualfunds contractualfunds1 = getContractualfundsSample1();
        Contractualfunds contractualfunds2 = new Contractualfunds();
        assertThat(contractualfunds1).isNotEqualTo(contractualfunds2);

        contractualfunds2.setId(contractualfunds1.getId());
        assertThat(contractualfunds1).isEqualTo(contractualfunds2);

        contractualfunds2 = getContractualfundsSample2();
        assertThat(contractualfunds1).isNotEqualTo(contractualfunds2);
    }

    @Test
    void creatoridTest() throws Exception {
        Contractualfunds contractualfunds = getContractualfundsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        contractualfunds.setCreatorid(officersBack);
        assertThat(contractualfunds.getCreatorid()).isEqualTo(officersBack);

        contractualfunds.creatorid(null);
        assertThat(contractualfunds.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Contractualfunds contractualfunds = getContractualfundsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        contractualfunds.setAuditorid(officersBack);
        assertThat(contractualfunds.getAuditorid()).isEqualTo(officersBack);

        contractualfunds.auditorid(null);
        assertThat(contractualfunds.getAuditorid()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        Contractualfunds contractualfunds = getContractualfundsRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        contractualfunds.setProject(projectBack);
        assertThat(contractualfunds.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getContractualfunds()).isEqualTo(contractualfunds);

        contractualfunds.project(null);
        assertThat(contractualfunds.getProject()).isNull();
        assertThat(projectBack.getContractualfunds()).isNull();
    }
}
