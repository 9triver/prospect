<template>
  <div>
    <h2 id="page-heading" data-cy="SecrecymanagementHeading">
      <span v-text="t$('jHipster3App.secrecymanagement.home.title')" id="secrecymanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.secrecymanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SecrecymanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-secrecymanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.secrecymanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && secrecymanagements && secrecymanagements.length === 0">
      <span v-text="t$('jHipster3App.secrecymanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="secrecymanagements && secrecymanagements.length > 0">
      <table class="table table-striped" aria-describedby="secrecymanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.secrecymanagement.secrecyid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.secrecymanagement.publishedby')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.secrecymanagement.documentname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.secrecymanagement.documenttype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.secrecymanagement.documentsize')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.secrecymanagement.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.secrecymanagement.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.secrecymanagement.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.secrecymanagement.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="secrecymanagement in secrecymanagements" :key="secrecymanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SecrecymanagementView', params: { secrecymanagementId: secrecymanagement.id } }">{{
                secrecymanagement.id
              }}</router-link>
            </td>
            <td>{{ secrecymanagement.secrecyid }}</td>
            <td>{{ secrecymanagement.publishedby }}</td>
            <td>{{ secrecymanagement.documentname }}</td>
            <td>{{ secrecymanagement.documenttype }}</td>
            <td>{{ secrecymanagement.documentsize }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + secrecymanagement.secretlevel)"></td>
            <td v-text="t$('jHipster3App.AuditStatus.' + secrecymanagement.auditStatus)"></td>
            <td>
              <div v-if="secrecymanagement.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: secrecymanagement.creatorid.id } }">{{
                  secrecymanagement.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="secrecymanagement.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: secrecymanagement.auditorid.id } }">{{
                  secrecymanagement.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SecrecymanagementView', params: { secrecymanagementId: secrecymanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SecrecymanagementEdit', params: { secrecymanagementId: secrecymanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(secrecymanagement)"
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
          id="jHipster3App.secrecymanagement.delete.question"
          data-cy="secrecymanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-secrecymanagement-heading" v-text="t$('jHipster3App.secrecymanagement.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-secrecymanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSecrecymanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./secrecymanagement.component.ts"></script>
