<template>
  <div>
    <h2 id="page-heading" data-cy="PlanmonitorHeading">
      <span v-text="t$('jHipster3App.planmonitor.home.title')" id="planmonitor-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.planmonitor.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'PlanmonitorCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-planmonitor"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.planmonitor.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && planmonitors && planmonitors.length === 0">
      <span v-text="t$('jHipster3App.planmonitor.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="planmonitors && planmonitors.length > 0">
      <table class="table table-striped" aria-describedby="planmonitors">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planmonitor.month')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planmonitor.type')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planmonitor.year')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planmonitor.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planmonitor.status')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="planmonitor in planmonitors" :key="planmonitor.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PlanmonitorView', params: { planmonitorId: planmonitor.id } }">{{ planmonitor.id }}</router-link>
            </td>
            <td>{{ planmonitor.month }}</td>
            <td>{{ planmonitor.type }}</td>
            <td>{{ planmonitor.year }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + planmonitor.secretlevel)"></td>
            <td>{{ planmonitor.status }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PlanmonitorView', params: { planmonitorId: planmonitor.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PlanmonitorEdit', params: { planmonitorId: planmonitor.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(planmonitor)"
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
          id="jHipster3App.planmonitor.delete.question"
          data-cy="planmonitorDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-planmonitor-heading" v-text="t$('jHipster3App.planmonitor.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-planmonitor"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePlanmonitor()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./planmonitor.component.ts"></script>
