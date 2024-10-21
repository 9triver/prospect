package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.QualitytozeroTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QualitytozeroTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Qualitytozero.class);
        Qualitytozero qualitytozero1 = getQualitytozeroSample1();
        Qualitytozero qualitytozero2 = new Qualitytozero();
        assertThat(qualitytozero1).isNotEqualTo(qualitytozero2);

        qualitytozero2.setId(qualitytozero1.getId());
        assertThat(qualitytozero1).isEqualTo(qualitytozero2);

        qualitytozero2 = getQualitytozeroSample2();
        assertThat(qualitytozero1).isNotEqualTo(qualitytozero2);
    }

    @Test
    void workbagTest() {
        Qualitytozero qualitytozero = getQualitytozeroRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        qualitytozero.setWorkbag(workbagBack);
        assertThat(qualitytozero.getWorkbag()).isEqualTo(workbagBack);

        qualitytozero.workbag(null);
        assertThat(qualitytozero.getWorkbag()).isNull();
    }
}
