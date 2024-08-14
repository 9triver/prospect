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
          prop="caption"
          :label="t$('jy1App.technicalCondition.caption')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.caption }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectname"
          :label="t$('jy1App.technicalCondition.projectname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="decumentid"
          :label="t$('jy1App.technicalCondition.decumentid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.decumentid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="claimant"
          :label="t$('jy1App.technicalCondition.claimant')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.claimant }}</span>
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
          prop="applicanttime"
          :label="t$('jy1App.technicalCondition.applicanttime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.applicanttime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="validrange"
          :label="t$('jy1App.technicalCondition.validrange')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.validrange }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="createtime"
          :label="t$('jy1App.technicalCondition.createtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.createtime }}</span>
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="creatorid.id" :label="t$('jy1App.technicalCondition.creatorid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: scope.row.creatorid.id } }">{{
                  scope.row.creatorid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="auditorid.id" :label="t$('jy1App.technicalCondition.auditorid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: scope.row.auditorid.id } }">{{
                  scope.row.auditorid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="projectwbs.id" :label="t$('jy1App.technicalCondition.projectwbs')">
          <template #default="scope">
            <td>
              <span v-for="(projectwbs, i) in scope.row.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
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
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.caption')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.projectname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.decumentid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.claimant')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.applicant')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.applicanttime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.validrange')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.createtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.creatorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.auditorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.technicalCondition.projectwbs')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="technicalCondition in technicalConditions"
                    :key="technicalCondition.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'TechnicalConditionView', params: {technicalConditionId: technicalCondition.id}}">{{technicalCondition.id}}</router-link>
                    </td>
                    <td>{{technicalCondition.caption}}</td>
                    <td>{{technicalCondition.projectname}}</td>
                    <td>{{technicalCondition.decumentid}}</td>
                    <td>{{technicalCondition.claimant}}</td>
                    <td>{{technicalCondition.applicant}}</td>
                    <td>{{technicalCondition.applicanttime}}</td>
                    <td>{{technicalCondition.validrange}}</td>
                    <td>{{technicalCondition.createtime}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + technicalCondition.auditStatus)"></td>
                    <td>
                        <div v-if="technicalCondition.creatorid">
                            <router-link :to="{name: 'OfficersView', params: {officersId: technicalCondition.creatorid.id}}">{{technicalCondition.creatorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="technicalCondition.auditorid">
                            <router-link :to="{name: 'OfficersView', params: {officersId: technicalCondition.auditorid.id}}">{{technicalCondition.auditorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(projectwbs, i) in technicalCondition.projectwbs" :key="projectwbs.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectwbsView', params: {projectwbsId: projectwbs.id}}">{{projectwbs.id}}</router-link>
                        </span>
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
