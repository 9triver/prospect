<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectSecrecyHeading">
      <span v-text="t$('jHipster0App.projectSecrecy.home.title')" id="project-secrecy-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.projectSecrecy.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectSecrecyCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project-secrecy"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.projectSecrecy.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectSecrecies && projectSecrecies.length === 0">
      <span v-text="t$('jHipster0App.projectSecrecy.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectSecrecies && projectSecrecies.length > 0">
      <table class="table table-striped" aria-describedby="projectSecrecies">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectSecrecy.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectSecrecy.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectSecrecy.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectSecrecy.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectSecrecy.secrecysystem')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectSecrecy.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectSecrecy.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectSecrecy.projectid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectSecrecy in projectSecrecies" :key="projectSecrecy.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectSecrecyView', params: { projectSecrecyId: projectSecrecy.id } }">{{
                projectSecrecy.id
              }}</router-link>
            </td>
            <td>{{ projectSecrecy.projectname }}</td>
            <td>{{ projectSecrecy.description }}</td>
            <td>{{ projectSecrecy.createtime }}</td>
            <td v-text="t$('jHipster0App.AuditStatus.' + projectSecrecy.auditStatus)"></td>
            <td>
              <div v-if="projectSecrecy.secrecysystem">
                <router-link :to="{ name: 'SecrecysystemView', params: { secrecysystemId: projectSecrecy.secrecysystem.id } }">{{
                  projectSecrecy.secrecysystem.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectSecrecy.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectSecrecy.creatorid.id } }">{{
                  projectSecrecy.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectSecrecy.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectSecrecy.auditorid.id } }">{{
                  projectSecrecy.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectSecrecy.projectid">
                <router-link :to="{ name: 'ProjectView', params: { projectId: projectSecrecy.projectid.id } }">{{
                  projectSecrecy.projectid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProjectSecrecyView', params: { projectSecrecyId: projectSecrecy.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProjectSecrecyEdit', params: { projectSecrecyId: projectSecrecy.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(projectSecrecy)"
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
          id="jHipster0App.projectSecrecy.delete.question"
          data-cy="projectSecrecyDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-projectSecrecy-heading" v-text="t$('jHipster0App.projectSecrecy.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-projectSecrecy"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProjectSecrecy()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./project-secrecy.component.ts"></script>
