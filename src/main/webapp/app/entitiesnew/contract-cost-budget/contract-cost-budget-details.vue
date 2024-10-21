<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="contractCostBudget">
        <h2 class="jh-entity-heading" data-cy="contractCostBudgetDetailsHeading">
          <span v-text="t$('jy1App.contractCostBudget.detail.title')"></span> {{ contractCostBudget.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.contractCostBudget.subject')"></span>
          </dt>
          <dd>
            <span>{{ contractCostBudget.subject }}</span>
          </dd>

          <dt>
            <span v-text="t$('jy1App.contractCostBudget.auxiliaryitem')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.ContractSubject.' + contractCostBudget.auxiliaryitem)"></span>
          </dd>

          <dt>
            <span v-text="t$('jy1App.contractCostBudget.unit')"></span>
          </dt>
          <dd>
            <span>{{ contractCostBudget.unit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.contractCostBudget.number')"></span>
          </dt>
          <dd>
            <span>{{ contractCostBudget.number }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.contractCostBudget.unitprice')"></span>
          </dt>
          <dd>
            <span>{{ contractCostBudget.unitprice }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.contractCostBudget.totalprice')"></span>
          </dt>
          <dd>
            <span>{{ contractCostBudget.totalprice }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.contractCostBudget.projectwbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectwbs, i) in contractCostBudget.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="contractCostBudget.id"
          :to="{ name: 'ContractCostBudgetEdit', params: { contractCostBudgetId: contractCostBudget.id } }"
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

<script lang="ts" src="./contract-cost-budget-details.component.ts"></script>
