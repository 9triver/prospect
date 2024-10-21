<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectpbsHeading">
      <span v-text="t$('jy1App.projectpbs.home.title')" id="projectpbs-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectpbs.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'ProjectpbsCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectpbs"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.projectpbs.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectpbs && projectpbs.length === 0">
      <span v-text="t$('jy1App.projectpbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectpbs && projectpbs.length > 0">
      <el-table :data="projectpbs" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'ProjectpbsView', params: { projectpbsId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="pbsname" :label="t$('jy1App.projectpbs.pbsname')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.pbsname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="parentpbsid"
          :label="t$('jy1App.projectpbs.parentpbsid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.parentpbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.projectpbs.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="starttime"
          :label="t$('jy1App.projectpbs.starttime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.starttime }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="endtime" :label="t$('jy1App.projectpbs.endtime')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.endtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="productlevel"
          :label="t$('jy1App.projectpbs.productlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.productlevel }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="iskey" :label="t$('jy1App.projectpbs.iskey')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.iskey }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="isimportant"
          :label="t$('jy1App.projectpbs.isimportant')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.isimportant }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="description"
          :label="t$('jy1App.projectpbs.description')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="progress" :label="t$('jy1App.projectpbs.progress')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.progress }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="type" :label="t$('jy1App.projectpbs.type')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.type }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="priorty" :label="t$('jy1App.projectpbs.priorty')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.priorty }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid" :label="t$('jy1App.projectpbs.wbsid')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="status" :label="t$('jy1App.projectpbs.status')" :sortable="false">
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.ProjectStatus.' + scope.row.status)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.projectpbs.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="technicaldirector.id"
          :label="t$('jy1App.projectpbs.technicaldirector')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.technicaldirector">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.technicaldirector.id } }">{{
                  scope.row.technicaldirector.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="administrativedirector.id"
          :label="t$('jy1App.projectpbs.administrativedirector')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.administrativedirector">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.administrativedirector.id } }">{{
                  scope.row.administrativedirector.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="knowingpeople.id" :label="t$('jy1App.projectpbs.knowingpeople')">
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="auditorid.id" :label="t$('jy1App.projectpbs.auditorid')">
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
          :label="t$('jy1App.projectpbs.responsibledepartment')"
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
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="relevantdepartment.id"
          :label="t$('jy1App.projectpbs.relevantdepartment')"
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="project.id" :label="t$('jy1App.projectpbs.project')">
          <template #default="scope">
            <td>
              <span v-for="(project, i) in scope.row.projects" :key="project.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectView', params: { projectId: project.id } }">{{
                  project.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProjectpbsView', params: { projectpbsId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProjectpbsEdit', params: { projectpbsId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="projectpbs">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.pbsname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.parentpbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.starttime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.endtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.productlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.iskey')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.isimportant')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.description')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.progress')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.type')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.priorty')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.technicaldirector')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.administrativedirector')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.knowingpeople')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.auditorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.responsibledepartment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.relevantdepartment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.projectpbs.project')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="projectpbs in projectpbs"
                    :key="projectpbs.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'ProjectpbsView', params: {projectpbsId: projectpbs.id}}">{{projectpbs.id}}</router-link>
                    </td>
                    <td>{{projectpbs.pbsname}}</td>
                    <td>{{projectpbs.parentpbsid}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + projectpbs.secretlevel)"></td>
                    <td>{{projectpbs.starttime}}</td>
                    <td>{{projectpbs.endtime}}</td>
                    <td>{{projectpbs.productlevel}}</td>
                    <td>{{projectpbs.iskey}}</td>
                    <td>{{projectpbs.isimportant}}</td>
                    <td>{{projectpbs.description}}</td>
                    <td>{{projectpbs.progress}}</td>
                    <td>{{projectpbs.type}}</td>
                    <td>{{projectpbs.priorty}}</td>
                    <td>{{projectpbs.wbsid}}</td>
                    <td v-text="t$('jy1App.ProjectStatus.' + projectpbs.status)"></td>
                    <td v-text="t$('jy1App.AuditStatus.' + projectpbs.auditStatus)"></td>
                    <td>
                        <div v-if="projectpbs.technicaldirector">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: projectpbs.technicaldirector.id}}">{{projectpbs.technicaldirector.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectpbs.administrativedirector">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: projectpbs.administrativedirector.id}}">{{projectpbs.administrativedirector.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectpbs.knowingpeople">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: projectpbs.knowingpeople.id}}">{{projectpbs.knowingpeople.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectpbs.auditorid">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: projectpbs.auditorid.id}}">{{projectpbs.auditorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="projectpbs.responsibledepartment">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: projectpbs.responsibledepartment.id}}">{{projectpbs.responsibledepartment.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(relevantdepartment, i) in projectpbs.relevantdepartments" :key="relevantdepartment.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'DepartmentView', params: {departmentId: relevantdepartment.id}}">{{relevantdepartment.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(project, i) in projectpbs.projects" :key="project.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectView', params: {projectId: project.id}}">{{project.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ProjectpbsView', params: {projectpbsId: projectpbs.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'ProjectpbsEdit', params: {projectpbsId: projectpbs.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(projectpbs)"
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
        <span id="jy1App.projectpbs.delete.question" data-cy="projectpbsDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-projectpbs-heading" v-text="t$('jy1App.projectpbs.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-projectpbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProjectpbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./projectpbs.component.ts"></script>
