<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="fundsmanagementWbs">
        <h2 class="jh-entity-heading" data-cy="fundsmanagementWbsDetailsHeading">
          <span v-text="t$('jHipster0App.fundsmanagementWbs.detail.title')"></span> {{ fundsmanagementWbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagementWbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagementWbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagementWbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagementWbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagementWbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.auditedbudget')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagementWbs.auditedbudget">
              <router-link :to="{ name: 'AuditedbudgetView', params: { auditedbudgetId: fundsmanagementWbs.auditedbudget.id } }">{{
                fundsmanagementWbs.auditedbudget.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.totalbudget')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagementWbs.totalbudget">
              <router-link :to="{ name: 'TotalbudgetView', params: { totalbudgetId: fundsmanagementWbs.totalbudget.id } }">{{
                fundsmanagementWbs.totalbudget.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.unitbudget')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagementWbs.unitbudget">
              <router-link :to="{ name: 'UnitbudgetView', params: { unitbudgetId: fundsmanagementWbs.unitbudget.id } }">{{
                fundsmanagementWbs.unitbudget.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.fundsavailability')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagementWbs.fundsavailability">
              <router-link
                :to="{ name: 'FundsavailabilityView', params: { fundsavailabilityId: fundsmanagementWbs.fundsavailability.id } }"
                >{{ fundsmanagementWbs.fundsavailability.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.contractualfunds')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagementWbs.contractualfunds">
              <router-link :to="{ name: 'ContractualfundsView', params: { contractualfundsId: fundsmanagementWbs.contractualfunds.id } }">{{
                fundsmanagementWbs.contractualfunds.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="fundsmanagementWbs.id"
          :to="{ name: 'FundsmanagementWbsEdit', params: { fundsmanagementWbsId: fundsmanagementWbs.id } }"
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

<script lang="ts" src="./fundsmanagement-wbs-details.component.ts"></script>
