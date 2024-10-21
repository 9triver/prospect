<template>
    <div>
      <h2 id="page-heading" data-cy="ProjectwbsHeading">
        <span v-text="t$('jy1App.projectwbs.home.title')" id="projectwbs-heading"></span>
        <div class="d-flex justify-content-end">
          <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
            <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
            <span v-text="t$('jy1App.projectwbs.home.refreshListLabel')"></span>
          </el-button>
          <router-link :to="{ name: 'ProjectwbsCreate' }" custom v-slot="{ navigate }">
            <el-button
              @click="navigate"
              id="jh-create-entity"
              data-cy="entityCreateButton"
              class="btn btn-primary jh-create-entity create-projectwbs"
              type="primary"
            >
              <font-awesome-icon icon="plus"></font-awesome-icon>
              <span v-text="t$('jy1App.projectwbs.home.createLabel')"></span>
            </el-button>
          </router-link>
        </div>
      </h2>
      <br />
      <div class="alert alert-warning" v-if="!isFetching && projectwbs && projectwbs.length === 0">
        <span v-text="t$('jy1App.projectwbs.home.notFound')"></span>
      </div>
      <div class="table-responsive" v-if="projectwbs && projectwbs.length > 0">
        <el-table :data="projectwbs" style="width: 100%" border stripe fit v-loading="isFetching">
          <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
            <template #default="scope">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: scope.row.id } }">{{ scope.row.id }}</router-link>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="wbsname" :label="t$('jy1App.projectwbs.wbsname')" :sortable="false">
            <template #default="scope">
              <span class="field-default">{{ scope.row.wbsname }}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="parentwbsid"
            :label="t$('jy1App.projectwbs.parentwbsid')"
            :sortable="false"
          >
            <template #default="scope">
              <span class="field-default">{{ scope.row.parentwbsid }}</span>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="pbsid" :label="t$('jy1App.projectwbs.pbsid')" :sortable="false">
            <template #default="scope">
              <span class="field-default">{{ scope.row.pbsid }}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="description"
            :label="t$('jy1App.projectwbs.description')"
            :sortable="false"
          >
            <template #default="scope">
              <span class="field-default">{{ scope.row.description }}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="belongfrontline"
            :label="t$('jy1App.projectwbs.belongfrontline')"
            :sortable="false"
          >
            <template #default="scope">
              <span class="field-default">{{ scope.row.belongfrontline }}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="starttime"
            :label="t$('jy1App.projectwbs.starttime')"
            :sortable="true"
          >
            <template #default="scope">
              <span class="field-default">{{ scope.row.starttime }}</span>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="endtime" :label="t$('jy1App.projectwbs.endtime')" :sortable="true">
            <template #default="scope">
              <span class="field-default">{{ scope.row.endtime }}</span>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="progress" :label="t$('jy1App.projectwbs.progress')" :sortable="true">
            <template #default="scope">
              <span class="field-default">{{ scope.row.progress }}</span>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="type" :label="t$('jy1App.projectwbs.type')" :sortable="true">
            <template #default="scope">
              <span class="field-default">{{ scope.row.type }}</span>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="priorty" :label="t$('jy1App.projectwbs.priorty')" :sortable="true">
            <template #default="scope">
              <span class="field-default">{{ scope.row.priorty }}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="secretlevel"
            :label="t$('jy1App.projectwbs.secretlevel')"
            :sortable="false"
          >
            <template #default="scope">
              <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="deliverables"
            :label="t$('jy1App.projectwbs.deliverables')"
            :sortable="false"
          >
            <template #default="scope">
              <span class="field-default">{{ scope.row.deliverables }}</span>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="status" :label="t$('jy1App.projectwbs.status')" :sortable="false">
            <template #default="scope">
              <span class="field-fieldIsEnum" v-text="t$('jy1App.ProjectStatus.' + scope.row.status)"></span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="auditStatus"
            :label="t$('jy1App.projectwbs.auditStatus')"
            :sortable="false"
          >
            <template #default="scope">
              <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="workbagid"
            :label="t$('jy1App.projectwbs.workbagid')"
            :sortable="false"
          >
            <template #default="scope">
              <span class="field-default">{{ scope.row.workbagid }}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="responsibleperson.id"
            :label="t$('jy1App.projectwbs.responsibleperson')"
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
            prop="technicaldirector.id"
            :label="t$('jy1App.projectwbs.technicaldirector')"
          >
            <template #default="scope">
              <td>
                <div v-if="scope.row.technicaldirector">
                  <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.technicaldirector.id } }">{{
                    scope.row.technicaldirector.id
                  }}</router-link>
                </div>
              </td>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="knowingpeople.id" :label="t$('jy1App.projectwbs.knowingpeople')">
            <template #default="scope">
              <td>
                <div v-if="scope.row.knowingpeople">
                  <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.knowingpeople.id } }">{{
                    scope.row.knowingpeople.id
                  }}</router-link>
                </div>
              </td>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="auditorid.id" :label="t$('jy1App.projectwbs.auditorid')">
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
            :label="t$('jy1App.projectwbs.responsibledepartment')"
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
            prop="projectdeliverables.id"
            :label="t$('jy1App.projectwbs.projectdeliverables')"
          >
            <template #default="scope">
              <td>
                <span v-for="(projectdeliverables, i) in scope.row.projectdeliverables" :key="projectdeliverables.id"
                  >{{ i > 0 ? ', ' : '' }}
                  <router-link
                    class="form-control-static"
                    :to="{ name: 'ProjectdeliverablesView', params: { projectdeliverablesId: projectdeliverables.id } }"
                    >{{ projectdeliverables.id }}</router-link
                  >
                </span>
              </td>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="relevantdepartment.id"
            :label="t$('jy1App.projectwbs.relevantdepartment')"
          >
            <template #default="scope">
              <td>
                <span v-for="(relevantdepartment, i) in scope.row.relevantdepartments" :key="relevantdepartment.id"
                  >{{ i > 0 ? ', ' : '' }}
                  <router-link
                    class="form-control-static"
                    :to="{ name: 'DepartmentView', params: { departmentId: relevantdepartment.id } }"
                    >{{ relevantdepartment.id }}</router-link
                  >
                </span>
              </td>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.projectwbs.workbag')">
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
          <el-table-column min-width="150px" show-overflow-tooltip prop="progressPlan.id" :label="t$('jy1App.projectwbs.progressPlan')">
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
          <el-table-column min-width="150px" show-overflow-tooltip prop="projectBudget.id" :label="t$('jy1App.projectwbs.projectBudget')">
            <template #default="scope">
              <td>
                <span v-for="(projectBudget, i) in scope.row.projectBudgets" :key="projectBudget.id"
                  >{{ i > 0 ? ', ' : '' }}
                  <router-link
                    class="form-control-static"
                    :to="{ name: 'ProjectBudgetView', params: { projectBudgetId: projectBudget.id } }"
                    >{{ projectBudget.id }}</router-link
                  >
                </span>
              </td>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="project.id" :label="t$('jy1App.projectwbs.project')">
            <template #default="scope">
              <td>
                <span v-for="(project, i) in scope.row.projects" :key="project.id"
                  >{{ i > 0 ? ', ' : '' }}
                  <router-link class="form-control-static" :to="{ name: 'ProjectView', params: { projectId: project.id } }">{{
                    project.id
                  }}</router-link>
                </span>
              </td>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="fundsEstimation.id" :label="t$('jy1App.projectwbs.fundsEstimation')">
            <template #default="scope">
              <td>
                <span v-for="(fundsEstimation, i) in scope.row.fundsEstimations" :key="fundsEstimation.id"
                  >{{ i > 0 ? ', ' : '' }}
                  <router-link
                    class="form-control-static"
                    :to="{ name: 'FundsEstimationView', params: { fundsEstimationId: fundsEstimation.id } }"
                    >{{ fundsEstimation.id }}</router-link
                  >
                </span>
              </td>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="contractCostBudget.id"
            :label="t$('jy1App.projectwbs.contractCostBudget')"
          >
            <template #default="scope">
              <td>
                <span v-for="(contractCostBudget, i) in scope.row.contractCostBudgets" :key="contractCostBudget.id"
                  >{{ i > 0 ? ', ' : '' }}
                  <router-link
                    class="form-control-static"
                    :to="{ name: 'ContractCostBudgetView', params: { contractCostBudgetId: contractCostBudget.id } }"
                    >{{ contractCostBudget.id }}</router-link
                  >
                </span>
              </td>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="costControlSystem.id"
            :label="t$('jy1App.projectwbs.costControlSystem')"
          >
            <template #default="scope">
              <td>
                <span v-for="(costControlSystem, i) in scope.row.costControlSystems" :key="costControlSystem.id"
                  >{{ i > 0 ? ', ' : '' }}
                  <router-link
                    class="form-control-static"
                    :to="{ name: 'CostControlSystemView', params: { costControlSystemId: costControlSystem.id } }"
                    >{{ costControlSystem.id }}</router-link
                  >
                </span>
              </td>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="outsourcingContractual.id"
            :label="t$('jy1App.projectwbs.outsourcingContractual')"
          >
            <template #default="scope">
              <td>
                <span v-for="(outsourcingContractual, i) in scope.row.outsourcingContractuals" :key="outsourcingContractual.id"
                  >{{ i > 0 ? ', ' : '' }}
                  <router-link
                    class="form-control-static"
                    :to="{ name: 'OutsourcingContractualView', params: { outsourcingContractualId: outsourcingContractual.id } }"
                    >{{ outsourcingContractual.id }}</router-link
                  >
                </span>
              </td>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            show-overflow-tooltip
            prop="outsourcingPurchasePlan.id"
            :label="t$('jy1App.projectwbs.outsourcingPurchasePlan')"
          >
            <template #default="scope">
              <td>
                <span v-for="(outsourcingPurchasePlan, i) in scope.row.outsourcingPurchasePlans" :key="outsourcingPurchasePlan.id"
                  >{{ i > 0 ? ', ' : '' }}
                  <router-link
                    class="form-control-static"
                    :to="{ name: 'OutsourcingPurchasePlanView', params: { outsourcingPurchasePlanId: outsourcingPurchasePlan.id } }"
                    >{{ outsourcingPurchasePlan.id }}</router-link
                  >
                </span>
              </td>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip prop="technical.id" :label="t$('jy1App.projectwbs.technical')">
            <template #default="scope">
              <td>
                <span v-for="(technical, i) in scope.row.technicals" :key="technical.id"
                  >{{ i > 0 ? ', ' : '' }}
                  <router-link class="form-control-static" :to="{ name: 'TechnicalView', params: { technicalId: technical.id } }">{{
                    technical.id
                  }}</router-link>
                </span>
              </td>
            </template>
          </el-table-column>
          <el-table-column min-width="150px" show-overflow-tooltip label="操作">
            <template #default="scope">
              <td class="text-right">
                <div class="btn-group">
                  <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: scope.row.id } }" custom v-slot="{ navigate }">
                    <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                      <font-awesome-icon icon="eye"></font-awesome-icon>
                      <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                    </button>
                  </router-link>
                  <router-link :to="{ name: 'ProjectwbsEdit', params: { projectwbsId: scope.row.id } }" custom v-slot="{ navigate }">
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
        <!-- <table class="table table-striped" aria-describedby="projectwbs">
                  <thead>
                  <tr>
                      <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.wbsname')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.parentwbsid')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.pbsid')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.description')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.belongfrontline')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.starttime')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.endtime')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.progress')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.type')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.priorty')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.secretlevel')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.deliverables')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.status')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.auditStatus')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.workbagid')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.responsibleperson')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.technicaldirector')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.knowingpeople')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.auditorid')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.responsibledepartment')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.projectdeliverables')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.relevantdepartment')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.workbag')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.progressPlan')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.projectBudget')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.project')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.fundsEstimation')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.contractCostBudget')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.costControlSystem')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.outsourcingContractual')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.outsourcingPurchasePlan')"></span></th>
                      <th scope="row"><span v-text="t$('jy1App.projectwbs.technical')"></span></th>
                      <th scope="row"></th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="projectwbs in projectwbs"
                      :key="projectwbs.id" data-cy="entityTable">
                      <td>
                          <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: projectwbs.id}}">{{projectwbs.id}}</router-link>
                      </td>
                      <td>{{projectwbs.wbsname}}</td>
                      <td>{{projectwbs.parentwbsid}}</td>
                      <td>{{projectwbs.pbsid}}</td>
                      <td>{{projectwbs.description}}</td>
                      <td>{{projectwbs.belongfrontline}}</td>
                      <td>{{projectwbs.starttime}}</td>
                      <td>{{projectwbs.endtime}}</td>
                      <td>{{projectwbs.progress}}</td>
                      <td>{{projectwbs.type}}</td>
                      <td>{{projectwbs.priorty}}</td>
                      <td v-text="t$('jy1App.Secretlevel.' + projectwbs.secretlevel)"></td>
                      <td>{{projectwbs.deliverables}}</td>
                      <td v-text="t$('jy1App.ProjectStatus.' + projectwbs.status)"></td>
                      <td v-text="t$('jy1App.AuditStatus.' + projectwbs.auditStatus)"></td>
                      <td>{{projectwbs.workbagid}}</td>
                      <td>
                          <div v-if="projectwbs.responsibleperson">
                              <router-link :to="{name: 'HrManagementView', params: {hrManagementId: projectwbs.responsibleperson.id}}">{{projectwbs.responsibleperson.id}}</router-link>
                          </div>
                      </td>
                      <td>
                          <div v-if="projectwbs.technicaldirector">
                              <router-link :to="{name: 'HrManagementView', params: {hrManagementId: projectwbs.technicaldirector.id}}">{{projectwbs.technicaldirector.id}}</router-link>
                          </div>
                      </td>
                      <td>
                          <div v-if="projectwbs.knowingpeople">
                              <router-link :to="{name: 'HrManagementView', params: {hrManagementId: projectwbs.knowingpeople.id}}">{{projectwbs.knowingpeople.id}}</router-link>
                          </div>
                      </td>
                      <td>
                          <div v-if="projectwbs.auditorid">
                              <router-link :to="{name: 'HrManagementView', params: {hrManagementId: projectwbs.auditorid.id}}">{{projectwbs.auditorid.id}}</router-link>
                          </div>
                      </td>
                      <td>
                          <div v-if="projectwbs.responsibledepartment">
                              <router-link :to="{name: 'DepartmentView', params: {departmentId: projectwbs.responsibledepartment.id}}">{{projectwbs.responsibledepartment.id}}</router-link>
                          </div>
                      </td>
                      <td>
                          <span v-for="(projectdeliverables, i) in projectwbs.projectdeliverables" :key="projectdeliverables.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'ProjectdeliverablesView', params: {projectdeliverablesId: projectdeliverables.id}}">{{projectdeliverables.id}}</router-link>
                          </span>
                      </td>
                      <td>
                          <span v-for="(relevantdepartment, i) in projectwbs.relevantdepartments" :key="relevantdepartment.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'DepartmentView', params: {departmentId: relevantdepartment.id}}">{{relevantdepartment.id}}</router-link>
                          </span>
                      </td>
                      <td>
                          <div v-if="projectwbs.workbag">
                              <router-link :to="{name: 'WorkbagView', params: {workbagId: projectwbs.workbag.id}}">{{projectwbs.workbag.id}}</router-link>
                          </div>
                      </td>
                      <td>
                          <span v-for="(progressPlan, i) in projectwbs.progressPlans" :key="progressPlan.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'ProgressPlanView', params: {progressPlanId: progressPlan.id}}">{{progressPlan.id}}</router-link>
                          </span>
                      </td>
                      <td>
                          <span v-for="(projectBudget, i) in projectwbs.projectBudgets" :key="projectBudget.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'ProjectBudgetView', params: {projectBudgetId: projectBudget.id}}">{{projectBudget.id}}</router-link>
                          </span>
                      </td>
                      <td>
                          <span v-for="(project, i) in projectwbs.projects" :key="project.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'ProjectView', params: {projectId: project.id}}">{{project.id}}</router-link>
                          </span>
                      </td>
                      <td>
                          <span v-for="(fundsEstimation, i) in projectwbs.fundsEstimations" :key="fundsEstimation.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'FundsEstimationView', params: {fundsEstimationId: fundsEstimation.id}}">{{fundsEstimation.id}}</router-link>
                          </span>
                      </td>
                      <td>
                          <span v-for="(contractCostBudget, i) in projectwbs.contractCostBudgets" :key="contractCostBudget.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'ContractCostBudgetView', params: {contractCostBudgetId: contractCostBudget.id}}">{{contractCostBudget.id}}</router-link>
                          </span>
                      </td>
                      <td>
                          <span v-for="(costControlSystem, i) in projectwbs.costControlSystems" :key="costControlSystem.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'CostControlSystemView', params: {costControlSystemId: costControlSystem.id}}">{{costControlSystem.id}}</router-link>
                          </span>
                      </td>
                      <td>
                          <span v-for="(outsourcingContractual, i) in projectwbs.outsourcingContractuals" :key="outsourcingContractual.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'OutsourcingContractualView', params: {outsourcingContractualId: outsourcingContractual.id}}">{{outsourcingContractual.id}}</router-link>
                          </span>
                      </td>
                      <td>
                          <span v-for="(outsourcingPurchasePlan, i) in projectwbs.outsourcingPurchasePlans" :key="outsourcingPurchasePlan.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'OutsourcingPurchasePlanView', params: {outsourcingPurchasePlanId: outsourcingPurchasePlan.id}}">{{outsourcingPurchasePlan.id}}</router-link>
                          </span>
                      </td>
                      <td>
                          <span v-for="(technical, i) in projectwbs.technicals" :key="technical.id">{{i > 0 ? ', ' : ''}}
                              <router-link class="form-control-static" :to="{name: 'TechnicalView', params: {technicalId: technical.id}}">{{technical.id}}</router-link>
                          </span>
                      </td>
                      <td class="text-right">
                          <div class="btn-group">
                              <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: projectwbs.id}}" custom v-slot="{ navigate }">
                                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                      <font-awesome-icon icon="eye"></font-awesome-icon>
                                      <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                  </button>
                              </router-link>
                              <router-link :to="{name: 'ProjectwbsEdit', params: {projectwbsId: projectwbs.id}}" custom v-slot="{ navigate }">
                                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                      <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                      <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                  </button>
                              </router-link>
                              <b-button v-on:click="prepareRemove(projectwbs)"
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
  