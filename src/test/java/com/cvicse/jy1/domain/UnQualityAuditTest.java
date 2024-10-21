package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.UnQualityAuditTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UnQualityAuditTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnQualityAudit.class);
        UnQualityAudit unQualityAudit1 = getUnQualityAuditSample1();
        UnQualityAudit unQualityAudit2 = new UnQualityAudit();
        assertThat(unQualityAudit1).isNotEqualTo(unQualityAudit2);

        unQualityAudit2.setId(unQualityAudit1.getId());
        assertThat(unQualityAudit1).isEqualTo(unQualityAudit2);

        unQualityAudit2 = getUnQualityAuditSample2();
        assertThat(unQualityAudit1).isNotEqualTo(unQualityAudit2);
    }

    @Test
    void workbagTest() {
        UnQualityAudit unQualityAudit = getUnQualityAuditRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        unQualityAudit.setWorkbag(workbagBack);
        assertThat(unQualityAudit.getWorkbag()).isEqualTo(workbagBack);

        unQualityAudit.workbag(null);
        assertThat(unQualityAudit.getWorkbag()).isNull();
    }
}
