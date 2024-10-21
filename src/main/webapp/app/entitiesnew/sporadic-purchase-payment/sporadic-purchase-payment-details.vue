<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sporadicPurchasePayment">
        <h2 class="jh-entity-heading" data-cy="sporadicPurchasePaymentDetailsHeading">
          <span v-text="t$('jy1App.sporadicPurchasePayment.detail.title')"></span> {{ sporadicPurchasePayment.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.sporadicPurchasePayment.planpaymentnode')"></span>
          </dt>
          <dd class="field">
            <span>{{ sporadicPurchasePayment.planpaymentnode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.sporadicPurchasePayment.planpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ sporadicPurchasePayment.planpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.sporadicPurchasePayment.actualpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ sporadicPurchasePayment.actualpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.sporadicPurchasePayment.paymenttype')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.PaymentType.' + sporadicPurchasePayment.paymenttype)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.sporadicPurchasePayment.financialvoucherid')"></span>
          </dt>
          <dd class="field">
            <span>{{ sporadicPurchasePayment.financialvoucherid }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="sporadicPurchasePayment.id"
          :to="{ name: 'SporadicPurchasePaymentEdit', params: { sporadicPurchasePaymentId: sporadicPurchasePayment.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./sporadic-purchase-payment-details.component.ts"></script>
