package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class SecrecysystemAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSecrecysystemAllPropertiesEquals(Secrecysystem expected, Secrecysystem actual) {
        assertSecrecysystemAutoGeneratedPropertiesEquals(expected, actual);
        assertSecrecysystemAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSecrecysystemAllUpdatablePropertiesEquals(Secrecysystem expected, Secrecysystem actual) {
        assertSecrecysystemUpdatableFieldsEquals(expected, actual);
        assertSecrecysystemUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSecrecysystemAutoGeneratedPropertiesEquals(Secrecysystem expected, Secrecysystem actual) {
        assertThat(expected)
            .as("Verify Secrecysystem auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSecrecysystemUpdatableFieldsEquals(Secrecysystem expected, Secrecysystem actual) {
        assertThat(expected)
            .as("Verify Secrecysystem relevant properties")
            .satisfies(e -> assertThat(e.getPublishedby()).as("check publishedby").isEqualTo(actual.getPublishedby()))
            .satisfies(e -> assertThat(e.getDocumentname()).as("check documentname").isEqualTo(actual.getDocumentname()))
            .satisfies(e -> assertThat(e.getDocumenttype()).as("check documenttype").isEqualTo(actual.getDocumenttype()))
            .satisfies(e -> assertThat(e.getDocumentsize()).as("check documentsize").isEqualTo(actual.getDocumentsize()))
            .satisfies(e -> assertThat(e.getSecretlevel()).as("check secretlevel").isEqualTo(actual.getSecretlevel()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSecrecysystemUpdatableRelationshipsEquals(Secrecysystem expected, Secrecysystem actual) {
        assertThat(expected)
            .as("Verify Secrecysystem relationships")
            .satisfies(e -> assertThat(e.getCreatorid()).as("check creatorid").isEqualTo(actual.getCreatorid()))
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()));
    }
}
