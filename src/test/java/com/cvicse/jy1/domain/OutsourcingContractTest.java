package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.MilestoneNodeTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingContractTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OutsourcingContractTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutsourcingContract.class);
        OutsourcingContract outsourcingContract1 = getOutsourcingContractSample1();
        OutsourcingContract outsourcingContract2 = new OutsourcingContract();
        assertThat(outsourcingContract1).isNotEqualTo(outsourcingContract2);

        outsourcingContract2.setId(outsourcingContract1.getId());
        assertThat(outsourcingContract1).isEqualTo(outsourcingContract2);

        outsourcingContract2 = getOutsourcingContractSample2();
        assertThat(outsourcingContract1).isNotEqualTo(outsourcingContract2);
    }

    @Test
    void workbagTest() {
        OutsourcingContract outsourcingContract = getOutsourcingContractRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        outsourcingContract.setWorkbag(workbagBack);
        assertThat(outsourcingContract.getWorkbag()).isEqualTo(workbagBack);

        outsourcingContract.workbag(null);
        assertThat(outsourcingContract.getWorkbag()).isNull();
    }

    @Test
    void milestoneNodeTest() {
        OutsourcingContract outsourcingContract = getOutsourcingContractRandomSampleGenerator();
        MilestoneNode milestoneNodeBack = getMilestoneNodeRandomSampleGenerator();

        outsourcingContract.addMilestoneNode(milestoneNodeBack);
        assertThat(outsourcingContract.getMilestoneNodes()).containsOnly(milestoneNodeBack);

        outsourcingContract.removeMilestoneNode(milestoneNodeBack);
        assertThat(outsourcingContract.getMilestoneNodes()).doesNotContain(milestoneNodeBack);

        outsourcingContract.milestoneNodes(new HashSet<>(Set.of(milestoneNodeBack)));
        assertThat(outsourcingContract.getMilestoneNodes()).containsOnly(milestoneNodeBack);

        outsourcingContract.setMilestoneNodes(new HashSet<>());
        assertThat(outsourcingContract.getMilestoneNodes()).doesNotContain(milestoneNodeBack);
    }
}
