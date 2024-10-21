<template>
  <div>
    <h2 id="page-heading" data-cy="ContractCostBudgetHeading">
      <span v-text="t$('jy1App.contractCostBudget.home.title')" id="contract-cost-budget-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.contractCostBudget.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ContractCostBudgetCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-contract-cost-budget"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.contractCostBudget.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && contractCostBudgets && contractCostBudgets.length === 0">
      <span v-text="t$('jy1App.contractCostBudget.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="contractCostBudgets && contractCostBudgets.length > 0">
      <table class="table table-striped" aria-describedby="contractCostBudgets">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contractCostBudget.subject')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contractCostBudget.auxiliaryitem')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contractCostBudget.unit')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contractCostBudget.number')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contractCostBudget.unitprice')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contractCostBudget.totalprice')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contractCostBudget.projectwbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="contractCostBudget in contractCostBudgets" :key="contractCostBudget.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ContractCostBudgetView', params: { contractCostBudgetId: contractCostBudget.id } }">{{
                contractCostBudget.id
              }}</router-link>
            </td>
            <td v-text="t$('jy1App.ContractSubject.' + contractCostBudget.subject)"></td>
            <td>{{ contractCostBudget.auxiliaryitem }}</td>
            <td>{{ contractCostBudget.unit }}</td>
            <td>{{ contractCostBudget.number }}</td>
            <td>{{ contractCostBudget.unitprice }}</td>
            <td>{{ contractCostBudget.totalprice }}</td>
            <td>
              <span v-for="(projectwbs, i) in contractCostBudget.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ContractCostBudgetView', params: { contractCostBudgetId: contractCostBudget.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ContractCostBudgetEdit', params: { contractCostBudgetId: contractCostBudget.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(contractCostBudget)"
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
          id="jy1App.contractCostBudget.delete.question"
          data-cy="contractCostBudgetDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-contractCostBudget-heading" v-text="t$('jy1App.contractCostBudget.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-contractCostBudget"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeContractCostBudget()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./contract-cost-budget.component.ts"></script>
