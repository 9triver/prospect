package com.cvicse.jy1.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class PlanReturnsAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPlanReturnsAllPropertiesEquals(PlanReturns expected, PlanReturns actual) {
        assertPlanReturnsAutoGeneratedPropertiesEquals(expected, actual);
        assertPlanReturnsAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPlanReturnsAllUpdatablePropertiesEquals(PlanReturns expected, PlanReturns actual) {
        assertPlanReturnsUpdatableFieldsEquals(expected, actual);
        assertPlanReturnsUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPlanReturnsAutoGeneratedPropertiesEquals(PlanReturns expected, PlanReturns actual) {
        assertThat(expected)
            .as("Verify PlanReturns auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPlanReturnsUpdatableFieldsEquals(PlanReturns expected, PlanReturns actual) {
        assertThat(expected)
            .as("Verify PlanReturns relevant properties")
            .satisfies(e -> assertThat(e.getPlanreturnsname()).as("check planreturnsname").isEqualTo(actual.getPlanreturnsname()))
            .satisfies(e -> assertThat(e.getPlantype()).as("check plantype").isEqualTo(actual.getPlantype()))
            .satisfies(e -> assertThat(e.getPlanlevel()).as("check planlevel").isEqualTo(actual.getPlanlevel()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()))
            .satisfies(e -> assertThat(e.getActualstarttime()).as("check actualstarttime").isEqualTo(actual.getActualstarttime()))
            .satisfies(e -> assertThat(e.getActualendtime()).as("check actualendtime").isEqualTo(actual.getActualendtime()))
            .satisfies(e -> assertThat(e.getDeliverables()).as("check deliverables").isEqualTo(actual.getDeliverables()))
            .satisfies(e -> assertThat(e.getProgress()).as("check progress").isEqualTo(actual.getProgress()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(e -> assertThat(e.getImpactanalysis()).as("check impactanalysis").isEqualTo(actual.getImpactanalysis()))
            .satisfies(e -> assertThat(e.getReturnstime()).as("check returnstime").isEqualTo(actual.getReturnstime()))
            .satisfies(e -> assertThat(e.getRejectionreason()).as("check rejectionreason").isEqualTo(actual.getRejectionreason()))
            .satisfies(e -> assertThat(e.getReturnsstatus()).as("check returnsstatus").isEqualTo(actual.getReturnsstatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPlanReturnsUpdatableRelationshipsEquals(PlanReturns expected, PlanReturns actual) {
        assertThat(expected)
            .as("Verify PlanReturns relationships")
            .satisfies(e -> assertThat(e.getResponsibleperson()).as("check responsibleperson").isEqualTo(actual.getResponsibleperson()))
            .satisfies(e -> assertThat(e.getAuditorid()).as("check auditorid").isEqualTo(actual.getAuditorid()))
            .satisfies(e -> assertThat(e.getProgressPlan()).as("check progressPlan").isEqualTo(actual.getProgressPlan()));
    }
}
