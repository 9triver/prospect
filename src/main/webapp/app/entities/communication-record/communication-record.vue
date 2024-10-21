<template>
  <div>
    <h2 id="page-heading" data-cy="CommunicationRecordHeading">
      <span v-text="t$('jy1App.communicationRecord.home.title')" id="communication-record-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.communicationRecord.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'CommunicationRecordCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-communication-record"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.communicationRecord.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && communicationRecords && communicationRecords.length === 0">
      <span v-text="t$('jy1App.communicationRecord.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="communicationRecords && communicationRecords.length > 0">
      <el-table :data="communicationRecords" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'CommunicationRecordView', params: { communicationRecordId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="wbsid"
          :label="t$('jy1App.communicationRecord.wbsid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="wbsname"
          :label="t$('jy1App.communicationRecord.wbsname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagid"
          :label="t$('jy1App.communicationRecord.workbagid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="associationmeetingname"
          :label="t$('jy1App.communicationRecord.associationmeetingname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.associationmeetingname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="communicationtime"
          :label="t$('jy1App.communicationRecord.communicationtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.communicationtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="communicationlocation"
          :label="t$('jy1App.communicationRecord.communicationlocation')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.communicationlocation }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="communicationcontent"
          :label="t$('jy1App.communicationRecord.communicationcontent')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.communicationcontent }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditorid"
          :label="t$('jy1App.communicationRecord.auditorid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.auditorid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditorname"
          :label="t$('jy1App.communicationRecord.auditorname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.auditorname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="remarks"
          :label="t$('jy1App.communicationRecord.remarks')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.remarks }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="projectwbs.id" :label="t$('jy1App.communicationRecord.projectwbs')">
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.communicationRecord.workbag')">
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
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="communication.id"
          :label="t$('jy1App.communicationRecord.communication')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.communication">
                <router-link
                  :to="{ name: 'CommunicationDictionaryView', params: { communicationDictionaryId: scope.row.communication.id } }"
                  >{{ scope.row.communication.id }}</router-link
                >
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workCommunicationForm.id"
          :label="t$('jy1App.communicationRecord.workCommunicationForm')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.workCommunicationForm">
                <router-link
                  :to="{
                    name: 'CommunicationFormDictionaryView',
                    params: { communicationFormDictionaryId: scope.row.workCommunicationForm.id },
                  }"
                  >{{ scope.row.workCommunicationForm.id }}</router-link
                >
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'CommunicationRecordView', params: { communicationRecordId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'CommunicationRecordEdit', params: { communicationRecordId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="communicationRecords">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.wbsname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.associationmeetingname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.communicationtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.communicationlocation')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.communicationcontent')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.auditorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.auditorname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.remarks')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.projectwbs')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.workbag')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.communication')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationRecord.workCommunicationForm')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="communicationRecord in communicationRecords"
                    :key="communicationRecord.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'CommunicationRecordView', params: {communicationRecordId: communicationRecord.id}}">{{communicationRecord.id}}</router-link>
                    </td>
                    <td>{{communicationRecord.wbsid}}</td>
                    <td>{{communicationRecord.wbsname}}</td>
                    <td>{{communicationRecord.workbagid}}</td>
                    <td>{{communicationRecord.associationmeetingname}}</td>
                    <td>{{communicationRecord.communicationtime}}</td>
                    <td>{{communicationRecord.communicationlocation}}</td>
                    <td>{{communicationRecord.communicationcontent}}</td>
                    <td>{{communicationRecord.auditorid}}</td>
                    <td>{{communicationRecord.auditorname}}</td>
                    <td>{{communicationRecord.remarks}}</td>
                    <td>
                        <div v-if="communicationRecord.projectwbs">
                            <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: communicationRecord.projectwbs.id}}">{{communicationRecord.projectwbs.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="communicationRecord.workbag">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: communicationRecord.workbag.id}}">{{communicationRecord.workbag.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="communicationRecord.communication">
                            <router-link :to="{name: 'CommunicationDictionaryView', params: {communicationDictionaryId: communicationRecord.communication.id}}">{{communicationRecord.communication.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="communicationRecord.workCommunicationForm">
                            <router-link :to="{name: 'CommunicationFormDictionaryView', params: {communicationFormDictionaryId: communicationRecord.workCommunicationForm.id}}">{{communicationRecord.workCommunicationForm.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CommunicationRecordView', params: {communicationRecordId: communicationRecord.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'CommunicationRecordEdit', params: {communicationRecordId: communicationRecord.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(communicationRecord)"
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
          id="jy1App.communicationRecord.delete.question"
          data-cy="communicationRecordDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-communicationRecord-heading" v-text="t$('jy1App.communicationRecord.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-communicationRecord"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeCommunicationRecord()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./communication-record.component.ts"></script>
