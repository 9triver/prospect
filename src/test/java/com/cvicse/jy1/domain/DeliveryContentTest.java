package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DeliveryContentTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingContractTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryContentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryContent.class);
        DeliveryContent deliveryContent1 = getDeliveryContentSample1();
        DeliveryContent deliveryContent2 = new DeliveryContent();
        assertThat(deliveryContent1).isNotEqualTo(deliveryContent2);

        deliveryContent2.setId(deliveryContent1.getId());
        assertThat(deliveryContent1).isEqualTo(deliveryContent2);

        deliveryContent2 = getDeliveryContentSample2();
        assertThat(deliveryContent1).isNotEqualTo(deliveryContent2);
    }

    @Test
    void outsourcingContractTest() {
        DeliveryContent deliveryContent = getDeliveryContentRandomSampleGenerator();
        OutsourcingContract outsourcingContractBack = getOutsourcingContractRandomSampleGenerator();

        deliveryContent.setOutsourcingContract(outsourcingContractBack);
        assertThat(deliveryContent.getOutsourcingContract()).isEqualTo(outsourcingContractBack);

        deliveryContent.outsourcingContract(null);
        assertThat(deliveryContent.getOutsourcingContract()).isNull();
    }
}
