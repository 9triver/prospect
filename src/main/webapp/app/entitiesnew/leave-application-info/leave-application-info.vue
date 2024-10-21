<template>
  <div>
    <h2 id="page-heading" data-cy="LeaveApplicationInfoHeading">
      <span v-text="t$('jy1App.leaveApplicationInfo.home.title')" id="leave-application-info-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.leaveApplicationInfo.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'LeaveApplicationInfoCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-leave-application-info"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.leaveApplicationInfo.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && leaveApplicationInfos && leaveApplicationInfos.length === 0">
      <span v-text="t$('jy1App.leaveApplicationInfo.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="leaveApplicationInfos && leaveApplicationInfos.length > 0">
      <el-table :data="leaveApplicationInfos" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'LeaveApplicationInfoView', params: { leaveApplicationInfoId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="startDate"
          :label="t$('jy1App.leaveApplicationInfo.startDate')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.startDate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="endDate"
          :label="t$('jy1App.leaveApplicationInfo.endDate')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.endDate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="leaveType"
          :label="t$('jy1App.leaveApplicationInfo.leaveType')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.leaveType }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="reason"
          :label="t$('jy1App.leaveApplicationInfo.reason')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.reason }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="status"
          :label="t$('jy1App.leaveApplicationInfo.status')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.status }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'LeaveApplicationInfoView', params: { leaveApplicationInfoId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'LeaveApplicationInfoEdit', params: { leaveApplicationInfoId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="leaveApplicationInfos">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.leaveApplicationInfo.startDate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.leaveApplicationInfo.endDate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.leaveApplicationInfo.leaveType')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.leaveApplicationInfo.reason')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.leaveApplicationInfo.status')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="leaveApplicationInfo in leaveApplicationInfos"
                    :key="leaveApplicationInfo.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'LeaveApplicationInfoView', params: {leaveApplicationInfoId: leaveApplicationInfo.id}}">{{leaveApplicationInfo.id}}</router-link>
                    </td>
                    <td>{{leaveApplicationInfo.startDate}}</td>
                    <td>{{leaveApplicationInfo.endDate}}</td>
                    <td>{{leaveApplicationInfo.leaveType}}</td>
                    <td>{{leaveApplicationInfo.reason}}</td>
                    <td>{{leaveApplicationInfo.status}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'LeaveApplicationInfoView', params: {leaveApplicationInfoId: leaveApplicationInfo.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'LeaveApplicationInfoEdit', params: {leaveApplicationInfoId: leaveApplicationInfo.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(leaveApplicationInfo)"
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
          id="jy1App.leaveApplicationInfo.delete.question"
          data-cy="leaveApplicationInfoDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-leaveApplicationInfo-heading" v-text="t$('jy1App.leaveApplicationInfo.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-leaveApplicationInfo"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeLeaveApplicationInfo()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./leave-application-info.component.ts"></script>
