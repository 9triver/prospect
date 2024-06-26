package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class PermissionAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPermissionAllPropertiesEquals(Permission expected, Permission actual) {
        assertPermissionAutoGeneratedPropertiesEquals(expected, actual);
        assertPermissionAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPermissionAllUpdatablePropertiesEquals(Permission expected, Permission actual) {
        assertPermissionUpdatableFieldsEquals(expected, actual);
        assertPermissionUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPermissionAutoGeneratedPropertiesEquals(Permission expected, Permission actual) {
        assertThat(expected)
            .as("Verify Permission auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPermissionUpdatableFieldsEquals(Permission expected, Permission actual) {
        assertThat(expected)
            .as("Verify Permission relevant properties")
            .satisfies(e -> assertThat(e.getPermissionname()).as("check permissionname").isEqualTo(actual.getPermissionname()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPermissionUpdatableRelationshipsEquals(Permission expected, Permission actual) {}
}