<template>
  <div>
    <h2 id="page-heading" data-cy="SecrecysystemHeading">
      <span v-text="t$('jHipster0App.secrecysystem.home.title')" id="secrecysystem-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.secrecysystem.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SecrecysystemCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-secrecysystem"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.secrecysystem.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && secrecysystems && secrecysystems.length === 0">
      <span v-text="t$('jHipster0App.secrecysystem.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="secrecysystems && secrecysystems.length > 0">
      <table class="table table-striped" aria-describedby="secrecysystems">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecysystem.publishedby')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecysystem.documentname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecysystem.documenttype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecysystem.documentsize')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecysystem.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecysystem.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecysystem.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecysystem.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="secrecysystem in secrecysystems" :key="secrecysystem.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SecrecysystemView', params: { secrecysystemId: secrecysystem.id } }">{{
                secrecysystem.id
              }}</router-link>
            </td>
            <td>{{ secrecysystem.publishedby }}</td>
            <td>{{ secrecysystem.documentname }}</td>
            <td>{{ secrecysystem.documenttype }}</td>
            <td>{{ secrecysystem.documentsize }}</td>
            <td v-text="t$('jHipster0App.Secretlevel.' + secrecysystem.secretlevel)"></td>
            <td v-text="t$('jHipster0App.AuditStatus.' + secrecysystem.auditStatus)"></td>
            <td>
              <div v-if="secrecysystem.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: secrecysystem.creatorid.id } }">{{
                  secrecysystem.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="secrecysystem.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: secrecysystem.auditorid.id } }">{{
                  secrecysystem.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SecrecysystemView', params: { secrecysystemId: secrecysystem.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SecrecysystemEdit', params: { secrecysystemId: secrecysystem.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(secrecysystem)"
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
          id="jHipster0App.secrecysystem.delete.question"
          data-cy="secrecysystemDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-secrecysystem-heading" v-text="t$('jHipster0App.secrecysystem.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-secrecysystem"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSecrecysystem()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./secrecysystem.component.ts"></script>
