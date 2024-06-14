package com.cvicse.domain;

import static com.cvicse.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class ContractualfundsAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertContractualfundsAllPropertiesEquals(Contractualfunds expected, Contractualfunds actual) {
        assertContractualfundsAutoGeneratedPropertiesEquals(expected, actual);
        assertContractualfundsAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertContractualfundsAllUpdatablePropertiesEquals(Contractualfunds expected, Contractualfunds actual) {
        assertContractualfundsUpdatableFieldsEquals(expected, actual);
        assertContractualfundsUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertContractualfundsAutoGeneratedPropertiesEquals(Contractualfunds expected, Contractualfunds actual) {
        assertThat(expected)
            .as("Verify Contractualfunds auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertContractualfundsUpdatableFieldsEquals(Contractualfunds expected, Contractualfunds actual) {
        assertThat(expected)
            .as("Verify Contractualfunds relevant properties")
            .satisfies(e -> assertThat(e.getContractualid()).as("check contractualid").isEqualTo(actual.getContractualid()))
            .satisfies(e -> assertThat(e.getDepartment()).as("check department").isEqualTo(actual.getDepartment()))
            .satisfies(e -> assertThat(e.getYear()).as("check year").isEqualTo(actual.getYear()))
            .satisfies(e -> assertThat(e.getStarttime()).as("check starttime").isEqualTo(actual.getStarttime()))
            .satisfies(e -> assertThat(e.getEndtime()).as("check endtime").isEqualTo(actual.getEndtime()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(e -> assertThat(e.getSecretlevel()).as("check secretlevel").isEqualTo(actual.getSecretlevel()))
            .satisfies(
                e ->
                    assertThat(e.getForeigncurrency())
                        .as("check foreigncurrency")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getForeigncurrency())
            )
            .satisfies(
                e ->
                    assertThat(e.getTotalbudget())
                        .as("check totalbudget")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getTotalbudget())
            )
            .satisfies(
                e ->
                    assertThat(e.getFundsinplace())
                        .as("check fundsinplace")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getFundsinplace())
            )
            .satisfies(
                e -> assertThat(e.getResponsibleunitname()).as("check responsibleunitname").isEqualTo(actual.getResponsibleunitname())
            )
            .satisfies(e -> assertThat(e.getAudittime()).as("check audittime").isEqualTo(actual.getAudittime()))
            .satisfies(e -> assertThat(e.getAccountbank()).as("check accountbank").isEqualTo(actual.getAccountbank()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertContractualfundsUpdatableRelationshipsEquals(Contractualfunds expected, Contractualfunds actual) {
        assertThat(expected)
            .as("Verify Contractualfunds relationships")
            .satisfies(e -> assertThat(e.getCreatorid()).as("check creatorid").isEqualTo(actual.getCreatorid()))
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()));
    }
}
