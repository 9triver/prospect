package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.CustomerSatisfactionTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CustomerSatisfactionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerSatisfaction.class);
        CustomerSatisfaction customerSatisfaction1 = getCustomerSatisfactionSample1();
        CustomerSatisfaction customerSatisfaction2 = new CustomerSatisfaction();
        assertThat(customerSatisfaction1).isNotEqualTo(customerSatisfaction2);

        customerSatisfaction2.setId(customerSatisfaction1.getId());
        assertThat(customerSatisfaction1).isEqualTo(customerSatisfaction2);

        customerSatisfaction2 = getCustomerSatisfactionSample2();
        assertThat(customerSatisfaction1).isNotEqualTo(customerSatisfaction2);
    }

    @Test
    void wbsidTest() {
        CustomerSatisfaction customerSatisfaction = getCustomerSatisfactionRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        customerSatisfaction.setWbsid(projectwbsBack);
        assertThat(customerSatisfaction.getWbsid()).isEqualTo(projectwbsBack);

        customerSatisfaction.wbsid(null);
        assertThat(customerSatisfaction.getWbsid()).isNull();
    }
}
