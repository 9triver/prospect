<template>
  <div>
    <h2 id="page-heading" data-cy="DepartmentHeading">
      <span v-text="t$('jy1App.department.home.title')" id="department-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.department.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'DepartmentCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-department"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.department.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && departments && departments.length === 0">
      <span v-text="t$('jy1App.department.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="departments && departments.length > 0">
      <el-table :data="departments" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'DepartmentView', params: { departmentId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.department.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="description"
          :label="t$('jy1App.department.description')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="role" :label="t$('jy1App.department.role')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.role }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="officersnum"
          :label="t$('jy1App.department.officersnum')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.officersnum }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="superior.id" :label="t$('jy1App.department.superior')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.superior">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: scope.row.superior.id } }">{{
                  scope.row.superior.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="officers.id" :label="t$('jy1App.department.officers')">
          <template #default="scope">
            <td>
              <span v-for="(officers, i) in scope.row.officers" :key="officers.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'OfficersView', params: { officersId: officers.id } }">{{
                  officers.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="pbs.id" :label="t$('jy1App.department.pbs')">
          <template #default="scope">
            <td>
              <span v-for="(pbs, i) in scope.row.pbs" :key="pbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectpbsView', params: { projectpbsId: pbs.id } }">{{
                  pbs.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbs.id" :label="t$('jy1App.department.wbs')">
          <template #default="scope">
            <td>
              <span v-for="(wbs, i) in scope.row.wbs" :key="wbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: wbs.id } }">{{
                  wbs.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.department.workbag')">
          <template #default="scope">
            <td>
              <span v-for="(workbag, i) in scope.row.workbags" :key="workbag.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'WorkbagView', params: { workbagId: workbag.id } }">{{
                  workbag.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'DepartmentEdit', params: { departmentId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="departments">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.department.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.department.description')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.department.role')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.department.officersnum')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.department.superior')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.department.officers')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.department.pbs')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.department.wbs')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.department.workbag')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="department in departments"
                    :key="department.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'DepartmentView', params: {departmentId: department.id}}">{{department.id}}</router-link>
                    </td>
                    <td>{{department.name}}</td>
                    <td>{{department.description}}</td>
                    <td>{{department.role}}</td>
                    <td>{{department.officersnum}}</td>
                    <td>
                        <div v-if="department.superior">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: department.superior.id}}">{{department.superior.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(officers, i) in department.officers" :key="officers.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'OfficersView', params: {officersId: officers.id}}">{{officers.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(pbs, i) in department.pbs" :key="pbs.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectpbsView', params: {projectpbsId: pbs.id}}">{{pbs.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(wbs, i) in department.wbs" :key="wbs.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectwbsView', params: {projectwbsId: wbs.id}}">{{wbs.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(workbag, i) in department.workbags" :key="workbag.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'WorkbagView', params: {workbagId: workbag.id}}">{{workbag.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: department.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'DepartmentEdit', params: {departmentId: department.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(department)"
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
        <span id="jy1App.department.delete.question" data-cy="departmentDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-department-heading" v-text="t$('jy1App.department.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-department"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeDepartment()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./department.component.ts"></script>
