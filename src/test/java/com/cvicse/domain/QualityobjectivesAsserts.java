package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class QualityobjectivesAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertQualityobjectivesAllPropertiesEquals(Qualityobjectives expected, Qualityobjectives actual) {
        assertQualityobjectivesAutoGeneratedPropertiesEquals(expected, actual);
        assertQualityobjectivesAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertQualityobjectivesAllUpdatablePropertiesEquals(Qualityobjectives expected, Qualityobjectives actual) {
        assertQualityobjectivesUpdatableFieldsEquals(expected, actual);
        assertQualityobjectivesUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertQualityobjectivesAutoGeneratedPropertiesEquals(Qualityobjectives expected, Qualityobjectives actual) {
        assertThat(expected)
            .as("Verify Qualityobjectives auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertQualityobjectivesUpdatableFieldsEquals(Qualityobjectives expected, Qualityobjectives actual) {
        assertThat(expected)
            .as("Verify Qualityobjectives relevant properties")
            .satisfies(
                e -> assertThat(e.getQualityobjectivesid()).as("check qualityobjectivesid").isEqualTo(actual.getQualityobjectivesid())
            )
            .satisfies(
                e -> assertThat(e.getQualityobjectivesname()).as("check qualityobjectivesname").isEqualTo(actual.getQualityobjectivesname())
            )
            .satisfies(e -> assertThat(e.getYear()).as("check year").isEqualTo(actual.getYear()))
            .satisfies(e -> assertThat(e.getCreatetime()).as("check createtime").isEqualTo(actual.getCreatetime()))
            .satisfies(e -> assertThat(e.getCreatorname()).as("check creatorname").isEqualTo(actual.getCreatorname()))
            .satisfies(e -> assertThat(e.getSecretlevel()).as("check secretlevel").isEqualTo(actual.getSecretlevel()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertQualityobjectivesUpdatableRelationshipsEquals(Qualityobjectives expected, Qualityobjectives actual) {
        assertThat(expected)
            .as("Verify Qualityobjectives relationships")
            .satisfies(e -> assertThat(e.getQualityreturns()).as("check qualityreturns").isEqualTo(actual.getQualityreturns()))
            .satisfies(e -> assertThat(e.getCreatorid()).as("check creatorid").isEqualTo(actual.getCreatorid()))
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()));
    }
}
