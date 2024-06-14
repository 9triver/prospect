<template>
  <div>
    <h2 id="page-heading" data-cy="OutsourcingmPurchasePlanHeading">
      <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.home.title')" id="outsourcingm-purchase-plan-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OutsourcingmPurchasePlanCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-outsourcingm-purchase-plan"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && outsourcingmPurchasePlans && outsourcingmPurchasePlans.length === 0">
      <span v-text="t$('jHipster3App.outsourcingmPurchasePlan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="outsourcingmPurchasePlans && outsourcingmPurchasePlans.length > 0">
      <table class="table table-striped" aria-describedby="outsourcingmPurchasePlans">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.outsourcingplanid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.matarialname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.purchasingmethod')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.budgit')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.needtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.planusetime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.supplierid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.price')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.project')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.outsourcingmPurchasePlan.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="outsourcingmPurchasePlan in outsourcingmPurchasePlans" :key="outsourcingmPurchasePlan.id" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'OutsourcingmPurchasePlanView', params: { outsourcingmPurchasePlanId: outsourcingmPurchasePlan.id } }"
                >{{ outsourcingmPurchasePlan.id }}</router-link
              >
            </td>
            <td>{{ outsourcingmPurchasePlan.outsourcingplanid }}</td>
            <td>{{ outsourcingmPurchasePlan.matarialname }}</td>
            <td>{{ outsourcingmPurchasePlan.purchasingmethod }}</td>
            <td>{{ outsourcingmPurchasePlan.budgit }}</td>
            <td>{{ outsourcingmPurchasePlan.needtime }}</td>
            <td>{{ outsourcingmPurchasePlan.planusetime }}</td>
            <td>{{ outsourcingmPurchasePlan.supplierid }}</td>
            <td>{{ outsourcingmPurchasePlan.price }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + outsourcingmPurchasePlan.secretlevel)"></td>
            <td v-text="t$('jHipster3App.AuditStatus.' + outsourcingmPurchasePlan.auditStatus)"></td>
            <td>
              <div v-if="outsourcingmPurchasePlan.project">
                <router-link :to="{ name: 'ProjectView', params: { projectId: outsourcingmPurchasePlan.project.id } }">{{
                  outsourcingmPurchasePlan.project.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="outsourcingmPurchasePlan.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingmPurchasePlan.responsibleid.id } }">{{
                  outsourcingmPurchasePlan.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="outsourcingmPurchasePlan.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingmPurchasePlan.auditorid.id } }">{{
                  outsourcingmPurchasePlan.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OutsourcingmPurchasePlanView', params: { outsourcingmPurchasePlanId: outsourcingmPurchasePlan.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OutsourcingmPurchasePlanEdit', params: { outsourcingmPurchasePlanId: outsourcingmPurchasePlan.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(outsourcingmPurchasePlan)"
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
          id="jHipster3App.outsourcingmPurchasePlan.delete.question"
          data-cy="outsourcingmPurchasePlanDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-outsourcingmPurchasePlan-heading"
          v-text="t$('jHipster3App.outsourcingmPurchasePlan.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-outsourcingmPurchasePlan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOutsourcingmPurchasePlan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./outsourcingm-purchase-plan.component.ts"></script>
