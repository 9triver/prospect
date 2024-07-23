package com.cvicse.jy1.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectpbsAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectpbsAllPropertiesEquals(Projectpbs expected, Projectpbs actual) {
        assertProjectpbsAutoGeneratedPropertiesEquals(expected, actual);
        assertProjectpbsAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectpbsAllUpdatablePropertiesEquals(Projectpbs expected, Projectpbs actual) {
        assertProjectpbsUpdatableFieldsEquals(expected, actual);
        assertProjectpbsUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectpbsAutoGeneratedPropertiesEquals(Projectpbs expected, Projectpbs actual) {
        assertThat(expected)
            .as("Verify Projectpbs auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectpbsUpdatableFieldsEquals(Projectpbs expected, Projectpbs actual) {
        assertThat(expected)
            .as("Verify Projectpbs relevant properties")
            .satisfies(e -> assertThat(e.getPbsname()).as("check pbsname").isEqualTo(actual.getPbsname()))
            .satisfies(e -> assertThat(e.getParentpbsid()).as("check parentpbsid").isEqualTo(actual.getParentpbsid()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()))
            .satisfies(e -> assertThat(e.getStarttime()).as("check starttime").isEqualTo(actual.getStarttime()))
            .satisfies(e -> assertThat(e.getEndtime()).as("check endtime").isEqualTo(actual.getEndtime()))
            .satisfies(e -> assertThat(e.getProgress()).as("check progress").isEqualTo(actual.getProgress()))
            .satisfies(e -> assertThat(e.getType()).as("check type").isEqualTo(actual.getType()))
            .satisfies(e -> assertThat(e.getPriorty()).as("check priorty").isEqualTo(actual.getPriorty()))
            .satisfies(e -> assertThat(e.getSecretlevel()).as("check secretlevel").isEqualTo(actual.getSecretlevel()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()))
            .satisfies(e -> assertThat(e.getWbsid()).as("check wbsid").isEqualTo(actual.getWbsid()))
            .satisfies(e -> assertThat(e.getWorkbag()).as("check workbag").isEqualTo(actual.getWorkbag()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectpbsUpdatableRelationshipsEquals(Projectpbs expected, Projectpbs actual) {
        assertThat(expected)
            .as("Verify Projectpbs relationships")
            .satisfies(e -> assertThat(e.getResponsibleid()).as("check responsibleid").isEqualTo(actual.getResponsibleid()))
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()))
            .satisfies(e -> assertThat(e.getDepartment()).as("check department").isEqualTo(actual.getDepartment()))
            .satisfies(e -> assertThat(e.getProjects()).as("check projects").isEqualTo(actual.getProjects()));
    }
}
