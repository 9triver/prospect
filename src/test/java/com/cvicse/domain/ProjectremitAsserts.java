package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectremitAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectremitAllPropertiesEquals(Projectremit expected, Projectremit actual) {
        assertProjectremitAutoGeneratedPropertiesEquals(expected, actual);
        assertProjectremitAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectremitAllUpdatablePropertiesEquals(Projectremit expected, Projectremit actual) {
        assertProjectremitUpdatableFieldsEquals(expected, actual);
        assertProjectremitUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectremitAutoGeneratedPropertiesEquals(Projectremit expected, Projectremit actual) {
        assertThat(expected)
            .as("Verify Projectremit auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectremitUpdatableFieldsEquals(Projectremit expected, Projectremit actual) {
        assertThat(expected)
            .as("Verify Projectremit relevant properties")
            .satisfies(e -> assertThat(e.getRemit()).as("check remit").isEqualTo(actual.getRemit()))
            .satisfies(e -> assertThat(e.getOutdeportment()).as("check outdeportment").isEqualTo(actual.getOutdeportment()))
            .satisfies(e -> assertThat(e.getIndeportment()).as("check indeportment").isEqualTo(actual.getIndeportment()))
            .satisfies(e -> assertThat(e.getProjectname()).as("check projectname").isEqualTo(actual.getProjectname()))
            .satisfies(e -> assertThat(e.getDeportment()).as("check deportment").isEqualTo(actual.getDeportment()))
            .satisfies(e -> assertThat(e.getProjectleader()).as("check projectleader").isEqualTo(actual.getProjectleader()))
            .satisfies(e -> assertThat(e.getSecretlevel()).as("check secretlevel").isEqualTo(actual.getSecretlevel()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectremitUpdatableRelationshipsEquals(Projectremit expected, Projectremit actual) {
        assertThat(expected)
            .as("Verify Projectremit relationships")
            .satisfies(e -> assertThat(e.getProject()).as("check project").isEqualTo(actual.getProject()))
            .satisfies(e -> assertThat(e.getCreatorid()).as("check creatorid").isEqualTo(actual.getCreatorid()));
    }
}
