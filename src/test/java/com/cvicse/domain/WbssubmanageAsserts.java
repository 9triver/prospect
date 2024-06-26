package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class WbssubmanageAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertWbssubmanageAllPropertiesEquals(Wbssubmanage expected, Wbssubmanage actual) {
        assertWbssubmanageAutoGeneratedPropertiesEquals(expected, actual);
        assertWbssubmanageAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertWbssubmanageAllUpdatablePropertiesEquals(Wbssubmanage expected, Wbssubmanage actual) {
        assertWbssubmanageUpdatableFieldsEquals(expected, actual);
        assertWbssubmanageUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertWbssubmanageAutoGeneratedPropertiesEquals(Wbssubmanage expected, Wbssubmanage actual) {
        assertThat(expected)
            .as("Verify Wbssubmanage auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertWbssubmanageUpdatableFieldsEquals(Wbssubmanage expected, Wbssubmanage actual) {
        assertThat(expected)
            .as("Verify Wbssubmanage relevant properties")
            .satisfies(e -> assertThat(e.getPbssubid()).as("check pbssubid").isEqualTo(actual.getPbssubid()))
            .satisfies(e -> assertThat(e.getPbssubname()).as("check pbssubname").isEqualTo(actual.getPbssubname()))
            .satisfies(e -> assertThat(e.getResponsiblename()).as("check responsiblename").isEqualTo(actual.getResponsiblename()))
            .satisfies(
                e -> assertThat(e.getResponsibledepartment()).as("check responsibledepartment").isEqualTo(actual.getResponsibledepartment())
            )
            .satisfies(e -> assertThat(e.getRelevantdepartment()).as("check relevantdepartment").isEqualTo(actual.getRelevantdepartment()))
            .satisfies(e -> assertThat(e.getType()).as("check type").isEqualTo(actual.getType()))
            .satisfies(e -> assertThat(e.getStarttime()).as("check starttime").isEqualTo(actual.getStarttime()))
            .satisfies(e -> assertThat(e.getEndtime()).as("check endtime").isEqualTo(actual.getEndtime()))
            .satisfies(e -> assertThat(e.getSecretlevel()).as("check secretlevel").isEqualTo(actual.getSecretlevel()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertWbssubmanageUpdatableRelationshipsEquals(Wbssubmanage expected, Wbssubmanage actual) {
        assertThat(expected)
            .as("Verify Wbssubmanage relationships")
            .satisfies(e -> assertThat(e.getResponsibleid()).as("check responsibleid").isEqualTo(actual.getResponsibleid()))
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()));
    }
}
