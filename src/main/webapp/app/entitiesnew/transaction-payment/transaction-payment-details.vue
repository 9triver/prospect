<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="transactionPayment">
        <h2 class="jh-entity-heading" data-cy="transactionPaymentDetailsHeading">
          <span v-text="t$('jy1App.transactionPayment.detail.title')"></span> {{ transactionPayment.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.transactionPayment.planpaymentnode')"></span>
          </dt>
          <dd class="field">
            <span>{{ transactionPayment.planpaymentnode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.transactionPayment.planpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ transactionPayment.planpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.transactionPayment.actualpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ transactionPayment.actualpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.transactionPayment.paymenttype')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.PaymentType.' + transactionPayment.paymenttype)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.transactionPayment.financialvoucherid')"></span>
          </dt>
          <dd class="field">
            <span>{{ transactionPayment.financialvoucherid }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="transactionPayment.id"
          :to="{ name: 'TransactionPaymentEdit', params: { transactionPaymentId: transactionPayment.id } }"
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

<script lang="ts" src="./transaction-payment-details.component.ts"></script>
