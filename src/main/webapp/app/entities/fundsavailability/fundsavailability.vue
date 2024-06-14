<template>
  <div>
    <h2 id="page-heading" data-cy="FundsavailabilityHeading">
      <span v-text="t$('jHipster3App.fundsavailability.home.title')" id="fundsavailability-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.fundsavailability.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'FundsavailabilityCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-fundsavailability"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.fundsavailability.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && fundsavailabilities && fundsavailabilities.length === 0">
      <span v-text="t$('jHipster3App.fundsavailability.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="fundsavailabilities && fundsavailabilities.length > 0">
      <table class="table table-striped" aria-describedby="fundsavailabilities">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsavailability.fundsavailabilityid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsavailability.fundsid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsavailability.year')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsavailability.budgit')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsavailability.funding')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsavailability.fundsmanagement')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="fundsavailability in fundsavailabilities" :key="fundsavailability.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'FundsavailabilityView', params: { fundsavailabilityId: fundsavailability.id } }">{{
                fundsavailability.id
              }}</router-link>
            </td>
            <td>{{ fundsavailability.fundsavailabilityid }}</td>
            <td>{{ fundsavailability.fundsid }}</td>
            <td>{{ fundsavailability.year }}</td>
            <td>{{ fundsavailability.budgit }}</td>
            <td>{{ fundsavailability.funding }}</td>
            <td>
              <div v-if="fundsavailability.fundsmanagement">
                <router-link :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: fundsavailability.fundsmanagement.id } }">{{
                  fundsavailability.fundsmanagement.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'FundsavailabilityView', params: { fundsavailabilityId: fundsavailability.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'FundsavailabilityEdit', params: { fundsavailabilityId: fundsavailability.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(fundsavailability)"
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
          id="jHipster3App.fundsavailability.delete.question"
          data-cy="fundsavailabilityDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-fundsavailability-heading" v-text="t$('jHipster3App.fundsavailability.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-fundsavailability"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeFundsavailability()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./fundsavailability.component.ts"></script>
