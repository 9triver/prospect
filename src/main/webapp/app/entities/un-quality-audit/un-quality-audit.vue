<template>
  <div>
    <h2 id="page-heading" data-cy="UnQualityAuditHeading">
      <span v-text="t$('jy1App.unQualityAudit.home.title')" id="un-quality-audit-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.unQualityAudit.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'UnQualityAuditCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-un-quality-audit"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.unQualityAudit.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && unQualityAudits && unQualityAudits.length === 0">
      <span v-text="t$('jy1App.unQualityAudit.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="unQualityAudits && unQualityAudits.length > 0">
      <el-table :data="unQualityAudits" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'UnQualityAuditView', params: { unQualityAuditId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unqualityname"
          :label="t$('jy1App.unQualityAudit.unqualityname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unqualityname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unqualitytype"
          :label="t$('jy1App.unQualityAudit.unqualitytype')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unqualitytype }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongunitid"
          :label="t$('jy1App.unQualityAudit.belongunitid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.belongunitid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongunitname"
          :label="t$('jy1App.unQualityAudit.belongunitname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.belongunitname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditteam"
          :label="t$('jy1App.unQualityAudit.auditteam')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.auditteam }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditperson"
          :label="t$('jy1App.unQualityAudit.auditperson')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.auditperson }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unqualitynum"
          :label="t$('jy1App.unQualityAudit.unqualitynum')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unqualitynum }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="creatorname"
          :label="t$('jy1App.unQualityAudit.creatorname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.creatorname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.unQualityAudit.auditStatus')"
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
                <router-link :to="{ name: 'UnQualityAuditView', params: { unQualityAuditId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'UnQualityAuditEdit', params: { unQualityAuditId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="unQualityAudits">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualityname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualitytype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.belongunitid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.belongunitname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.auditteam')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.auditperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualitynum')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.creatorname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.auditStatus')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="unQualityAudit in unQualityAudits"
                    :key="unQualityAudit.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'UnQualityAuditView', params: {unQualityAuditId: unQualityAudit.id}}">{{unQualityAudit.id}}</router-link>
                    </td>
                    <td>{{unQualityAudit.unqualityname}}</td>
                    <td>{{unQualityAudit.unqualitytype}}</td>
                    <td>{{unQualityAudit.belongunitid}}</td>
                    <td>{{unQualityAudit.belongunitname}}</td>
                    <td>{{unQualityAudit.auditteam}}</td>
                    <td>{{unQualityAudit.auditperson}}</td>
                    <td>{{unQualityAudit.unqualitynum}}</td>
                    <td>{{unQualityAudit.creatorname}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + unQualityAudit.auditStatus)"></td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'UnQualityAuditView', params: {unQualityAuditId: unQualityAudit.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'UnQualityAuditEdit', params: {unQualityAuditId: unQualityAudit.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(unQualityAudit)"
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
          id="jy1App.unQualityAudit.delete.question"
          data-cy="unQualityAuditDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-unQualityAudit-heading" v-text="t$('jy1App.unQualityAudit.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-unQualityAudit"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeUnQualityAudit()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./un-quality-audit.component.ts"></script>
