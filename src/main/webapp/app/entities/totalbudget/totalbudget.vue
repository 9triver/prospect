<template>
  <div>
    <h2 id="page-heading" data-cy="TotalbudgetHeading">
      <span v-text="t$('jHipster3App.totalbudget.home.title')" id="totalbudget-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.totalbudget.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'TotalbudgetCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-totalbudget"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.totalbudget.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && totalbudgets && totalbudgets.length === 0">
      <span v-text="t$('jHipster3App.totalbudget.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="totalbudgets && totalbudgets.length > 0">
      <table class="table table-striped" aria-describedby="totalbudgets">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.totalbudget.totalbudgetid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.totalbudget.valuationsubjects')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.totalbudget.budget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.totalbudget.percentage')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.totalbudget.remarks')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="totalbudget in totalbudgets" :key="totalbudget.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TotalbudgetView', params: { totalbudgetId: totalbudget.id } }">{{ totalbudget.id }}</router-link>
            </td>
            <td>{{ totalbudget.totalbudgetid }}</td>
            <td>{{ totalbudget.valuationsubjects }}</td>
            <td>{{ totalbudget.budget }}</td>
            <td>{{ totalbudget.percentage }}</td>
            <td>{{ totalbudget.remarks }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TotalbudgetView', params: { totalbudgetId: totalbudget.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TotalbudgetEdit', params: { totalbudgetId: totalbudget.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(totalbudget)"
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
          id="jHipster3App.totalbudget.delete.question"
          data-cy="totalbudgetDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-totalbudget-heading" v-text="t$('jHipster3App.totalbudget.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-totalbudget"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTotalbudget()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./totalbudget.component.ts"></script>
