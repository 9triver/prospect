<template>
  <div>
    <h2 id="page-heading" data-cy="HumanresourcesHeading">
      <span v-text="t$('jHipster3App.humanresources.home.title')" id="humanresources-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.humanresources.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'HumanresourcesCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-humanresources"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.humanresources.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && humanresources && humanresources.length === 0">
      <span v-text="t$('jHipster3App.humanresources.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="humanresources && humanresources.length > 0">
      <table class="table table-striped" aria-describedby="humanresources">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.humanresourcesid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.name')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.outdeportment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.indeportment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.adjusttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.deportment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.projectleader')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.project')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.humanresources.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="humanresources in humanresources" :key="humanresources.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'HumanresourcesView', params: { humanresourcesId: humanresources.id } }">{{
                humanresources.id
              }}</router-link>
            </td>
            <td>{{ humanresources.humanresourcesid }}</td>
            <td>{{ humanresources.name }}</td>
            <td>{{ humanresources.outdeportment }}</td>
            <td>{{ humanresources.indeportment }}</td>
            <td>{{ humanresources.adjusttime }}</td>
            <td>{{ humanresources.projectname }}</td>
            <td>{{ humanresources.deportment }}</td>
            <td>{{ humanresources.projectleader }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + humanresources.secretlevel)"></td>
            <td v-text="t$('jHipster3App.AuditStatus.' + humanresources.auditStatus)"></td>
            <td>
              <div v-if="humanresources.project">
                <router-link :to="{ name: 'ProjectView', params: { projectId: humanresources.project.id } }">{{
                  humanresources.project.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="humanresources.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: humanresources.creatorid.id } }">{{
                  humanresources.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="humanresources.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: humanresources.auditorid.id } }">{{
                  humanresources.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'HumanresourcesView', params: { humanresourcesId: humanresources.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'HumanresourcesEdit', params: { humanresourcesId: humanresources.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(humanresources)"
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
          id="jHipster3App.humanresources.delete.question"
          data-cy="humanresourcesDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-humanresources-heading" v-text="t$('jHipster3App.humanresources.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-humanresources"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeHumanresources()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./humanresources.component.ts"></script>
