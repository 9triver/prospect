<template>
  <div>
    <h2 id="page-heading" data-cy="PlanreturnsHeading">
      <span v-text="t$('jHipster3App.planreturns.home.title')" id="planreturns-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.planreturns.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'PlanreturnsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-planreturns"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.planreturns.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && planreturns && planreturns.length === 0">
      <span v-text="t$('jHipster3App.planreturns.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="planreturns && planreturns.length > 0">
      <table class="table table-striped" aria-describedby="planreturns">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planreturns.planreturnsid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planreturns.planreturnsname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planreturns.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planreturns.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planreturns.plantype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planreturns.returnstime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planreturns.returnsstatus')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="planreturns in planreturns" :key="planreturns.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PlanreturnsView', params: { planreturnsId: planreturns.id } }">{{ planreturns.id }}</router-link>
            </td>
            <td>{{ planreturns.planreturnsid }}</td>
            <td>{{ planreturns.planreturnsname }}</td>
            <td>{{ planreturns.starttime }}</td>
            <td>{{ planreturns.endtime }}</td>
            <td>{{ planreturns.plantype }}</td>
            <td>{{ planreturns.returnstime }}</td>
            <td v-text="t$('jHipster3App.ReturnsStatus.' + planreturns.returnsstatus)"></td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PlanreturnsView', params: { planreturnsId: planreturns.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PlanreturnsEdit', params: { planreturnsId: planreturns.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(planreturns)"
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
          id="jHipster3App.planreturns.delete.question"
          data-cy="planreturnsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-planreturns-heading" v-text="t$('jHipster3App.planreturns.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-planreturns"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePlanreturns()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./planreturns.component.ts"></script>
