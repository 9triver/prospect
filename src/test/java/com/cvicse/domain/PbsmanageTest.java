package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PbsmanageTestSamples.*;
import static com.cvicse.domain.PbssubmanageTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PbsmanageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pbsmanage.class);
        Pbsmanage pbsmanage1 = getPbsmanageSample1();
        Pbsmanage pbsmanage2 = new Pbsmanage();
        assertThat(pbsmanage1).isNotEqualTo(pbsmanage2);

        pbsmanage2.setId(pbsmanage1.getId());
        assertThat(pbsmanage1).isEqualTo(pbsmanage2);

        pbsmanage2 = getPbsmanageSample2();
        assertThat(pbsmanage1).isNotEqualTo(pbsmanage2);
    }

    @Test
    void pbssubmanageTest() throws Exception {
        Pbsmanage pbsmanage = getPbsmanageRandomSampleGenerator();
        Pbssubmanage pbssubmanageBack = getPbssubmanageRandomSampleGenerator();

        pbsmanage.setPbssubmanage(pbssubmanageBack);
        assertThat(pbsmanage.getPbssubmanage()).isEqualTo(pbssubmanageBack);

        pbsmanage.pbssubmanage(null);
        assertThat(pbsmanage.getPbssubmanage()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Pbsmanage pbsmanage = getPbsmanageRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        pbsmanage.setResponsibleid(officersBack);
        assertThat(pbsmanage.getResponsibleid()).isEqualTo(officersBack);

        pbsmanage.responsibleid(null);
        assertThat(pbsmanage.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Pbsmanage pbsmanage = getPbsmanageRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        pbsmanage.setAuditorid(officersBack);
        assertThat(pbsmanage.getAuditorid()).isEqualTo(officersBack);

        pbsmanage.auditorid(null);
        assertThat(pbsmanage.getAuditorid()).isNull();
    }
}
