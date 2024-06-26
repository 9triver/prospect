<template>
  <div>
    <h2 id="page-heading" data-cy="RiskreportHeading">
      <span v-text="t$('jHipster0App.riskreport.home.title')" id="riskreport-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.riskreport.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RiskreportCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-riskreport"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.riskreport.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && riskreports && riskreports.length === 0">
      <span v-text="t$('jHipster0App.riskreport.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="riskreports && riskreports.length > 0">
      <table class="table table-striped" aria-describedby="riskreports">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskreport.type')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskreport.riskreportname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskreport.releasetime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskreport.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskreport.riskidentification')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskreport.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskreport.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="riskreport in riskreports" :key="riskreport.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RiskreportView', params: { riskreportId: riskreport.id } }">{{ riskreport.id }}</router-link>
            </td>
            <td>{{ riskreport.type }}</td>
            <td>{{ riskreport.riskreportname }}</td>
            <td>{{ riskreport.releasetime }}</td>
            <td v-text="t$('jHipster0App.AuditStatus.' + riskreport.auditStatus)"></td>
            <td>
              <div v-if="riskreport.riskidentification">
                <router-link :to="{ name: 'RiskidentificationView', params: { riskidentificationId: riskreport.riskidentification.id } }">{{
                  riskreport.riskidentification.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="riskreport.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: riskreport.creatorid.id } }">{{
                  riskreport.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="riskreport.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: riskreport.auditorid.id } }">{{
                  riskreport.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'RiskreportView', params: { riskreportId: riskreport.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'RiskreportEdit', params: { riskreportId: riskreport.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(riskreport)"
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
          id="jHipster0App.riskreport.delete.question"
          data-cy="riskreportDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-riskreport-heading" v-text="t$('jHipster0App.riskreport.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-riskreport"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRiskreport()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./riskreport.component.ts"></script>
