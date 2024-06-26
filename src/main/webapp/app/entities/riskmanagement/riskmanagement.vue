<template>
  <div>
    <h2 id="page-heading" data-cy="RiskmanagementHeading">
      <span v-text="t$('jHipster0App.riskmanagement.home.title')" id="riskmanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.riskmanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RiskmanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-riskmanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.riskmanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && riskmanagements && riskmanagements.length === 0">
      <span v-text="t$('jHipster0App.riskmanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="riskmanagements && riskmanagements.length > 0">
      <table class="table table-striped" aria-describedby="riskmanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagement.name')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagement.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagement.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagement.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagement.wbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="riskmanagement in riskmanagements" :key="riskmanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RiskmanagementView', params: { riskmanagementId: riskmanagement.id } }">{{
                riskmanagement.id
              }}</router-link>
            </td>
            <td>{{ riskmanagement.name }}</td>
            <td>{{ riskmanagement.description }}</td>
            <td>{{ riskmanagement.starttime }}</td>
            <td>{{ riskmanagement.endtime }}</td>
            <td>
              <div v-if="riskmanagement.wbs">
                <router-link :to="{ name: 'RiskmanagementWbsView', params: { riskmanagementWbsId: riskmanagement.wbs.id } }">{{
                  riskmanagement.wbs.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RiskmanagementView', params: { riskmanagementId: riskmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RiskmanagementEdit', params: { riskmanagementId: riskmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(riskmanagement)"
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
          id="jHipster0App.riskmanagement.delete.question"
          data-cy="riskmanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-riskmanagement-heading" v-text="t$('jHipster0App.riskmanagement.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-riskmanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRiskmanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./riskmanagement.component.ts"></script>
