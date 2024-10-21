<template>
  <div>
    <h2 id="page-heading" data-cy="DeviationPermitApplicationHeading">
      <span v-text="t$('jy1App.deviationPermitApplication.home.title')" id="deviation-permit-application-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.deviationPermitApplication.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'DeviationPermitApplicationCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-deviation-permit-application"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.deviationPermitApplication.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && deviationPermitApplications && deviationPermitApplications.length === 0">
      <span v-text="t$('jy1App.deviationPermitApplication.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="deviationPermitApplications && deviationPermitApplications.length > 0">
      <el-table :data="deviationPermitApplications" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'DeviationPermitApplicationView', params: { deviationPermitApplicationId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="wbsid"
          :label="t$('jy1App.deviationPermitApplication.wbsid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="technicalfileid"
          :label="t$('jy1App.deviationPermitApplication.technicalfileid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.technicalfileid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="applicationunit"
          :label="t$('jy1App.deviationPermitApplication.applicationunit')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.applicationunit }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="applicant"
          :label="t$('jy1App.deviationPermitApplication.applicant')"
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
          :label="t$('jy1App.deviationPermitApplication.applicationdate')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.applicationdate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="permitcontent"
          :label="t$('jy1App.deviationPermitApplication.permitcontent')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.permitcontent }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="permitreason"
          :label="t$('jy1App.deviationPermitApplication.permitreason')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.permitreason }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectinfluence"
          :label="t$('jy1App.deviationPermitApplication.projectinfluence')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectinfluence }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractinfluence"
          :label="t$('jy1App.deviationPermitApplication.contractinfluence')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractinfluence }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="permitrange"
          :label="t$('jy1App.deviationPermitApplication.permitrange')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.permitrange }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="implementationdate"
          :label="t$('jy1App.deviationPermitApplication.implementationdate')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.implementationdate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="remarks"
          :label="t$('jy1App.deviationPermitApplication.remarks')"
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
          :label="t$('jy1App.deviationPermitApplication.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectwbs.id"
          :label="t$('jy1App.deviationPermitApplication.projectwbs')"
        >
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
                  :to="{ name: 'DeviationPermitApplicationView', params: { deviationPermitApplicationId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'DeviationPermitApplicationEdit', params: { deviationPermitApplicationId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="deviationPermitApplications">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.technicalfileid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.applicationunit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.applicant')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.applicationdate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.permitcontent')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.permitreason')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.projectinfluence')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.contractinfluence')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.permitrange')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.implementationdate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.remarks')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deviationPermitApplication.projectwbs')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="deviationPermitApplication in deviationPermitApplications"
                    :key="deviationPermitApplication.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'DeviationPermitApplicationView', params: {deviationPermitApplicationId: deviationPermitApplication.id}}">{{deviationPermitApplication.id}}</router-link>
                    </td>
                    <td>{{deviationPermitApplication.wbsid}}</td>
                    <td>{{deviationPermitApplication.technicalfileid}}</td>
                    <td>{{deviationPermitApplication.applicationunit}}</td>
                    <td>{{deviationPermitApplication.applicant}}</td>
                    <td>{{deviationPermitApplication.applicationdate}}</td>
                    <td>{{deviationPermitApplication.permitcontent}}</td>
                    <td>{{deviationPermitApplication.permitreason}}</td>
                    <td>{{deviationPermitApplication.projectinfluence}}</td>
                    <td>{{deviationPermitApplication.contractinfluence}}</td>
                    <td>{{deviationPermitApplication.permitrange}}</td>
                    <td>{{deviationPermitApplication.implementationdate}}</td>
                    <td>{{deviationPermitApplication.remarks}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + deviationPermitApplication.auditStatus)"></td>
                    <td>
                        <div v-if="deviationPermitApplication.projectwbs">
                            <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: deviationPermitApplication.projectwbs.id}}">{{deviationPermitApplication.projectwbs.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DeviationPermitApplicationView', params: {deviationPermitApplicationId: deviationPermitApplication.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'DeviationPermitApplicationEdit', params: {deviationPermitApplicationId: deviationPermitApplication.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(deviationPermitApplication)"
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
          id="jy1App.deviationPermitApplication.delete.question"
          data-cy="deviationPermitApplicationDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-deviationPermitApplication-heading"
          v-text="t$('jy1App.deviationPermitApplication.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-deviationPermitApplication"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeDeviationPermitApplication()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./deviation-permit-application.component.ts"></script>
