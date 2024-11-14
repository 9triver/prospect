<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectBudgetHeading">
      <span v-text="t$('jy1App.projectBudget.home.title')" id="project-budget-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectBudget.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'ProjectBudgetCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project-budget"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.projectBudget.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectBudgets && projectBudgets.length === 0">
      <span v-text="t$('jy1App.projectBudget.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectBudgets && projectBudgets.length > 0">
      <el-table :data="projectBudgets" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'ProjectBudgetView', params: { projectBudgetId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid" :label="t$('jy1App.projectBudget.wbsid')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="wbsname"
          :label="t$('jy1App.projectBudget.wbsname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="parentwbsid"
          :label="t$('jy1App.projectBudget.parentwbsid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.parentwbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="subjectid"
          :label="t$('jy1App.projectBudget.subjectid')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.subjectid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="subjectname"
          :label="t$('jy1App.projectBudget.subjectname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.subjectname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractid"
          :label="t$('jy1App.projectBudget.contractid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractname"
          :label="t$('jy1App.projectBudget.contractname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractname }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="year" :label="t$('jy1App.projectBudget.year')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.year }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auxiliaryitem"
          :label="t$('jy1App.projectBudget.auxiliaryitem')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.auxiliaryitem }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="unit" :label="t$('jy1App.projectBudget.unit')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.unit }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="number" :label="t$('jy1App.projectBudget.number')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.number }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unitprice"
          :label="t$('jy1App.projectBudget.unitprice')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unitprice }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="budgetamount"
          :label="t$('jy1App.projectBudget.budgetamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.budgetamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="estimatedamount"
          :label="t$('jy1App.projectBudget.estimatedamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.estimatedamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="implementedamount"
          :label="t$('jy1App.projectBudget.implementedamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.implementedamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="difference"
          :label="t$('jy1App.projectBudget.difference')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.difference }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="remark" :label="t$('jy1App.projectBudget.remark')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibleperson.id"
          :label="t$('jy1App.projectBudget.responsibleperson')"
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="auditorid.id" :label="t$('jy1App.projectBudget.auditorid')">
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="projectwbs.id" :label="t$('jy1App.projectBudget.projectwbs')">
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
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProjectBudgetView', params: { projectBudgetId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProjectBudgetEdit', params: { projectBudgetId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="projectBudgets">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.wbsname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.parentwbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.subjectid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.subjectname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.contractid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.contractname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.year')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.auxiliaryitem')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.unit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.number')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.unitprice')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.budgetamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.estimatedamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.implementedamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.difference')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.remark')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.responsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.auditorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectBudget.projectwbs')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="projectBudget in projectBudgets"
                    :key="projectBudget.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'ProjectBudgetView', params: {projectBudgetId: projectBudget.id}}">{{projectBudget.id}}</router-link>
                    </td>
                    <td>{{projectBudget.wbsid}}</td>
                    <td>{{projectBudget.wbsname}}</td>
                    <td>{{projectBudget.parentwbsid}}</td>
                    <td>{{projectBudget.subjectid}}</td>
                    <td>{{projectBudget.subjectname}}</td>
                    <td>{{projectBudget.contractid}}</td>
                    <td>{{projectBudget.contractname}}</td>
                    <td>{{projectBudget.year}}</td>
                    <td>{{projectBudget.auxiliaryitem}}</td>
                    <td>{{projectBudget.unit}}</td>
                    <td>{{projectBudget.number}}</td>
                    <td>{{projectBudget.unitprice}}</td>
                    <td>{{projectBudget.budgetamount}}</td>
                    <td>{{projectBudget.estimatedamount}}</td>
                    <td>{{projectBudget.implementedamount}}</td>
                    <td>{{projectBudget.difference}}</td>
                    <td>{{projectBudget.remark}}</td>
                    <td>
                        <div v-if="projectBudget.responsibleperson">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: projectBudget.responsibleperson.id}}">{{projectBudget.responsibleperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectBudget.auditorid">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: projectBudget.auditorid.id}}">{{projectBudget.auditorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(projectwbs, i) in projectBudget.projectwbs" :key="projectwbs.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectwbsView', params: {projectwbsId: projectwbs.id}}">{{projectwbs.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ProjectBudgetView', params: {projectBudgetId: projectBudget.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'ProjectBudgetEdit', params: {projectBudgetId: projectBudget.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(projectBudget)"
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
        <span
          id="jy1App.projectBudget.delete.question"
          data-cy="projectBudgetDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-projectBudget-heading" v-text="t$('jy1App.projectBudget.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-projectBudget"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProjectBudget()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./project-budget.component.ts"></script>
