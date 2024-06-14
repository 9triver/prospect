<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="outsourcingmPurchaseExecute">
        <h2 class="jh-entity-heading" data-cy="outsourcingmPurchaseExecuteDetailsHeading">
          <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.detail.title')"></span> {{ outsourcingmPurchaseExecute.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.outsourcingexecuteid')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchaseExecute.outsourcingexecuteid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.matarialname')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchaseExecute.matarialname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.purchasingmethod')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchaseExecute.purchasingmethod }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.budgit')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchaseExecute.budgit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.needtime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchaseExecute.needtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.planusetime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchaseExecute.planusetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.supplierid')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchaseExecute.supplierid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.price')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmPurchaseExecute.price }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + outsourcingmPurchaseExecute.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.outsourcingplanid')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingmPurchaseExecute.outsourcingplanid">
              <router-link
                :to="{
                  name: 'OutsourcingmPurchasePlanView',
                  params: { outsourcingmPurchasePlanId: outsourcingmPurchaseExecute.outsourcingplanid.id },
                }"
                >{{ outsourcingmPurchaseExecute.outsourcingplanid.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingmPurchaseExecute.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingmPurchaseExecute.responsibleid.id } }">{{
                outsourcingmPurchaseExecute.responsibleid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="outsourcingmPurchaseExecute.id"
          :to="{ name: 'OutsourcingmPurchaseExecuteEdit', params: { outsourcingmPurchaseExecuteId: outsourcingmPurchaseExecute.id } }"
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

<script lang="ts" src="./outsourcingm-purchase-execute-details.component.ts"></script>
