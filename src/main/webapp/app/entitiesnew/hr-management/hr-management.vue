<template>
  <div>
    <h2 id="page-heading" data-cy="HrManagementHeading">
      <span v-text="t$('jy1App.hrManagement.home.title')" id="hr-management-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.hrManagement.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'HrManagementCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-hr-management"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.hrManagement.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && hrManagements && hrManagements.length === 0">
      <span v-text="t$('jy1App.hrManagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="hrManagements && hrManagements.length > 0">
      <el-table :data="hrManagements" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="officersid"
          :label="t$('jy1App.hrManagement.officersid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.officersid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="officersname"
          :label="t$('jy1App.hrManagement.officersname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.officersname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectid"
          :label="t$('jy1App.hrManagement.projectid')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectname"
          :label="t$('jy1App.hrManagement.projectname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectrole"
          :label="t$('jy1App.hrManagement.projectrole')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectrole }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="jobgrade"
          :label="t$('jy1App.hrManagement.jobgrade')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.jobgrade }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="departmentid"
          :label="t$('jy1App.hrManagement.departmentid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.departmentid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="departmentname"
          :label="t$('jy1App.hrManagement.departmentname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.departmentname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="frontlineid"
          :label="t$('jy1App.hrManagement.frontlineid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.frontlineid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="frontlinename"
          :label="t$('jy1App.hrManagement.frontlinename')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.frontlinename }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="jobduty"
          :label="t$('jy1App.hrManagement.jobduty')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.jobduty }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="annualworktime"
          :label="t$('jy1App.hrManagement.annualworktime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.annualworktime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="annualtasktarget"
          :label="t$('jy1App.hrManagement.annualtasktarget')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.annualtasktarget }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="officers.id" :label="t$('jy1App.hrManagement.officers')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.officers">
                <router-link :to="{ name: 'OfficersView', params: { officersId: scope.row.officers.id } }">{{
                  scope.row.officers.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'HrManagementEdit', params: { hrManagementId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="hrManagements">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.officersid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.officersname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.projectid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.projectname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.projectrole')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.jobgrade')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.departmentid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.departmentname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.frontlineid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.frontlinename')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.jobduty')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.annualworktime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.annualtasktarget')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.hrManagement.officers')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="hrManagement in hrManagements"
                    :key="hrManagement.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'HrManagementView', params: {hrManagementId: hrManagement.id}}">{{hrManagement.id}}</router-link>
                    </td>
                    <td>{{hrManagement.officersid}}</td>
                    <td>{{hrManagement.officersname}}</td>
                    <td>{{hrManagement.projectid}}</td>
                    <td>{{hrManagement.projectname}}</td>
                    <td>{{hrManagement.projectrole}}</td>
                    <td>{{hrManagement.jobgrade}}</td>
                    <td>{{hrManagement.departmentid}}</td>
                    <td>{{hrManagement.departmentname}}</td>
                    <td>{{hrManagement.frontlineid}}</td>
                    <td>{{hrManagement.frontlinename}}</td>
                    <td>{{hrManagement.jobduty}}</td>
                    <td>{{hrManagement.annualworktime}}</td>
                    <td>{{hrManagement.annualtasktarget}}</td>
                    <td>
                        <div v-if="hrManagement.officers">
                            <router-link :to="{name: 'OfficersView', params: {officersId: hrManagement.officers.id}}">{{hrManagement.officers.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: hrManagement.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'HrManagementEdit', params: {hrManagementId: hrManagement.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(hrManagement)"
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
        <span id="jy1App.hrManagement.delete.question" data-cy="hrManagementDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-hrManagement-heading" v-text="t$('jy1App.hrManagement.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-hrManagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeHrManagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./hr-management.component.ts"></script>
