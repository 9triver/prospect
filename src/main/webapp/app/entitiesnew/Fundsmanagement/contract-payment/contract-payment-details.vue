<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="contractPayment">
        <h2 class="jh-entity-heading" data-cy="contractPaymentDetailsHeading">
          <span v-text="t$('jy1App.contractPayment.detail.title')"></span> {{ contractPayment.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.contractPayment.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ contractPayment.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contractPayment.workbagname')"></span>
          </dt>
          <dd class="field">
            <span>{{ contractPayment.workbagname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contractPayment.contractcode')"></span>
          </dt>
          <dd class="field">
            <span>{{ contractPayment.contractcode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contractPayment.contractname')"></span>
          </dt>
          <dd class="field">
            <span>{{ contractPayment.contractname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contractPayment.planpaymentnode')"></span>
          </dt>
          <dd class="field">
            <span>{{ contractPayment.planpaymentnode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contractPayment.planpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ contractPayment.planpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contractPayment.actualpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ contractPayment.actualpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contractPayment.paymenttype')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.PaymentType.' + contractPayment.paymenttype)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contractPayment.financialvoucherid')"></span>
          </dt>
          <dd class="field">
            <span>{{ contractPayment.financialvoucherid }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.contractPayment.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="contractPayment.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: contractPayment.workbag.id } }">{{
                contractPayment.workbag.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.contractPayment.paymentApplication')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="contractPayment.paymentApplication">
              <router-link
                :to="{ name: 'PaymentApplicationView', params: { paymentApplicationId: contractPayment.paymentApplication.id } }"
                >{{ contractPayment.paymentApplication.id }}</router-link
              >
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.contractPayment.paymentCostList')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(paymentCostList, i) in contractPayment.paymentCostLists" :key="paymentCostList.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'PaymentCostListView', params: { paymentCostListId: paymentCostList.id } }">{{
                paymentCostList.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.contractPayment.fundSourceList')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(fundSourceList, i) in contractPayment.fundSourceLists" :key="fundSourceList.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'FundSourceListView', params: { fundSourceListId: fundSourceList.id } }">{{
                fundSourceList.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="contractPayment.id"
          :to="{ name: 'ContractPaymentEdit', params: { contractPaymentId: contractPayment.id } }"
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

<script lang="ts" src="./contract-payment-details.component.ts"></script>
