package com.cvicse.domain;

import static com.cvicse.domain.AnnualplanTestSamples.*;
import static com.cvicse.domain.CycleplanTestSamples.*;
import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.FundsmanagementTestSamples.*;
import static com.cvicse.domain.MonthplanTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
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
    void projectTest() throws Exception {
        Document document = getDocumentRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        document.setProject(projectBack);
        assertThat(document.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getDocument()).isEqualTo(document);

        document.project(null);
        assertThat(document.getProject()).isNull();
        assertThat(projectBack.getDocument()).isNull();
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
    void fundsmanagementTest() throws Exception {
        Document document = getDocumentRandomSampleGenerator();
        Fundsmanagement fundsmanagementBack = getFundsmanagementRandomSampleGenerator();

        document.setFundsmanagement(fundsmanagementBack);
        assertThat(document.getFundsmanagement()).isEqualTo(fundsmanagementBack);
        assertThat(fundsmanagementBack.getDocument()).isEqualTo(document);

        document.fundsmanagement(null);
        assertThat(document.getFundsmanagement()).isNull();
        assertThat(fundsmanagementBack.getDocument()).isNull();
    }
}
