<template>
  <div>
    <h2 id="page-heading" data-cy="ProgressPlanHeading">
      <span v-text="t$('jy1App.progressPlan.home.title')" id="progress-plan-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.progressPlan.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProgressPlanCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-progress-plan"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.progressPlan.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && progressPlans && progressPlans.length === 0">
      <span v-text="t$('jy1App.progressPlan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="progressPlans && progressPlans.length > 0">
      <table class="table table-striped" aria-describedby="progressPlans">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.planname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.plantype')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.planlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.planstage')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.readytime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.description')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.deliverables')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.planobjectives')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.preplan')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.actualstarttime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.actualendtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.progress')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.progresstype')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.iskey')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.status')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.remark')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.responsibleperson')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.cooperatingperson')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.responsibledepartment')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.cooperatingdepartment')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.planReturns')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.projectwbs')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.progressPlan.projectRisk')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="progressPlan in progressPlans" :key="progressPlan.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProgressPlanView', params: { progressPlanId: progressPlan.id } }">{{
                progressPlan.id
              }}</router-link>
            </td>
            <td>{{ progressPlan.planname }}</td>
            <td v-text="t$('jy1App.Secretlevel.' + progressPlan.secretlevel)"></td>
            <td>{{ progressPlan.plantype }}</td>
            <td v-text="t$('jy1App.PlanLevel.' + progressPlan.planlevel)"></td>
            <td>{{ progressPlan.planstage }}</td>
            <td>{{ progressPlan.readytime }}</td>
            <td>{{ progressPlan.description }}</td>
            <td>{{ progressPlan.deliverables }}</td>
            <td>{{ progressPlan.planobjectives }}</td>
            <td>{{ progressPlan.preplan }}</td>
            <td>{{ progressPlan.starttime }}</td>
            <td>{{ progressPlan.endtime }}</td>
            <td>{{ progressPlan.actualstarttime }}</td>
            <td>{{ progressPlan.actualendtime }}</td>
            <td>{{ progressPlan.progress }}</td>
            <td v-text="t$('jy1App.Progressstatus.' + progressPlan.progresstype)"></td>
            <td>{{ progressPlan.iskey }}</td>
            <td v-text="t$('jy1App.Planstatus.' + progressPlan.status)"></td>
            <td v-text="t$('jy1App.AuditStatus.' + progressPlan.auditStatus)"></td>
            <td>{{ progressPlan.remark }}</td>
            <td>
              <div v-if="progressPlan.responsibleperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: progressPlan.responsibleperson.id } }">{{
                  progressPlan.responsibleperson.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressPlan.cooperatingperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: progressPlan.cooperatingperson.id } }">{{
                  progressPlan.cooperatingperson.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressPlan.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: progressPlan.auditorid.id } }">{{
                  progressPlan.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressPlan.responsibledepartment">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: progressPlan.responsibledepartment.id } }">{{
                  progressPlan.responsibledepartment.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressPlan.cooperatingdepartment">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: progressPlan.cooperatingdepartment.id } }">{{
                  progressPlan.cooperatingdepartment.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressPlan.planReturns">
                <router-link :to="{ name: 'PlanReturnsView', params: { planReturnsId: progressPlan.planReturns.id } }">{{
                  progressPlan.planReturns.id
                }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(projectwbs, i) in progressPlan.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
            </td>
            <td>
              <span v-for="(projectRisk, i) in progressPlan.projectRisks" :key="projectRisk.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectRiskView', params: { projectRiskId: projectRisk.id } }">{{
                  projectRisk.id
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProgressPlanView', params: { progressPlanId: progressPlan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProgressPlanEdit', params: { progressPlanId: progressPlan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(progressPlan)"
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
        <span id="jy1App.progressPlan.delete.question" data-cy="progressPlanDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-progressPlan-heading" v-text="t$('jy1App.progressPlan.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-progressPlan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProgressPlan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./progress-plan.component.ts"></script>
