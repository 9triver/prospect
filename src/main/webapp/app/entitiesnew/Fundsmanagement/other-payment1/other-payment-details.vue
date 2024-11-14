<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="otherPayment">
        <h2 class="jh-entity-heading" data-cy="otherPaymentDetailsHeading">
          <span v-text="t$('jy1App.otherPayment.detail.title')"></span> {{ otherPayment.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.otherPayment.type')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.OtherPaymenttype.' + otherPayment.type)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.otherPayment.registertime')"></span>
          </dt>
          <dd class="field">
            <span>{{ otherPayment.registertime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.otherPayment.subjectid')"></span>
          </dt>
          <dd class="field">
            <span>{{ otherPayment.subjectid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.otherPayment.subjectname')"></span>
          </dt>
          <dd class="field">
            <span>{{ otherPayment.subjectname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.otherPayment.paymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ otherPayment.paymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.otherPayment.contractcode')"></span>
          </dt>
          <dd class="field">
            <span>{{ otherPayment.contractcode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.otherPayment.contractname')"></span>
          </dt>
          <dd class="field">
            <span>{{ otherPayment.contractname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.otherPayment.wbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ otherPayment.wbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.otherPayment.wbsname')"></span>
          </dt>
          <dd class="field">
            <span>{{ otherPayment.wbsname }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="otherPayment.id"
          :to="{ name: 'OtherPaymentEdit', params: { otherPaymentId: otherPayment.id } }"
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

<script lang="ts" src="./other-payment-details.component.ts"></script>
