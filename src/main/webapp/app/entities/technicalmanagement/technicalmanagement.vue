<template>
  <div>
    <h2 id="page-heading" data-cy="TechnicalmanagementHeading">
      <span v-text="t$('jHipster0App.technicalmanagement.home.title')" id="technicalmanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.technicalmanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'TechnicalmanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-technicalmanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.technicalmanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && technicalmanagements && technicalmanagements.length === 0">
      <span v-text="t$('jHipster0App.technicalmanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="technicalmanagements && technicalmanagements.length > 0">
      <table class="table table-striped" aria-describedby="technicalmanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagement.name')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagement.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagement.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagement.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagement.wbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="technicalmanagement in technicalmanagements" :key="technicalmanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TechnicalmanagementView', params: { technicalmanagementId: technicalmanagement.id } }">{{
                technicalmanagement.id
              }}</router-link>
            </td>
            <td>{{ technicalmanagement.name }}</td>
            <td>{{ technicalmanagement.description }}</td>
            <td>{{ technicalmanagement.starttime }}</td>
            <td>{{ technicalmanagement.endtime }}</td>
            <td>
              <div v-if="technicalmanagement.wbs">
                <router-link
                  :to="{ name: 'TechnicalmanagementWbsView', params: { technicalmanagementWbsId: technicalmanagement.wbs.id } }"
                  >{{ technicalmanagement.wbs.id }}</router-link
                >
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'TechnicalmanagementView', params: { technicalmanagementId: technicalmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TechnicalmanagementEdit', params: { technicalmanagementId: technicalmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(technicalmanagement)"
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
          id="jHipster0App.technicalmanagement.delete.question"
          data-cy="technicalmanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-technicalmanagement-heading"
          v-text="t$('jHipster0App.technicalmanagement.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-technicalmanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTechnicalmanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./technicalmanagement.component.ts"></script>
