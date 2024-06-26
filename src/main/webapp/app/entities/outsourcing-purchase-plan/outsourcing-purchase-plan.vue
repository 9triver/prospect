<template>
  <div>
    <h2 id="page-heading" data-cy="OutsourcingPurchasePlanHeading">
      <span v-text="t$('jHipster0App.outsourcingPurchasePlan.home.title')" id="outsourcing-purchase-plan-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.outsourcingPurchasePlan.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OutsourcingPurchasePlanCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-outsourcing-purchase-plan"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.outsourcingPurchasePlan.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && outsourcingPurchasePlans && outsourcingPurchasePlans.length === 0">
      <span v-text="t$('jHipster0App.outsourcingPurchasePlan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="outsourcingPurchasePlans && outsourcingPurchasePlans.length > 0">
      <table class="table table-striped" aria-describedby="outsourcingPurchasePlans">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.matarialname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.purchasingmethod')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.budgit')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.needtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.planusetime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.supplierid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.price')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.project')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingPurchasePlan.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="outsourcingPurchasePlan in outsourcingPurchasePlans" :key="outsourcingPurchasePlan.id" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'OutsourcingPurchasePlanView', params: { outsourcingPurchasePlanId: outsourcingPurchasePlan.id } }"
                >{{ outsourcingPurchasePlan.id }}</router-link
              >
            </td>
            <td>{{ outsourcingPurchasePlan.matarialname }}</td>
            <td>{{ outsourcingPurchasePlan.purchasingmethod }}</td>
            <td>{{ outsourcingPurchasePlan.budgit }}</td>
            <td>{{ outsourcingPurchasePlan.needtime }}</td>
            <td>{{ outsourcingPurchasePlan.planusetime }}</td>
            <td>{{ outsourcingPurchasePlan.supplierid }}</td>
            <td>{{ outsourcingPurchasePlan.price }}</td>
            <td v-text="t$('jHipster0App.Secretlevel.' + outsourcingPurchasePlan.secretlevel)"></td>
            <td v-text="t$('jHipster0App.AuditStatus.' + outsourcingPurchasePlan.auditStatus)"></td>
            <td>
              <div v-if="outsourcingPurchasePlan.project">
                <router-link :to="{ name: 'ProjectView', params: { projectId: outsourcingPurchasePlan.project.id } }">{{
                  outsourcingPurchasePlan.project.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="outsourcingPurchasePlan.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingPurchasePlan.responsibleid.id } }">{{
                  outsourcingPurchasePlan.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="outsourcingPurchasePlan.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingPurchasePlan.auditorid.id } }">{{
                  outsourcingPurchasePlan.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OutsourcingPurchasePlanView', params: { outsourcingPurchasePlanId: outsourcingPurchasePlan.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OutsourcingPurchasePlanEdit', params: { outsourcingPurchasePlanId: outsourcingPurchasePlan.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(outsourcingPurchasePlan)"
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
          id="jHipster0App.outsourcingPurchasePlan.delete.question"
          data-cy="outsourcingPurchasePlanDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-outsourcingPurchasePlan-heading"
          v-text="t$('jHipster0App.outsourcingPurchasePlan.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-outsourcingPurchasePlan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOutsourcingPurchasePlan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./outsourcing-purchase-plan.component.ts"></script>
