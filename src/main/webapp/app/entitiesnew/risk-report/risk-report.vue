<template>
  <div>
    <h2 id="page-heading" data-cy="RiskReportHeading">
      <span v-text="t$('jy1App.riskReport.home.title')" id="risk-report-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.riskReport.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'RiskReportCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-risk-report"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.riskReport.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && riskReports && riskReports.length === 0">
      <span v-text="t$('jy1App.riskReport.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="riskReports && riskReports.length > 0">
      <el-table :data="riskReports" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'RiskReportView', params: { riskReportId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="type" :label="t$('jy1App.riskReport.type')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.type }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="year" :label="t$('jy1App.riskReport.year')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.year }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="riskreportname"
          :label="t$('jy1App.riskReport.riskreportname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.riskreportname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="reporttime"
          :label="t$('jy1App.riskReport.reporttime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.reporttime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.riskReport.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="creatorid.id" :label="t$('jy1App.riskReport.creatorid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.creatorid">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.creatorid.id } }">{{
                  scope.row.creatorid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid.id" :label="t$('jy1App.riskReport.wbsid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.wbsid">
                <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: scope.row.wbsid.id } }">{{
                  scope.row.wbsid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.riskReport.workbag')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.workbag">
                <router-link :to="{ name: 'WorkbagView', params: { workbagId: scope.row.workbag.id } }">{{
                  scope.row.workbag.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'RiskReportView', params: { riskReportId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'RiskReportEdit', params: { riskReportId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(scope.row)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="trash"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </template>
        </el-table-column>
      </el-table>
      <!-- <table class="table table-striped" aria-describedby="riskReports">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReport.type')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReport.year')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReport.riskreportname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReport.reporttime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReport.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReport.creatorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReport.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReport.workbag')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="riskReport in riskReports"
                    :key="riskReport.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'RiskReportView', params: {riskReportId: riskReport.id}}">{{riskReport.id}}</router-link>
                    </td>
                    <td>{{riskReport.type}}</td>
                    <td>{{riskReport.year}}</td>
                    <td>{{riskReport.riskreportname}}</td>
                    <td>{{riskReport.reporttime}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + riskReport.auditStatus)"></td>
                    <td>
                        <div v-if="riskReport.creatorid">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: riskReport.creatorid.id}}">{{riskReport.creatorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="riskReport.wbsid">
                            <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: riskReport.wbsid.id}}">{{riskReport.wbsid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="riskReport.workbag">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: riskReport.workbag.id}}">{{riskReport.workbag.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'RiskReportView', params: {riskReportId: riskReport.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'RiskReportEdit', params: {riskReportId: riskReport.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(riskReport)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   data-cy="entityDeleteButton"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>-->
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
