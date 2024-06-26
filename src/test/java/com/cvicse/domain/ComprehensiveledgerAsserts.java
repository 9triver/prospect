package com.cvicse.domain;

import static com.cvicse.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class ComprehensiveledgerAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertComprehensiveledgerAllPropertiesEquals(Comprehensiveledger expected, Comprehensiveledger actual) {
        assertComprehensiveledgerAutoGeneratedPropertiesEquals(expected, actual);
        assertComprehensiveledgerAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertComprehensiveledgerAllUpdatablePropertiesEquals(Comprehensiveledger expected, Comprehensiveledger actual) {
        assertComprehensiveledgerUpdatableFieldsEquals(expected, actual);
        assertComprehensiveledgerUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertComprehensiveledgerAutoGeneratedPropertiesEquals(Comprehensiveledger expected, Comprehensiveledger actual) {
        assertThat(expected)
            .as("Verify Comprehensiveledger auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertComprehensiveledgerUpdatableFieldsEquals(Comprehensiveledger expected, Comprehensiveledger actual) {
        assertThat(expected)
            .as("Verify Comprehensiveledger relevant properties")
            .satisfies(e -> assertThat(e.getFundsname()).as("check fundsname").isEqualTo(actual.getFundsname()))
            .satisfies(e -> assertThat(e.getWbsname()).as("check wbsname").isEqualTo(actual.getWbsname()))
            .satisfies(e -> assertThat(e.getUnitname()).as("check unitname").isEqualTo(actual.getUnitname()))
            .satisfies(e -> assertThat(e.getBudgetsection()).as("check budgetsection").isEqualTo(actual.getBudgetsection()))
            .satisfies(e -> assertThat(e.getPurpose()).as("check purpose").isEqualTo(actual.getPurpose()))
            .satisfies(e -> assertThat(e.getUnit()).as("check unit").isEqualTo(actual.getUnit()))
            .satisfies(e -> assertThat(e.getNumber()).as("check number").isEqualTo(actual.getNumber()))
            .satisfies(
                e ->
                    assertThat(e.getUnitprice()).as("check unitprice").usingComparator(bigDecimalCompareTo).isEqualTo(actual.getUnitprice())
            )
            .satisfies(
                e ->
                    assertThat(e.getForeignexchange())
                        .as("check foreignexchange")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getForeignexchange())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertComprehensiveledgerUpdatableRelationshipsEquals(Comprehensiveledger expected, Comprehensiveledger actual) {}
}