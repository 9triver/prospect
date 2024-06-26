<template>
  <div>
    <h2 id="page-heading" data-cy="FundsmanagementHeading">
      <span v-text="t$('jHipster0App.fundsmanagement.home.title')" id="fundsmanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.fundsmanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'FundsmanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-fundsmanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.fundsmanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && fundsmanagements && fundsmanagements.length === 0">
      <span v-text="t$('jHipster0App.fundsmanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="fundsmanagements && fundsmanagements.length > 0">
      <table class="table table-striped" aria-describedby="fundsmanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagement.name')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagement.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagement.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagement.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagement.wbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="fundsmanagement in fundsmanagements" :key="fundsmanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: fundsmanagement.id } }">{{
                fundsmanagement.id
              }}</router-link>
            </td>
            <td>{{ fundsmanagement.name }}</td>
            <td>{{ fundsmanagement.description }}</td>
            <td>{{ fundsmanagement.starttime }}</td>
            <td>{{ fundsmanagement.endtime }}</td>
            <td>
              <div v-if="fundsmanagement.wbs">
                <router-link :to="{ name: 'FundsmanagementWbsView', params: { fundsmanagementWbsId: fundsmanagement.wbs.id } }">{{
                  fundsmanagement.wbs.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: fundsmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'FundsmanagementEdit', params: { fundsmanagementId: fundsmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(fundsmanagement)"
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
          id="jHipster0App.fundsmanagement.delete.question"
          data-cy="fundsmanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-fundsmanagement-heading" v-text="t$('jHipster0App.fundsmanagement.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-fundsmanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeFundsmanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./fundsmanagement.component.ts"></script>
