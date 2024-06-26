<template>
  <div>
    <h2 id="page-heading" data-cy="QualitymanagementHeading">
      <span v-text="t$('jHipster0App.qualitymanagement.home.title')" id="qualitymanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.qualitymanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'QualitymanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-qualitymanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.qualitymanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualitymanagements && qualitymanagements.length === 0">
      <span v-text="t$('jHipster0App.qualitymanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualitymanagements && qualitymanagements.length > 0">
      <table class="table table-striped" aria-describedby="qualitymanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagement.name')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagement.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagement.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagement.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagement.wbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="qualitymanagement in qualitymanagements" :key="qualitymanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'QualitymanagementView', params: { qualitymanagementId: qualitymanagement.id } }">{{
                qualitymanagement.id
              }}</router-link>
            </td>
            <td>{{ qualitymanagement.name }}</td>
            <td>{{ qualitymanagement.description }}</td>
            <td>{{ qualitymanagement.starttime }}</td>
            <td>{{ qualitymanagement.endtime }}</td>
            <td>
              <div v-if="qualitymanagement.wbs">
                <router-link :to="{ name: 'QualitymanagementWbsView', params: { qualitymanagementWbsId: qualitymanagement.wbs.id } }">{{
                  qualitymanagement.wbs.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'QualitymanagementView', params: { qualitymanagementId: qualitymanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'QualitymanagementEdit', params: { qualitymanagementId: qualitymanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(qualitymanagement)"
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
          id="jHipster0App.qualitymanagement.delete.question"
          data-cy="qualitymanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-qualitymanagement-heading" v-text="t$('jHipster0App.qualitymanagement.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-qualitymanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeQualitymanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./qualitymanagement.component.ts"></script>
