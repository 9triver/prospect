package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class SporadicPurchasePaymentAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSporadicPurchasePaymentAllPropertiesEquals(SporadicPurchasePayment expected, SporadicPurchasePayment actual) {
        assertSporadicPurchasePaymentAutoGeneratedPropertiesEquals(expected, actual);
        assertSporadicPurchasePaymentAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSporadicPurchasePaymentAllUpdatablePropertiesEquals(
        SporadicPurchasePayment expected,
        SporadicPurchasePayment actual
    ) {
        assertSporadicPurchasePaymentUpdatableFieldsEquals(expected, actual);
        assertSporadicPurchasePaymentUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSporadicPurchasePaymentAutoGeneratedPropertiesEquals(
        SporadicPurchasePayment expected,
        SporadicPurchasePayment actual
    ) {
        assertThat(expected)
            .as("Verify SporadicPurchasePayment auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSporadicPurchasePaymentUpdatableFieldsEquals(
        SporadicPurchasePayment expected,
        SporadicPurchasePayment actual
    ) {
        assertThat(expected)
            .as("Verify SporadicPurchasePayment relevant properties")
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
    public static void assertSporadicPurchasePaymentUpdatableRelationshipsEquals(
        SporadicPurchasePayment expected,
        SporadicPurchasePayment actual
    ) {}
}