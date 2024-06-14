package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PbsmanageTestSamples.*;
import static com.cvicse.domain.PbssubmanageTestSamples.*;
import static com.cvicse.domain.WbsmanageTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PbssubmanageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pbssubmanage.class);
        Pbssubmanage pbssubmanage1 = getPbssubmanageSample1();
        Pbssubmanage pbssubmanage2 = new Pbssubmanage();
        assertThat(pbssubmanage1).isNotEqualTo(pbssubmanage2);

        pbssubmanage2.setId(pbssubmanage1.getId());
        assertThat(pbssubmanage1).isEqualTo(pbssubmanage2);

        pbssubmanage2 = getPbssubmanageSample2();
        assertThat(pbssubmanage1).isNotEqualTo(pbssubmanage2);
    }

    @Test
    void responsibleidTest() throws Exception {
        Pbssubmanage pbssubmanage = getPbssubmanageRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        pbssubmanage.setResponsibleid(officersBack);
        assertThat(pbssubmanage.getResponsibleid()).isEqualTo(officersBack);

        pbssubmanage.responsibleid(null);
        assertThat(pbssubmanage.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Pbssubmanage pbssubmanage = getPbssubmanageRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        pbssubmanage.setAuditorid(officersBack);
        assertThat(pbssubmanage.getAuditorid()).isEqualTo(officersBack);

        pbssubmanage.auditorid(null);
        assertThat(pbssubmanage.getAuditorid()).isNull();
    }

    @Test
    void pbsmanageTest() throws Exception {
        Pbssubmanage pbssubmanage = getPbssubmanageRandomSampleGenerator();
        Pbsmanage pbsmanageBack = getPbsmanageRandomSampleGenerator();

        pbssubmanage.setPbsmanage(pbsmanageBack);
        assertThat(pbssubmanage.getPbsmanage()).isEqualTo(pbsmanageBack);
        assertThat(pbsmanageBack.getPbssubmanage()).isEqualTo(pbssubmanage);

        pbssubmanage.pbsmanage(null);
        assertThat(pbssubmanage.getPbsmanage()).isNull();
        assertThat(pbsmanageBack.getPbssubmanage()).isNull();
    }

    @Test
    void wbsmanageTest() throws Exception {
        Pbssubmanage pbssubmanage = getPbssubmanageRandomSampleGenerator();
        Wbsmanage wbsmanageBack = getWbsmanageRandomSampleGenerator();

        pbssubmanage.setWbsmanage(wbsmanageBack);
        assertThat(pbssubmanage.getWbsmanage()).isEqualTo(wbsmanageBack);
        assertThat(wbsmanageBack.getPbssubmanage()).isEqualTo(pbssubmanage);

        pbssubmanage.wbsmanage(null);
        assertThat(pbssubmanage.getWbsmanage()).isNull();
        assertThat(wbsmanageBack.getPbssubmanage()).isNull();
    }
}
