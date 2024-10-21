<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectRiskHeading">
      <span v-text="t$('jy1App.projectRisk.home.title')" id="project-risk-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectRisk.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'ProjectRiskCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project-risk"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.projectRisk.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectRisks && projectRisks.length === 0">
      <span v-text="t$('jy1App.projectRisk.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectRisks && projectRisks.length > 0">
      <el-table :data="projectRisks" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'ProjectRiskView', params: { projectRiskId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="year" :label="t$('jy1App.projectRisk.year')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.year }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.projectRisk.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="riskcontent"
          :label="t$('jy1App.projectRisk.riskcontent')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.riskcontent }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="identificationtime"
          :label="t$('jy1App.projectRisk.identificationtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.identificationtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="riskreason"
          :label="t$('jy1App.projectRisk.riskreason')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.riskreason }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="importantrange"
          :label="t$('jy1App.projectRisk.importantrange')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.importantrange }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="measuresandtimelimit"
          :label="t$('jy1App.projectRisk.measuresandtimelimit')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.measuresandtimelimit }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="conditions"
          :label="t$('jy1App.projectRisk.conditions')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.conditions }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="closedloopindicator"
          :label="t$('jy1App.projectRisk.closedloopindicator')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.closedloopindicator }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid.id" :label="t$('jy1App.projectRisk.wbsid')">
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.projectRisk.workbag')">
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="frontlineid.id" :label="t$('jy1App.projectRisk.frontlineid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.frontlineid">
                <router-link :to="{ name: 'FrontlineView', params: { frontlineId: scope.row.frontlineid.id } }">{{
                  scope.row.frontlineid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="systemLevel.id" :label="t$('jy1App.projectRisk.systemLevel')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.systemLevel">
                <router-link :to="{ name: 'SystemLevelView', params: { systemLevelId: scope.row.systemLevel.id } }">{{
                  scope.row.systemLevel.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="riskType.id" :label="t$('jy1App.projectRisk.riskType')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.riskType">
                <router-link :to="{ name: 'RiskTypeView', params: { riskTypeId: scope.row.riskType.id } }">{{
                  scope.row.riskType.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="riskLevel.id" :label="t$('jy1App.projectRisk.riskLevel')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.riskLevel">
                <router-link :to="{ name: 'RiskLevelView', params: { riskLevelId: scope.row.riskLevel.id } }">{{
                  scope.row.riskLevel.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="riskPossibility.id"
          :label="t$('jy1App.projectRisk.riskPossibility')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.riskPossibility">
                <router-link :to="{ name: 'RiskPossibilityView', params: { riskPossibilityId: scope.row.riskPossibility.id } }">{{
                  scope.row.riskPossibility.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="progressPlan.id" :label="t$('jy1App.projectRisk.progressPlan')">
          <template #default="scope">
            <td>
              <span v-for="(progressPlan, i) in scope.row.progressPlans" :key="progressPlan.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProgressPlanView', params: { progressPlanId: progressPlan.id } }">{{
                  progressPlan.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProjectRiskView', params: { projectRiskId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProjectRiskEdit', params: { projectRiskId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="projectRisks">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.year')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.riskcontent')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.identificationtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.riskreason')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.importantrange')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.measuresandtimelimit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.conditions')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.closedloopindicator')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.workbag')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.frontlineid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.systemLevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.riskType')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.riskLevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.riskPossibility')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectRisk.progressPlan')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="projectRisk in projectRisks"
                    :key="projectRisk.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'ProjectRiskView', params: {projectRiskId: projectRisk.id}}">{{projectRisk.id}}</router-link>
                    </td>
                    <td>{{projectRisk.year}}</td>
                    <td>{{projectRisk.name}}</td>
                    <td>{{projectRisk.riskcontent}}</td>
                    <td>{{projectRisk.identificationtime}}</td>
                    <td>{{projectRisk.riskreason}}</td>
                    <td>{{projectRisk.importantrange}}</td>
                    <td>{{projectRisk.measuresandtimelimit}}</td>
                    <td>{{projectRisk.conditions}}</td>
                    <td>{{projectRisk.closedloopindicator}}</td>
                    <td>
                        <div v-if="projectRisk.wbsid">
                            <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: projectRisk.wbsid.id}}">{{projectRisk.wbsid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectRisk.workbag">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: projectRisk.workbag.id}}">{{projectRisk.workbag.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectRisk.frontlineid">
                            <router-link :to="{name: 'FrontlineView', params: {frontlineId: projectRisk.frontlineid.id}}">{{projectRisk.frontlineid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectRisk.systemLevel">
                            <router-link :to="{name: 'SystemLevelView', params: {systemLevelId: projectRisk.systemLevel.id}}">{{projectRisk.systemLevel.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectRisk.riskType">
                            <router-link :to="{name: 'RiskTypeView', params: {riskTypeId: projectRisk.riskType.id}}">{{projectRisk.riskType.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectRisk.riskLevel">
                            <router-link :to="{name: 'RiskLevelView', params: {riskLevelId: projectRisk.riskLevel.id}}">{{projectRisk.riskLevel.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectRisk.riskPossibility">
                            <router-link :to="{name: 'RiskPossibilityView', params: {riskPossibilityId: projectRisk.riskPossibility.id}}">{{projectRisk.riskPossibility.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(progressPlan, i) in projectRisk.progressPlans" :key="progressPlan.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProgressPlanView', params: {progressPlanId: progressPlan.id}}">{{progressPlan.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ProjectRiskView', params: {projectRiskId: projectRisk.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'ProjectRiskEdit', params: {projectRiskId: projectRisk.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(projectRisk)"
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
