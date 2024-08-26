<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectremitHeading">
      <span v-text="t$('jy1App.projectremit.home.title')" id="projectremit-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectremit.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'ProjectremitCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectremit"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.projectremit.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectremits && projectremits.length === 0">
      <span v-text="t$('jy1App.projectremit.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectremits && projectremits.length > 0">
      <el-table :data="projectremits" style="width: 100%" border stripe fit v-loading="isFetching">
        <!-- <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'ProjectremitView', params: { projectremitId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column> -->
        <el-table-column min-width="150px" show-overflow-tooltip prop="remit" :label="t$('jy1App.projectremit.remit')" :sortable="false">
          <template #default="scope">
            <router-link :to="{ name: 'ProjectremitView', params: { projectremitId: scope.row.id } }">{{ scope.row.remit }}</router-link>
            <!-- <span class="field-default">{{ scope.row.remit }}</span> -->
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="outdeportment"
          :label="t$('jy1App.projectremit.outdeportment')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.outdeportment }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="indeportment"
          :label="t$('jy1App.projectremit.indeportment')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.indeportment }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectname"
          :label="t$('jy1App.projectremit.projectname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="deportment"
          :label="t$('jy1App.projectremit.deportment')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.deportment }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectleader"
          :label="t$('jy1App.projectremit.projectleader')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectleader }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.projectremit.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.projectremit.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProjectremitView', params: { projectremitId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProjectremitEdit', params: { projectremitId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="projectremits">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectremit.remit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectremit.outdeportment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectremit.indeportment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectremit.projectname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectremit.deportment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectremit.projectleader')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectremit.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectremit.auditStatus')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="projectremit in projectremits"
                    :key="projectremit.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'ProjectremitView', params: {projectremitId: projectremit.id}}">{{projectremit.id}}</router-link>
                    </td>
                    <td>{{projectremit.remit}}</td>
                    <td>{{projectremit.outdeportment}}</td>
                    <td>{{projectremit.indeportment}}</td>
                    <td>{{projectremit.projectname}}</td>
                    <td>{{projectremit.deportment}}</td>
                    <td>{{projectremit.projectleader}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + projectremit.secretlevel)"></td>
                    <td v-text="t$('jy1App.AuditStatus.' + projectremit.auditStatus)"></td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ProjectremitView', params: {projectremitId: projectremit.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'ProjectremitEdit', params: {projectremitId: projectremit.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(projectremit)"
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
        <span id="jy1App.projectremit.delete.question" data-cy="projectremitDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-projectremit-heading" v-text="t$('jy1App.projectremit.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-projectremit"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProjectremit()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./projectremit.component.ts"></script>
