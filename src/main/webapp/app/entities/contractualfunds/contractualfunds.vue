<template>
  <div>
    <h2 id="page-heading" data-cy="ContractualfundsHeading">
      <span v-text="t$('jHipster3App.contractualfunds.home.title')" id="contractualfunds-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.contractualfunds.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ContractualfundsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-contractualfunds"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.contractualfunds.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && contractualfunds && contractualfunds.length === 0">
      <span v-text="t$('jHipster3App.contractualfunds.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="contractualfunds && contractualfunds.length > 0">
      <table class="table table-striped" aria-describedby="contractualfunds">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.contractualid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.department')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.year')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.foreigncurrency')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.totalbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.fundsinplace')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.responsibleunitname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.audittime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.accountbank')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.contractualfunds.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="contractualfunds in contractualfunds" :key="contractualfunds.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ContractualfundsView', params: { contractualfundsId: contractualfunds.id } }">{{
                contractualfunds.id
              }}</router-link>
            </td>
            <td>{{ contractualfunds.contractualid }}</td>
            <td>{{ contractualfunds.department }}</td>
            <td>{{ contractualfunds.year }}</td>
            <td>{{ contractualfunds.starttime }}</td>
            <td>{{ contractualfunds.endtime }}</td>
            <td>{{ contractualfunds.status }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + contractualfunds.secretlevel)"></td>
            <td>{{ contractualfunds.foreigncurrency }}</td>
            <td>{{ contractualfunds.totalbudget }}</td>
            <td>{{ contractualfunds.fundsinplace }}</td>
            <td>{{ contractualfunds.responsibleunitname }}</td>
            <td>{{ contractualfunds.audittime }}</td>
            <td>{{ contractualfunds.accountbank }}</td>
            <td>
              <div v-if="contractualfunds.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: contractualfunds.creatorid.id } }">{{
                  contractualfunds.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="contractualfunds.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: contractualfunds.auditorid.id } }">{{
                  contractualfunds.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ContractualfundsView', params: { contractualfundsId: contractualfunds.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ContractualfundsEdit', params: { contractualfundsId: contractualfunds.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(contractualfunds)"
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
          id="jHipster3App.contractualfunds.delete.question"
          data-cy="contractualfundsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-contractualfunds-heading" v-text="t$('jHipster3App.contractualfunds.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-contractualfunds"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeContractualfunds()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./contractualfunds.component.ts"></script>
