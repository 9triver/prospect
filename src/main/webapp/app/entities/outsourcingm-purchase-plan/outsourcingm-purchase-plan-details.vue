<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="outsourcingmPurchasePlan">
        <h2 class="jh-entity-heading" data-cy="outsourcingmPurchasePlanDetailsHeading">
          <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.detail.title')"></span> {{ outsourcingmPurchasePlan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.outsourcingplanid')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchasePlan.outsourcingplanid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.matarialname')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchasePlan.matarialname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.purchasingmethod')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchasePlan.purchasingmethod }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.budgit')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchasePlan.budgit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.needtime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchasePlan.needtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.planusetime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchasePlan.planusetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.supplierid')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchasePlan.supplierid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.price')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchasePlan.price }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + outsourcingmPurchasePlan.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + outsourcingmPurchasePlan.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.project')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingmPurchasePlan.project">
              <router-link :to="{ name: 'ProjectView', params: { projectId: outsourcingmPurchasePlan.project.id } }">{{
                outsourcingmPurchasePlan.project.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingmPurchasePlan.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingmPurchasePlan.responsibleid.id } }">{{
                outsourcingmPurchasePlan.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingmPurchasePlan.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingmPurchasePlan.auditorid.id } }">{{
                outsourcingmPurchasePlan.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="outsourcingmPurchasePlan.id"
          :to="{ name: 'OutsourcingmPurchasePlanEdit', params: { outsourcingmPurchasePlanId: outsourcingmPurchasePlan.id } }"
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

<script lang="ts" src="./outsourcingm-purchase-plan-details.component.ts"></script>
