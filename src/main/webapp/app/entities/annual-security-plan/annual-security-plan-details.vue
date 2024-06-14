<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="annualSecurityPlan">
        <h2 class="jh-entity-heading" data-cy="annualSecurityPlanDetailsHeading">
          <span v-text="t$('jHipster3App.annualSecurityPlan.detail.title')"></span> {{ annualSecurityPlan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.annualSecurityPlan.securityplanid')"></span>
          </dt>
          <dd>
            <span>{{ annualSecurityPlan.securityplanid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.annualSecurityPlan.securityplanname')"></span>
          </dt>
          <dd>
            <span>{{ annualSecurityPlan.securityplanname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.annualSecurityPlan.releasetime')"></span>
          </dt>
          <dd>
            <span>{{ annualSecurityPlan.releasetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.annualSecurityPlan.createtime')"></span>
          </dt>
          <dd>
            <span>{{ annualSecurityPlan.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.annualSecurityPlan.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ annualSecurityPlan.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.annualSecurityPlan.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + annualSecurityPlan.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.annualSecurityPlan.version')"></span>
          </dt>
          <dd>
            <span>{{ annualSecurityPlan.version }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.annualSecurityPlan.project')"></span>
          </dt>
          <dd>
            <div v-if="annualSecurityPlan.project">
              <router-link :to="{ name: 'ProjectView', params: { projectId: annualSecurityPlan.project.id } }">{{
                annualSecurityPlan.project.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.annualSecurityPlan.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="annualSecurityPlan.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: annualSecurityPlan.creatorid.id } }">{{
                annualSecurityPlan.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.annualSecurityPlan.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="annualSecurityPlan.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: annualSecurityPlan.auditorid.id } }">{{
                annualSecurityPlan.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="annualSecurityPlan.id"
          :to="{ name: 'AnnualSecurityPlanEdit', params: { annualSecurityPlanId: annualSecurityPlan.id } }"
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

<script lang="ts" src="./annual-security-plan-details.component.ts"></script>
