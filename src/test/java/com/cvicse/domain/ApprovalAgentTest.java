package com.cvicse.domain;

import static com.cvicse.domain.ApprovalAgentTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ApprovalAgentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApprovalAgent.class);
        ApprovalAgent approvalAgent1 = getApprovalAgentSample1();
        ApprovalAgent approvalAgent2 = new ApprovalAgent();
        assertThat(approvalAgent1).isNotEqualTo(approvalAgent2);

        approvalAgent2.setId(approvalAgent1.getId());
        assertThat(approvalAgent1).isEqualTo(approvalAgent2);

        approvalAgent2 = getApprovalAgentSample2();
        assertThat(approvalAgent1).isNotEqualTo(approvalAgent2);
    }

    @Test
    void originalapprovalidTest() throws Exception {
        ApprovalAgent approvalAgent = getApprovalAgentRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        approvalAgent.setOriginalapprovalid(officersBack);
        assertThat(approvalAgent.getOriginalapprovalid()).isEqualTo(officersBack);

        approvalAgent.originalapprovalid(null);
        assertThat(approvalAgent.getOriginalapprovalid()).isNull();
    }
}
