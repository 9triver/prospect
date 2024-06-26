<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectHumanresourcesplanHeading">
      <span v-text="t$('jHipster0App.projectHumanresourcesplan.home.title')" id="project-humanresourcesplan-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.projectHumanresourcesplan.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectHumanresourcesplanCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project-humanresourcesplan"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.projectHumanresourcesplan.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectHumanresourcesplans && projectHumanresourcesplans.length === 0">
      <span v-text="t$('jHipster0App.projectHumanresourcesplan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectHumanresourcesplans && projectHumanresourcesplans.length > 0">
      <table class="table table-striped" aria-describedby="projectHumanresourcesplans">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectHumanresourcesplan.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectHumanresourcesplan.clientname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectHumanresourcesplan.plandate')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectHumanresourcesplan.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectHumanresourcesplan.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectHumanresourcesplan.project')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectHumanresourcesplan in projectHumanresourcesplans" :key="projectHumanresourcesplan.id" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'ProjectHumanresourcesplanView', params: { projectHumanresourcesplanId: projectHumanresourcesplan.id } }"
                >{{ projectHumanresourcesplan.id }}</router-link
              >
            </td>
            <td>{{ projectHumanresourcesplan.projectname }}</td>
            <td>{{ projectHumanresourcesplan.clientname }}</td>
            <td>{{ projectHumanresourcesplan.plandate }}</td>
            <td v-text="t$('jHipster0App.Secretlevel.' + projectHumanresourcesplan.secretlevel)"></td>
            <td v-text="t$('jHipster0App.AuditStatus.' + projectHumanresourcesplan.auditStatus)"></td>
            <td>
              <div v-if="projectHumanresourcesplan.project">
                <router-link :to="{ name: 'ProjectView', params: { projectId: projectHumanresourcesplan.project.id } }">{{
                  projectHumanresourcesplan.project.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProjectHumanresourcesplanView', params: { projectHumanresourcesplanId: projectHumanresourcesplan.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProjectHumanresourcesplanEdit', params: { projectHumanresourcesplanId: projectHumanresourcesplan.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(projectHumanresourcesplan)"
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
        <span
          id="jHipster0App.projectHumanresourcesplan.delete.question"
          data-cy="projectHumanresourcesplanDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-projectHumanresourcesplan-heading"
          v-text="t$('jHipster0App.projectHumanresourcesplan.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-projectHumanresourcesplan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProjectHumanresourcesplan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./project-humanresourcesplan.component.ts"></script>
