<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectHeading">
      <span v-text="t$('jHipster0App.project.home.title')" id="project-heading"></span>
      <div>
        <router-link :to="{ name: 'ProjectGantt' }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
            <font-awesome-icon icon="eye"></font-awesome-icon>
            <span>甘特图</span>
          </button>
        </router-link>
      </div>

      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.project.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.project.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="table-responsive" v-if="projects && projects.length > 0">
      <table class="table table-striped" aria-describedby="projects">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.projectid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.number')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.projecttype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.responsiblename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.duedate')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.priorty')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.projectSecrecy')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.project.projectwbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="project in projects" :key="project.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectView', params: { projectId: project.id } }">{{ project.id }}</router-link>
            </td>
            <td>{{ project.projectid }}</td>
            <td>{{ project.projectname }}</td>
            <td>{{ project.description }}</td>
            <td>{{ project.number }}</td>
            <td>{{ project.projecttype }}</td>
            <td>
              <div v-if="project.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: project.responsibleid.id } }">
                  <span>{{ project.responsiblename }}</span>
                </router-link>
              </div>
            </td>
            <td>{{ project.duedate }}</td>
            <td>{{ project.priorty }}</td>
            <td v-text="t$('jHipster0App.ProjectStatus.' + project.status)"></td>
            <td>
              <div v-if="project.projectSecrecy">
                <router-link :to="{ name: 'ProjectSecrecyView', params: { projectSecrecyId: project.projectSecrecy.id } }">
                  <span>{{ project.projectSecrecyname }}</span>
                </router-link>
              </div>
            </td>
            <td v-text="t$('jHipster0App.AuditStatus.' + project.auditStatus)"></td>
            <td>
              <div v-if="project.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: project.auditorid.id } }">
                  <span>{{ project.auditorname }}</span>
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="project.projectwbs">
                <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: project.projectwbs.id } }">
                  <span>{{ project.projectwbsname }}</span>
                </router-link>
              </div>
              <div v-if="!project.projectwbs">
                <router-link :to="{ name: 'ProjectwbsCreateNew' }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">WBS创建</span>
                  </button>
                </router-link>
              </div>
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
        <span id="jHipster0App.project.delete.question" data-cy="projectDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-project-heading" v-text="t$('jHipster0App.project.delete.question', { id: removeId })"></p>
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
