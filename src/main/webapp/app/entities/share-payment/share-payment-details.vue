<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sharePayment">
        <h2 class="jh-entity-heading" data-cy="sharePaymentDetailsHeading">
          <span v-text="t$('jy1App.sharePayment.detail.title')"></span> {{ sharePayment.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.sharePayment.planpaymentnode')"></span>
          </dt>
          <dd class="field">
            <span>{{ sharePayment.planpaymentnode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.sharePayment.planpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ sharePayment.planpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.sharePayment.actualpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ sharePayment.actualpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.sharePayment.paymenttype')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.PaymentType.' + sharePayment.paymenttype)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.sharePayment.financialvoucherid')"></span>
          </dt>
          <dd class="field">
            <span>{{ sharePayment.financialvoucherid }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="sharePayment.id"
          :to="{ name: 'SharePaymentEdit', params: { sharePaymentId: sharePayment.id } }"
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

<script lang="ts" src="./share-payment-details.component.ts"></script>
