<template>
  <div>
    <h2 id="page-heading" data-cy="IntegratedmanagementHeading">
      <span v-text="t$('jHipster0App.integratedmanagement.home.title')" id="integratedmanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.integratedmanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'IntegratedmanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-integratedmanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.integratedmanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && integratedmanagements && integratedmanagements.length === 0">
      <span v-text="t$('jHipster0App.integratedmanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="integratedmanagements && integratedmanagements.length > 0">
      <table class="table table-striped" aria-describedby="integratedmanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagement.name')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagement.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagement.wbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="integratedmanagement in integratedmanagements" :key="integratedmanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'IntegratedmanagementView', params: { integratedmanagementId: integratedmanagement.id } }">{{
                integratedmanagement.id
              }}</router-link>
            </td>
            <td>{{ integratedmanagement.name }}</td>
            <td>{{ integratedmanagement.description }}</td>
            <td>
              <div v-if="integratedmanagement.wbs">
                <router-link
                  :to="{ name: 'IntegratedmanagementWbsView', params: { integratedmanagementWbsId: integratedmanagement.wbs.id } }"
                  >{{ integratedmanagement.wbs.id }}</router-link
                >
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'IntegratedmanagementView', params: { integratedmanagementId: integratedmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'IntegratedmanagementEdit', params: { integratedmanagementId: integratedmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(integratedmanagement)"
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
          id="jHipster0App.integratedmanagement.delete.question"
          data-cy="integratedmanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-integratedmanagement-heading"
          v-text="t$('jHipster0App.integratedmanagement.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-integratedmanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeIntegratedmanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./integratedmanagement.component.ts"></script>
