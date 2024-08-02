package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingPurchasePlanTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OutsourcingPurchasePlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutsourcingPurchasePlan.class);
        OutsourcingPurchasePlan outsourcingPurchasePlan1 = getOutsourcingPurchasePlanSample1();
        OutsourcingPurchasePlan outsourcingPurchasePlan2 = new OutsourcingPurchasePlan();
        assertThat(outsourcingPurchasePlan1).isNotEqualTo(outsourcingPurchasePlan2);

        outsourcingPurchasePlan2.setId(outsourcingPurchasePlan1.getId());
        assertThat(outsourcingPurchasePlan1).isEqualTo(outsourcingPurchasePlan2);

        outsourcingPurchasePlan2 = getOutsourcingPurchasePlanSample2();
        assertThat(outsourcingPurchasePlan1).isNotEqualTo(outsourcingPurchasePlan2);
    }

    @Test
    void responsiblepersonTest() {
        OutsourcingPurchasePlan outsourcingPurchasePlan = getOutsourcingPurchasePlanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingPurchasePlan.setResponsibleperson(officersBack);
        assertThat(outsourcingPurchasePlan.getResponsibleperson()).isEqualTo(officersBack);

        outsourcingPurchasePlan.responsibleperson(null);
        assertThat(outsourcingPurchasePlan.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        OutsourcingPurchasePlan outsourcingPurchasePlan = getOutsourcingPurchasePlanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingPurchasePlan.setAuditorid(officersBack);
        assertThat(outsourcingPurchasePlan.getAuditorid()).isEqualTo(officersBack);

        outsourcingPurchasePlan.auditorid(null);
        assertThat(outsourcingPurchasePlan.getAuditorid()).isNull();
    }

    @Test
    void projectwbsTest() {
        OutsourcingPurchasePlan outsourcingPurchasePlan = getOutsourcingPurchasePlanRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        outsourcingPurchasePlan.addProjectwbs(projectwbsBack);
        assertThat(outsourcingPurchasePlan.getProjectwbs()).containsOnly(projectwbsBack);

        outsourcingPurchasePlan.removeProjectwbs(projectwbsBack);
        assertThat(outsourcingPurchasePlan.getProjectwbs()).doesNotContain(projectwbsBack);

        outsourcingPurchasePlan.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(outsourcingPurchasePlan.getProjectwbs()).containsOnly(projectwbsBack);

        outsourcingPurchasePlan.setProjectwbs(new HashSet<>());
        assertThat(outsourcingPurchasePlan.getProjectwbs()).doesNotContain(projectwbsBack);
    }
}
