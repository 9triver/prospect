package com.cvicse.jy1.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class CommunicationPlanAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCommunicationPlanAllPropertiesEquals(CommunicationPlan expected, CommunicationPlan actual) {
        assertCommunicationPlanAutoGeneratedPropertiesEquals(expected, actual);
        assertCommunicationPlanAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCommunicationPlanAllUpdatablePropertiesEquals(CommunicationPlan expected, CommunicationPlan actual) {
        assertCommunicationPlanUpdatableFieldsEquals(expected, actual);
        assertCommunicationPlanUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCommunicationPlanAutoGeneratedPropertiesEquals(CommunicationPlan expected, CommunicationPlan actual) {
        assertThat(expected)
            .as("Verify CommunicationPlan auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCommunicationPlanUpdatableFieldsEquals(CommunicationPlan expected, CommunicationPlan actual) {
        assertThat(expected)
            .as("Verify CommunicationPlan relevant properties")
            .satisfies(e -> assertThat(e.getWbsid()).as("check wbsid").isEqualTo(actual.getWbsid()))
            .satisfies(e -> assertThat(e.getCommunicationtopic()).as("check communicationtopic").isEqualTo(actual.getCommunicationtopic()))
            .satisfies(e -> assertThat(e.getCommunicationtime()).as("check communicationtime").isEqualTo(actual.getCommunicationtime()))
            .satisfies(e -> assertThat(e.getWorktarget()).as("check worktarget").isEqualTo(actual.getWorktarget()))
            .satisfies(e -> assertThat(e.getWorkcontent()).as("check workcontent").isEqualTo(actual.getWorkcontent()))
            .satisfies(e -> assertThat(e.getRemarks()).as("check remarks").isEqualTo(actual.getRemarks()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCommunicationPlanUpdatableRelationshipsEquals(CommunicationPlan expected, CommunicationPlan actual) {
        assertThat(expected)
            .as("Verify CommunicationPlan relationships")
            .satisfies(e -> assertThat(e.getProjectwbs()).as("check projectwbs").isEqualTo(actual.getProjectwbs()));
    }
}