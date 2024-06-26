<template>
  <div>
    <h2 id="page-heading" data-cy="SecuritymanagementHeading">
      <span v-text="t$('jHipster0App.securitymanagement.home.title')" id="securitymanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.securitymanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SecuritymanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-securitymanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.securitymanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && securitymanagements && securitymanagements.length === 0">
      <span v-text="t$('jHipster0App.securitymanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="securitymanagements && securitymanagements.length > 0">
      <table class="table table-striped" aria-describedby="securitymanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagement.name')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagement.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagement.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagement.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagement.wbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="securitymanagement in securitymanagements" :key="securitymanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SecuritymanagementView', params: { securitymanagementId: securitymanagement.id } }">{{
                securitymanagement.id
              }}</router-link>
            </td>
            <td>{{ securitymanagement.name }}</td>
            <td>{{ securitymanagement.description }}</td>
            <td>{{ securitymanagement.starttime }}</td>
            <td>{{ securitymanagement.endtime }}</td>
            <td>
              <div v-if="securitymanagement.wbs">
                <router-link :to="{ name: 'SecuritymanagementWbsView', params: { securitymanagementWbsId: securitymanagement.wbs.id } }">{{
                  securitymanagement.wbs.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SecuritymanagementView', params: { securitymanagementId: securitymanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SecuritymanagementEdit', params: { securitymanagementId: securitymanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(securitymanagement)"
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
          id="jHipster0App.securitymanagement.delete.question"
          data-cy="securitymanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-securitymanagement-heading" v-text="t$('jHipster0App.securitymanagement.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-securitymanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSecuritymanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./securitymanagement.component.ts"></script>
