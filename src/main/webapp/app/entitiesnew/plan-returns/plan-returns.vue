<template>
  <div>
    <h2 id="page-heading" data-cy="PlanReturnsHeading">
      <span v-text="t$('jy1App.planReturns.home.title')" id="plan-returns-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.planReturns.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'PlanReturnsCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-plan-returns"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.planReturns.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && planReturns && planReturns.length === 0">
      <span v-text="t$('jy1App.planReturns.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="planReturns && planReturns.length > 0">
      <el-table :data="planReturns" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'PlanReturnsView', params: { planReturnsId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planreturnsname"
          :label="t$('jy1App.planReturns.planreturnsname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.planreturnsname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="plantype"
          :label="t$('jy1App.planReturns.plantype')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.plantype }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planlevel"
          :label="t$('jy1App.planReturns.planlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.PlanLevel.' + scope.row.planlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="description"
          :label="t$('jy1App.planReturns.description')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="actualstarttime"
          :label="t$('jy1App.planReturns.actualstarttime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.actualstarttime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="actualendtime"
          :label="t$('jy1App.planReturns.actualendtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.actualendtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="deliverables"
          :label="t$('jy1App.planReturns.deliverables')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.deliverables }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="progress"
          :label="t$('jy1App.planReturns.progress')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.progress }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="status" :label="t$('jy1App.planReturns.status')" :sortable="false">
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Planstatus.' + scope.row.status)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="impactanalysis"
          :label="t$('jy1App.planReturns.impactanalysis')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.impactanalysis }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="returnstime"
          :label="t$('jy1App.planReturns.returnstime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.returnstime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="rejectionreason"
          :label="t$('jy1App.planReturns.rejectionreason')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.rejectionreason }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="returnsstatus"
          :label="t$('jy1App.planReturns.returnsstatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.ReturnsStatus.' + scope.row.returnsstatus)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibleperson.id"
          :label="t$('jy1App.planReturns.responsibleperson')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.responsibleperson">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.responsibleperson.id } }">{{
                  scope.row.responsibleperson.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="auditorid.id" :label="t$('jy1App.planReturns.auditorid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.auditorid">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.auditorid.id } }">{{
                  scope.row.auditorid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="progressPlan.id" :label="t$('jy1App.planReturns.progressPlan')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.progressPlan">
                <router-link :to="{ name: 'ProgressPlanView', params: { progressPlanId: scope.row.progressPlan.id } }">{{
                  scope.row.progressPlan.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PlanReturnsView', params: { planReturnsId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PlanReturnsEdit', params: { planReturnsId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="planReturns">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.planreturnsname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.plantype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.planlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.description')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.actualstarttime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.actualendtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.deliverables')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.progress')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.impactanalysis')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.returnstime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.rejectionreason')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.returnsstatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.responsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.auditorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.planReturns.progressPlan')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="planReturns in planReturns"
                    :key="planReturns.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'PlanReturnsView', params: {planReturnsId: planReturns.id}}">{{planReturns.id}}</router-link>
                    </td>
                    <td>{{planReturns.planreturnsname}}</td>
                    <td>{{planReturns.plantype}}</td>
                    <td v-text="t$('jy1App.PlanLevel.' + planReturns.planlevel)"></td>
                    <td>{{planReturns.description}}</td>
                    <td>{{planReturns.actualstarttime}}</td>
                    <td>{{planReturns.actualendtime}}</td>
                    <td>{{planReturns.deliverables}}</td>
                    <td>{{planReturns.progress}}</td>
                    <td v-text="t$('jy1App.Planstatus.' + planReturns.status)"></td>
                    <td>{{planReturns.impactanalysis}}</td>
                    <td>{{planReturns.returnstime}}</td>
                    <td>{{planReturns.rejectionreason}}</td>
                    <td v-text="t$('jy1App.ReturnsStatus.' + planReturns.returnsstatus)"></td>
                    <td>
                        <div v-if="planReturns.responsibleperson">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: planReturns.responsibleperson.id}}">{{planReturns.responsibleperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="planReturns.auditorid">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: planReturns.auditorid.id}}">{{planReturns.auditorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="planReturns.progressPlan">
                            <router-link :to="{name: 'ProgressPlanView', params: {progressPlanId: planReturns.progressPlan.id}}">{{planReturns.progressPlan.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'PlanReturnsView', params: {planReturnsId: planReturns.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'PlanReturnsEdit', params: {planReturnsId: planReturns.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(planReturns)"
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
        <span id="jy1App.planReturns.delete.question" data-cy="planReturnsDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-planReturns-heading" v-text="t$('jy1App.planReturns.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-planReturns"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePlanReturns()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./plan-returns.component.ts"></script>
