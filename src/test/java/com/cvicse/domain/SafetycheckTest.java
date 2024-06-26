package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.SafetycheckTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SafetycheckTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Safetycheck.class);
        Safetycheck safetycheck1 = getSafetycheckSample1();
        Safetycheck safetycheck2 = new Safetycheck();
        assertThat(safetycheck1).isNotEqualTo(safetycheck2);

        safetycheck2.setId(safetycheck1.getId());
        assertThat(safetycheck1).isEqualTo(safetycheck2);

        safetycheck2 = getSafetycheckSample2();
        assertThat(safetycheck1).isNotEqualTo(safetycheck2);
    }

    @Test
    void auditoridTest() throws Exception {
        Safetycheck safetycheck = getSafetycheckRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        safetycheck.setAuditorid(officersBack);
        assertThat(safetycheck.getAuditorid()).isEqualTo(officersBack);

        safetycheck.auditorid(null);
        assertThat(safetycheck.getAuditorid()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Safetycheck safetycheck = getSafetycheckRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        safetycheck.setResponsibleid(officersBack);
        assertThat(safetycheck.getResponsibleid()).isEqualTo(officersBack);

        safetycheck.responsibleid(null);
        assertThat(safetycheck.getResponsibleid()).isNull();
    }
}
