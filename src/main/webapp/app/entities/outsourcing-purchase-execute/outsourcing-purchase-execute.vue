<template>
  <div>
    <h2 id="page-heading" data-cy="OutsourcingPurchaseExecuteHeading">
      <span v-text="t$('jHipster0App.outsourcingPurchaseExecute.home.title')" id="outsourcing-purchase-execute-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.outsourcingPurchaseExecute.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OutsourcingPurchaseExecuteCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-outsourcing-purchase-execute"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.outsourcingPurchaseExecute.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && outsourcingPurchaseExecutes && outsourcingPurchaseExecutes.length === 0">
      <span v-text="t$('jHipster0App.outsourcingPurchaseExecute.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="outsourcingPurchaseExecutes && outsourcingPurchaseExecutes.length > 0">
      <table class="table table-striped" aria-describedby="outsourcingPurchaseExecutes">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchaseExecute.matarialname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchaseExecute.purchasingmethod')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchaseExecute.budgit')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchaseExecute.needtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchaseExecute.planusetime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchaseExecute.supplierid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchaseExecute.price')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchaseExecute.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchaseExecute.outsourcingplanid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchaseExecute.responsibleid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="outsourcingPurchaseExecute in outsourcingPurchaseExecutes" :key="outsourcingPurchaseExecute.id" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'OutsourcingPurchaseExecuteView', params: { outsourcingPurchaseExecuteId: outsourcingPurchaseExecute.id } }"
                >{{ outsourcingPurchaseExecute.id }}</router-link
              >
            </td>
            <td>{{ outsourcingPurchaseExecute.matarialname }}</td>
            <td>{{ outsourcingPurchaseExecute.purchasingmethod }}</td>
            <td>{{ outsourcingPurchaseExecute.budgit }}</td>
            <td>{{ outsourcingPurchaseExecute.needtime }}</td>
            <td>{{ outsourcingPurchaseExecute.planusetime }}</td>
            <td>{{ outsourcingPurchaseExecute.supplierid }}</td>
            <td>{{ outsourcingPurchaseExecute.price }}</td>
            <td v-text="t$('jHipster0App.Secretlevel.' + outsourcingPurchaseExecute.secretlevel)"></td>
            <td>
              <div v-if="outsourcingPurchaseExecute.outsourcingplanid">
                <router-link
                  :to="{
                    name: 'OutsourcingPurchasePlanView',
                    params: { outsourcingPurchasePlanId: outsourcingPurchaseExecute.outsourcingplanid.id },
                  }"
                  >{{ outsourcingPurchaseExecute.outsourcingplanid.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="outsourcingPurchaseExecute.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingPurchaseExecute.responsibleid.id } }">{{
                  outsourcingPurchaseExecute.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OutsourcingPurchaseExecuteView', params: { outsourcingPurchaseExecuteId: outsourcingPurchaseExecute.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OutsourcingPurchaseExecuteEdit', params: { outsourcingPurchaseExecuteId: outsourcingPurchaseExecute.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(outsourcingPurchaseExecute)"
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
          id="jHipster0App.outsourcingPurchaseExecute.delete.question"
          data-cy="outsourcingPurchaseExecuteDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-outsourcingPurchaseExecute-heading"
          v-text="t$('jHipster0App.outsourcingPurchaseExecute.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-outsourcingPurchaseExecute"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOutsourcingPurchaseExecute()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./outsourcing-purchase-execute.component.ts"></script>
