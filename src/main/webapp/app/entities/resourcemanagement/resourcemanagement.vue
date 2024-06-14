<template>
  <div>
    <h2 id="page-heading" data-cy="ResourcemanagementHeading">
      <span v-text="t$('jHipster3App.resourcemanagement.home.title')" id="resourcemanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.resourcemanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ResourcemanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-resourcemanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.resourcemanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && resourcemanagements && resourcemanagements.length === 0">
      <span v-text="t$('jHipster3App.resourcemanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="resourcemanagements && resourcemanagements.length > 0">
      <table class="table table-striped" aria-describedby="resourcemanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.resourcemanagement.resourceid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.resourcemanagement.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.resourcemanagement.clientname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.resourcemanagement.plandate')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.resourcemanagement.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.resourcemanagement.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.resourcemanagement.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.resourcemanagement.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.resourcemanagement.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="resourcemanagement in resourcemanagements" :key="resourcemanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ResourcemanagementView', params: { resourcemanagementId: resourcemanagement.id } }">{{
                resourcemanagement.id
              }}</router-link>
            </td>
            <td>{{ resourcemanagement.resourceid }}</td>
            <td>{{ resourcemanagement.projectname }}</td>
            <td>{{ resourcemanagement.clientname }}</td>
            <td>{{ resourcemanagement.plandate }}</td>
            <td>{{ resourcemanagement.creatorname }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + resourcemanagement.secretlevel)"></td>
            <td v-text="t$('jHipster3App.AuditStatus.' + resourcemanagement.auditStatus)"></td>
            <td>
              <div v-if="resourcemanagement.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: resourcemanagement.creatorid.id } }">{{
                  resourcemanagement.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="resourcemanagement.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: resourcemanagement.auditorid.id } }">{{
                  resourcemanagement.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ResourcemanagementView', params: { resourcemanagementId: resourcemanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ResourcemanagementEdit', params: { resourcemanagementId: resourcemanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(resourcemanagement)"
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
          id="jHipster3App.resourcemanagement.delete.question"
          data-cy="resourcemanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-resourcemanagement-heading" v-text="t$('jHipster3App.resourcemanagement.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-resourcemanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeResourcemanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./resourcemanagement.component.ts"></script>
