<template>
  <div>
    <h2 id="page-heading" data-cy="SubjectCostBudgetHeading">
      <span v-text="t$('jy1App.subjectCostBudget.home.title')" id="subject-cost-budget-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.subjectCostBudget.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'SubjectCostBudgetCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-subject-cost-budget"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.subjectCostBudget.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && subjectCostBudgets && subjectCostBudgets.length === 0">
      <span v-text="t$('jy1App.subjectCostBudget.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="subjectCostBudgets && subjectCostBudgets.length > 0">
      <el-table :data="subjectCostBudgets" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'SubjectCostBudgetView', params: { subjectCostBudgetId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractid"
          :label="t$('jy1App.subjectCostBudget.contractid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="subjectid"
          :label="t$('jy1App.subjectCostBudget.subjectid')"
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
          :label="t$('jy1App.subjectCostBudget.subjectname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.subjectname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="budgetamount"
          :label="t$('jy1App.subjectCostBudget.budgetamount')"
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
          :label="t$('jy1App.subjectCostBudget.estimatedamount')"
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
          :label="t$('jy1App.subjectCostBudget.implementedamount')"
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
          :label="t$('jy1App.subjectCostBudget.difference')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.difference }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="percentage"
          :label="t$('jy1App.subjectCostBudget.percentage')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.percentage }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectBudget.id"
          :label="t$('jy1App.subjectCostBudget.projectBudget')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.projectBudget">
                <router-link :to="{ name: 'ProjectBudgetView', params: { projectBudgetId: scope.row.projectBudget.id } }">{{
                  scope.row.projectBudget.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibleperson.id"
          :label="t$('jy1App.subjectCostBudget.responsibleperson')"
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="auditorid.id" :label="t$('jy1App.subjectCostBudget.auditorid')">
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
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SubjectCostBudgetView', params: { subjectCostBudgetId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SubjectCostBudgetEdit', params: { subjectCostBudgetId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
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
      <!-- <table class="table table-striped" aria-describedby="subjectCostBudgets">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.contractid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.subjectid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.subjectname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.budgetamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.estimatedamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.implementedamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.difference')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.percentage')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.projectBudget')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.responsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.subjectCostBudget.auditorid')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="subjectCostBudget in subjectCostBudgets"
                    :key="subjectCostBudget.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'SubjectCostBudgetView', params: {subjectCostBudgetId: subjectCostBudget.id}}">{{subjectCostBudget.id}}</router-link>
                    </td>
                    <td>{{subjectCostBudget.contractid}}</td>
                    <td>{{subjectCostBudget.subjectid}}</td>
                    <td>{{subjectCostBudget.subjectname}}</td>
                    <td>{{subjectCostBudget.budgetamount}}</td>
                    <td>{{subjectCostBudget.estimatedamount}}</td>
                    <td>{{subjectCostBudget.implementedamount}}</td>
                    <td>{{subjectCostBudget.difference}}</td>
                    <td>{{subjectCostBudget.percentage}}</td>
                    <td>
                        <div v-if="subjectCostBudget.projectBudget">
                            <router-link :to="{name: 'ProjectBudgetView', params: {projectBudgetId: subjectCostBudget.projectBudget.id}}">{{subjectCostBudget.projectBudget.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="subjectCostBudget.responsibleperson">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: subjectCostBudget.responsibleperson.id}}">{{subjectCostBudget.responsibleperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="subjectCostBudget.auditorid">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: subjectCostBudget.auditorid.id}}">{{subjectCostBudget.auditorid.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SubjectCostBudgetView', params: {subjectCostBudgetId: subjectCostBudget.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'SubjectCostBudgetEdit', params: {subjectCostBudgetId: subjectCostBudget.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(subjectCostBudget)"
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
          id="jy1App.subjectCostBudget.delete.question"
          data-cy="subjectCostBudgetDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-subjectCostBudget-heading" v-text="t$('jy1App.subjectCostBudget.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-subjectCostBudget"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSubjectCostBudget()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./subject-cost-budget.component.ts"></script>
