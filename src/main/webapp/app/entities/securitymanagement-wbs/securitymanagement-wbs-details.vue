<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="securitymanagementWbs">
        <h2 class="jh-entity-heading" data-cy="securitymanagementWbsDetailsHeading">
          <span v-text="t$('jHipster0App.securitymanagementWbs.detail.title')"></span> {{ securitymanagementWbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.securitymanagementWbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ securitymanagementWbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.securitymanagementWbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ securitymanagementWbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.securitymanagementWbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ securitymanagementWbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.securitymanagementWbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ securitymanagementWbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.securitymanagementWbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ securitymanagementWbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.securitymanagementWbs.annualSecurityPlan')"></span>
          </dt>
          <dd>
            <div v-if="securitymanagementWbs.annualSecurityPlan">
              <router-link
                :to="{ name: 'AnnualSecurityPlanView', params: { annualSecurityPlanId: securitymanagementWbs.annualSecurityPlan.id } }"
                >{{ securitymanagementWbs.annualSecurityPlan.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.securitymanagementWbs.safetycheck')"></span>
          </dt>
          <dd>
            <div v-if="securitymanagementWbs.safetycheck">
              <router-link :to="{ name: 'SafetycheckView', params: { safetycheckId: securitymanagementWbs.safetycheck.id } }">{{
                securitymanagementWbs.safetycheck.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="securitymanagementWbs.id"
          :to="{ name: 'SecuritymanagementWbsEdit', params: { securitymanagementWbsId: securitymanagementWbs.id } }"
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

<script lang="ts" src="./securitymanagement-wbs-details.component.ts"></script>
