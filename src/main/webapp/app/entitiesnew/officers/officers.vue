<template>
  <div>
    <h2 id="page-heading" data-cy="OfficersHeading">
      <span v-text="t$('jy1App.officers.home.title')" id="officers-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.officers.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'OfficersCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-officers"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.officers.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && officers && officers.length === 0">
      <span v-text="t$('jy1App.officers.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="officers && officers.length > 0">
      <el-table :data="officers" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'OfficersView', params: { officersId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.officers.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="password" :label="t$('jy1App.officers.password')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.password }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="email" :label="t$('jy1App.officers.email')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="phone" :label="t$('jy1App.officers.phone')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="hiredate" :label="t$('jy1App.officers.hiredate')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.hiredate }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="years" :label="t$('jy1App.officers.years')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.years }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="status" :label="t$('jy1App.officers.status')" :sortable="false">
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.OfficersStatus.' + scope.row.status)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="departments.id" :label="t$('jy1App.officers.departments')">
          <template #default="scope">
            <td>
              <span v-for="(departments, i) in scope.row.departments" :key="departments.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'DepartmentView', params: { departmentId: departments.id } }">{{
                  departments.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="frontline.id" :label="t$('jy1App.officers.frontline')">
          <template #default="scope">
            <td>
              <span v-for="(frontline, i) in scope.row.frontlines" :key="frontline.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'FrontlineView', params: { frontlineId: frontline.id } }">{{
                  frontline.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="role.id" :label="t$('jy1App.officers.role')">
          <template #default="scope">
            <td>
              <span v-for="(role, i) in scope.row.roles" :key="role.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'RoleView', params: { roleId: role.id } }">{{ role.id }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'OfficersView', params: { officersId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'OfficersEdit', params: { officersId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="officers">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.officers.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.officers.password')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.officers.email')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.officers.phone')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.officers.hiredate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.officers.years')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.officers.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.officers.departments')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.officers.frontline')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.officers.role')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="officers in officers"
                    :key="officers.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'OfficersView', params: {officersId: officers.id}}">{{officers.id}}</router-link>
                    </td>
                    <td>{{officers.name}}</td>
                    <td>{{officers.password}}</td>
                    <td>{{officers.email}}</td>
                    <td>{{officers.phone}}</td>
                    <td>{{officers.hiredate}}</td>
                    <td>{{officers.years}}</td>
                    <td v-text="t$('jy1App.OfficersStatus.' + officers.status)"></td>
                    <td>
                        <span v-for="(departments, i) in officers.departments" :key="departments.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'DepartmentView', params: {departmentId: departments.id}}">{{departments.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(frontline, i) in officers.frontlines" :key="frontline.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'FrontlineView', params: {frontlineId: frontline.id}}">{{frontline.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(role, i) in officers.roles" :key="role.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'RoleView', params: {roleId: role.id}}">{{role.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'OfficersView', params: {officersId: officers.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'OfficersEdit', params: {officersId: officers.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(officers)"
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
        <span id="jy1App.officers.delete.question" data-cy="officersDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-officers-heading" v-text="t$('jy1App.officers.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-officers"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOfficers()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./officers.component.ts"></script>
