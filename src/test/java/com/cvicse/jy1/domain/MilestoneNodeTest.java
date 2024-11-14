package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.MilestoneNodeTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingContractTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MilestoneNodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MilestoneNode.class);
        MilestoneNode milestoneNode1 = getMilestoneNodeSample1();
        MilestoneNode milestoneNode2 = new MilestoneNode();
        assertThat(milestoneNode1).isNotEqualTo(milestoneNode2);

        milestoneNode2.setId(milestoneNode1.getId());
        assertThat(milestoneNode1).isEqualTo(milestoneNode2);

        milestoneNode2 = getMilestoneNodeSample2();
        assertThat(milestoneNode1).isNotEqualTo(milestoneNode2);
    }

    @Test
    void outsourcingContractTest() {
        MilestoneNode milestoneNode = getMilestoneNodeRandomSampleGenerator();
        OutsourcingContract outsourcingContractBack = getOutsourcingContractRandomSampleGenerator();

        milestoneNode.addOutsourcingContract(outsourcingContractBack);
        assertThat(milestoneNode.getOutsourcingContracts()).containsOnly(outsourcingContractBack);
        assertThat(outsourcingContractBack.getMilestoneNodes()).containsOnly(milestoneNode);

        milestoneNode.removeOutsourcingContract(outsourcingContractBack);
        assertThat(milestoneNode.getOutsourcingContracts()).doesNotContain(outsourcingContractBack);
        assertThat(outsourcingContractBack.getMilestoneNodes()).doesNotContain(milestoneNode);

        milestoneNode.outsourcingContracts(new HashSet<>(Set.of(outsourcingContractBack)));
        assertThat(milestoneNode.getOutsourcingContracts()).containsOnly(outsourcingContractBack);
        assertThat(outsourcingContractBack.getMilestoneNodes()).containsOnly(milestoneNode);

        milestoneNode.setOutsourcingContracts(new HashSet<>());
        assertThat(milestoneNode.getOutsourcingContracts()).doesNotContain(outsourcingContractBack);
        assertThat(outsourcingContractBack.getMilestoneNodes()).doesNotContain(milestoneNode);
    }
}
