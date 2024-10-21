package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentCostListAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPaymentCostListAllPropertiesEquals(PaymentCostList expected, PaymentCostList actual) {
        assertPaymentCostListAutoGeneratedPropertiesEquals(expected, actual);
        assertPaymentCostListAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPaymentCostListAllUpdatablePropertiesEquals(PaymentCostList expected, PaymentCostList actual) {
        assertPaymentCostListUpdatableFieldsEquals(expected, actual);
        assertPaymentCostListUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPaymentCostListAutoGeneratedPropertiesEquals(PaymentCostList expected, PaymentCostList actual) {
        assertThat(expected)
            .as("Verify PaymentCostList auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPaymentCostListUpdatableFieldsEquals(PaymentCostList expected, PaymentCostList actual) {
        assertThat(expected)
            .as("Verify PaymentCostList relevant properties")
            .satisfies(e -> assertThat(e.getWbsid()).as("check wbsid").isEqualTo(actual.getWbsid()))
            .satisfies(e -> assertThat(e.getWbsname()).as("check wbsname").isEqualTo(actual.getWbsname()))
            .satisfies(e -> assertThat(e.getParentwbsid()).as("check parentwbsid").isEqualTo(actual.getParentwbsid()))
            .satisfies(e -> assertThat(e.getUnit()).as("check unit").isEqualTo(actual.getUnit()))
            .satisfies(
                e ->
                    assertThat(e.getUnitprice()).as("check unitprice").usingComparator(bigDecimalCompareTo).isEqualTo(actual.getUnitprice())
            )
            .satisfies(e -> assertThat(e.getNumber()).as("check number").usingComparator(bigDecimalCompareTo).isEqualTo(actual.getNumber()))
            .satisfies(
                e ->
                    assertThat(e.getInvoicepaymentamount())
                        .as("check invoicepaymentamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getInvoicepaymentamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getBorrowingpaymentamount())
                        .as("check borrowingpaymentamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getBorrowingpaymentamount())
            )
            .satisfies(
                e ->
                    assertThat(e.getAccountingamount())
                        .as("check accountingamount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getAccountingamount())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPaymentCostListUpdatableRelationshipsEquals(PaymentCostList expected, PaymentCostList actual) {
        assertThat(expected)
            .as("Verify PaymentCostList relationships")
            .satisfies(e -> assertThat(e.getContractPayment()).as("check contractPayment").isEqualTo(actual.getContractPayment()));
    }
}
