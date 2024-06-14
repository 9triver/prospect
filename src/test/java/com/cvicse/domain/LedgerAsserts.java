package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class LedgerAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLedgerAllPropertiesEquals(Ledger expected, Ledger actual) {
        assertLedgerAutoGeneratedPropertiesEquals(expected, actual);
        assertLedgerAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLedgerAllUpdatablePropertiesEquals(Ledger expected, Ledger actual) {
        assertLedgerUpdatableFieldsEquals(expected, actual);
        assertLedgerUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLedgerAutoGeneratedPropertiesEquals(Ledger expected, Ledger actual) {
        assertThat(expected)
            .as("Verify Ledger auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLedgerUpdatableFieldsEquals(Ledger expected, Ledger actual) {}

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLedgerUpdatableRelationshipsEquals(Ledger expected, Ledger actual) {
        assertThat(expected)
            .as("Verify Ledger relationships")
            .satisfies(e -> assertThat(e.getOfficers()).as("check officers").isEqualTo(actual.getOfficers()));
    }
}
