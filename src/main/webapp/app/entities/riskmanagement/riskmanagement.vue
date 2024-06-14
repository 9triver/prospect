<template>
  <div>
    <h2 id="page-heading" data-cy="RiskmanagementHeading">
      <span v-text="t$('jHipster3App.riskmanagement.home.title')" id="riskmanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.riskmanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RiskmanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-riskmanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.riskmanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && riskmanagements && riskmanagements.length === 0">
      <span v-text="t$('jHipster3App.riskmanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="riskmanagements && riskmanagements.length > 0">
      <table class="table table-striped" aria-describedby="riskmanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.riskid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.year')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.nodename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.risktype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.decumentid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.version')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.usetime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.systemlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.risklevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.limitationtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.closetype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.riskmanagement.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="riskmanagement in riskmanagements" :key="riskmanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RiskmanagementView', params: { riskmanagementId: riskmanagement.id } }">{{
                riskmanagement.id
              }}</router-link>
            </td>
            <td>{{ riskmanagement.riskid }}</td>
            <td>{{ riskmanagement.projectname }}</td>
            <td>{{ riskmanagement.year }}</td>
            <td>{{ riskmanagement.nodename }}</td>
            <td>{{ riskmanagement.risktype }}</td>
            <td>{{ riskmanagement.decumentid }}</td>
            <td>{{ riskmanagement.version }}</td>
            <td>{{ riskmanagement.usetime }}</td>
            <td>{{ riskmanagement.systemlevel }}</td>
            <td v-text="t$('jHipster3App.Risklevel.' + riskmanagement.risklevel)"></td>
            <td>{{ riskmanagement.limitationtime }}</td>
            <td>{{ riskmanagement.closetype }}</td>
            <td>
              <div v-if="riskmanagement.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: riskmanagement.creatorid.id } }">{{
                  riskmanagement.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="riskmanagement.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: riskmanagement.responsibleid.id } }">{{
                  riskmanagement.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="riskmanagement.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: riskmanagement.auditorid.id } }">{{
                  riskmanagement.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RiskmanagementView', params: { riskmanagementId: riskmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RiskmanagementEdit', params: { riskmanagementId: riskmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(riskmanagement)"
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
          id="jHipster3App.riskmanagement.delete.question"
          data-cy="riskmanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-riskmanagement-heading" v-text="t$('jHipster3App.riskmanagement.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-riskmanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRiskmanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./riskmanagement.component.ts"></script>
