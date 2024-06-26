package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class AnnualSecurityPlanAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAnnualSecurityPlanAllPropertiesEquals(AnnualSecurityPlan expected, AnnualSecurityPlan actual) {
        assertAnnualSecurityPlanAutoGeneratedPropertiesEquals(expected, actual);
        assertAnnualSecurityPlanAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAnnualSecurityPlanAllUpdatablePropertiesEquals(AnnualSecurityPlan expected, AnnualSecurityPlan actual) {
        assertAnnualSecurityPlanUpdatableFieldsEquals(expected, actual);
        assertAnnualSecurityPlanUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAnnualSecurityPlanAutoGeneratedPropertiesEquals(AnnualSecurityPlan expected, AnnualSecurityPlan actual) {
        assertThat(expected)
            .as("Verify AnnualSecurityPlan auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAnnualSecurityPlanUpdatableFieldsEquals(AnnualSecurityPlan expected, AnnualSecurityPlan actual) {
        assertThat(expected)
            .as("Verify AnnualSecurityPlan relevant properties")
            .satisfies(e -> assertThat(e.getSecurityplanname()).as("check securityplanname").isEqualTo(actual.getSecurityplanname()))
            .satisfies(e -> assertThat(e.getReleasetime()).as("check releasetime").isEqualTo(actual.getReleasetime()))
            .satisfies(e -> assertThat(e.getCreatetime()).as("check createtime").isEqualTo(actual.getCreatetime()))
            .satisfies(e -> assertThat(e.getCreatorname()).as("check creatorname").isEqualTo(actual.getCreatorname()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()))
            .satisfies(e -> assertThat(e.getVersion()).as("check version").isEqualTo(actual.getVersion()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAnnualSecurityPlanUpdatableRelationshipsEquals(AnnualSecurityPlan expected, AnnualSecurityPlan actual) {
        assertThat(expected)
            .as("Verify AnnualSecurityPlan relationships")
            .satisfies(e -> assertThat(e.getProject()).as("check project").isEqualTo(actual.getProject()))
            .satisfies(e -> assertThat(e.getCreatorid()).as("check creatorid").isEqualTo(actual.getCreatorid()))
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()));
    }
}
