package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class SecrecymanagementAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSecrecymanagementAllPropertiesEquals(Secrecymanagement expected, Secrecymanagement actual) {
        assertSecrecymanagementAutoGeneratedPropertiesEquals(expected, actual);
        assertSecrecymanagementAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSecrecymanagementAllUpdatablePropertiesEquals(Secrecymanagement expected, Secrecymanagement actual) {
        assertSecrecymanagementUpdatableFieldsEquals(expected, actual);
        assertSecrecymanagementUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSecrecymanagementAutoGeneratedPropertiesEquals(Secrecymanagement expected, Secrecymanagement actual) {
        assertThat(expected)
            .as("Verify Secrecymanagement auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSecrecymanagementUpdatableFieldsEquals(Secrecymanagement expected, Secrecymanagement actual) {
        assertThat(expected)
            .as("Verify Secrecymanagement relevant properties")
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
    public static void assertSecrecymanagementUpdatableRelationshipsEquals(Secrecymanagement expected, Secrecymanagement actual) {
        assertThat(expected)
            .as("Verify Secrecymanagement relationships")
            .satisfies(e -> assertThat(e.getWbs()).as("check wbs").isEqualTo(actual.getWbs()));
    }
}
