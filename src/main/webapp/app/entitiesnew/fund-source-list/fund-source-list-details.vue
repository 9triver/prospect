<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="fundSourceList">
        <h2 class="jh-entity-heading" data-cy="fundSourceListDetailsHeading">
          <span v-text="t$('jy1App.fundSourceList.detail.title')"></span> {{ fundSourceList.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.fundSourceList.paymentid')"></span>
          </dt>
          <dd class="field">
            <span>{{ fundSourceList.paymentid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.fundSourceList.contractcode')"></span>
          </dt>
          <dd class="field">
            <span>{{ fundSourceList.contractcode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.fundSourceList.contractname')"></span>
          </dt>
          <dd class="field">
            <span>{{ fundSourceList.contractname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.fundSourceList.amount')"></span>
          </dt>
          <dd class="field">
            <span>{{ fundSourceList.amount }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.fundSourceList.transactionPayment')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="fundSourceList.transactionPayment">
              <router-link
                :to="{ name: 'TransactionPaymentView', params: { transactionPaymentId: fundSourceList.transactionPayment.id } }"
                >{{ fundSourceList.transactionPayment.id }}</router-link
              >
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.fundSourceList.sporadicPurchasePayment')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="fundSourceList.sporadicPurchasePayment">
              <router-link
                :to="{
                  name: 'SporadicPurchasePaymentView',
                  params: { sporadicPurchasePaymentId: fundSourceList.sporadicPurchasePayment.id },
                }"
                >{{ fundSourceList.sporadicPurchasePayment.id }}</router-link
              >
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.fundSourceList.sharePayment')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="fundSourceList.sharePayment">
              <router-link :to="{ name: 'SharePaymentView', params: { sharePaymentId: fundSourceList.sharePayment.id } }">{{
                fundSourceList.sharePayment.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.fundSourceList.contractPayment')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="fundSourceList.contractPayment">
              <router-link :to="{ name: 'ContractPaymentView', params: { contractPaymentId: fundSourceList.contractPayment.id } }">{{
                fundSourceList.contractPayment.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="fundSourceList.id"
          :to="{ name: 'FundSourceListEdit', params: { fundSourceListId: fundSourceList.id } }"
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

<script lang="ts" src="./fund-source-list-details.component.ts"></script>
