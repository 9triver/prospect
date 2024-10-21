<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectdeliverablesHeading">
      <span v-text="t$('jy1App.projectdeliverables.home.title')" id="projectdeliverables-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectdeliverables.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'ProjectdeliverablesCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectdeliverables"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.projectdeliverables.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectdeliverables && projectdeliverables.length === 0">
      <span v-text="t$('jy1App.projectdeliverables.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectdeliverables && projectdeliverables.length > 0">
      <el-table :data="projectdeliverables" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'ProjectdeliverablesView', params: { projectdeliverablesId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="parentcode"
          :label="t$('jy1App.projectdeliverables.parentcode')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.parentcode }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="isSubmit"
          :label="t$('jy1App.projectdeliverables.isSubmit')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.isSubmit }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="status"
          :label="t$('jy1App.projectdeliverables.status')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.status }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="code.id" :label="t$('jy1App.projectdeliverables.code')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.code">
                <router-link :to="{ name: 'DeliverablesView', params: { deliverablesId: scope.row.code.id } }">{{
                  scope.row.code.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongwbsid.id"
          :label="t$('jy1App.projectdeliverables.belongwbsid')"
        >
          <template #default="scope">
            <td>
              <span v-for="(belongwbsid, i) in scope.row.belongwbsids" :key="belongwbsid.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: belongwbsid.id } }">{{
                  belongwbsid.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongworkbagid.id"
          :label="t$('jy1App.projectdeliverables.belongworkbagid')"
        >
          <template #default="scope">
            <td>
              <span v-for="(belongworkbagid, i) in scope.row.belongworkbagids" :key="belongworkbagid.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'WorkbagView', params: { workbagId: belongworkbagid.id } }">{{
                  belongworkbagid.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProjectdeliverablesView', params: { projectdeliverablesId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProjectdeliverablesEdit', params: { projectdeliverablesId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="projectdeliverables">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectdeliverables.parentcode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectdeliverables.isSubmit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectdeliverables.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectdeliverables.code')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectdeliverables.belongwbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectdeliverables.belongworkbagid')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="projectdeliverables in projectdeliverables"
                    :key="projectdeliverables.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'ProjectdeliverablesView', params: {projectdeliverablesId: projectdeliverables.id}}">{{projectdeliverables.id}}</router-link>
                    </td>
                    <td>{{projectdeliverables.parentcode}}</td>
                    <td>{{projectdeliverables.isSubmit}}</td>
                    <td>{{projectdeliverables.status}}</td>
                    <td>
                        <div v-if="projectdeliverables.code">
                            <router-link :to="{name: 'DeliverablesView', params: {deliverablesId: projectdeliverables.code.id}}">{{projectdeliverables.code.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(belongwbsid, i) in projectdeliverables.belongwbsids" :key="belongwbsid.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectwbsView', params: {projectwbsId: belongwbsid.id}}">{{belongwbsid.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(belongworkbagid, i) in projectdeliverables.belongworkbagids" :key="belongworkbagid.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'WorkbagView', params: {workbagId: belongworkbagid.id}}">{{belongworkbagid.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ProjectdeliverablesView', params: {projectdeliverablesId: projectdeliverables.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'ProjectdeliverablesEdit', params: {projectdeliverablesId: projectdeliverables.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(projectdeliverables)"
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
          id="jy1App.projectdeliverables.delete.question"
          data-cy="projectdeliverablesDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-projectdeliverables-heading" v-text="t$('jy1App.projectdeliverables.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-projectdeliverables"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProjectdeliverables()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./projectdeliverables.component.ts"></script>
