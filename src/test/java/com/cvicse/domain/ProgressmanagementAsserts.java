package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ProgressmanagementAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProgressmanagementAllPropertiesEquals(Progressmanagement expected, Progressmanagement actual) {
        assertProgressmanagementAutoGeneratedPropertiesEquals(expected, actual);
        assertProgressmanagementAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProgressmanagementAllUpdatablePropertiesEquals(Progressmanagement expected, Progressmanagement actual) {
        assertProgressmanagementUpdatableFieldsEquals(expected, actual);
        assertProgressmanagementUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProgressmanagementAutoGeneratedPropertiesEquals(Progressmanagement expected, Progressmanagement actual) {
        assertThat(expected)
            .as("Verify Progressmanagement auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProgressmanagementUpdatableFieldsEquals(Progressmanagement expected, Progressmanagement actual) {
        assertThat(expected)
            .as("Verify Progressmanagement relevant properties")
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()))
            .satisfies(e -> assertThat(e.getStarttime()).as("check starttime").isEqualTo(actual.getStarttime()))
            .satisfies(e -> assertThat(e.getEndtime()).as("check endtime").isEqualTo(actual.getEndtime()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProgressmanagementUpdatableRelationshipsEquals(Progressmanagement expected, Progressmanagement actual) {
        assertThat(expected)
            .as("Verify Progressmanagement relationships")
            .satisfies(e -> assertThat(e.getWbs()).as("check wbs").isEqualTo(actual.getWbs()));
    }
}
