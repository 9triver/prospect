<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="paymentCostList">
        <h2 class="jh-entity-heading" data-cy="paymentCostListDetailsHeading">
          <span v-text="t$('jy1App.paymentCostList.detail.title')"></span> {{ paymentCostList.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.paymentCostList.wbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentCostList.wbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentCostList.wbsname')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentCostList.wbsname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentCostList.parentwbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentCostList.parentwbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentCostList.unit')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentCostList.unit }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentCostList.unitprice')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentCostList.unitprice }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentCostList.number')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentCostList.number }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentCostList.invoicepaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentCostList.invoicepaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentCostList.borrowingpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentCostList.borrowingpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentCostList.accountingamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentCostList.accountingamount }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.paymentCostList.contractPayment')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="paymentCostList.contractPayment">
              <router-link :to="{ name: 'ContractPaymentView', params: { contractPaymentId: paymentCostList.contractPayment.id } }">{{
                paymentCostList.contractPayment.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="paymentCostList.id"
          :to="{ name: 'PaymentCostListEdit', params: { paymentCostListId: paymentCostList.id } }"
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

<script lang="ts" src="./payment-cost-list-details.component.ts"></script>
