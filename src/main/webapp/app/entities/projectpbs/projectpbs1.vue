<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectpbsHeading">
      <span v-text="t$('jy1App.projectpbs.home.title')" id="projectpbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectpbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectpbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectpbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.projectpbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectpbs && projectpbs.length === 0">
      <span v-text="t$('jy1App.projectpbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectpbs && projectpbs.length > 0">
      <table class="table table-striped" aria-describedby="projectpbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.pbsname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.parentpbsid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.description')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.progress')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.type')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.priorty')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.status')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.wbsid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.workbag')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.department')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectpbs.project')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectpbs in projectpbs" :key="projectpbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectpbsView', params: { projectpbsId: projectpbs.id } }">{{ projectpbs.id }}</router-link>
            </td>
            <td>{{ projectpbs.pbsname }}</td>
            <td>{{ projectpbs.parentpbsid }}</td>
            <td>{{ projectpbs.description }}</td>
            <td>{{ projectpbs.starttime }}</td>
            <td>{{ projectpbs.endtime }}</td>
            <td>{{ projectpbs.progress }}</td>
            <td>{{ projectpbs.type }}</td>
            <td>{{ projectpbs.priorty }}</td>
            <td v-text="t$('jy1App.Secretlevel.' + projectpbs.secretlevel)"></td>
            <td v-text="t$('jy1App.ProjectStatus.' + projectpbs.status)"></td>
            <td v-text="t$('jy1App.AuditStatus.' + projectpbs.auditStatus)"></td>
            <td>{{ projectpbs.wbsid }}</td>
            <td>{{ projectpbs.workbag }}</td>
            <td>
              <div v-if="projectpbs.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.responsibleid.id } }">{{
                  projectpbs.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectpbs.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.auditorid.id } }">{{
                  projectpbs.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectpbs.department">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectpbs.department.id } }">{{
                  projectpbs.department.id
                }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(project, i) in projectpbs.projects" :key="project.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectView', params: { projectId: project.id } }">{{
                  project.id
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProjectpbsView', params: { projectpbsId: projectpbs.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProjectpbsEdit', params: { projectpbsId: projectpbs.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(projectpbs)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
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
