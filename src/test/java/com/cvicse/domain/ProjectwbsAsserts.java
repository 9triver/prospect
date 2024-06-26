package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectwbsAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectwbsAllPropertiesEquals(Projectwbs expected, Projectwbs actual) {
        assertProjectwbsAutoGeneratedPropertiesEquals(expected, actual);
        assertProjectwbsAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectwbsAllUpdatablePropertiesEquals(Projectwbs expected, Projectwbs actual) {
        assertProjectwbsUpdatableFieldsEquals(expected, actual);
        assertProjectwbsUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectwbsAutoGeneratedPropertiesEquals(Projectwbs expected, Projectwbs actual) {
        assertThat(expected)
            .as("Verify Projectwbs auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectwbsUpdatableFieldsEquals(Projectwbs expected, Projectwbs actual) {
        assertThat(expected)
            .as("Verify Projectwbs relevant properties")
            .satisfies(e -> assertThat(e.getProjectwbsname()).as("check projectwbsname").isEqualTo(actual.getProjectwbsname()))
            .satisfies(e -> assertThat(e.getWbsspare1()).as("check wbsspare1").isEqualTo(actual.getWbsspare1()))
            .satisfies(e -> assertThat(e.getWbsspare2()).as("check wbsspare2").isEqualTo(actual.getWbsspare2()))
            .satisfies(e -> assertThat(e.getWbsspare3()).as("check wbsspare3").isEqualTo(actual.getWbsspare3()))
            .satisfies(e -> assertThat(e.getWbsspare4()).as("check wbsspare4").isEqualTo(actual.getWbsspare4()))
            .satisfies(e -> assertThat(e.getWbsspare5()).as("check wbsspare5").isEqualTo(actual.getWbsspare5()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProjectwbsUpdatableRelationshipsEquals(Projectwbs expected, Projectwbs actual) {
        assertThat(expected)
            .as("Verify Projectwbs relationships")
            .satisfies(e -> assertThat(e.getCycleplan()).as("check cycleplan").isEqualTo(actual.getCycleplan()))
            .satisfies(e -> assertThat(e.getProgressmanagement()).as("check progressmanagement").isEqualTo(actual.getProgressmanagement()))
            .satisfies(e -> assertThat(e.getQualitymanagement()).as("check qualitymanagement").isEqualTo(actual.getQualitymanagement()))
            .satisfies(e -> assertThat(e.getFundsmanagement()).as("check fundsmanagement").isEqualTo(actual.getFundsmanagement()))
            .satisfies(
                e -> assertThat(e.getTechnicalmanagement()).as("check technicalmanagement").isEqualTo(actual.getTechnicalmanagement())
            )
            .satisfies(e -> assertThat(e.getContractualfunds()).as("check contractualfunds").isEqualTo(actual.getContractualfunds()))
            .satisfies(
                e -> assertThat(e.getOutsourcingmanagement()).as("check outsourcingmanagement").isEqualTo(actual.getOutsourcingmanagement())
            )
            .satisfies(e -> assertThat(e.getResourcemanagement()).as("check resourcemanagement").isEqualTo(actual.getResourcemanagement()))
            .satisfies(e -> assertThat(e.getRiskmanagement()).as("check riskmanagement").isEqualTo(actual.getRiskmanagement()))
            .satisfies(e -> assertThat(e.getSecuritymanagement()).as("check securitymanagement").isEqualTo(actual.getSecuritymanagement()))
            .satisfies(e -> assertThat(e.getDocument()).as("check document").isEqualTo(actual.getDocument()))
            .satisfies(e -> assertThat(e.getSafetycheck()).as("check safetycheck").isEqualTo(actual.getSafetycheck()))
            .satisfies(e -> assertThat(e.getDepartment()).as("check department").isEqualTo(actual.getDepartment()))
            .satisfies(e -> assertThat(e.getEvaluationCriteria()).as("check evaluationCriteria").isEqualTo(actual.getEvaluationCriteria()));
    }
}