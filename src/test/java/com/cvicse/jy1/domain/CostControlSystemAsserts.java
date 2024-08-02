package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class CostControlSystemAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCostControlSystemAllPropertiesEquals(CostControlSystem expected, CostControlSystem actual) {
        assertCostControlSystemAutoGeneratedPropertiesEquals(expected, actual);
        assertCostControlSystemAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCostControlSystemAllUpdatablePropertiesEquals(CostControlSystem expected, CostControlSystem actual) {
        assertCostControlSystemUpdatableFieldsEquals(expected, actual);
        assertCostControlSystemUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCostControlSystemAutoGeneratedPropertiesEquals(CostControlSystem expected, CostControlSystem actual) {
        assertThat(expected)
            .as("Verify CostControlSystem auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCostControlSystemUpdatableFieldsEquals(CostControlSystem expected, CostControlSystem actual) {
        assertThat(expected)
            .as("Verify CostControlSystem relevant properties")
            .satisfies(e -> assertThat(e.getType()).as("check type").isEqualTo(actual.getType()))
            .satisfies(e -> assertThat(e.getSubject()).as("check subject").isEqualTo(actual.getSubject()))
            .satisfies(
                e ->
                    assertThat(e.getImplementedamount())
                        .as("check implementedamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getImplementedamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getApprovedamount())
                        .as("check approvedamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getApprovedamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getPendingimplementationamount())
                        .as("check pendingimplementationamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getPendingimplementationamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getContractpaymentamount())
                        .as("check contractpaymentamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getContractpaymentamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getManagementregistrationnumber())
                        .as("check managementregistrationnumber")
                        .isEqualTo(actual.getManagementregistrationnumber())
            )
            .satisfies(
                e ->
                    assertThat(e.getFinancialregistrationnumber())
                        .as("check financialregistrationnumber")
                        .isEqualTo(actual.getFinancialregistrationnumber())
            )
            .satisfies(
                e ->
                    assertThat(e.getContractbudgetamount())
                        .as("check contractbudgetamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getContractbudgetamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getContractsigningamount())
                        .as("check contractsigningamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getContractsigningamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getContractsettlementamount())
                        .as("check contractsettlementamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getContractsettlementamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getUnforeseeableamount())
                        .as("check unforeseeableamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getUnforeseeableamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getInvoicepaymentamount())
                        .as("check invoicepaymentamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getInvoicepaymentamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getLoanpaymentamount())
                        .as("check loanpaymentamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getLoanpaymentamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getAccountoutstandingamount())
                        .as("check accountoutstandingamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getAccountoutstandingamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getPendingpaymentamount())
                        .as("check pendingpaymentamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getPendingpaymentamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getPendinginvoiceamount())
                        .as("check pendinginvoiceamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getPendinginvoiceamount())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCostControlSystemUpdatableRelationshipsEquals(CostControlSystem expected, CostControlSystem actual) {
        assertThat(expected)
            .as("Verify CostControlSystem relationships")
            .satisfies(e -> assertThat(e.getResponsibleperson()).as("check responsibleperson").isEqualTo(actual.getResponsibleperson()))
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()))
            .satisfies(e -> assertThat(e.getProjectwbs()).as("check projectwbs").isEqualTo(actual.getProjectwbs()))
            .satisfies(e -> assertThat(e.getContracts()).as("check contracts").isEqualTo(actual.getContracts()));
    }
}
