package com.cvicse.domain;

import static com.cvicse.domain.AnnualplanTestSamples.*;
import static com.cvicse.domain.AuditedbudgetTestSamples.*;
import static com.cvicse.domain.CycleplanTestSamples.*;
import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.MonthplanTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProgressplanreturnsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Document.class);
        Document document1 = getDocumentSample1();
        Document document2 = new Document();
        assertThat(document1).isNotEqualTo(document2);

        document2.setId(document1.getId());
        assertThat(document1).isEqualTo(document2);

        document2 = getDocumentSample2();
        assertThat(document1).isNotEqualTo(document2);
    }

    @Test
    void creatoridTest() throws Exception {
        Document document = getDocumentRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        document.setCreatorid(officersBack);
        assertThat(document.getCreatorid()).isEqualTo(officersBack);

        document.creatorid(null);
        assertThat(document.getCreatorid()).isNull();
    }

    @Test
    void cycleplanTest() throws Exception {
        Document document = getDocumentRandomSampleGenerator();
        Cycleplan cycleplanBack = getCycleplanRandomSampleGenerator();

        document.setCycleplan(cycleplanBack);
        assertThat(document.getCycleplan()).isEqualTo(cycleplanBack);
        assertThat(cycleplanBack.getDocument()).isEqualTo(document);

        document.cycleplan(null);
        assertThat(document.getCycleplan()).isNull();
        assertThat(cycleplanBack.getDocument()).isNull();
    }

    @Test
    void annualplanTest() throws Exception {
        Document document = getDocumentRandomSampleGenerator();
        Annualplan annualplanBack = getAnnualplanRandomSampleGenerator();

        document.setAnnualplan(annualplanBack);
        assertThat(document.getAnnualplan()).isEqualTo(annualplanBack);
        assertThat(annualplanBack.getDocument()).isEqualTo(document);

        document.annualplan(null);
        assertThat(document.getAnnualplan()).isNull();
        assertThat(annualplanBack.getDocument()).isNull();
    }

    @Test
    void monthplanTest() throws Exception {
        Document document = getDocumentRandomSampleGenerator();
        Monthplan monthplanBack = getMonthplanRandomSampleGenerator();

        document.setMonthplan(monthplanBack);
        assertThat(document.getMonthplan()).isEqualTo(monthplanBack);
        assertThat(monthplanBack.getDocument()).isEqualTo(document);

        document.monthplan(null);
        assertThat(document.getMonthplan()).isNull();
        assertThat(monthplanBack.getDocument()).isNull();
    }

    @Test
    void progressplanreturnsTest() throws Exception {
        Document document = getDocumentRandomSampleGenerator();
        Progressplanreturns progressplanreturnsBack = getProgressplanreturnsRandomSampleGenerator();

        document.setProgressplanreturns(progressplanreturnsBack);
        assertThat(document.getProgressplanreturns()).isEqualTo(progressplanreturnsBack);
        assertThat(progressplanreturnsBack.getDocument()).isEqualTo(document);

        document.progressplanreturns(null);
        assertThat(document.getProgressplanreturns()).isNull();
        assertThat(progressplanreturnsBack.getDocument()).isNull();
    }

    @Test
    void auditedbudgetTest() throws Exception {
        Document document = getDocumentRandomSampleGenerator();
        Auditedbudget auditedbudgetBack = getAuditedbudgetRandomSampleGenerator();

        document.setAuditedbudget(auditedbudgetBack);
        assertThat(document.getAuditedbudget()).isEqualTo(auditedbudgetBack);
        assertThat(auditedbudgetBack.getDocument()).isEqualTo(document);

        document.auditedbudget(null);
        assertThat(document.getAuditedbudget()).isNull();
        assertThat(auditedbudgetBack.getDocument()).isNull();
    }
}
