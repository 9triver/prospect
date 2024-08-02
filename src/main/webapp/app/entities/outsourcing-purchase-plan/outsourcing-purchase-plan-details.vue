<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="outsourcingPurchasePlan">
        <h2 class="jh-entity-heading" data-cy="outsourcingPurchasePlanDetailsHeading">
          <span v-text="t$('jy1App.outsourcingPurchasePlan.detail.title')"></span> {{ outsourcingPurchasePlan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.matarialname')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchasePlan.matarialname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.purchasingmethod')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchasePlan.purchasingmethod }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.budgit')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchasePlan.budgit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.needtime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchasePlan.needtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.planusetime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchasePlan.planusetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.supplierid')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchasePlan.supplierid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.price')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchasePlan.price }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + outsourcingPurchasePlan.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + outsourcingPurchasePlan.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.responsibleperson')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingPurchasePlan.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingPurchasePlan.responsibleperson.id } }">{{
                outsourcingPurchasePlan.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingPurchasePlan.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingPurchasePlan.auditorid.id } }">{{
                outsourcingPurchasePlan.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.projectwbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectwbs, i) in outsourcingPurchasePlan.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="outsourcingPurchasePlan.id"
          :to="{ name: 'OutsourcingPurchasePlanEdit', params: { outsourcingPurchasePlanId: outsourcingPurchasePlan.id } }"
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

<script lang="ts" src="./outsourcing-purchase-plan-details.component.ts"></script>
