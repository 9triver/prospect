<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="paymentApplication">
        <h2 class="jh-entity-heading" data-cy="paymentApplicationDetailsHeading">
          <span v-text="t$('jy1App.paymentApplication.detail.title')"></span> {{ paymentApplication.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.paymentApplication.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentApplication.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentApplication.contractcode')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentApplication.contractcode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentApplication.planpaymentnode')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentApplication.planpaymentnode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.paymentApplication.planpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ paymentApplication.planpaymentamount }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.paymentApplication.outsourcingContract')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="paymentApplication.outsourcingContract">
              <router-link
                :to="{ name: 'OutsourcingContractView', params: { outsourcingContractId: paymentApplication.outsourcingContract.id } }"
                >{{ paymentApplication.outsourcingContract.id }}</router-link
              >
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="paymentApplication.id"
          :to="{ name: 'PaymentApplicationEdit', params: { paymentApplicationId: paymentApplication.id } }"
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

<script lang="ts" src="./payment-application-details.component.ts"></script>
