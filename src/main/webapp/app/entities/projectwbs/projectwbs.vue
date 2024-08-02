<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectwbsHeading">
      <span v-text="t$('jy1App.projectwbs.home.title')" id="projectwbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectwbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectwbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectwbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.projectwbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectwbs && projectwbs.length === 0">
      <span v-text="t$('jy1App.projectwbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectwbs && projectwbs.length > 0">
      <table class="table table-striped" aria-describedby="projectwbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.wbsname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.parentwbsid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.pbsid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.description')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.belongfront')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.progress')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.type')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.priorty')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.deliverables')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.status')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.workbag')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.responsibleperson')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.technicaldirector')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.administrativedirector')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.knowingpeople')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.responsibledepartment')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.relevantdepartment')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.department')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.project')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.projectpbs')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.progressPlan')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.fundsEstimation')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.contractCostBudget')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.costControlSystem')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.qualityObjectives')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.outsourcingContractual')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.outsourcingPurchasePlan')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.technical')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.technicalCondition')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectwbs.projectRisk')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectwbs in projectwbs" :key="projectwbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </td>
            <td>{{ projectwbs.wbsname }}</td>
            <td>{{ projectwbs.parentwbsid }}</td>
            <td>{{ projectwbs.pbsid }}</td>
            <td>{{ projectwbs.description }}</td>
            <td>{{ projectwbs.belongfront }}</td>
            <td>{{ projectwbs.starttime }}</td>
            <td>{{ projectwbs.endtime }}</td>
            <td>{{ projectwbs.progress }}</td>
            <td>{{ projectwbs.type }}</td>
            <td>{{ projectwbs.priorty }}</td>
            <td v-text="t$('jy1App.Secretlevel.' + projectwbs.secretlevel)"></td>
            <td>{{ projectwbs.deliverables }}</td>
            <td v-text="t$('jy1App.ProjectStatus.' + projectwbs.status)"></td>
            <td v-text="t$('jy1App.AuditStatus.' + projectwbs.auditStatus)"></td>
            <td>{{ projectwbs.workbag }}</td>
            <td>
              <div v-if="projectwbs.responsibleperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.responsibleperson.id } }">{{
                  projectwbs.responsibleperson.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.technicaldirector">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.technicaldirector.id } }">{{
                  projectwbs.technicaldirector.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.administrativedirector">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.administrativedirector.id } }">{{
                  projectwbs.administrativedirector.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.knowingpeople">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.knowingpeople.id } }">{{
                  projectwbs.knowingpeople.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.auditorid.id } }">{{
                  projectwbs.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.responsibledepartment">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectwbs.responsibledepartment.id } }">{{
                  projectwbs.responsibledepartment.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.relevantdepartment">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectwbs.relevantdepartment.id } }">{{
                  projectwbs.relevantdepartment.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.department">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectwbs.department.id } }">{{
                  projectwbs.department.id
                }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(project, i) in projectwbs.projects" :key="project.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectView', params: { projectId: project.id } }">{{
                  project.id
                }}</router-link>
              </span>
            </td>
            <td>
              <span v-for="(projectpbs, i) in projectwbs.projectpbs" :key="projectpbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectpbsView', params: { projectpbsId: projectpbs.id } }">{{
                  projectpbs.id
                }}</router-link>
              </span>
            </td>
            <td>
              <span v-for="(progressPlan, i) in projectwbs.progressPlans" :key="progressPlan.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProgressPlanView', params: { progressPlanId: progressPlan.id } }">{{
                  progressPlan.id
                }}</router-link>
              </span>
            </td>
            <td>
              <span v-for="(fundsEstimation, i) in projectwbs.fundsEstimations" :key="fundsEstimation.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'FundsEstimationView', params: { fundsEstimationId: fundsEstimation.id } }"
                  >{{ fundsEstimation.id }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(contractCostBudget, i) in projectwbs.contractCostBudgets" :key="contractCostBudget.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'ContractCostBudgetView', params: { contractCostBudgetId: contractCostBudget.id } }"
                  >{{ contractCostBudget.id }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(costControlSystem, i) in projectwbs.costControlSystems" :key="costControlSystem.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'CostControlSystemView', params: { costControlSystemId: costControlSystem.id } }"
                  >{{ costControlSystem.id }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(qualityObjectives, i) in projectwbs.qualityObjectives" :key="qualityObjectives.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'QualityObjectivesView', params: { qualityObjectivesId: qualityObjectives.id } }"
                  >{{ qualityObjectives.id }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(outsourcingContractual, i) in projectwbs.outsourcingContractuals" :key="outsourcingContractual.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'OutsourcingContractualView', params: { outsourcingContractualId: outsourcingContractual.id } }"
                  >{{ outsourcingContractual.id }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(outsourcingPurchasePlan, i) in projectwbs.outsourcingPurchasePlans" :key="outsourcingPurchasePlan.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'OutsourcingPurchasePlanView', params: { outsourcingPurchasePlanId: outsourcingPurchasePlan.id } }"
                  >{{ outsourcingPurchasePlan.id }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(technical, i) in projectwbs.technicals" :key="technical.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'TechnicalView', params: { technicalId: technical.id } }">{{
                  technical.id
                }}</router-link>
              </span>
            </td>
            <td>
              <span v-for="(technicalCondition, i) in projectwbs.technicalConditions" :key="technicalCondition.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'TechnicalConditionView', params: { technicalConditionId: technicalCondition.id } }"
                  >{{ technicalCondition.id }}</router-link
                >
              </span>
            </td>
            <td>
              <span v-for="(projectRisk, i) in projectwbs.projectRisks" :key="projectRisk.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectRiskView', params: { projectRiskId: projectRisk.id } }">{{
                  projectRisk.id
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProjectwbsEdit', params: { projectwbsId: projectwbs.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(projectwbs)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span id="jy1App.projectwbs.delete.question" data-cy="projectwbsDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-projectwbs-heading" v-text="t$('jy1App.projectwbs.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-projectwbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProjectwbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./projectwbs.component.ts"></script>
