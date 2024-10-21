package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DocumentTestSamples.*;
import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
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
    void creatoridTest() {
        Document document = getDocumentRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        document.setCreatorid(hrManagementBack);
        assertThat(document.getCreatorid()).isEqualTo(hrManagementBack);

        document.creatorid(null);
        assertThat(document.getCreatorid()).isNull();
    }

    @Test
    void projectwbsTest() {
        Document document = getDocumentRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        document.setProjectwbs(projectwbsBack);
        assertThat(document.getProjectwbs()).isEqualTo(projectwbsBack);

        document.projectwbs(null);
        assertThat(document.getProjectwbs()).isNull();
    }
}
