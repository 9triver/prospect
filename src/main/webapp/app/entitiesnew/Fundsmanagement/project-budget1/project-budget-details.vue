<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectBudget">
        <h2 class="jh-entity-heading" data-cy="projectBudgetDetailsHeading">
          <span v-text="t$('jy1App.projectBudget.detail.title')"></span> {{ projectBudget.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.wbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.wbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.wbsname')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.wbsname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.parentwbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.parentwbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.subjectid')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.subjectid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.subjectname')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.subjectname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.contractid')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.contractid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.contractname')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.contractname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.year')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.year }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.auxiliaryitem')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.auxiliaryitem }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.unit')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.unit }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.number')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.number }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.unitprice')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.unitprice }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.budgetamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.budgetamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.estimatedamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.estimatedamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.implementedamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.implementedamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.difference')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.difference }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectBudget.remark')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectBudget.remark }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectBudget.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectBudget.responsibleperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: projectBudget.responsibleperson.id } }">{{
                projectBudget.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectBudget.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectBudget.auditorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: projectBudget.auditorid.id } }">{{
                projectBudget.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectBudget.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(projectwbs, i) in projectBudget.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectBudget.id"
          :to="{ name: 'ProjectBudgetEdit', params: { projectBudgetId: projectBudget.id } }"
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

<script lang="ts" src="./project-budget-details.component.ts"></script>
