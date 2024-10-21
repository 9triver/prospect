package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.TechnicalConditionTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TechnicalConditionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TechnicalCondition.class);
        TechnicalCondition technicalCondition1 = getTechnicalConditionSample1();
        TechnicalCondition technicalCondition2 = new TechnicalCondition();
        assertThat(technicalCondition1).isNotEqualTo(technicalCondition2);

        technicalCondition2.setId(technicalCondition1.getId());
        assertThat(technicalCondition1).isEqualTo(technicalCondition2);

        technicalCondition2 = getTechnicalConditionSample2();
        assertThat(technicalCondition1).isNotEqualTo(technicalCondition2);
    }

    @Test
    void workbagTest() {
        TechnicalCondition technicalCondition = getTechnicalConditionRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        technicalCondition.setWorkbag(workbagBack);
        assertThat(technicalCondition.getWorkbag()).isEqualTo(workbagBack);

        technicalCondition.workbag(null);
        assertThat(technicalCondition.getWorkbag()).isNull();
    }
}
