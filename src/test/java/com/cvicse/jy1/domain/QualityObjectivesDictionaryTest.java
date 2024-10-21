package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.QualityObjectivesDictionaryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QualityObjectivesDictionaryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QualityObjectivesDictionary.class);
        QualityObjectivesDictionary qualityObjectivesDictionary1 = getQualityObjectivesDictionarySample1();
        QualityObjectivesDictionary qualityObjectivesDictionary2 = new QualityObjectivesDictionary();
        assertThat(qualityObjectivesDictionary1).isNotEqualTo(qualityObjectivesDictionary2);

        qualityObjectivesDictionary2.setId(qualityObjectivesDictionary1.getId());
        assertThat(qualityObjectivesDictionary1).isEqualTo(qualityObjectivesDictionary2);

        qualityObjectivesDictionary2 = getQualityObjectivesDictionarySample2();
        assertThat(qualityObjectivesDictionary1).isNotEqualTo(qualityObjectivesDictionary2);
    }
}
