<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectwbs">
        <h2 class="jh-entity-heading" data-cy="projectwbsDetailsHeading">
          <span v-text="t$('jy1App.projectwbs.detail.title')"></span> {{ projectwbs.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.wbsname')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.wbsname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.parentwbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.parentwbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.pbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.pbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.description }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.belongfrontline')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.belongfrontline }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.starttime')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.starttime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.endtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.endtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.progress')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.progress }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.type')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.type }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.priorty')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.priorty }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + projectwbs.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.deliverables')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.deliverables }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.status')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.ProjectStatus.' + projectwbs.status)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + projectwbs.auditStatus)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectwbs.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectwbs.workbagid }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectwbs.responsibleperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: projectwbs.responsibleperson.id } }">{{
                projectwbs.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.technicaldirector')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectwbs.technicaldirector">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: projectwbs.technicaldirector.id } }">{{
                projectwbs.technicaldirector.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.knowingpeople')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectwbs.knowingpeople">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: projectwbs.knowingpeople.id } }">{{
                projectwbs.knowingpeople.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectwbs.auditorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: projectwbs.auditorid.id } }">{{
                projectwbs.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.responsibledepartment')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectwbs.responsibledepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectwbs.responsibledepartment.id } }">{{
                projectwbs.responsibledepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.projectdeliverables')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(projectdeliverables, i) in projectwbs.projectdeliverables" :key="projectdeliverables.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectdeliverablesView', params: { projectdeliverablesId: projectdeliverables.id } }">{{
                projectdeliverables.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.relevantdepartment')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(relevantdepartment, i) in projectwbs.relevantdepartments" :key="relevantdepartment.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: relevantdepartment.id } }">{{
                relevantdepartment.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectwbs.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: projectwbs.workbag.id } }">{{
                projectwbs.workbag.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.progressPlan')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(progressPlan, i) in projectwbs.progressPlans" :key="progressPlan.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProgressPlanView', params: { progressPlanId: progressPlan.id } }">{{
                progressPlan.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.projectBudget')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(projectBudget, i) in projectwbs.projectBudgets" :key="projectBudget.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectBudgetView', params: { projectBudgetId: projectBudget.id } }">{{
                projectBudget.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.project')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(project, i) in projectwbs.projects" :key="project.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectView', params: { projectId: project.id } }">{{ project.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.fundsEstimation')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(fundsEstimation, i) in projectwbs.fundsEstimations" :key="fundsEstimation.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'FundsEstimationView', params: { fundsEstimationId: fundsEstimation.id } }">{{
                fundsEstimation.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.contractCostBudget')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(contractCostBudget, i) in projectwbs.contractCostBudgets" :key="contractCostBudget.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ContractCostBudgetView', params: { contractCostBudgetId: contractCostBudget.id } }">{{
                contractCostBudget.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.costControlSystem')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(costControlSystem, i) in projectwbs.costControlSystems" :key="costControlSystem.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'CostControlSystemView', params: { costControlSystemId: costControlSystem.id } }">{{
                costControlSystem.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.outsourcingContractual')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(outsourcingContractual, i) in projectwbs.outsourcingContractuals" :key="outsourcingContractual.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'OutsourcingContractualView', params: { outsourcingContractualId: outsourcingContractual.id } }">{{
                outsourcingContractual.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.outsourcingPurchasePlan')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(outsourcingPurchasePlan, i) in projectwbs.outsourcingPurchasePlans" :key="outsourcingPurchasePlan.id"
              >{{ i > 0 ? '' : '' }}
              <router-link
                :to="{ name: 'OutsourcingPurchasePlanView', params: { outsourcingPurchasePlanId: outsourcingPurchasePlan.id } }"
                >{{ outsourcingPurchasePlan.id }}</router-link
              >
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectwbs.technical')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(technical, i) in projectwbs.technicals" :key="technical.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'TechnicalView', params: { technicalId: technical.id } }">{{ technical.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectwbs.id"
          :to="{ name: 'ProjectwbsEdit', params: { projectwbsId: projectwbs.id } }"
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

<script lang="ts" src="./projectwbs-details.component.ts"></script>
