package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.UnQualityAuditTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
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
    void inspectorTest() throws Exception {
        UnQualityAudit unQualityAudit = getUnQualityAuditRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        unQualityAudit.setInspector(officersBack);
        assertThat(unQualityAudit.getInspector()).isEqualTo(officersBack);

        unQualityAudit.inspector(null);
        assertThat(unQualityAudit.getInspector()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        UnQualityAudit unQualityAudit = getUnQualityAuditRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        unQualityAudit.setAuditorid(officersBack);
        assertThat(unQualityAudit.getAuditorid()).isEqualTo(officersBack);

        unQualityAudit.auditorid(null);
        assertThat(unQualityAudit.getAuditorid()).isNull();
    }
}
