package com.cvicse.jy1.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemLevelAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSystemLevelAllPropertiesEquals(SystemLevel expected, SystemLevel actual) {
        assertSystemLevelAutoGeneratedPropertiesEquals(expected, actual);
        assertSystemLevelAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSystemLevelAllUpdatablePropertiesEquals(SystemLevel expected, SystemLevel actual) {
        assertSystemLevelUpdatableFieldsEquals(expected, actual);
        assertSystemLevelUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSystemLevelAutoGeneratedPropertiesEquals(SystemLevel expected, SystemLevel actual) {
        assertThat(expected)
            .as("Verify SystemLevel auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSystemLevelUpdatableFieldsEquals(SystemLevel expected, SystemLevel actual) {
        assertThat(expected)
            .as("Verify SystemLevel relevant properties")
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSystemLevelUpdatableRelationshipsEquals(SystemLevel expected, SystemLevel actual) {}
}
