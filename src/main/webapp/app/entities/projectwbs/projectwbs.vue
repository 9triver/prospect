<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectwbsHeading">
      <span v-text="t$('jHipster0App.projectwbs.home.title')" id="projectwbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.projectwbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectwbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectwbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.projectwbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectwbs && projectwbs.length === 0">
      <span v-text="t$('jHipster0App.projectwbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectwbs && projectwbs.length > 0">
      <table class="table table-striped" aria-describedby="projectwbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.projectwbsname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.cycleplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.progressmanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.qualitymanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.fundsmanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.technicalmanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.contractualfunds')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.outsourcingmanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.resourcemanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.riskmanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.securitymanagement')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.document')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.safetycheck')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.department')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectwbs.evaluationCriteria')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectwbs in projectwbs" :key="projectwbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </td>
            <td>{{ projectwbs.projectwbsname }}</td>
            <td>{{ projectwbs.wbsspare1 }}</td>
            <td>{{ projectwbs.wbsspare2 }}</td>
            <td>{{ projectwbs.wbsspare3 }}</td>
            <td>{{ projectwbs.wbsspare4 }}</td>
            <td>{{ projectwbs.wbsspare5 }}</td>
            <td>
              <div v-if="projectwbs.cycleplan">
                <router-link :to="{ name: 'CycleplanView', params: { cycleplanId: projectwbs.cycleplan.id } }">{{
                  projectwbs.cycleplan.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.progressmanagement">
                <router-link :to="{ name: 'ProgressmanagementView', params: { progressmanagementId: projectwbs.progressmanagement.id } }">{{
                  projectwbs.progressmanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.qualitymanagement">
                <router-link :to="{ name: 'QualitymanagementView', params: { qualitymanagementId: projectwbs.qualitymanagement.id } }">{{
                  projectwbs.qualitymanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.fundsmanagement">
                <router-link :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: projectwbs.fundsmanagement.id } }">{{
                  projectwbs.fundsmanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.technicalmanagement">
                <router-link
                  :to="{ name: 'TechnicalmanagementView', params: { technicalmanagementId: projectwbs.technicalmanagement.id } }"
                  >{{ projectwbs.technicalmanagement.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="projectwbs.contractualfunds">
                <router-link :to="{ name: 'ContractualfundsView', params: { contractualfundsId: projectwbs.contractualfunds.id } }">{{
                  projectwbs.contractualfunds.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.outsourcingmanagement">
                <router-link
                  :to="{ name: 'OutsourcingmanagementView', params: { outsourcingmanagementId: projectwbs.outsourcingmanagement.id } }"
                  >{{ projectwbs.outsourcingmanagement.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="projectwbs.resourcemanagement">
                <router-link :to="{ name: 'ResourcemanagementView', params: { resourcemanagementId: projectwbs.resourcemanagement.id } }">{{
                  projectwbs.resourcemanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.riskmanagement">
                <router-link :to="{ name: 'RiskmanagementView', params: { riskmanagementId: projectwbs.riskmanagement.id } }">{{
                  projectwbs.riskmanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.securitymanagement">
                <router-link :to="{ name: 'SecuritymanagementView', params: { securitymanagementId: projectwbs.securitymanagement.id } }">{{
                  projectwbs.securitymanagement.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.document">
                <router-link :to="{ name: 'DocumentView', params: { documentId: projectwbs.document.id } }">{{
                  projectwbs.document.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectwbs.safetycheck">
                <router-link :to="{ name: 'SafetycheckView', params: { safetycheckId: projectwbs.safetycheck.id } }">{{
                  projectwbs.safetycheck.id
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
              <div v-if="projectwbs.evaluationCriteria">
                <router-link :to="{ name: 'EvaluationCriteriaView', params: { evaluationCriteriaId: projectwbs.evaluationCriteria.id } }">{{
                  projectwbs.evaluationCriteria.id
                }}</router-link>
              </div>
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
        <span
          id="jHipster0App.projectwbs.delete.question"
          data-cy="projectwbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-projectwbs-heading" v-text="t$('jHipster0App.projectwbs.delete.question', { id: removeId })"></p>
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
