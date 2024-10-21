package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.KeyNodeInspectionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class KeyNodeInspectionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KeyNodeInspection.class);
        KeyNodeInspection keyNodeInspection1 = getKeyNodeInspectionSample1();
        KeyNodeInspection keyNodeInspection2 = new KeyNodeInspection();
        assertThat(keyNodeInspection1).isNotEqualTo(keyNodeInspection2);

        keyNodeInspection2.setId(keyNodeInspection1.getId());
        assertThat(keyNodeInspection1).isEqualTo(keyNodeInspection2);

        keyNodeInspection2 = getKeyNodeInspectionSample2();
        assertThat(keyNodeInspection1).isNotEqualTo(keyNodeInspection2);
    }
}
