package com.cvicse.jy1.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DocumentAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDocumentAllPropertiesEquals(Document expected, Document actual) {
        assertDocumentAutoGeneratedPropertiesEquals(expected, actual);
        assertDocumentAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDocumentAllUpdatablePropertiesEquals(Document expected, Document actual) {
        assertDocumentUpdatableFieldsEquals(expected, actual);
        assertDocumentUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDocumentAutoGeneratedPropertiesEquals(Document expected, Document actual) {
        assertThat(expected)
            .as("Verify Document auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDocumentUpdatableFieldsEquals(Document expected, Document actual) {
        assertThat(expected)
            .as("Verify Document relevant properties")
            .satisfies(e -> assertThat(e.getDocumentname()).as("check documentname").isEqualTo(actual.getDocumentname()))
            .satisfies(e -> assertThat(e.getDocumenttype()).as("check documenttype").isEqualTo(actual.getDocumenttype()))
            .satisfies(e -> assertThat(e.getDocumentsize()).as("check documentsize").isEqualTo(actual.getDocumentsize()))
            .satisfies(e -> assertThat(e.getSecretlevel()).as("check secretlevel").isEqualTo(actual.getSecretlevel()))
            .satisfies(e -> assertThat(e.getCreatetime()).as("check createtime").isEqualTo(actual.getCreatetime()))
            .satisfies(e -> assertThat(e.getCreatorname()).as("check creatorname").isEqualTo(actual.getCreatorname()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDocumentUpdatableRelationshipsEquals(Document expected, Document actual) {
        assertThat(expected)
            .as("Verify Document relationships")
            .satisfies(e -> assertThat(e.getCreatorid()).as("check creatorid").isEqualTo(actual.getCreatorid()))
            .satisfies(e -> assertThat(e.getProjectwbs()).as("check projectwbs").isEqualTo(actual.getProjectwbs()));
    }
}
