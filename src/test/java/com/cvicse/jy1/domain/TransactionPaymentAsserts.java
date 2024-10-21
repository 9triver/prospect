package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionPaymentAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTransactionPaymentAllPropertiesEquals(TransactionPayment expected, TransactionPayment actual) {
        assertTransactionPaymentAutoGeneratedPropertiesEquals(expected, actual);
        assertTransactionPaymentAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTransactionPaymentAllUpdatablePropertiesEquals(TransactionPayment expected, TransactionPayment actual) {
        assertTransactionPaymentUpdatableFieldsEquals(expected, actual);
        assertTransactionPaymentUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTransactionPaymentAutoGeneratedPropertiesEquals(TransactionPayment expected, TransactionPayment actual) {
        assertThat(expected)
            .as("Verify TransactionPayment auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTransactionPaymentUpdatableFieldsEquals(TransactionPayment expected, TransactionPayment actual) {
        assertThat(expected)
            .as("Verify TransactionPayment relevant properties")
            .satisfies(e -> assertThat(e.getPlanpaymentnode()).as("check planpaymentnode").isEqualTo(actual.getPlanpaymentnode()))
            .satisfies(
                e ->
                    assertThat(e.getPlanpaymentamount())
                        .as("check planpaymentamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getPlanpaymentamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getActualpaymentamount())
                        .as("check actualpaymentamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getActualpaymentamount())
            )
            .satisfies(e -> assertThat(e.getPaymenttype()).as("check paymenttype").isEqualTo(actual.getPaymenttype()))
            .satisfies(e -> assertThat(e.getFinancialvoucherid()).as("check financialvoucherid").isEqualTo(actual.getFinancialvoucherid()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTransactionPaymentUpdatableRelationshipsEquals(TransactionPayment expected, TransactionPayment actual) {}
}
