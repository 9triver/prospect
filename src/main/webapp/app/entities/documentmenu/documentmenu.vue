<template>
  <div>
    <h2 id="page-heading" data-cy="DocumentmenuHeading">
      <span v-text="t$('jy1App.documentmenu.home.title')" id="documentmenu-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.documentmenu.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'DocumentmenuCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-documentmenu"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.documentmenu.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && documentmenus && documentmenus.length === 0">
      <span v-text="t$('jy1App.documentmenu.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="documentmenus && documentmenus.length > 0">
      <el-table :data="documentmenus" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'DocumentmenuView', params: { documentmenuId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="menuid" :label="t$('jy1App.documentmenu.menuid')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.menuid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongtype"
          :label="t$('jy1App.documentmenu.belongtype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.belongtype }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="menuname"
          :label="t$('jy1App.documentmenu.menuname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.menuname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="parentmenuid"
          :label="t$('jy1App.documentmenu.parentmenuid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.parentmenuid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="createtime"
          :label="t$('jy1App.documentmenu.createtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.createtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="creatorid"
          :label="t$('jy1App.documentmenu.creatorid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.creatorid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="creatorname"
          :label="t$('jy1App.documentmenu.creatorname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.creatorname }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="type" :label="t$('jy1App.documentmenu.type')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.type }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="filenum" :label="t$('jy1App.documentmenu.filenum')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.filenum }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="fileurl"
          :label="t$('jy1App.documentmenu.fileurl')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.fileurl }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="departmentid"
          :label="t$('jy1App.documentmenu.departmentid')"
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
          :label="t$('jy1App.documentmenu.departmentname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.departmentname }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="spare1" :label="t$('jy1App.documentmenu.spare1')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.spare1 }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="spare2" :label="t$('jy1App.documentmenu.spare2')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.spare2 }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="spare3" :label="t$('jy1App.documentmenu.spare3')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.spare3 }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'DocumentmenuView', params: { documentmenuId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'DocumentmenuEdit', params: { documentmenuId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="documentmenus">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.menuid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.belongtype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.menuname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.parentmenuid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.createtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.creatorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.creatorname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.type')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.filenum')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.fileurl')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.departmentid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.departmentname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.spare1')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.spare2')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.documentmenu.spare3')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="documentmenu in documentmenus"
                    :key="documentmenu.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'DocumentmenuView', params: {documentmenuId: documentmenu.id}}">{{documentmenu.id}}</router-link>
                    </td>
                    <td>{{documentmenu.menuid}}</td>
                    <td>{{documentmenu.belongtype}}</td>
                    <td>{{documentmenu.menuname}}</td>
                    <td>{{documentmenu.parentmenuid}}</td>
                    <td>{{documentmenu.createtime}}</td>
                    <td>{{documentmenu.creatorid}}</td>
                    <td>{{documentmenu.creatorname}}</td>
                    <td>{{documentmenu.type}}</td>
                    <td>{{documentmenu.filenum}}</td>
                    <td>{{documentmenu.fileurl}}</td>
                    <td>{{documentmenu.departmentid}}</td>
                    <td>{{documentmenu.departmentname}}</td>
                    <td>{{documentmenu.spare1}}</td>
                    <td>{{documentmenu.spare2}}</td>
                    <td>{{documentmenu.spare3}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DocumentmenuView', params: {documentmenuId: documentmenu.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'DocumentmenuEdit', params: {documentmenuId: documentmenu.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(documentmenu)"
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
        <span id="jy1App.documentmenu.delete.question" data-cy="documentmenuDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-documentmenu-heading" v-text="t$('jy1App.documentmenu.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-documentmenu"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeDocumentmenu()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./documentmenu.component.ts"></script>
