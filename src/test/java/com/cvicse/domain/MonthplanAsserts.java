package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class MonthplanAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMonthplanAllPropertiesEquals(Monthplan expected, Monthplan actual) {
        assertMonthplanAutoGeneratedPropertiesEquals(expected, actual);
        assertMonthplanAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMonthplanAllUpdatablePropertiesEquals(Monthplan expected, Monthplan actual) {
        assertMonthplanUpdatableFieldsEquals(expected, actual);
        assertMonthplanUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMonthplanAutoGeneratedPropertiesEquals(Monthplan expected, Monthplan actual) {
        assertThat(expected)
            .as("Verify Monthplan auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMonthplanUpdatableFieldsEquals(Monthplan expected, Monthplan actual) {
        assertThat(expected)
            .as("Verify Monthplan relevant properties")
            .satisfies(e -> assertThat(e.getMonthplanname()).as("check monthplanname").isEqualTo(actual.getMonthplanname()))
            .satisfies(e -> assertThat(e.getMonth()).as("check month").isEqualTo(actual.getMonth()))
            .satisfies(e -> assertThat(e.getSecretlevel()).as("check secretlevel").isEqualTo(actual.getSecretlevel()))
            .satisfies(e -> assertThat(e.getCreatorname()).as("check creatorname").isEqualTo(actual.getCreatorname()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMonthplanUpdatableRelationshipsEquals(Monthplan expected, Monthplan actual) {
        assertThat(expected)
            .as("Verify Monthplan relationships")
            .satisfies(e -> assertThat(e.getDocument()).as("check document").isEqualTo(actual.getDocument()))
            .satisfies(e -> assertThat(e.getPlanreturns()).as("check planreturns").isEqualTo(actual.getPlanreturns()))
            .satisfies(e -> assertThat(e.getProjectcharge()).as("check projectcharge").isEqualTo(actual.getProjectcharge()))
            .satisfies(e -> assertThat(e.getCreatorid()).as("check creatorid").isEqualTo(actual.getCreatorid()))
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()));
    }
}
