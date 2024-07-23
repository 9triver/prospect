<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectHeading">
      <span v-text="t$('jy1App.project.home.title')" id="project-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.project.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.project.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projects && projects.length === 0">
      <span v-text="t$('jy1App.project.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projects && projects.length > 0">
      <table class="table table-striped" aria-describedby="projects">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.parentid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.pbsid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.description')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.number')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.projecttype')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.priorty')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.createdate')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.status')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.progress')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.projectpbs')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.project.projectwbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="project in projects" :key="project.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectView', params: { projectId: project.id } }">{{ project.id }}</router-link>
            </td>
            <td>{{ project.projectname }}</td>
            <td>{{ project.parentid }}</td>
            <td>{{ project.pbsid }}</td>
            <td>{{ project.description }}</td>
            <td>{{ project.number }}</td>
            <td>{{ project.projecttype }}</td>
            <td>{{ project.priorty }}</td>
            <td>{{ project.createdate }}</td>
            <td v-text="t$('jy1App.Secretlevel.' + project.secretlevel)"></td>
            <td v-text="t$('jy1App.ProjectStatus.' + project.status)"></td>
            <td v-text="t$('jy1App.AuditStatus.' + project.auditStatus)"></td>
            <td>{{ project.progress }}</td>
            <td>
              <span v-for="(projectpbs, i) in project.projectpbs" :key="projectpbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectpbsView', params: { projectpbsId: projectpbs.id } }">{{
                  projectpbs.id
                }}</router-link>
              </span>
            </td>
            <td>
              <span v-for="(projectwbs, i) in project.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProjectView', params: { projectId: project.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProjectEdit', params: { projectId: project.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(project)"
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
        <span id="jy1App.project.delete.question" data-cy="projectDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-project-heading" v-text="t$('jy1App.project.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-project"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProject()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./project.component.ts"></script>
