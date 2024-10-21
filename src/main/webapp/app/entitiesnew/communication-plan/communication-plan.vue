<template>
  <div>
    <h2 id="page-heading" data-cy="CommunicationPlanHeading">
      <span v-text="t$('jy1App.communicationPlan.home.title')" id="communication-plan-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.communicationPlan.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'CommunicationPlanCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-communication-plan"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.communicationPlan.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && communicationPlans && communicationPlans.length === 0">
      <span v-text="t$('jy1App.communicationPlan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="communicationPlans && communicationPlans.length > 0">
      <el-table :data="communicationPlans" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'CommunicationPlanView', params: { communicationPlanId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="wbsid"
          :label="t$('jy1App.communicationPlan.wbsid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="communicationtopic"
          :label="t$('jy1App.communicationPlan.communicationtopic')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.communicationtopic }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="communicationtime"
          :label="t$('jy1App.communicationPlan.communicationtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.communicationtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="worktarget"
          :label="t$('jy1App.communicationPlan.worktarget')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.worktarget }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workcontent"
          :label="t$('jy1App.communicationPlan.workcontent')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workcontent }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="remarks"
          :label="t$('jy1App.communicationPlan.remarks')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.remarks }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.communicationPlan.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="projectwbs.id" :label="t$('jy1App.communicationPlan.projectwbs')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.projectwbs">
                <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: scope.row.projectwbs.id } }">{{
                  scope.row.projectwbs.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'CommunicationPlanView', params: { communicationPlanId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'CommunicationPlanEdit', params: { communicationPlanId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="communicationPlans">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationPlan.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationPlan.communicationtopic')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationPlan.communicationtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationPlan.worktarget')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationPlan.workcontent')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationPlan.remarks')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationPlan.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationPlan.projectwbs')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="communicationPlan in communicationPlans"
                    :key="communicationPlan.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'CommunicationPlanView', params: {communicationPlanId: communicationPlan.id}}">{{communicationPlan.id}}</router-link>
                    </td>
                    <td>{{communicationPlan.wbsid}}</td>
                    <td>{{communicationPlan.communicationtopic}}</td>
                    <td>{{communicationPlan.communicationtime}}</td>
                    <td>{{communicationPlan.worktarget}}</td>
                    <td>{{communicationPlan.workcontent}}</td>
                    <td>{{communicationPlan.remarks}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + communicationPlan.auditStatus)"></td>
                    <td>
                        <div v-if="communicationPlan.projectwbs">
                            <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: communicationPlan.projectwbs.id}}">{{communicationPlan.projectwbs.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CommunicationPlanView', params: {communicationPlanId: communicationPlan.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'CommunicationPlanEdit', params: {communicationPlanId: communicationPlan.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(communicationPlan)"
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
          id="jy1App.communicationPlan.delete.question"
          data-cy="communicationPlanDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-communicationPlan-heading" v-text="t$('jy1App.communicationPlan.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-communicationPlan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeCommunicationPlan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./communication-plan.component.ts"></script>
