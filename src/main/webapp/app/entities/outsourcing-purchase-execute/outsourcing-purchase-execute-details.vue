<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="outsourcingPurchaseExecute">
        <h2 class="jh-entity-heading" data-cy="outsourcingPurchaseExecuteDetailsHeading">
          <span v-text="t$('jy1App.outsourcingPurchaseExecute.detail.title')"></span> {{ outsourcingPurchaseExecute.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.matarialname')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchaseExecute.matarialname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.purchasingmethod')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchaseExecute.purchasingmethod }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.budgit')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchaseExecute.budgit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.needtime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchaseExecute.needtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.planusetime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchaseExecute.planusetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.supplierid')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchaseExecute.supplierid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.price')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingPurchaseExecute.price }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + outsourcingPurchaseExecute.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.responsibleperson')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingPurchaseExecute.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingPurchaseExecute.responsibleperson.id } }">{{
                outsourcingPurchaseExecute.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.outsourcingplanid')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingPurchaseExecute.outsourcingplanid">
              <router-link
                :to="{
                  name: 'OutsourcingPurchasePlanView',
                  params: { outsourcingPurchasePlanId: outsourcingPurchaseExecute.outsourcingplanid.id },
                }"
                >{{ outsourcingPurchaseExecute.outsourcingplanid.id }}</router-link
              >
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="outsourcingPurchaseExecute.id"
          :to="{ name: 'OutsourcingPurchaseExecuteEdit', params: { outsourcingPurchaseExecuteId: outsourcingPurchaseExecute.id } }"
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

<script lang="ts" src="./outsourcing-purchase-execute-details.component.ts"></script>
