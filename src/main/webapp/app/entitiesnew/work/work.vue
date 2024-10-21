<template>
  <div>
    <h2 id="page-heading" data-cy="WorkHeading">
      <span v-text="t$('jy1App.work.home.title')" id="work-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.work.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'WorkCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-work"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.work.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && works && works.length === 0">
      <span v-text="t$('jy1App.work.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="works && works.length > 0">
      <el-table :data="works" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'WorkView', params: { workId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.work.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.work.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="description"
          :label="t$('jy1App.work.description')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.work.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.work.workbag')">
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
                <router-link :to="{ name: 'WorkView', params: { workId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'WorkEdit', params: { workId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="works">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.work.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.work.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.work.description')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.work.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.work.workbag')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="work in works"
                    :key="work.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'WorkView', params: {workId: work.id}}">{{work.id}}</router-link>
                    </td>
                    <td>{{work.name}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + work.secretlevel)"></td>
                    <td>{{work.description}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + work.auditStatus)"></td>
                    <td>
                        <span v-for="(workbag, i) in work.workbags" :key="workbag.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'WorkbagView', params: {workbagId: workbag.id}}">{{workbag.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'WorkView', params: {workId: work.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'WorkEdit', params: {workId: work.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(work)"
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
        <span id="jy1App.work.delete.question" data-cy="workDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-work-heading" v-text="t$('jy1App.work.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-work"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeWork()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./work.component.ts"></script>
