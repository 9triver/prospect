<template>
  <div>
    <h2 id="page-heading" data-cy="QualityPlanHeading">
      <span v-text="t$('jy1App.qualityPlan.home.title')" id="quality-plan-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.qualityPlan.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'QualityPlanCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-quality-plan"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.qualityPlan.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualityPlans && qualityPlans.length === 0">
      <span v-text="t$('jy1App.qualityPlan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualityPlans && qualityPlans.length > 0">
      <el-table :data="qualityPlans" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'QualityPlanView', params: { qualityPlanId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.qualityPlan.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="qualitytype"
          :label="t$('jy1App.qualityPlan.qualitytype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.QualityType.' + scope.row.qualitytype)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.qualityPlan.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid" :label="t$('jy1App.qualityPlan.wbsid')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagid"
          :label="t$('jy1App.qualityPlan.workbagid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="fileversion"
          :label="t$('jy1App.qualityPlan.fileversion')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.fileversion }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="author" :label="t$('jy1App.qualityPlan.author')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.author }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="attachment"
          :label="t$('jy1App.qualityPlan.attachment')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.attachment }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="projectwbs.id" :label="t$('jy1App.qualityPlan.projectwbs')">
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.qualityPlan.workbag')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.workbag">
                <router-link :to="{ name: 'WorkbagView', params: { workbagId: scope.row.workbag.id } }">{{
                  scope.row.workbag.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'QualityPlanView', params: { qualityPlanId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'QualityPlanEdit', params: { qualityPlanId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="qualityPlans">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityPlan.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityPlan.qualitytype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityPlan.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityPlan.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityPlan.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityPlan.fileversion')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityPlan.author')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityPlan.attachment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityPlan.projectwbs')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityPlan.workbag')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="qualityPlan in qualityPlans"
                    :key="qualityPlan.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'QualityPlanView', params: {qualityPlanId: qualityPlan.id}}">{{qualityPlan.id}}</router-link>
                    </td>
                    <td>{{qualityPlan.name}}</td>
                    <td v-text="t$('jy1App.QualityType.' + qualityPlan.qualitytype)"></td>
                    <td v-text="t$('jy1App.Secretlevel.' + qualityPlan.secretlevel)"></td>
                    <td>{{qualityPlan.wbsid}}</td>
                    <td>{{qualityPlan.workbagid}}</td>
                    <td>{{qualityPlan.fileversion}}</td>
                    <td>{{qualityPlan.author}}</td>
                    <td>{{qualityPlan.attachment}}</td>
                    <td>
                        <div v-if="qualityPlan.projectwbs">
                            <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: qualityPlan.projectwbs.id}}">{{qualityPlan.projectwbs.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="qualityPlan.workbag">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: qualityPlan.workbag.id}}">{{qualityPlan.workbag.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'QualityPlanView', params: {qualityPlanId: qualityPlan.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'QualityPlanEdit', params: {qualityPlanId: qualityPlan.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(qualityPlan)"
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
        <span id="jy1App.qualityPlan.delete.question" data-cy="qualityPlanDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-qualityPlan-heading" v-text="t$('jy1App.qualityPlan.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-qualityPlan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeQualityPlan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./quality-plan.component.ts"></script>
