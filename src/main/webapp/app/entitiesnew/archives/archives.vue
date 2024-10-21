<template>
  <div>
    <h2 id="page-heading" data-cy="ArchivesHeading">
      <span v-text="t$('jy1App.archives.home.title')" id="archives-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.archives.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'ArchivesCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-archives"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.archives.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && archives && archives.length === 0">
      <span v-text="t$('jy1App.archives.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="archives && archives.length > 0">
      <el-table :data="archives" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'ArchivesView', params: { archivesId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="title" :label="t$('jy1App.archives.title')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="content" :label="t$('jy1App.archives.content')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.content }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="date" :label="t$('jy1App.archives.date')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.date }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="page" :label="t$('jy1App.archives.page')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.page }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.archives.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="confidentialityperiod"
          :label="t$('jy1App.archives.confidentialityperiod')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.confidentialityperiod }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="confidentialnumber"
          :label="t$('jy1App.archives.confidentialnumber')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.confidentialnumber }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="storageperiod"
          :label="t$('jy1App.archives.storageperiod')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.storageperiod }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="plannumber"
          :label="t$('jy1App.archives.plannumber')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.plannumber }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="remarks" :label="t$('jy1App.archives.remarks')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.remarks }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="receivingnumber"
          :label="t$('jy1App.archives.receivingnumber')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.receivingnumber }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="responsibleid.id" :label="t$('jy1App.archives.responsibleid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.responsibleid">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.responsibleid.id } }">{{
                  scope.row.responsibleid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ArchivesView', params: { archivesId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ArchivesEdit', params: { archivesId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="archives">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.title')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.content')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.date')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.page')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.confidentialityperiod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.confidentialnumber')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.storageperiod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.plannumber')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.remarks')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.receivingnumber')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.archives.responsibleid')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="archives in archives"
                    :key="archives.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'ArchivesView', params: {archivesId: archives.id}}">{{archives.id}}</router-link>
                    </td>
                    <td>{{archives.title}}</td>
                    <td>{{archives.content}}</td>
                    <td>{{archives.date}}</td>
                    <td>{{archives.page}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + archives.secretlevel)"></td>
                    <td>{{archives.confidentialityperiod}}</td>
                    <td>{{archives.confidentialnumber}}</td>
                    <td>{{archives.storageperiod}}</td>
                    <td>{{archives.plannumber}}</td>
                    <td>{{archives.remarks}}</td>
                    <td>{{archives.receivingnumber}}</td>
                    <td>
                        <div v-if="archives.responsibleid">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: archives.responsibleid.id}}">{{archives.responsibleid.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ArchivesView', params: {archivesId: archives.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'ArchivesEdit', params: {archivesId: archives.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(archives)"
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
        <span id="jy1App.archives.delete.question" data-cy="archivesDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-archives-heading" v-text="t$('jy1App.archives.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-archives"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeArchives()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./archives.component.ts"></script>
