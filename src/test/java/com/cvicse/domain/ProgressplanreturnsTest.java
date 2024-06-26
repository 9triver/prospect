package com.cvicse.domain;

import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.ProgressplanTestSamples.*;
import static com.cvicse.domain.ProgressplanreturnsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProgressplanreturnsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Progressplanreturns.class);
        Progressplanreturns progressplanreturns1 = getProgressplanreturnsSample1();
        Progressplanreturns progressplanreturns2 = new Progressplanreturns();
        assertThat(progressplanreturns1).isNotEqualTo(progressplanreturns2);

        progressplanreturns2.setId(progressplanreturns1.getId());
        assertThat(progressplanreturns1).isEqualTo(progressplanreturns2);

        progressplanreturns2 = getProgressplanreturnsSample2();
        assertThat(progressplanreturns1).isNotEqualTo(progressplanreturns2);
    }

    @Test
    void progressplanTest() throws Exception {
        Progressplanreturns progressplanreturns = getProgressplanreturnsRandomSampleGenerator();
        Progressplan progressplanBack = getProgressplanRandomSampleGenerator();

        progressplanreturns.setProgressplan(progressplanBack);
        assertThat(progressplanreturns.getProgressplan()).isEqualTo(progressplanBack);

        progressplanreturns.progressplan(null);
        assertThat(progressplanreturns.getProgressplan()).isNull();
    }

    @Test
    void documentTest() throws Exception {
        Progressplanreturns progressplanreturns = getProgressplanreturnsRandomSampleGenerator();
        Document documentBack = getDocumentRandomSampleGenerator();

        progressplanreturns.setDocument(documentBack);
        assertThat(progressplanreturns.getDocument()).isEqualTo(documentBack);

        progressplanreturns.document(null);
        assertThat(progressplanreturns.getDocument()).isNull();
    }
}
