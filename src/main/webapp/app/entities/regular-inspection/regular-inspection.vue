<template>
  <div>
    <h2 id="page-heading" data-cy="RegularInspectionHeading">
      <span v-text="t$('jy1App.regularInspection.home.title')" id="regular-inspection-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.regularInspection.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'RegularInspectionCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-regular-inspection"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.regularInspection.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && regularInspections && regularInspections.length === 0">
      <span v-text="t$('jy1App.regularInspection.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="regularInspections && regularInspections.length > 0">
      <el-table :data="regularInspections" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'RegularInspectionView', params: { regularInspectionId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.regularInspection.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagid"
          :label="t$('jy1App.regularInspection.workbagid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagname"
          :label="t$('jy1App.regularInspection.workbagname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagname }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="type" :label="t$('jy1App.regularInspection.type')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.type }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.regularInspection.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="standard"
          :label="t$('jy1App.regularInspection.standard')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.standard }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="measurementmethod"
          :label="t$('jy1App.regularInspection.measurementmethod')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.measurementmethod }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="checkresult"
          :label="t$('jy1App.regularInspection.checkresult')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.checkresult }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="checktarget"
          :label="t$('jy1App.regularInspection.checktarget')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.checktarget }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="checktime"
          :label="t$('jy1App.regularInspection.checktime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.checktime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="checkcompletion"
          :label="t$('jy1App.regularInspection.checkcompletion')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.checkcompletion }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="checkstatus"
          :label="t$('jy1App.regularInspection.checkstatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.checkstatus }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.regularInspection.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibleperson.id"
          :label="t$('jy1App.regularInspection.responsibleperson')"
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="designer.id" :label="t$('jy1App.regularInspection.designer')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.designer">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.designer.id } }">{{
                  scope.row.designer.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="checkperson.id" :label="t$('jy1App.regularInspection.checkperson')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.checkperson">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.checkperson.id } }">{{
                  scope.row.checkperson.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.regularInspection.workbag')">
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
                  :to="{ name: 'RegularInspectionView', params: { regularInspectionId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RegularInspectionEdit', params: { regularInspectionId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="regularInspections">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.workbagname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.type')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.standard')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.measurementmethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.checkresult')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.checktarget')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.checktime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.checkcompletion')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.checkstatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.responsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.designer')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.checkperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.regularInspection.workbag')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="regularInspection in regularInspections"
                    :key="regularInspection.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'RegularInspectionView', params: {regularInspectionId: regularInspection.id}}">{{regularInspection.id}}</router-link>
                    </td>
                    <td>{{regularInspection.name}}</td>
                    <td>{{regularInspection.workbagid}}</td>
                    <td>{{regularInspection.workbagname}}</td>
                    <td>{{regularInspection.type}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + regularInspection.secretlevel)"></td>
                    <td>{{regularInspection.standard}}</td>
                    <td>{{regularInspection.measurementmethod}}</td>
                    <td>{{regularInspection.checkresult}}</td>
                    <td>{{regularInspection.checktarget}}</td>
                    <td>{{regularInspection.checktime}}</td>
                    <td>{{regularInspection.checkcompletion}}</td>
                    <td>{{regularInspection.checkstatus}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + regularInspection.auditStatus)"></td>
                    <td>
                        <div v-if="regularInspection.responsibleperson">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: regularInspection.responsibleperson.id}}">{{regularInspection.responsibleperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="regularInspection.designer">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: regularInspection.designer.id}}">{{regularInspection.designer.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="regularInspection.checkperson">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: regularInspection.checkperson.id}}">{{regularInspection.checkperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="regularInspection.workbag">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: regularInspection.workbag.id}}">{{regularInspection.workbag.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'RegularInspectionView', params: {regularInspectionId: regularInspection.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'RegularInspectionEdit', params: {regularInspectionId: regularInspection.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(regularInspection)"
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
          id="jy1App.regularInspection.delete.question"
          data-cy="regularInspectionDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-regularInspection-heading" v-text="t$('jy1App.regularInspection.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-regularInspection"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRegularInspection()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./regular-inspection.component.ts"></script>
