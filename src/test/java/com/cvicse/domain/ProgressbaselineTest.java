package com.cvicse.domain;

import static com.cvicse.domain.ProgressbaselineTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProgressbaselineTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Progressbaseline.class);
        Progressbaseline progressbaseline1 = getProgressbaselineSample1();
        Progressbaseline progressbaseline2 = new Progressbaseline();
        assertThat(progressbaseline1).isNotEqualTo(progressbaseline2);

        progressbaseline2.setId(progressbaseline1.getId());
        assertThat(progressbaseline1).isEqualTo(progressbaseline2);

        progressbaseline2 = getProgressbaselineSample2();
        assertThat(progressbaseline1).isNotEqualTo(progressbaseline2);
    }
}
