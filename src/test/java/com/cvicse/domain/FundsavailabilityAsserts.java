package com.cvicse.domain;

import static com.cvicse.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class FundsavailabilityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFundsavailabilityAllPropertiesEquals(Fundsavailability expected, Fundsavailability actual) {
        assertFundsavailabilityAutoGeneratedPropertiesEquals(expected, actual);
        assertFundsavailabilityAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFundsavailabilityAllUpdatablePropertiesEquals(Fundsavailability expected, Fundsavailability actual) {
        assertFundsavailabilityUpdatableFieldsEquals(expected, actual);
        assertFundsavailabilityUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFundsavailabilityAutoGeneratedPropertiesEquals(Fundsavailability expected, Fundsavailability actual) {
        assertThat(expected)
            .as("Verify Fundsavailability auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFundsavailabilityUpdatableFieldsEquals(Fundsavailability expected, Fundsavailability actual) {
        assertThat(expected)
            .as("Verify Fundsavailability relevant properties")
            .satisfies(
                e -> assertThat(e.getFundsavailabilityid()).as("check fundsavailabilityid").isEqualTo(actual.getFundsavailabilityid())
            )
            .satisfies(e -> assertThat(e.getFundsid()).as("check fundsid").isEqualTo(actual.getFundsid()))
            .satisfies(e -> assertThat(e.getYear()).as("check year").isEqualTo(actual.getYear()))
            .satisfies(e -> assertThat(e.getBudgit()).as("check budgit").usingComparator(bigDecimalCompareTo).isEqualTo(actual.getBudgit()))
            .satisfies(
                e -> assertThat(e.getFunding()).as("check funding").usingComparator(bigDecimalCompareTo).isEqualTo(actual.getFunding())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFundsavailabilityUpdatableRelationshipsEquals(Fundsavailability expected, Fundsavailability actual) {
        assertThat(expected)
            .as("Verify Fundsavailability relationships")
            .satisfies(e -> assertThat(e.getFundsmanagement()).as("check fundsmanagement").isEqualTo(actual.getFundsmanagement()));
    }
}
