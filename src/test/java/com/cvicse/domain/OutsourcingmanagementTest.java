package com.cvicse.domain;

import static com.cvicse.domain.OutsourcingmanagementTestSamples.*;
import static com.cvicse.domain.OutsourcingmanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OutsourcingmanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Outsourcingmanagement.class);
        Outsourcingmanagement outsourcingmanagement1 = getOutsourcingmanagementSample1();
        Outsourcingmanagement outsourcingmanagement2 = new Outsourcingmanagement();
        assertThat(outsourcingmanagement1).isNotEqualTo(outsourcingmanagement2);

        outsourcingmanagement2.setId(outsourcingmanagement1.getId());
        assertThat(outsourcingmanagement1).isEqualTo(outsourcingmanagement2);

        outsourcingmanagement2 = getOutsourcingmanagementSample2();
        assertThat(outsourcingmanagement1).isNotEqualTo(outsourcingmanagement2);
    }

    @Test
    void wbsTest() throws Exception {
        Outsourcingmanagement outsourcingmanagement = getOutsourcingmanagementRandomSampleGenerator();
        OutsourcingmanagementWbs outsourcingmanagementWbsBack = getOutsourcingmanagementWbsRandomSampleGenerator();

        outsourcingmanagement.setWbs(outsourcingmanagementWbsBack);
        assertThat(outsourcingmanagement.getWbs()).isEqualTo(outsourcingmanagementWbsBack);

        outsourcingmanagement.wbs(null);
        assertThat(outsourcingmanagement.getWbs()).isNull();
    }
}
