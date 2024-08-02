package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractTestSamples.*;
import static com.cvicse.jy1.domain.CostControlSystemTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ContractTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contract.class);
        Contract contract1 = getContractSample1();
        Contract contract2 = new Contract();
        assertThat(contract1).isNotEqualTo(contract2);

        contract2.setId(contract1.getId());
        assertThat(contract1).isEqualTo(contract2);

        contract2 = getContractSample2();
        assertThat(contract1).isNotEqualTo(contract2);
    }

    @Test
    void costControlSystemTest() {
        Contract contract = getContractRandomSampleGenerator();
        CostControlSystem costControlSystemBack = getCostControlSystemRandomSampleGenerator();

        contract.addCostControlSystem(costControlSystemBack);
        assertThat(contract.getCostControlSystems()).containsOnly(costControlSystemBack);
        assertThat(costControlSystemBack.getContracts()).containsOnly(contract);

        contract.removeCostControlSystem(costControlSystemBack);
        assertThat(contract.getCostControlSystems()).doesNotContain(costControlSystemBack);
        assertThat(costControlSystemBack.getContracts()).doesNotContain(contract);

        contract.costControlSystems(new HashSet<>(Set.of(costControlSystemBack)));
        assertThat(contract.getCostControlSystems()).containsOnly(costControlSystemBack);
        assertThat(costControlSystemBack.getContracts()).containsOnly(contract);

        contract.setCostControlSystems(new HashSet<>());
        assertThat(contract.getCostControlSystems()).doesNotContain(costControlSystemBack);
        assertThat(costControlSystemBack.getContracts()).doesNotContain(contract);
    }
}
