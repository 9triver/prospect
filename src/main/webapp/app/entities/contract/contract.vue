<template>
  <div>
    <h2 id="page-heading" data-cy="ContractHeading">
      <span v-text="t$('jy1App.contract.home.title')" id="contract-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.contract.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ContractCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-contract"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.contract.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && contracts && contracts.length === 0">
      <span v-text="t$('jy1App.contract.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="contracts && contracts.length > 0">
      <table class="table table-striped" aria-describedby="contracts">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contract.contractname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contract.year')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contract.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contract.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contract.contractbudgetcost')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contract.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contract.status')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.contract.costControlSystem')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="contract in contracts" :key="contract.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ContractView', params: { contractId: contract.id } }">{{ contract.id }}</router-link>
            </td>
            <td>{{ contract.contractname }}</td>
            <td>{{ contract.year }}</td>
            <td>{{ contract.starttime }}</td>
            <td>{{ contract.endtime }}</td>
            <td>{{ contract.contractbudgetcost }}</td>
            <td v-text="t$('jy1App.Secretlevel.' + contract.secretlevel)"></td>
            <td v-text="t$('jy1App.ContractStatus.' + contract.status)"></td>
            <td>
              <span v-for="(costControlSystem, i) in contract.costControlSystems" :key="costControlSystem.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'CostControlSystemView', params: { costControlSystemId: costControlSystem.id } }"
                  >{{ costControlSystem.id }}</router-link
                >
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ContractView', params: { contractId: contract.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ContractEdit', params: { contractId: contract.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(contract)"
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
        <span id="jy1App.contract.delete.question" data-cy="contractDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-contract-heading" v-text="t$('jy1App.contract.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-contract"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeContract()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./contract.component.ts"></script>
