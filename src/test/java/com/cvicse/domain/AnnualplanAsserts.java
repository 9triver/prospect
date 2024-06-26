package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class AnnualplanAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAnnualplanAllPropertiesEquals(Annualplan expected, Annualplan actual) {
        assertAnnualplanAutoGeneratedPropertiesEquals(expected, actual);
        assertAnnualplanAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAnnualplanAllUpdatablePropertiesEquals(Annualplan expected, Annualplan actual) {
        assertAnnualplanUpdatableFieldsEquals(expected, actual);
        assertAnnualplanUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAnnualplanAutoGeneratedPropertiesEquals(Annualplan expected, Annualplan actual) {
        assertThat(expected)
            .as("Verify Annualplan auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAnnualplanUpdatableFieldsEquals(Annualplan expected, Annualplan actual) {
        assertThat(expected)
            .as("Verify Annualplan relevant properties")
            .satisfies(e -> assertThat(e.getAnnualplanname()).as("check annualplanname").isEqualTo(actual.getAnnualplanname()))
            .satisfies(e -> assertThat(e.getYear()).as("check year").isEqualTo(actual.getYear()))
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
    public static void assertAnnualplanUpdatableRelationshipsEquals(Annualplan expected, Annualplan actual) {
        assertThat(expected)
            .as("Verify Annualplan relationships")
            .satisfies(e -> assertThat(e.getDocument()).as("check document").isEqualTo(actual.getDocument()))
            .satisfies(e -> assertThat(e.getMonthplan()).as("check monthplan").isEqualTo(actual.getMonthplan()))
            .satisfies(e -> assertThat(e.getProjectcharge()).as("check projectcharge").isEqualTo(actual.getProjectcharge()))
            .satisfies(e -> assertThat(e.getCreatorid()).as("check creatorid").isEqualTo(actual.getCreatorid()))
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()));
    }
}
