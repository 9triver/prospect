<template>
  <div>
    <h2 id="page-heading" data-cy="FundsmanagementWbsHeading">
      <span v-text="t$('jHipster0App.fundsmanagementWbs.home.title')" id="fundsmanagement-wbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.fundsmanagementWbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'FundsmanagementWbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-fundsmanagement-wbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.fundsmanagementWbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && fundsmanagementWbs && fundsmanagementWbs.length === 0">
      <span v-text="t$('jHipster0App.fundsmanagementWbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="fundsmanagementWbs && fundsmanagementWbs.length > 0">
      <table class="table table-striped" aria-describedby="fundsmanagementWbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagementWbs.auditedbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagementWbs.totalbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagementWbs.unitbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagementWbs.fundsavailability')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.fundsmanagementWbs.contractualfunds')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="fundsmanagementWbs in fundsmanagementWbs" :key="fundsmanagementWbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'FundsmanagementWbsView', params: { fundsmanagementWbsId: fundsmanagementWbs.id } }">{{
                fundsmanagementWbs.id
              }}</router-link>
            </td>
            <td>{{ fundsmanagementWbs.wbsspare1 }}</td>
            <td>{{ fundsmanagementWbs.wbsspare2 }}</td>
            <td>{{ fundsmanagementWbs.wbsspare3 }}</td>
            <td>{{ fundsmanagementWbs.wbsspare4 }}</td>
            <td>{{ fundsmanagementWbs.wbsspare5 }}</td>
            <td>
              <div v-if="fundsmanagementWbs.auditedbudget">
                <router-link :to="{ name: 'AuditedbudgetView', params: { auditedbudgetId: fundsmanagementWbs.auditedbudget.id } }">{{
                  fundsmanagementWbs.auditedbudget.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="fundsmanagementWbs.totalbudget">
                <router-link :to="{ name: 'TotalbudgetView', params: { totalbudgetId: fundsmanagementWbs.totalbudget.id } }">{{
                  fundsmanagementWbs.totalbudget.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="fundsmanagementWbs.unitbudget">
                <router-link :to="{ name: 'UnitbudgetView', params: { unitbudgetId: fundsmanagementWbs.unitbudget.id } }">{{
                  fundsmanagementWbs.unitbudget.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="fundsmanagementWbs.fundsavailability">
                <router-link
                  :to="{ name: 'FundsavailabilityView', params: { fundsavailabilityId: fundsmanagementWbs.fundsavailability.id } }"
                  >{{ fundsmanagementWbs.fundsavailability.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="fundsmanagementWbs.contractualfunds">
                <router-link
                  :to="{ name: 'ContractualfundsView', params: { contractualfundsId: fundsmanagementWbs.contractualfunds.id } }"
                  >{{ fundsmanagementWbs.contractualfunds.id }}</router-link
                >
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'FundsmanagementWbsView', params: { fundsmanagementWbsId: fundsmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'FundsmanagementWbsEdit', params: { fundsmanagementWbsId: fundsmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(fundsmanagementWbs)"
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
          id="jHipster0App.fundsmanagementWbs.delete.question"
          data-cy="fundsmanagementWbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-fundsmanagementWbs-heading" v-text="t$('jHipster0App.fundsmanagementWbs.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-fundsmanagementWbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeFundsmanagementWbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./fundsmanagement-wbs.component.ts"></script>
