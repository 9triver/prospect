package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.FrontlineTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FrontlineTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Frontline.class);
        Frontline frontline1 = getFrontlineSample1();
        Frontline frontline2 = new Frontline();
        assertThat(frontline1).isNotEqualTo(frontline2);

        frontline2.setId(frontline1.getId());
        assertThat(frontline1).isEqualTo(frontline2);

        frontline2 = getFrontlineSample2();
        assertThat(frontline1).isNotEqualTo(frontline2);
    }

    @Test
    void officersTest() {
        Frontline frontline = getFrontlineRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        frontline.addOfficers(officersBack);
        assertThat(frontline.getOfficers()).containsOnly(officersBack);
        assertThat(officersBack.getFrontlines()).containsOnly(frontline);

        frontline.removeOfficers(officersBack);
        assertThat(frontline.getOfficers()).doesNotContain(officersBack);
        assertThat(officersBack.getFrontlines()).doesNotContain(frontline);

        frontline.officers(new HashSet<>(Set.of(officersBack)));
        assertThat(frontline.getOfficers()).containsOnly(officersBack);
        assertThat(officersBack.getFrontlines()).containsOnly(frontline);

        frontline.setOfficers(new HashSet<>());
        assertThat(frontline.getOfficers()).doesNotContain(officersBack);
        assertThat(officersBack.getFrontlines()).doesNotContain(frontline);
    }
}
