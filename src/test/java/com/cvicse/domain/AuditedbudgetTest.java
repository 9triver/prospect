package com.cvicse.domain;

import static com.cvicse.domain.AuditedbudgetTestSamples.*;
import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.FundsavailabilityTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.TotalbudgetTestSamples.*;
import static com.cvicse.domain.UnitbudgetTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AuditedbudgetTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Auditedbudget.class);
        Auditedbudget auditedbudget1 = getAuditedbudgetSample1();
        Auditedbudget auditedbudget2 = new Auditedbudget();
        assertThat(auditedbudget1).isNotEqualTo(auditedbudget2);

        auditedbudget2.setId(auditedbudget1.getId());
        assertThat(auditedbudget1).isEqualTo(auditedbudget2);

        auditedbudget2 = getAuditedbudgetSample2();
        assertThat(auditedbudget1).isNotEqualTo(auditedbudget2);
    }

    @Test
    void totalbudgetTest() throws Exception {
        Auditedbudget auditedbudget = getAuditedbudgetRandomSampleGenerator();
        Totalbudget totalbudgetBack = getTotalbudgetRandomSampleGenerator();

        auditedbudget.setTotalbudget(totalbudgetBack);
        assertThat(auditedbudget.getTotalbudget()).isEqualTo(totalbudgetBack);

        auditedbudget.totalbudget(null);
        assertThat(auditedbudget.getTotalbudget()).isNull();
    }

    @Test
    void unitbudgetTest() throws Exception {
        Auditedbudget auditedbudget = getAuditedbudgetRandomSampleGenerator();
        Unitbudget unitbudgetBack = getUnitbudgetRandomSampleGenerator();

        auditedbudget.setUnitbudget(unitbudgetBack);
        assertThat(auditedbudget.getUnitbudget()).isEqualTo(unitbudgetBack);

        auditedbudget.unitbudget(null);
        assertThat(auditedbudget.getUnitbudget()).isNull();
    }

    @Test
    void documentTest() throws Exception {
        Auditedbudget auditedbudget = getAuditedbudgetRandomSampleGenerator();
        Document documentBack = getDocumentRandomSampleGenerator();

        auditedbudget.setDocument(documentBack);
        assertThat(auditedbudget.getDocument()).isEqualTo(documentBack);

        auditedbudget.document(null);
        assertThat(auditedbudget.getDocument()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        Auditedbudget auditedbudget = getAuditedbudgetRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        auditedbudget.setCreatorid(officersBack);
        assertThat(auditedbudget.getCreatorid()).isEqualTo(officersBack);

        auditedbudget.creatorid(null);
        assertThat(auditedbudget.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Auditedbudget auditedbudget = getAuditedbudgetRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        auditedbudget.setAuditorid(officersBack);
        assertThat(auditedbudget.getAuditorid()).isEqualTo(officersBack);

        auditedbudget.auditorid(null);
        assertThat(auditedbudget.getAuditorid()).isNull();
    }

    @Test
    void fundsavailabilityTest() throws Exception {
        Auditedbudget auditedbudget = getAuditedbudgetRandomSampleGenerator();
        Fundsavailability fundsavailabilityBack = getFundsavailabilityRandomSampleGenerator();

        auditedbudget.setFundsavailability(fundsavailabilityBack);
        assertThat(auditedbudget.getFundsavailability()).isEqualTo(fundsavailabilityBack);
        assertThat(fundsavailabilityBack.getAuditedbudget()).isEqualTo(auditedbudget);

        auditedbudget.fundsavailability(null);
        assertThat(auditedbudget.getFundsavailability()).isNull();
        assertThat(fundsavailabilityBack.getAuditedbudget()).isNull();
    }
}
