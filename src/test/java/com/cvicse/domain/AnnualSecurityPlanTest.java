package com.cvicse.domain;

import static com.cvicse.domain.AnnualSecurityPlanTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnnualSecurityPlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnnualSecurityPlan.class);
        AnnualSecurityPlan annualSecurityPlan1 = getAnnualSecurityPlanSample1();
        AnnualSecurityPlan annualSecurityPlan2 = new AnnualSecurityPlan();
        assertThat(annualSecurityPlan1).isNotEqualTo(annualSecurityPlan2);

        annualSecurityPlan2.setId(annualSecurityPlan1.getId());
        assertThat(annualSecurityPlan1).isEqualTo(annualSecurityPlan2);

        annualSecurityPlan2 = getAnnualSecurityPlanSample2();
        assertThat(annualSecurityPlan1).isNotEqualTo(annualSecurityPlan2);
    }

    @Test
    void projectTest() throws Exception {
        AnnualSecurityPlan annualSecurityPlan = getAnnualSecurityPlanRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        annualSecurityPlan.setProject(projectBack);
        assertThat(annualSecurityPlan.getProject()).isEqualTo(projectBack);

        annualSecurityPlan.project(null);
        assertThat(annualSecurityPlan.getProject()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        AnnualSecurityPlan annualSecurityPlan = getAnnualSecurityPlanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        annualSecurityPlan.setCreatorid(officersBack);
        assertThat(annualSecurityPlan.getCreatorid()).isEqualTo(officersBack);

        annualSecurityPlan.creatorid(null);
        assertThat(annualSecurityPlan.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        AnnualSecurityPlan annualSecurityPlan = getAnnualSecurityPlanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        annualSecurityPlan.setAuditorid(officersBack);
        assertThat(annualSecurityPlan.getAuditorid()).isEqualTo(officersBack);

        annualSecurityPlan.auditorid(null);
        assertThat(annualSecurityPlan.getAuditorid()).isNull();
    }
}
