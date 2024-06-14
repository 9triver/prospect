<template>
  <div>
    <h2 id="page-heading" data-cy="OutsourcingmPurchaseExecuteHeading">
      <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.home.title')" id="outsourcingm-purchase-execute-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OutsourcingmPurchaseExecuteCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-outsourcingm-purchase-execute"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && outsourcingmPurchaseExecutes && outsourcingmPurchaseExecutes.length === 0">
      <span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="outsourcingmPurchaseExecutes && outsourcingmPurchaseExecutes.length > 0">
      <table class="table table-striped" aria-describedby="outsourcingmPurchaseExecutes">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.outsourcingexecuteid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.matarialname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.purchasingmethod')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.budgit')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.needtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.planusetime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.supplierid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.price')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.outsourcingplanid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchaseExecute.responsibleid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="outsourcingmPurchaseExecute in outsourcingmPurchaseExecutes"
            :key="outsourcingmPurchaseExecute.id"
            data-cy="entityTable"
          >
            <td>
              <router-link
                :to="{ name: 'OutsourcingmPurchaseExecuteView', params: { outsourcingmPurchaseExecuteId: outsourcingmPurchaseExecute.id } }"
                >{{ outsourcingmPurchaseExecute.id }}</router-link
              >
            </td>
            <td>{{ outsourcingmPurchaseExecute.outsourcingexecuteid }}</td>
            <td>{{ outsourcingmPurchaseExecute.matarialname }}</td>
            <td>{{ outsourcingmPurchaseExecute.purchasingmethod }}</td>
            <td>{{ outsourcingmPurchaseExecute.budgit }}</td>
            <td>{{ outsourcingmPurchaseExecute.needtime }}</td>
            <td>{{ outsourcingmPurchaseExecute.planusetime }}</td>
            <td>{{ outsourcingmPurchaseExecute.supplierid }}</td>
            <td>{{ outsourcingmPurchaseExecute.price }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + outsourcingmPurchaseExecute.secretlevel)"></td>
            <td>
              <div v-if="outsourcingmPurchaseExecute.outsourcingplanid">
                <router-link
                  :to="{
                    name: 'OutsourcingmPurchasePlanView',
                    params: { outsourcingmPurchasePlanId: outsourcingmPurchaseExecute.outsourcingplanid.id },
                  }"
                  >{{ outsourcingmPurchaseExecute.outsourcingplanid.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="outsourcingmPurchaseExecute.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingmPurchaseExecute.responsibleid.id } }">{{
                  outsourcingmPurchaseExecute.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{
                    name: 'OutsourcingmPurchaseExecuteView',
                    params: { outsourcingmPurchaseExecuteId: outsourcingmPurchaseExecute.id },
                  }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{
                    name: 'OutsourcingmPurchaseExecuteEdit',
                    params: { outsourcingmPurchaseExecuteId: outsourcingmPurchaseExecute.id },
                  }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(outsourcingmPurchaseExecute)"
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
          id="jHipster3App.outsourcingmPurchaseExecute.delete.question"
          data-cy="outsourcingmPurchaseExecuteDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-outsourcingmPurchaseExecute-heading"
          v-text="t$('jHipster3App.outsourcingmPurchaseExecute.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-outsourcingmPurchaseExecute"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOutsourcingmPurchaseExecute()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./outsourcingm-purchase-execute.component.ts"></script>
