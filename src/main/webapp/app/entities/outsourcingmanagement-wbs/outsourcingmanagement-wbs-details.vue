<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="outsourcingmanagementWbs">
        <h2 class="jh-entity-heading" data-cy="outsourcingmanagementWbsDetailsHeading">
          <span v-text="t$('jHipster0App.outsourcingmanagementWbs.detail.title')"></span> {{ outsourcingmanagementWbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmanagementWbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmanagementWbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmanagementWbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmanagementWbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmanagementWbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagementWbs.outsourcingPurchasePlan')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingmanagementWbs.outsourcingPurchasePlan">
              <router-link
                :to="{
                  name: 'OutsourcingPurchasePlanView',
                  params: { outsourcingPurchasePlanId: outsourcingmanagementWbs.outsourcingPurchasePlan.id },
                }"
                >{{ outsourcingmanagementWbs.outsourcingPurchasePlan.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagementWbs.outsourcingPurchaseExecute')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingmanagementWbs.outsourcingPurchaseExecute">
              <router-link
                :to="{
                  name: 'OutsourcingPurchaseExecuteView',
                  params: { outsourcingPurchaseExecuteId: outsourcingmanagementWbs.outsourcingPurchaseExecute.id },
                }"
                >{{ outsourcingmanagementWbs.outsourcingPurchaseExecute.id }}</router-link
              >
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="outsourcingmanagementWbs.id"
          :to="{ name: 'OutsourcingmanagementWbsEdit', params: { outsourcingmanagementWbsId: outsourcingmanagementWbs.id } }"
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

<script lang="ts" src="./outsourcingmanagement-wbs-details.component.ts"></script>
