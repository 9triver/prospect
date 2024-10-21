<template>
  <div>
    <h2 id="page-heading" data-cy="TechnicalConditionHeading">
      <span v-text="t$('jy1App.technicalCondition.home.title')" id="technical-condition-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.technicalCondition.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'TechnicalConditionCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-technical-condition"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.technicalCondition.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && technicalConditions && technicalConditions.length === 0">
      <span v-text="t$('jy1App.technicalCondition.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="technicalConditions && technicalConditions.length > 0">
      <el-table :data="technicalConditions" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'TechnicalConditionView', params: { technicalConditionId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagid"
          :label="t$('jy1App.technicalCondition.workbagid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongwbsid"
          :label="t$('jy1App.technicalCondition.belongwbsid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.belongwbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="outsourcingcontractid"
          :label="t$('jy1App.technicalCondition.outsourcingcontractid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.outsourcingcontractid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="technicalid"
          :label="t$('jy1App.technicalCondition.technicalid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.technicalid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="technicalname"
          :label="t$('jy1App.technicalCondition.technicalname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.technicalname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="changedfilename"
          :label="t$('jy1App.technicalCondition.changedfilename')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.changedfilename }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="applicant"
          :label="t$('jy1App.technicalCondition.applicant')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.applicant }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="applicationdate"
          :label="t$('jy1App.technicalCondition.applicationdate')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.applicationdate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="changedreason"
          :label="t$('jy1App.technicalCondition.changedreason')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.changedreason }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="changedbefore"
          :label="t$('jy1App.technicalCondition.changedbefore')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.changedbefore }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="changedafter"
          :label="t$('jy1App.technicalCondition.changedafter')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.changedafter }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="distributionrange"
          :label="t$('jy1App.technicalCondition.distributionrange')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.distributionrange }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="remarks"
          :label="t$('jy1App.technicalCondition.remarks')"
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
          :label="t$('jy1App.technicalCondition.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.technicalCondition.workbag')">
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
                <router-link
                  :to="{ name: 'TechnicalConditionView', params: { technicalConditionId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TechnicalConditionEdit', params: { technicalConditionId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="technicalConditions">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.belongwbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.outsourcingcontractid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.technicalid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.technicalname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.changedfilename')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.applicant')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.applicationdate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.changedreason')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.changedbefore')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.changedafter')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.distributionrange')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.remarks')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.workbag')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="technicalCondition in technicalConditions"
                    :key="technicalCondition.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'TechnicalConditionView', params: {technicalConditionId: technicalCondition.id}}">{{technicalCondition.id}}</router-link>
                    </td>
                    <td>{{technicalCondition.workbagid}}</td>
                    <td>{{technicalCondition.belongwbsid}}</td>
                    <td>{{technicalCondition.outsourcingcontractid}}</td>
                    <td>{{technicalCondition.technicalid}}</td>
                    <td>{{technicalCondition.technicalname}}</td>
                    <td>{{technicalCondition.changedfilename}}</td>
                    <td>{{technicalCondition.applicant}}</td>
                    <td>{{technicalCondition.applicationdate}}</td>
                    <td>{{technicalCondition.changedreason}}</td>
                    <td>{{technicalCondition.changedbefore}}</td>
                    <td>{{technicalCondition.changedafter}}</td>
                    <td>{{technicalCondition.distributionrange}}</td>
                    <td>{{technicalCondition.remarks}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + technicalCondition.auditStatus)"></td>
                    <td>
                        <div v-if="technicalCondition.workbag">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: technicalCondition.workbag.id}}">{{technicalCondition.workbag.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'TechnicalConditionView', params: {technicalConditionId: technicalCondition.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'TechnicalConditionEdit', params: {technicalConditionId: technicalCondition.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(technicalCondition)"
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
          id="jy1App.technicalCondition.delete.question"
          data-cy="technicalConditionDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-technicalCondition-heading" v-text="t$('jy1App.technicalCondition.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-technicalCondition"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTechnicalCondition()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./technical-condition.component.ts"></script>
