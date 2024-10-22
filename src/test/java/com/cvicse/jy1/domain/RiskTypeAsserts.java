package com.cvicse.jy1.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class RiskTypeAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRiskTypeAllPropertiesEquals(RiskType expected, RiskType actual) {
        assertRiskTypeAutoGeneratedPropertiesEquals(expected, actual);
        assertRiskTypeAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRiskTypeAllUpdatablePropertiesEquals(RiskType expected, RiskType actual) {
        assertRiskTypeUpdatableFieldsEquals(expected, actual);
        assertRiskTypeUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRiskTypeAutoGeneratedPropertiesEquals(RiskType expected, RiskType actual) {
        assertThat(expected)
            .as("Verify RiskType auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRiskTypeUpdatableFieldsEquals(RiskType expected, RiskType actual) {
        assertThat(expected)
            .as("Verify RiskType relevant properties")
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRiskTypeUpdatableRelationshipsEquals(RiskType expected, RiskType actual) {}
}