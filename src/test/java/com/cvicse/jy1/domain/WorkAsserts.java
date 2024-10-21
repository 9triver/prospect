package com.cvicse.jy1.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertWorkAllPropertiesEquals(Work expected, Work actual) {
        assertWorkAutoGeneratedPropertiesEquals(expected, actual);
        assertWorkAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertWorkAllUpdatablePropertiesEquals(Work expected, Work actual) {
        assertWorkUpdatableFieldsEquals(expected, actual);
        assertWorkUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertWorkAutoGeneratedPropertiesEquals(Work expected, Work actual) {
        assertThat(expected)
            .as("Verify Work auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertWorkUpdatableFieldsEquals(Work expected, Work actual) {
        assertThat(expected)
            .as("Verify Work relevant properties")
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()))
            .satisfies(e -> assertThat(e.getSecretlevel()).as("check secretlevel").isEqualTo(actual.getSecretlevel()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()))
            .satisfies(e -> assertThat(e.getWorkbagid()).as("check workbagid").isEqualTo(actual.getWorkbagid()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertWorkUpdatableRelationshipsEquals(Work expected, Work actual) {
        assertThat(expected)
            .as("Verify Work relationships")
            .satisfies(e -> assertThat(e.getWorkbags()).as("check workbags").isEqualTo(actual.getWorkbags()));
    }
}
