<template>
  <div>
    <h2 id="page-heading" data-cy="OutsourcingmanagementWbsHeading">
      <span v-text="t$('jHipster0App.outsourcingmanagementWbs.home.title')" id="outsourcingmanagement-wbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.outsourcingmanagementWbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OutsourcingmanagementWbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-outsourcingmanagement-wbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.outsourcingmanagementWbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && outsourcingmanagementWbs && outsourcingmanagementWbs.length === 0">
      <span v-text="t$('jHipster0App.outsourcingmanagementWbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="outsourcingmanagementWbs && outsourcingmanagementWbs.length > 0">
      <table class="table table-striped" aria-describedby="outsourcingmanagementWbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagementWbs.outsourcingPurchasePlan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagementWbs.outsourcingPurchaseExecute')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="outsourcingmanagementWbs in outsourcingmanagementWbs" :key="outsourcingmanagementWbs.id" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'OutsourcingmanagementWbsView', params: { outsourcingmanagementWbsId: outsourcingmanagementWbs.id } }"
                >{{ outsourcingmanagementWbs.id }}</router-link
              >
            </td>
            <td>{{ outsourcingmanagementWbs.wbsspare1 }}</td>
            <td>{{ outsourcingmanagementWbs.wbsspare2 }}</td>
            <td>{{ outsourcingmanagementWbs.wbsspare3 }}</td>
            <td>{{ outsourcingmanagementWbs.wbsspare4 }}</td>
            <td>{{ outsourcingmanagementWbs.wbsspare5 }}</td>
            <td>
              <div v-if="outsourcingmanagementWbs.outsourcingPurchasePlan">
                <router-link
                  :to="{
                    name: 'OutsourcingPurchasePlanView',
                    params: { outsourcingPurchasePlanId: outsourcingmanagementWbs.outsourcingPurchasePlan.id },
                  }"
                  >{{ outsourcingmanagementWbs.outsourcingPurchasePlan.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="outsourcingmanagementWbs.outsourcingPurchaseExecute">
                <router-link
                  :to="{
                    name: 'OutsourcingPurchaseExecuteView',
                    params: { outsourcingPurchaseExecuteId: outsourcingmanagementWbs.outsourcingPurchaseExecute.id },
                  }"
                  >{{ outsourcingmanagementWbs.outsourcingPurchaseExecute.id }}</router-link
                >
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OutsourcingmanagementWbsView', params: { outsourcingmanagementWbsId: outsourcingmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OutsourcingmanagementWbsEdit', params: { outsourcingmanagementWbsId: outsourcingmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(outsourcingmanagementWbs)"
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
          id="jHipster0App.outsourcingmanagementWbs.delete.question"
          data-cy="outsourcingmanagementWbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-outsourcingmanagementWbs-heading"
          v-text="t$('jHipster0App.outsourcingmanagementWbs.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-outsourcingmanagementWbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOutsourcingmanagementWbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./outsourcingmanagement-wbs.component.ts"></script>
