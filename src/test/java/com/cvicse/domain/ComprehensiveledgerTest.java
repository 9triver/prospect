package com.cvicse.domain;

import static com.cvicse.domain.ComprehensiveledgerTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ComprehensiveledgerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comprehensiveledger.class);
        Comprehensiveledger comprehensiveledger1 = getComprehensiveledgerSample1();
        Comprehensiveledger comprehensiveledger2 = new Comprehensiveledger();
        assertThat(comprehensiveledger1).isNotEqualTo(comprehensiveledger2);

        comprehensiveledger2.setId(comprehensiveledger1.getId());
        assertThat(comprehensiveledger1).isEqualTo(comprehensiveledger2);

        comprehensiveledger2 = getComprehensiveledgerSample2();
        assertThat(comprehensiveledger1).isNotEqualTo(comprehensiveledger2);
    }
}
