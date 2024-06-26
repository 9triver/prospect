package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class OfficersAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOfficersAllPropertiesEquals(Officers expected, Officers actual) {
        assertOfficersAutoGeneratedPropertiesEquals(expected, actual);
        assertOfficersAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOfficersAllUpdatablePropertiesEquals(Officers expected, Officers actual) {
        assertOfficersUpdatableFieldsEquals(expected, actual);
        assertOfficersUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOfficersAutoGeneratedPropertiesEquals(Officers expected, Officers actual) {
        assertThat(expected)
            .as("Verify Officers auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOfficersUpdatableFieldsEquals(Officers expected, Officers actual) {
        assertThat(expected)
            .as("Verify Officers relevant properties")
            .satisfies(e -> assertThat(e.getOfficersname()).as("check officersname").isEqualTo(actual.getOfficersname()))
            .satisfies(e -> assertThat(e.getPassword()).as("check password").isEqualTo(actual.getPassword()))
            .satisfies(e -> assertThat(e.getEmail()).as("check email").isEqualTo(actual.getEmail()))
            .satisfies(e -> assertThat(e.getPhone()).as("check phone").isEqualTo(actual.getPhone()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOfficersUpdatableRelationshipsEquals(Officers expected, Officers actual) {
        assertThat(expected)
            .as("Verify Officers relationships")
            .satisfies(e -> assertThat(e.getRole()).as("check role").isEqualTo(actual.getRole()))
            .satisfies(e -> assertThat(e.getDepartments()).as("check departments").isEqualTo(actual.getDepartments()));
    }
}
