<template>
  <div>
    <h2 id="page-heading" data-cy="ProgressbaselineHeading">
      <span v-text="t$('jHipster0App.progressbaseline.home.title')" id="progressbaseline-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.progressbaseline.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProgressbaselineCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-progressbaseline"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.progressbaseline.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && progressbaselines && progressbaselines.length === 0">
      <span v-text="t$('jHipster0App.progressbaseline.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="progressbaselines && progressbaselines.length > 0">
      <table class="table table-striped" aria-describedby="progressbaselines">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressbaseline.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressbaseline.requestdeportment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressbaseline.chargetype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressbaseline.chargecontent')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressbaseline.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressbaseline.version')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressbaseline.remark')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="progressbaseline in progressbaselines" :key="progressbaseline.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProgressbaselineView', params: { progressbaselineId: progressbaseline.id } }">{{
                progressbaseline.id
              }}</router-link>
            </td>
            <td v-text="t$('jHipster0App.Secretlevel.' + progressbaseline.secretlevel)"></td>
            <td>{{ progressbaseline.requestdeportment }}</td>
            <td>{{ progressbaseline.chargetype }}</td>
            <td>{{ progressbaseline.chargecontent }}</td>
            <td>{{ progressbaseline.status }}</td>
            <td>{{ progressbaseline.version }}</td>
            <td>{{ progressbaseline.remark }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProgressbaselineView', params: { progressbaselineId: progressbaseline.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProgressbaselineEdit', params: { progressbaselineId: progressbaseline.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(progressbaseline)"
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
          id="jHipster0App.progressbaseline.delete.question"
          data-cy="progressbaselineDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-progressbaseline-heading" v-text="t$('jHipster0App.progressbaseline.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-progressbaseline"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProgressbaseline()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./progressbaseline.component.ts"></script>
