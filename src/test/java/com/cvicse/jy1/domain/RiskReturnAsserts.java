package com.cvicse.jy1.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class RiskReturnAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRiskReturnAllPropertiesEquals(RiskReturn expected, RiskReturn actual) {
        assertRiskReturnAutoGeneratedPropertiesEquals(expected, actual);
        assertRiskReturnAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRiskReturnAllUpdatablePropertiesEquals(RiskReturn expected, RiskReturn actual) {
        assertRiskReturnUpdatableFieldsEquals(expected, actual);
        assertRiskReturnUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRiskReturnAutoGeneratedPropertiesEquals(RiskReturn expected, RiskReturn actual) {
        assertThat(expected)
            .as("Verify RiskReturn auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRiskReturnUpdatableFieldsEquals(RiskReturn expected, RiskReturn actual) {
        assertThat(expected)
            .as("Verify RiskReturn relevant properties")
            .satisfies(e -> assertThat(e.getBelongriskid()).as("check belongriskid").isEqualTo(actual.getBelongriskid()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(e -> assertThat(e.getClosestatus()).as("check closestatus").isEqualTo(actual.getClosestatus()))
            .satisfies(e -> assertThat(e.getEvidencefile()).as("check evidencefile").isEqualTo(actual.getEvidencefile()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRiskReturnUpdatableRelationshipsEquals(RiskReturn expected, RiskReturn actual) {
        assertThat(expected)
            .as("Verify RiskReturn relationships")
            .satisfies(e -> assertThat(e.getRiskid()).as("check riskid").isEqualTo(actual.getRiskid()))
            .satisfies(e -> assertThat(e.getCreatorid()).as("check creatorid").isEqualTo(actual.getCreatorid()));
    }
}