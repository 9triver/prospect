<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectHeading">
      <span v-text="t$('jHipster3App.project.home.title')" id="project-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.project.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.project.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projects && projects.length === 0">
      <span v-text="t$('jHipster3App.project.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projects && projects.length > 0">
      <table class="table table-striped" aria-describedby="projects">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.projectid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.number')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.projecttype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.responsiblename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.duedate')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.priorty')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.progressid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.returnsid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.qualityid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.fundsid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.cycleplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.progressmanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.qualitymanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.fundsmanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.technicalCondition')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.contractualfunds')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.outsourcingmPurchaseExecute')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.resourcemanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.riskmanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.document')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.safetycheck')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.department')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.evaluationCriteria')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.project.projectSecrecy')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="project in projects" :key="project.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectView', params: { projectId: project.id } }">{{ project.id }}</router-link>
            </td>
            <td>{{ project.projectid }}</td>
            <td>{{ project.projectname }}</td>
            <td>{{ project.description }}</td>
            <td>{{ project.number }}</td>
            <td>{{ project.projecttype }}</td>
            <td>{{ project.responsiblename }}</td>
            <td>{{ project.duedate }}</td>
            <td>{{ project.priorty }}</td>
            <td>{{ project.progressid }}</td>
            <td>{{ project.returnsid }}</td>
            <td>{{ project.qualityid }}</td>
            <td>{{ project.fundsid }}</td>
            <td v-text="t$('jHipster3App.ProjectStatus.' + project.status)"></td>
            <td v-text="t$('jHipster3App.AuditStatus.' + project.auditStatus)"></td>
            <td>
              <div v-if="project.cycleplan">
                <router-link :to="{ name: 'CycleplanView', params: { cycleplanId: project.cycleplan.id } }">{{
                  project.cycleplan.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.progressmanagement">
                <router-link :to="{ name: 'ProgressmanagementView', params: { progressmanagementId: project.progressmanagement.id } }">{{
                  project.progressmanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.qualitymanagement">
                <router-link :to="{ name: 'QualitymanagementView', params: { qualitymanagementId: project.qualitymanagement.id } }">{{
                  project.qualitymanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.fundsmanagement">
                <router-link :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: project.fundsmanagement.id } }">{{
                  project.fundsmanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.technicalCondition">
                <router-link :to="{ name: 'TechnicalConditionView', params: { technicalConditionId: project.technicalCondition.id } }">{{
                  project.technicalCondition.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.contractualfunds">
                <router-link :to="{ name: 'ContractualfundsView', params: { contractualfundsId: project.contractualfunds.id } }">{{
                  project.contractualfunds.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.outsourcingmPurchaseExecute">
                <router-link
                  :to="{
                    name: 'OutsourcingmPurchaseExecuteView',
                    params: { outsourcingmPurchaseExecuteId: project.outsourcingmPurchaseExecute.id },
                  }"
                  >{{ project.outsourcingmPurchaseExecute.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="project.resourcemanagement">
                <router-link :to="{ name: 'ResourcemanagementView', params: { resourcemanagementId: project.resourcemanagement.id } }">{{
                  project.resourcemanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.riskmanagement">
                <router-link :to="{ name: 'RiskmanagementView', params: { riskmanagementId: project.riskmanagement.id } }">{{
                  project.riskmanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.document">
                <router-link :to="{ name: 'DocumentView', params: { documentId: project.document.id } }">{{
                  project.document.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.safetycheck">
                <router-link :to="{ name: 'SafetycheckView', params: { safetycheckId: project.safetycheck.id } }">{{
                  project.safetycheck.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.department">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: project.department.id } }">{{
                  project.department.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.evaluationCriteria">
                <router-link :to="{ name: 'EvaluationCriteriaView', params: { evaluationCriteriaId: project.evaluationCriteria.id } }">{{
                  project.evaluationCriteria.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: project.responsibleid.id } }">{{
                  project.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: project.auditorid.id } }">{{
                  project.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="project.projectSecrecy">
                <router-link :to="{ name: 'ProjectSecrecyView', params: { projectSecrecyId: project.projectSecrecy.id } }">{{
                  project.projectSecrecy.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProjectView', params: { projectId: project.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProjectEdit', params: { projectId: project.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(project)"
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
        <span id="jHipster3App.project.delete.question" data-cy="projectDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-project-heading" v-text="t$('jHipster3App.project.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-project"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProject()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./project.component.ts"></script>
