package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class SafetycheckAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSafetycheckAllPropertiesEquals(Safetycheck expected, Safetycheck actual) {
        assertSafetycheckAutoGeneratedPropertiesEquals(expected, actual);
        assertSafetycheckAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSafetycheckAllUpdatablePropertiesEquals(Safetycheck expected, Safetycheck actual) {
        assertSafetycheckUpdatableFieldsEquals(expected, actual);
        assertSafetycheckUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSafetycheckAutoGeneratedPropertiesEquals(Safetycheck expected, Safetycheck actual) {
        assertThat(expected)
            .as("Verify Safetycheck auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSafetycheckUpdatableFieldsEquals(Safetycheck expected, Safetycheck actual) {
        assertThat(expected)
            .as("Verify Safetycheck relevant properties")
            .satisfies(e -> assertThat(e.getSafetycheckid()).as("check safetycheckid").isEqualTo(actual.getSafetycheckid()))
            .satisfies(e -> assertThat(e.getSafetycheckname()).as("check safetycheckname").isEqualTo(actual.getSafetycheckname()))
            .satisfies(e -> assertThat(e.getChecksource()).as("check checksource").isEqualTo(actual.getChecksource()))
            .satisfies(e -> assertThat(e.getChecktime()).as("check checktime").isEqualTo(actual.getChecktime()))
            .satisfies(e -> assertThat(e.getEffectivetime()).as("check effectivetime").isEqualTo(actual.getEffectivetime()))
            .satisfies(e -> assertThat(e.getOperatinglocation()).as("check operatinglocation").isEqualTo(actual.getOperatinglocation()))
            .satisfies(e -> assertThat(e.getDeprotment()).as("check deprotment").isEqualTo(actual.getDeprotment()))
            .satisfies(e -> assertThat(e.getPhonenumber()).as("check phonenumber").isEqualTo(actual.getPhonenumber()))
            .satisfies(e -> assertThat(e.getRisklevel()).as("check risklevel").isEqualTo(actual.getRisklevel()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSafetycheckUpdatableRelationshipsEquals(Safetycheck expected, Safetycheck actual) {
        assertThat(expected)
            .as("Verify Safetycheck relationships")
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()))
            .satisfies(e -> assertThat(e.getResponsibleid()).as("check responsibleid").isEqualTo(actual.getResponsibleid()));
    }
}
