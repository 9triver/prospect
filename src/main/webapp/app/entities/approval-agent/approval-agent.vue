<template>
  <div>
    <h2 id="page-heading" data-cy="ApprovalAgentHeading">
      <span v-text="t$('jHipster3App.approvalAgent.home.title')" id="approval-agent-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.approvalAgent.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ApprovalAgentCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-approval-agent"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.approvalAgent.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && approvalAgents && approvalAgents.length === 0">
      <span v-text="t$('jHipster3App.approvalAgent.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="approvalAgents && approvalAgents.length > 0">
      <table class="table table-striped" aria-describedby="approvalAgents">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.approvalAgent.agentid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.approvalAgent.agentname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.approvalAgent.agentstarttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.approvalAgent.autocanceltime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.approvalAgent.agentdepartment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.approvalAgent.originalapprovalname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.approvalAgent.originaldepartment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.approvalAgent.secrecylevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.approvalAgent.originalapprovalid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="approvalAgent in approvalAgents" :key="approvalAgent.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ApprovalAgentView', params: { approvalAgentId: approvalAgent.id } }">{{
                approvalAgent.id
              }}</router-link>
            </td>
            <td>{{ approvalAgent.agentid }}</td>
            <td>{{ approvalAgent.agentname }}</td>
            <td>{{ approvalAgent.agentstarttime }}</td>
            <td>{{ approvalAgent.autocanceltime }}</td>
            <td>{{ approvalAgent.agentdepartment }}</td>
            <td>{{ approvalAgent.originalapprovalname }}</td>
            <td>{{ approvalAgent.originaldepartment }}</td>
            <td>{{ approvalAgent.secrecylevel }}</td>
            <td>
              <div v-if="approvalAgent.originalapprovalid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: approvalAgent.originalapprovalid.id } }">{{
                  approvalAgent.originalapprovalid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ApprovalAgentView', params: { approvalAgentId: approvalAgent.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ApprovalAgentEdit', params: { approvalAgentId: approvalAgent.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(approvalAgent)"
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
          id="jHipster3App.approvalAgent.delete.question"
          data-cy="approvalAgentDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-approvalAgent-heading" v-text="t$('jHipster3App.approvalAgent.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-approvalAgent"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeApprovalAgent()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./approval-agent.component.ts"></script>
