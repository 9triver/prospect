<template>
  <div>
    <h2 id="page-heading" data-cy="ProgressPlanHeading">
      <span v-text="t$('jy1App.progressPlan.home.title')" id="progress-plan-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.progressPlan.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'ProgressPlanCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-progress-plan"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.progressPlan.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && progressPlans && progressPlans.length === 0">
      <span v-text="t$('jy1App.progressPlan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="progressPlans && progressPlans.length > 0">
      <el-table :data="progressPlans" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'ProgressPlanView', params: { progressPlanId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planname"
          :label="t$('jy1App.progressPlan.planname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.planname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongproject"
          :label="t$('jy1App.progressPlan.belongproject')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.belongproject }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongplanid"
          :label="t$('jy1App.progressPlan.belongplanid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.belongplanid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.progressPlan.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="plantype"
          :label="t$('jy1App.progressPlan.plantype')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.plantype }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planlevel"
          :label="t$('jy1App.progressPlan.planlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.PlanLevel.' + scope.row.planlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planstage"
          :label="t$('jy1App.progressPlan.planstage')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.planstage }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="readytime"
          :label="t$('jy1App.progressPlan.readytime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.readytime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="description"
          :label="t$('jy1App.progressPlan.description')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="deliverables"
          :label="t$('jy1App.progressPlan.deliverables')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.deliverables }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planobjectives"
          :label="t$('jy1App.progressPlan.planobjectives')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.planobjectives }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="preplan"
          :label="t$('jy1App.progressPlan.preplan')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.preplan }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="starttime"
          :label="t$('jy1App.progressPlan.starttime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.starttime }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="endtime" :label="t$('jy1App.progressPlan.endtime')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.endtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="actualstarttime"
          :label="t$('jy1App.progressPlan.actualstarttime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.actualstarttime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="actualendtime"
          :label="t$('jy1App.progressPlan.actualendtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.actualendtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="progress"
          :label="t$('jy1App.progressPlan.progress')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.progress }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="progresstype"
          :label="t$('jy1App.progressPlan.progresstype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Progressstatus.' + scope.row.progresstype)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="iskey" :label="t$('jy1App.progressPlan.iskey')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.iskey }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="status" :label="t$('jy1App.progressPlan.status')" :sortable="false">
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Planstatus.' + scope.row.status)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.progressPlan.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="returns"
          :label="t$('jy1App.progressPlan.returns')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.returns }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="remark" :label="t$('jy1App.progressPlan.remark')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibleperson.id"
          :label="t$('jy1App.progressPlan.responsibleperson')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.responsibleperson">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.responsibleperson.id } }">{{
                  scope.row.responsibleperson.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="cooperatingperson.id"
          :label="t$('jy1App.progressPlan.cooperatingperson')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.cooperatingperson">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.cooperatingperson.id } }">{{
                  scope.row.cooperatingperson.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="auditorid.id" :label="t$('jy1App.progressPlan.auditorid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.auditorid">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.auditorid.id } }">{{
                  scope.row.auditorid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibledepartment.id"
          :label="t$('jy1App.progressPlan.responsibledepartment')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.responsibledepartment">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: scope.row.responsibledepartment.id } }">{{
                  scope.row.responsibledepartment.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="cooperatingdepartment.id"
          :label="t$('jy1App.progressPlan.cooperatingdepartment')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.cooperatingdepartment">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: scope.row.cooperatingdepartment.id } }">{{
                  scope.row.cooperatingdepartment.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="projectwbs.id" :label="t$('jy1App.progressPlan.projectwbs')">
          <template #default="scope">
            <td>
              <span v-for="(projectwbs, i) in scope.row.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="projectRisk.id" :label="t$('jy1App.progressPlan.projectRisk')">
          <template #default="scope">
            <td>
              <span v-for="(projectRisk, i) in scope.row.projectRisks" :key="projectRisk.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectRiskView', params: { projectRiskId: projectRisk.id } }">{{
                  projectRisk.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="riskReturn.id" :label="t$('jy1App.progressPlan.riskReturn')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.riskReturn">
                <router-link :to="{ name: 'RiskReturnView', params: { riskReturnId: scope.row.riskReturn.id } }">{{
                  scope.row.riskReturn.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProgressPlanView', params: { progressPlanId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProgressPlanEdit', params: { progressPlanId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="progressPlans">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.planname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.belongproject')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.belongplanid')"></span></th>
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
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.returns')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.remark')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.responsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.cooperatingperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.auditorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.responsibledepartment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.cooperatingdepartment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.projectwbs')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.projectRisk')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.progressPlan.riskReturn')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="progressPlan in progressPlans"
                    :key="progressPlan.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'ProgressPlanView', params: {progressPlanId: progressPlan.id}}">{{progressPlan.id}}</router-link>
                    </td>
                    <td>{{progressPlan.planname}}</td>
                    <td>{{progressPlan.belongproject}}</td>
                    <td>{{progressPlan.belongplanid}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + progressPlan.secretlevel)"></td>
                    <td>{{progressPlan.plantype}}</td>
                    <td v-text="t$('jy1App.PlanLevel.' + progressPlan.planlevel)"></td>
                    <td>{{progressPlan.planstage}}</td>
                    <td>{{progressPlan.readytime}}</td>
                    <td>{{progressPlan.description}}</td>
                    <td>{{progressPlan.deliverables}}</td>
                    <td>{{progressPlan.planobjectives}}</td>
                    <td>{{progressPlan.preplan}}</td>
                    <td>{{progressPlan.starttime}}</td>
                    <td>{{progressPlan.endtime}}</td>
                    <td>{{progressPlan.actualstarttime}}</td>
                    <td>{{progressPlan.actualendtime}}</td>
                    <td>{{progressPlan.progress}}</td>
                    <td v-text="t$('jy1App.Progressstatus.' + progressPlan.progresstype)"></td>
                    <td>{{progressPlan.iskey}}</td>
                    <td v-text="t$('jy1App.Planstatus.' + progressPlan.status)"></td>
                    <td v-text="t$('jy1App.AuditStatus.' + progressPlan.auditStatus)"></td>
                    <td>{{progressPlan.returns}}</td>
                    <td>{{progressPlan.remark}}</td>
                    <td>
                        <div v-if="progressPlan.responsibleperson">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: progressPlan.responsibleperson.id}}">{{progressPlan.responsibleperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="progressPlan.cooperatingperson">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: progressPlan.cooperatingperson.id}}">{{progressPlan.cooperatingperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="progressPlan.auditorid">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: progressPlan.auditorid.id}}">{{progressPlan.auditorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="progressPlan.responsibledepartment">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: progressPlan.responsibledepartment.id}}">{{progressPlan.responsibledepartment.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="progressPlan.cooperatingdepartment">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: progressPlan.cooperatingdepartment.id}}">{{progressPlan.cooperatingdepartment.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(projectwbs, i) in progressPlan.projectwbs" :key="projectwbs.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectwbsView', params: {projectwbsId: projectwbs.id}}">{{projectwbs.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(projectRisk, i) in progressPlan.projectRisks" :key="projectRisk.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectRiskView', params: {projectRiskId: projectRisk.id}}">{{projectRisk.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <div v-if="progressPlan.riskReturn">
                            <router-link :to="{name: 'RiskReturnView', params: {riskReturnId: progressPlan.riskReturn.id}}">{{progressPlan.riskReturn.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ProgressPlanView', params: {progressPlanId: progressPlan.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'ProgressPlanEdit', params: {progressPlanId: progressPlan.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(progressPlan)"
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
