package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractCostBudgetTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ContractCostBudgetTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractCostBudget.class);
        ContractCostBudget contractCostBudget1 = getContractCostBudgetSample1();
        ContractCostBudget contractCostBudget2 = new ContractCostBudget();
        assertThat(contractCostBudget1).isNotEqualTo(contractCostBudget2);

        contractCostBudget2.setId(contractCostBudget1.getId());
        assertThat(contractCostBudget1).isEqualTo(contractCostBudget2);

        contractCostBudget2 = getContractCostBudgetSample2();
        assertThat(contractCostBudget1).isNotEqualTo(contractCostBudget2);
    }

    @Test
    void projectwbsTest() {
        ContractCostBudget contractCostBudget = getContractCostBudgetRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        contractCostBudget.addProjectwbs(projectwbsBack);
        assertThat(contractCostBudget.getProjectwbs()).containsOnly(projectwbsBack);

        contractCostBudget.removeProjectwbs(projectwbsBack);
        assertThat(contractCostBudget.getProjectwbs()).doesNotContain(projectwbsBack);

        contractCostBudget.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(contractCostBudget.getProjectwbs()).containsOnly(projectwbsBack);

        contractCostBudget.setProjectwbs(new HashSet<>());
        assertThat(contractCostBudget.getProjectwbs()).doesNotContain(projectwbsBack);
    }
}
