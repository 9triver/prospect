<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="contract">
        <h2 class="jh-entity-heading" data-cy="contractDetailsHeading">
          <span v-text="t$('jy1App.contract.detail.title')"></span> {{ contract.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.contract.contractcode')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.contractcode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.contractname')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.contractname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.projectid')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.projectid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.projectname')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.projectname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.contracttype')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.ContractType.' + contract.contracttype)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.year')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.year }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.amount')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.amount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.starttime')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.starttime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.endtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.endtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + contract.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.status')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.ContractStatus.' + contract.status)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.budgetamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.budgetamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.estimatedamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.estimatedamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.implementedamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.implementedamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.contract.difference')"></span>
          </dt>
          <dd class="field">
            <span>{{ contract.difference }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.contract.costControlSystem')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(costControlSystem, i) in contract.costControlSystems" :key="costControlSystem.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'CostControlSystemView', params: { costControlSystemId: costControlSystem.id } }">{{
                costControlSystem.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="contract.id" :to="{ name: 'ContractEdit', params: { contractId: contract.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./contract-details.component.ts"></script>
