<template>
  <div>
    <h2 id="page-heading" data-cy="RiskReportHeading">
      <span v-text="t$('jy1App.riskReport.home.title')" id="risk-report-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.riskReport.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RiskReportCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-risk-report"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.riskReport.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && riskReports && riskReports.length === 0">
      <span v-text="t$('jy1App.riskReport.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="riskReports && riskReports.length > 0">
      <table class="table table-striped" aria-describedby="riskReports">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.riskReport.type')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.riskReport.riskreportname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.riskReport.releasetime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.riskReport.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.riskReport.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.riskReport.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="riskReport in riskReports" :key="riskReport.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RiskReportView', params: { riskReportId: riskReport.id } }">{{ riskReport.id }}</router-link>
            </td>
            <td>{{ riskReport.type }}</td>
            <td>{{ riskReport.riskreportname }}</td>
            <td>{{ riskReport.releasetime }}</td>
            <td v-text="t$('jy1App.AuditStatus.' + riskReport.auditStatus)"></td>
            <td>
              <div v-if="riskReport.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: riskReport.creatorid.id } }">{{
                  riskReport.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="riskReport.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: riskReport.auditorid.id } }">{{
                  riskReport.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'RiskReportView', params: { riskReportId: riskReport.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'RiskReportEdit', params: { riskReportId: riskReport.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(riskReport)"
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
        <span id="jy1App.riskReport.delete.question" data-cy="riskReportDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-riskReport-heading" v-text="t$('jy1App.riskReport.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-riskReport"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRiskReport()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./risk-report.component.ts"></script>
