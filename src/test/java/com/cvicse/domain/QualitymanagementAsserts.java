package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class QualitymanagementAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertQualitymanagementAllPropertiesEquals(Qualitymanagement expected, Qualitymanagement actual) {
        assertQualitymanagementAutoGeneratedPropertiesEquals(expected, actual);
        assertQualitymanagementAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertQualitymanagementAllUpdatablePropertiesEquals(Qualitymanagement expected, Qualitymanagement actual) {
        assertQualitymanagementUpdatableFieldsEquals(expected, actual);
        assertQualitymanagementUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertQualitymanagementAutoGeneratedPropertiesEquals(Qualitymanagement expected, Qualitymanagement actual) {
        assertThat(expected)
            .as("Verify Qualitymanagement auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertQualitymanagementUpdatableFieldsEquals(Qualitymanagement expected, Qualitymanagement actual) {
        assertThat(expected)
            .as("Verify Qualitymanagement relevant properties")
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
    public static void assertQualitymanagementUpdatableRelationshipsEquals(Qualitymanagement expected, Qualitymanagement actual) {
        assertThat(expected)
            .as("Verify Qualitymanagement relationships")
            .satisfies(e -> assertThat(e.getWbs()).as("check wbs").isEqualTo(actual.getWbs()));
    }
}