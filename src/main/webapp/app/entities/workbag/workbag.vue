<template>
  <div>
    <h2 id="page-heading" data-cy="WorkbagHeading">
      <span v-text="t$('jy1App.workbag.home.title')" id="workbag-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.workbag.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'WorkbagCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-workbag"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.workbag.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && workbags && workbags.length === 0">
      <span v-text="t$('jy1App.workbag.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="workbags && workbags.length > 0">
      <el-table :data="workbags" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'WorkbagView', params: { workbagId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.workbag.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="pbsid" :label="t$('jy1App.workbag.pbsid')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.pbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagtype"
          :label="t$('jy1App.workbag.workbagtype')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagtype }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="supplier" :label="t$('jy1App.workbag.supplier')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.supplier }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="iskeyimportant"
          :label="t$('jy1App.workbag.iskeyimportant')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.iskeyimportant }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="keypbsname"
          :label="t$('jy1App.workbag.keypbsname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.keypbsname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="importantpbsname"
          :label="t$('jy1App.workbag.importantpbsname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.importantpbsname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.workbag.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="description"
          :label="t$('jy1App.workbag.description')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="starttime" :label="t$('jy1App.workbag.starttime')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.starttime }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="endtime" :label="t$('jy1App.workbag.endtime')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.endtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="estimatedpurchasingtime"
          :label="t$('jy1App.workbag.estimatedpurchasingtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.estimatedpurchasingtime }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="progress" :label="t$('jy1App.workbag.progress')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.progress }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="issafetywork"
          :label="t$('jy1App.workbag.issafetywork')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.issafetywork }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="remark" :label="t$('jy1App.workbag.remark')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.workbag.auditStatus')"
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
          :label="t$('jy1App.workbag.responsibleperson')"
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="projectmanager.id" :label="t$('jy1App.workbag.projectmanager')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.projectmanager">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.projectmanager.id } }">{{
                  scope.row.projectmanager.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="knowingpeople.id" :label="t$('jy1App.workbag.knowingpeople')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.knowingpeople">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.knowingpeople.id } }">{{
                  scope.row.knowingpeople.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="auditorid.id" :label="t$('jy1App.workbag.auditorid')">
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
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibledepartment.id"
          :label="t$('jy1App.workbag.responsibledepartment')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.responsibledepartment">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: scope.row.responsibledepartment.id } }">{{
                  scope.row.responsibledepartment.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="department.id" :label="t$('jy1App.workbag.department')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.department">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: scope.row.department.id } }">{{
                  scope.row.department.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectdeliverables.id"
          :label="t$('jy1App.workbag.projectdeliverables')"
        >
          <template #default="scope">
            <td>
              <span v-for="(projectdeliverables, i) in scope.row.projectdeliverables" :key="projectdeliverables.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'ProjectdeliverablesView', params: { projectdeliverablesId: projectdeliverables.id } }"
                  >{{ projectdeliverables.id }}</router-link
                >
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="relevantdepartment.id"
          :label="t$('jy1App.workbag.relevantdepartment')"
        >
          <template #default="scope">
            <td>
              <span v-for="(relevantdepartment, i) in scope.row.relevantdepartments" :key="relevantdepartment.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'DepartmentView', params: { departmentId: relevantdepartment.id } }"
                  >{{ relevantdepartment.id }}</router-link
                >
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid.id" :label="t$('jy1App.workbag.wbsid')">
          <template #default="scope">
            <td>
              <span v-for="(wbsid, i) in scope.row.wbsids" :key="wbsid.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: wbsid.id } }">{{
                  wbsid.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="work.id" :label="t$('jy1App.workbag.work')">
          <template #default="scope">
            <td>
              <span v-for="(work, i) in scope.row.works" :key="work.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'WorkView', params: { workId: work.id } }">{{ work.id }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'WorkbagView', params: { workbagId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'WorkbagEdit', params: { workbagId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="workbags">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.pbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.workbagtype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.supplier')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.iskeyimportant')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.keypbsname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.importantpbsname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.description')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.starttime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.endtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.estimatedpurchasingtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.progress')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.issafetywork')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.remark')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.responsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.projectmanager')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.knowingpeople')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.auditorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.responsibledepartment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.department')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.projectdeliverables')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.relevantdepartment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.workbag.work')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="workbag in workbags"
                    :key="workbag.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'WorkbagView', params: {workbagId: workbag.id}}">{{workbag.id}}</router-link>
                    </td>
                    <td>{{workbag.name}}</td>
                    <td>{{workbag.pbsid}}</td>
                    <td>{{workbag.workbagtype}}</td>
                    <td>{{workbag.supplier}}</td>
                    <td>{{workbag.iskeyimportant}}</td>
                    <td>{{workbag.keypbsname}}</td>
                    <td>{{workbag.importantpbsname}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + workbag.secretlevel)"></td>
                    <td>{{workbag.description}}</td>
                    <td>{{workbag.starttime}}</td>
                    <td>{{workbag.endtime}}</td>
                    <td>{{workbag.estimatedpurchasingtime}}</td>
                    <td>{{workbag.progress}}</td>
                    <td>{{workbag.issafetywork}}</td>
                    <td>{{workbag.remark}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + workbag.auditStatus)"></td>
                    <td>
                        <div v-if="workbag.responsibleperson">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: workbag.responsibleperson.id}}">{{workbag.responsibleperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="workbag.projectmanager">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: workbag.projectmanager.id}}">{{workbag.projectmanager.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="workbag.knowingpeople">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: workbag.knowingpeople.id}}">{{workbag.knowingpeople.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="workbag.auditorid">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: workbag.auditorid.id}}">{{workbag.auditorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="workbag.responsibledepartment">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: workbag.responsibledepartment.id}}">{{workbag.responsibledepartment.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="workbag.department">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: workbag.department.id}}">{{workbag.department.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(projectdeliverables, i) in workbag.projectdeliverables" :key="projectdeliverables.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectdeliverablesView', params: {projectdeliverablesId: projectdeliverables.id}}">{{projectdeliverables.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(relevantdepartment, i) in workbag.relevantdepartments" :key="relevantdepartment.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'DepartmentView', params: {departmentId: relevantdepartment.id}}">{{relevantdepartment.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(wbsid, i) in workbag.wbsids" :key="wbsid.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectwbsView', params: {projectwbsId: wbsid.id}}">{{wbsid.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(work, i) in workbag.works" :key="work.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'WorkView', params: {workId: work.id}}">{{work.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: workbag.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'WorkbagEdit', params: {workbagId: workbag.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(workbag)"
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
        <span id="jy1App.workbag.delete.question" data-cy="workbagDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-workbag-heading" v-text="t$('jy1App.workbag.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-workbag"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeWorkbag()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./workbag.component.ts"></script>
