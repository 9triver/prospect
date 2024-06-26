<template>
  <div>
    <h2 id="page-heading" data-cy="ProgressplanreturnsHeading">
      <span v-text="t$('jHipster0App.progressplanreturns.home.title')" id="progressplanreturns-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.progressplanreturns.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProgressplanreturnsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-progressplanreturns"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.progressplanreturns.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && progressplanreturns && progressplanreturns.length === 0">
      <span v-text="t$('jHipster0App.progressplanreturns.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="progressplanreturns && progressplanreturns.length > 0">
      <table class="table table-striped" aria-describedby="progressplanreturns">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplanreturns.planstarttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplanreturns.planendtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplanreturns.returnstime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplanreturns.progressplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplanreturns.document')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="progressplanreturns in progressplanreturns" :key="progressplanreturns.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProgressplanreturnsView', params: { progressplanreturnsId: progressplanreturns.id } }">{{
                progressplanreturns.id
              }}</router-link>
            </td>
            <td>{{ progressplanreturns.planstarttime }}</td>
            <td>{{ progressplanreturns.planendtime }}</td>
            <td>{{ progressplanreturns.returnstime }}</td>
            <td>
              <div v-if="progressplanreturns.progressplan">
                <router-link :to="{ name: 'ProgressplanView', params: { progressplanId: progressplanreturns.progressplan.id } }">{{
                  progressplanreturns.progressplan.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressplanreturns.document">
                <router-link :to="{ name: 'DocumentView', params: { documentId: progressplanreturns.document.id } }">{{
                  progressplanreturns.document.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProgressplanreturnsView', params: { progressplanreturnsId: progressplanreturns.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProgressplanreturnsEdit', params: { progressplanreturnsId: progressplanreturns.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(progressplanreturns)"
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
          id="jHipster0App.progressplanreturns.delete.question"
          data-cy="progressplanreturnsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-progressplanreturns-heading"
          v-text="t$('jHipster0App.progressplanreturns.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-progressplanreturns"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProgressplanreturns()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./progressplanreturns.component.ts"></script>
