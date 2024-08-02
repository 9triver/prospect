<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectRiskHeading">
      <span v-text="t$('jy1App.projectRisk.home.title')" id="project-risk-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectRisk.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectRiskCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project-risk"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.projectRisk.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectRisks && projectRisks.length === 0">
      <span v-text="t$('jy1App.projectRisk.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectRisks && projectRisks.length > 0">
      <table class="table table-striped" aria-describedby="projectRisks">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.year')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.nodename')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.risktype')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.decumentid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.version')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.usetime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.systemlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.risklevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.limitationtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.closetype')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.riskReport')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.responsibleperson')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.projectwbs')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectRisk.progressPlan')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectRisk in projectRisks" :key="projectRisk.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectRiskView', params: { projectRiskId: projectRisk.id } }">{{ projectRisk.id }}</router-link>
            </td>
            <td>{{ projectRisk.year }}</td>
            <td>{{ projectRisk.nodename }}</td>
            <td>{{ projectRisk.risktype }}</td>
            <td>{{ projectRisk.decumentid }}</td>
            <td>{{ projectRisk.version }}</td>
            <td>{{ projectRisk.usetime }}</td>
            <td>{{ projectRisk.systemlevel }}</td>
            <td v-text="t$('jy1App.Risklevel.' + projectRisk.risklevel)"></td>
            <td>{{ projectRisk.limitationtime }}</td>
            <td>{{ projectRisk.closetype }}</td>
            <td>
              <div v-if="projectRisk.riskReport">
                <router-link :to="{ name: 'RiskReportView', params: { riskReportId: projectRisk.riskReport.id } }">{{
                  projectRisk.riskReport.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectRisk.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectRisk.creatorid.id } }">{{
                  projectRisk.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectRisk.responsibleperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectRisk.responsibleperson.id } }">{{
                  projectRisk.responsibleperson.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectRisk.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectRisk.auditorid.id } }">{{
                  projectRisk.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(projectwbs, i) in projectRisk.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
            </td>
            <td>
              <span v-for="(progressPlan, i) in projectRisk.progressPlans" :key="progressPlan.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProgressPlanView', params: { progressPlanId: progressPlan.id } }">{{
                  progressPlan.id
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProjectRiskView', params: { projectRiskId: projectRisk.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProjectRiskEdit', params: { projectRiskId: projectRisk.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(projectRisk)"
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
        <span id="jy1App.projectRisk.delete.question" data-cy="projectRiskDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-projectRisk-heading" v-text="t$('jy1App.projectRisk.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-projectRisk"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProjectRisk()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./project-risk.component.ts"></script>
