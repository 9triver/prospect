<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="subjectCostBudget">
        <h2 class="jh-entity-heading" data-cy="subjectCostBudgetDetailsHeading">
          <span v-text="t$('jy1App.subjectCostBudget.detail.title')"></span> {{ subjectCostBudget.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.subjectCostBudget.contractid')"></span>
          </dt>
          <dd class="field">
            <span>{{ subjectCostBudget.contractid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.subjectCostBudget.subjectid')"></span>
          </dt>
          <dd class="field">
            <span>{{ subjectCostBudget.subjectid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.subjectCostBudget.subjectname')"></span>
          </dt>
          <dd class="field">
            <span>{{ subjectCostBudget.subjectname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.subjectCostBudget.budgetamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ subjectCostBudget.budgetamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.subjectCostBudget.estimatedamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ subjectCostBudget.estimatedamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.subjectCostBudget.implementedamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ subjectCostBudget.implementedamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.subjectCostBudget.difference')"></span>
          </dt>
          <dd class="field">
            <span>{{ subjectCostBudget.difference }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.subjectCostBudget.percentage')"></span>
          </dt>
          <dd class="field">
            <span>{{ subjectCostBudget.percentage }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.subjectCostBudget.projectBudget')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="subjectCostBudget.projectBudget">
              <router-link :to="{ name: 'ProjectBudgetView', params: { projectBudgetId: subjectCostBudget.projectBudget.id } }">{{
                subjectCostBudget.projectBudget.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.subjectCostBudget.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="subjectCostBudget.responsibleperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: subjectCostBudget.responsibleperson.id } }">{{
                subjectCostBudget.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.subjectCostBudget.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="subjectCostBudget.auditorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: subjectCostBudget.auditorid.id } }">{{
                subjectCostBudget.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="subjectCostBudget.id"
          :to="{ name: 'SubjectCostBudgetEdit', params: { subjectCostBudgetId: subjectCostBudget.id } }"
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

<script lang="ts" src="./subject-cost-budget-details.component.ts"></script>
