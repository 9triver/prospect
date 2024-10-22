package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class OutsourcingContractAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOutsourcingContractAllPropertiesEquals(OutsourcingContract expected, OutsourcingContract actual) {
        assertOutsourcingContractAutoGeneratedPropertiesEquals(expected, actual);
        assertOutsourcingContractAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOutsourcingContractAllUpdatablePropertiesEquals(OutsourcingContract expected, OutsourcingContract actual) {
        assertOutsourcingContractUpdatableFieldsEquals(expected, actual);
        assertOutsourcingContractUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOutsourcingContractAutoGeneratedPropertiesEquals(OutsourcingContract expected, OutsourcingContract actual) {
        assertThat(expected)
            .as("Verify OutsourcingContract auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOutsourcingContractUpdatableFieldsEquals(OutsourcingContract expected, OutsourcingContract actual) {
        assertThat(expected)
            .as("Verify OutsourcingContract relevant properties")
            .satisfies(e -> assertThat(e.getContractid()).as("check contractid").isEqualTo(actual.getContractid()))
            .satisfies(e -> assertThat(e.getContractcode()).as("check contractcode").isEqualTo(actual.getContractcode()))
            .satisfies(e -> assertThat(e.getContractname()).as("check contractname").isEqualTo(actual.getContractname()))
            .satisfies(e -> assertThat(e.getContractqualityid()).as("check contractqualityid").isEqualTo(actual.getContractqualityid()))
            .satisfies(e -> assertThat(e.getContractcostid()).as("check contractcostid").isEqualTo(actual.getContractcostid()))
            .satisfies(e -> assertThat(e.getContractfinanceid()).as("check contractfinanceid").isEqualTo(actual.getContractfinanceid()))
            .satisfies(e -> assertThat(e.getProjectid()).as("check projectid").isEqualTo(actual.getProjectid()))
            .satisfies(e -> assertThat(e.getProjectsecretlevel()).as("check projectsecretlevel").isEqualTo(actual.getProjectsecretlevel()))
            .satisfies(e -> assertThat(e.getCounterpartyunit()).as("check counterpartyunit").isEqualTo(actual.getCounterpartyunit()))
            .satisfies(e -> assertThat(e.getNegotiationdate()).as("check negotiationdate").isEqualTo(actual.getNegotiationdate()))
            .satisfies(
                e -> assertThat(e.getNegotiationlocation()).as("check negotiationlocation").isEqualTo(actual.getNegotiationlocation())
            )
            .satisfies(e -> assertThat(e.getNegotiator()).as("check negotiator").isEqualTo(actual.getNegotiator()))
            .satisfies(
                e ->
                    assertThat(e.getBudgetamount())
                        .as("check budgetamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getBudgetamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getContractamount())
                        .as("check contractamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getContractamount())
            )
            .satisfies(e -> assertThat(e.getApprover()).as("check approver").isEqualTo(actual.getApprover()))
            .satisfies(e -> assertThat(e.getApprovaldate()).as("check approvaldate").isEqualTo(actual.getApprovaldate()))
            .satisfies(
                e -> assertThat(e.getContractsecretlevel()).as("check contractsecretlevel").isEqualTo(actual.getContractsecretlevel())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOutsourcingContractUpdatableRelationshipsEquals(OutsourcingContract expected, OutsourcingContract actual) {
        assertThat(expected)
            .as("Verify OutsourcingContract relationships")
            .satisfies(e -> assertThat(e.getWorkbag()).as("check workbag").isEqualTo(actual.getWorkbag()));
    }
}