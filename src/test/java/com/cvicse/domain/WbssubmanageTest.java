package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.WbsmanageTestSamples.*;
import static com.cvicse.domain.WbssubmanageTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WbssubmanageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wbssubmanage.class);
        Wbssubmanage wbssubmanage1 = getWbssubmanageSample1();
        Wbssubmanage wbssubmanage2 = new Wbssubmanage();
        assertThat(wbssubmanage1).isNotEqualTo(wbssubmanage2);

        wbssubmanage2.setId(wbssubmanage1.getId());
        assertThat(wbssubmanage1).isEqualTo(wbssubmanage2);

        wbssubmanage2 = getWbssubmanageSample2();
        assertThat(wbssubmanage1).isNotEqualTo(wbssubmanage2);
    }

    @Test
    void responsibleidTest() throws Exception {
        Wbssubmanage wbssubmanage = getWbssubmanageRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        wbssubmanage.setResponsibleid(officersBack);
        assertThat(wbssubmanage.getResponsibleid()).isEqualTo(officersBack);

        wbssubmanage.responsibleid(null);
        assertThat(wbssubmanage.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Wbssubmanage wbssubmanage = getWbssubmanageRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        wbssubmanage.setAuditorid(officersBack);
        assertThat(wbssubmanage.getAuditorid()).isEqualTo(officersBack);

        wbssubmanage.auditorid(null);
        assertThat(wbssubmanage.getAuditorid()).isNull();
    }

    @Test
    void wbsmanageTest() throws Exception {
        Wbssubmanage wbssubmanage = getWbssubmanageRandomSampleGenerator();
        Wbsmanage wbsmanageBack = getWbsmanageRandomSampleGenerator();

        wbssubmanage.setWbsmanage(wbsmanageBack);
        assertThat(wbssubmanage.getWbsmanage()).isEqualTo(wbsmanageBack);
        assertThat(wbsmanageBack.getWbssubmanage()).isEqualTo(wbssubmanage);

        wbssubmanage.wbsmanage(null);
        assertThat(wbssubmanage.getWbsmanage()).isNull();
        assertThat(wbsmanageBack.getWbssubmanage()).isNull();
    }
}
