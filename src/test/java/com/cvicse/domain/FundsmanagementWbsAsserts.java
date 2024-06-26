package com.cvicse.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class FundsmanagementWbsAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFundsmanagementWbsAllPropertiesEquals(FundsmanagementWbs expected, FundsmanagementWbs actual) {
        assertFundsmanagementWbsAutoGeneratedPropertiesEquals(expected, actual);
        assertFundsmanagementWbsAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFundsmanagementWbsAllUpdatablePropertiesEquals(FundsmanagementWbs expected, FundsmanagementWbs actual) {
        assertFundsmanagementWbsUpdatableFieldsEquals(expected, actual);
        assertFundsmanagementWbsUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFundsmanagementWbsAutoGeneratedPropertiesEquals(FundsmanagementWbs expected, FundsmanagementWbs actual) {
        assertThat(expected)
            .as("Verify FundsmanagementWbs auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFundsmanagementWbsUpdatableFieldsEquals(FundsmanagementWbs expected, FundsmanagementWbs actual) {
        assertThat(expected)
            .as("Verify FundsmanagementWbs relevant properties")
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
    public static void assertFundsmanagementWbsUpdatableRelationshipsEquals(FundsmanagementWbs expected, FundsmanagementWbs actual) {
        assertThat(expected)
            .as("Verify FundsmanagementWbs relationships")
            .satisfies(e -> assertThat(e.getAuditedbudget()).as("check auditedbudget").isEqualTo(actual.getAuditedbudget()))
            .satisfies(e -> assertThat(e.getTotalbudget()).as("check totalbudget").isEqualTo(actual.getTotalbudget()))
            .satisfies(e -> assertThat(e.getUnitbudget()).as("check unitbudget").isEqualTo(actual.getUnitbudget()))
            .satisfies(e -> assertThat(e.getFundsavailability()).as("check fundsavailability").isEqualTo(actual.getFundsavailability()))
            .satisfies(e -> assertThat(e.getContractualfunds()).as("check contractualfunds").isEqualTo(actual.getContractualfunds()));
    }
}
