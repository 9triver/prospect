<template>
  <div>
    <h2 id="page-heading" data-cy="CostControlSystemHeading">
      <span v-text="t$('jy1App.costControlSystem.home.title')" id="cost-control-system-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.costControlSystem.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'CostControlSystemCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-cost-control-system"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.costControlSystem.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && costControlSystems && costControlSystems.length === 0">
      <span v-text="t$('jy1App.costControlSystem.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="costControlSystems && costControlSystems.length > 0">
      <table class="table table-striped" aria-describedby="costControlSystems">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.type')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.subject')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.implementedamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.approvedamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.pendingimplementationamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.contractpaymentamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.managementregistrationnumber')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.financialregistrationnumber')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.contractbudgetamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.contractsigningamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.contractsettlementamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.unforeseeableamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.invoicepaymentamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.loanpaymentamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.accountoutstandingamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.pendingpaymentamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.pendinginvoiceamount')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.responsibleperson')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.projectwbs')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.costControlSystem.contract')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="costControlSystem in costControlSystems" :key="costControlSystem.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CostControlSystemView', params: { costControlSystemId: costControlSystem.id } }">{{
                costControlSystem.id
              }}</router-link>
            </td>
            <td>{{ costControlSystem.type }}</td>
            <td v-text="t$('jy1App.ContractSubject.' + costControlSystem.subject)"></td>
            <td>{{ costControlSystem.implementedamount }}</td>
            <td>{{ costControlSystem.approvedamount }}</td>
            <td>{{ costControlSystem.pendingimplementationamount }}</td>
            <td>{{ costControlSystem.contractpaymentamount }}</td>
            <td>{{ costControlSystem.managementregistrationnumber }}</td>
            <td>{{ costControlSystem.financialregistrationnumber }}</td>
            <td>{{ costControlSystem.contractbudgetamount }}</td>
            <td>{{ costControlSystem.contractsigningamount }}</td>
            <td>{{ costControlSystem.contractsettlementamount }}</td>
            <td>{{ costControlSystem.unforeseeableamount }}</td>
            <td>{{ costControlSystem.invoicepaymentamount }}</td>
            <td>{{ costControlSystem.loanpaymentamount }}</td>
            <td>{{ costControlSystem.accountoutstandingamount }}</td>
            <td>{{ costControlSystem.pendingpaymentamount }}</td>
            <td>{{ costControlSystem.pendinginvoiceamount }}</td>
            <td>
              <div v-if="costControlSystem.responsibleperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: costControlSystem.responsibleperson.id } }">{{
                  costControlSystem.responsibleperson.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="costControlSystem.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: costControlSystem.auditorid.id } }">{{
                  costControlSystem.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(projectwbs, i) in costControlSystem.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
            </td>
            <td>
              <span v-for="(contract, i) in costControlSystem.contracts" :key="contract.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ContractView', params: { contractId: contract.id } }">{{
                  contract.id
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'CostControlSystemView', params: { costControlSystemId: costControlSystem.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'CostControlSystemEdit', params: { costControlSystemId: costControlSystem.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(costControlSystem)"
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
          id="jy1App.costControlSystem.delete.question"
          data-cy="costControlSystemDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-costControlSystem-heading" v-text="t$('jy1App.costControlSystem.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-costControlSystem"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeCostControlSystem()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./cost-control-system.component.ts"></script>
