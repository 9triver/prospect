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
          prop="workbagid"
          :label="t$('jy1App.unQualityAudit.workbagid')"
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
          :label="t$('jy1App.unQualityAudit.belongwbsid')"
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
          :label="t$('jy1App.unQualityAudit.outsourcingcontractid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.outsourcingcontractid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unqualityid"
          :label="t$('jy1App.unQualityAudit.unqualityid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unqualityid }}</span>
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
          prop="unqualityunit"
          :label="t$('jy1App.unQualityAudit.unqualityunit')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unqualityunit }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unqualitytrialgroup"
          :label="t$('jy1App.unQualityAudit.unqualitytrialgroup')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unqualitytrialgroup }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="inspector"
          :label="t$('jy1App.unQualityAudit.inspector')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.inspector }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unqualitystage"
          :label="t$('jy1App.unQualityAudit.unqualitystage')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unqualitystage }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unqualitynumber"
          :label="t$('jy1App.unQualityAudit.unqualitynumber')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unqualitynumber }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unqualityintroduction"
          :label="t$('jy1App.unQualityAudit.unqualityintroduction')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unqualityintroduction }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unqualitycategory"
          :label="t$('jy1App.unQualityAudit.unqualitycategory')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unqualitycategory }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="handlingopinion"
          :label="t$('jy1App.unQualityAudit.handlingopinion')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.handlingopinion }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="applicant"
          :label="t$('jy1App.unQualityAudit.applicant')"
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
          :label="t$('jy1App.unQualityAudit.applicationdate')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.applicationdate }}</span>
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
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="attachment"
          :label="t$('jy1App.unQualityAudit.attachment')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.attachment }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="disposalmethod"
          :label="t$('jy1App.unQualityAudit.disposalmethod')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.disposalmethod }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="causeanalysis"
          :label="t$('jy1App.unQualityAudit.causeanalysis')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.causeanalysis }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="correctivemeasures"
          :label="t$('jy1App.unQualityAudit.correctivemeasures')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.correctivemeasures }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="remarks"
          :label="t$('jy1App.unQualityAudit.remarks')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.remarks }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.unQualityAudit.workbag')">
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
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.belongwbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.outsourcingcontractid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualityid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualityname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualityunit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualitytrialgroup')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.inspector')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualitystage')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualitynumber')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualityintroduction')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.unqualitycategory')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.handlingopinion')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.applicant')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.applicationdate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.attachment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.disposalmethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.causeanalysis')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.correctivemeasures')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.remarks')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.unQualityAudit.workbag')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="unQualityAudit in unQualityAudits"
                    :key="unQualityAudit.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'UnQualityAuditView', params: {unQualityAuditId: unQualityAudit.id}}">{{unQualityAudit.id}}</router-link>
                    </td>
                    <td>{{unQualityAudit.workbagid}}</td>
                    <td>{{unQualityAudit.belongwbsid}}</td>
                    <td>{{unQualityAudit.outsourcingcontractid}}</td>
                    <td>{{unQualityAudit.unqualityid}}</td>
                    <td>{{unQualityAudit.unqualityname}}</td>
                    <td>{{unQualityAudit.unqualityunit}}</td>
                    <td>{{unQualityAudit.unqualitytrialgroup}}</td>
                    <td>{{unQualityAudit.inspector}}</td>
                    <td>{{unQualityAudit.unqualitystage}}</td>
                    <td>{{unQualityAudit.unqualitynumber}}</td>
                    <td>{{unQualityAudit.unqualityintroduction}}</td>
                    <td>{{unQualityAudit.unqualitycategory}}</td>
                    <td>{{unQualityAudit.handlingopinion}}</td>
                    <td>{{unQualityAudit.applicant}}</td>
                    <td>{{unQualityAudit.applicationdate}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + unQualityAudit.auditStatus)"></td>
                    <td>{{unQualityAudit.attachment}}</td>
                    <td>{{unQualityAudit.disposalmethod}}</td>
                    <td>{{unQualityAudit.causeanalysis}}</td>
                    <td>{{unQualityAudit.correctivemeasures}}</td>
                    <td>{{unQualityAudit.remarks}}</td>
                    <td>
                        <div v-if="unQualityAudit.workbag">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: unQualityAudit.workbag.id}}">{{unQualityAudit.workbag.id}}</router-link>
                        </div>
                    </td>
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
