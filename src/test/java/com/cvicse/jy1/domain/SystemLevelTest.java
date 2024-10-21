package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.SystemLevelTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SystemLevelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemLevel.class);
        SystemLevel systemLevel1 = getSystemLevelSample1();
        SystemLevel systemLevel2 = new SystemLevel();
        assertThat(systemLevel1).isNotEqualTo(systemLevel2);

        systemLevel2.setId(systemLevel1.getId());
        assertThat(systemLevel1).isEqualTo(systemLevel2);

        systemLevel2 = getSystemLevelSample2();
        assertThat(systemLevel1).isNotEqualTo(systemLevel2);
    }
}
