package com.cvicse.domain;

import static com.cvicse.domain.AuditedbudgetTestSamples.*;
import static com.cvicse.domain.ContractualfundsTestSamples.*;
import static com.cvicse.domain.FundsavailabilityTestSamples.*;
import static com.cvicse.domain.FundsmanagementTestSamples.*;
import static com.cvicse.domain.FundsmanagementWbsTestSamples.*;
import static com.cvicse.domain.TotalbudgetTestSamples.*;
import static com.cvicse.domain.UnitbudgetTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FundsmanagementWbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundsmanagementWbs.class);
        FundsmanagementWbs fundsmanagementWbs1 = getFundsmanagementWbsSample1();
        FundsmanagementWbs fundsmanagementWbs2 = new FundsmanagementWbs();
        assertThat(fundsmanagementWbs1).isNotEqualTo(fundsmanagementWbs2);

        fundsmanagementWbs2.setId(fundsmanagementWbs1.getId());
        assertThat(fundsmanagementWbs1).isEqualTo(fundsmanagementWbs2);

        fundsmanagementWbs2 = getFundsmanagementWbsSample2();
        assertThat(fundsmanagementWbs1).isNotEqualTo(fundsmanagementWbs2);
    }

    @Test
    void auditedbudgetTest() throws Exception {
        FundsmanagementWbs fundsmanagementWbs = getFundsmanagementWbsRandomSampleGenerator();
        Auditedbudget auditedbudgetBack = getAuditedbudgetRandomSampleGenerator();

        fundsmanagementWbs.setAuditedbudget(auditedbudgetBack);
        assertThat(fundsmanagementWbs.getAuditedbudget()).isEqualTo(auditedbudgetBack);

        fundsmanagementWbs.auditedbudget(null);
        assertThat(fundsmanagementWbs.getAuditedbudget()).isNull();
    }

    @Test
    void totalbudgetTest() throws Exception {
        FundsmanagementWbs fundsmanagementWbs = getFundsmanagementWbsRandomSampleGenerator();
        Totalbudget totalbudgetBack = getTotalbudgetRandomSampleGenerator();

        fundsmanagementWbs.setTotalbudget(totalbudgetBack);
        assertThat(fundsmanagementWbs.getTotalbudget()).isEqualTo(totalbudgetBack);

        fundsmanagementWbs.totalbudget(null);
        assertThat(fundsmanagementWbs.getTotalbudget()).isNull();
    }

    @Test
    void unitbudgetTest() throws Exception {
        FundsmanagementWbs fundsmanagementWbs = getFundsmanagementWbsRandomSampleGenerator();
        Unitbudget unitbudgetBack = getUnitbudgetRandomSampleGenerator();

        fundsmanagementWbs.setUnitbudget(unitbudgetBack);
        assertThat(fundsmanagementWbs.getUnitbudget()).isEqualTo(unitbudgetBack);

        fundsmanagementWbs.unitbudget(null);
        assertThat(fundsmanagementWbs.getUnitbudget()).isNull();
    }

    @Test
    void fundsavailabilityTest() throws Exception {
        FundsmanagementWbs fundsmanagementWbs = getFundsmanagementWbsRandomSampleGenerator();
        Fundsavailability fundsavailabilityBack = getFundsavailabilityRandomSampleGenerator();

        fundsmanagementWbs.setFundsavailability(fundsavailabilityBack);
        assertThat(fundsmanagementWbs.getFundsavailability()).isEqualTo(fundsavailabilityBack);

        fundsmanagementWbs.fundsavailability(null);
        assertThat(fundsmanagementWbs.getFundsavailability()).isNull();
    }

    @Test
    void contractualfundsTest() throws Exception {
        FundsmanagementWbs fundsmanagementWbs = getFundsmanagementWbsRandomSampleGenerator();
        Contractualfunds contractualfundsBack = getContractualfundsRandomSampleGenerator();

        fundsmanagementWbs.setContractualfunds(contractualfundsBack);
        assertThat(fundsmanagementWbs.getContractualfunds()).isEqualTo(contractualfundsBack);

        fundsmanagementWbs.contractualfunds(null);
        assertThat(fundsmanagementWbs.getContractualfunds()).isNull();
    }

    @Test
    void fundsmanagementTest() throws Exception {
        FundsmanagementWbs fundsmanagementWbs = getFundsmanagementWbsRandomSampleGenerator();
        Fundsmanagement fundsmanagementBack = getFundsmanagementRandomSampleGenerator();

        fundsmanagementWbs.setFundsmanagement(fundsmanagementBack);
        assertThat(fundsmanagementWbs.getFundsmanagement()).isEqualTo(fundsmanagementBack);
        assertThat(fundsmanagementBack.getWbs()).isEqualTo(fundsmanagementWbs);

        fundsmanagementWbs.fundsmanagement(null);
        assertThat(fundsmanagementWbs.getFundsmanagement()).isNull();
        assertThat(fundsmanagementBack.getWbs()).isNull();
    }
}
