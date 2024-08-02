<template>
  <div>
    <h2 id="page-heading" data-cy="DocumentHeading">
      <span v-text="t$('jy1App.document.home.title')" id="document-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.document.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'DocumentCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-document"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.document.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && documents && documents.length === 0">
      <span v-text="t$('jy1App.document.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="documents && documents.length > 0">
      <table class="table table-striped" aria-describedby="documents">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.document.documentname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.document.documenttype')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.document.documentsize')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.document.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.document.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.document.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.document.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.document.projectwbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="document in documents" :key="document.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'DocumentView', params: { documentId: document.id } }">{{ document.id }}</router-link>
            </td>
            <td>{{ document.documentname }}</td>
            <td>{{ document.documenttype }}</td>
            <td>{{ document.documentsize }}</td>
            <td v-text="t$('jy1App.Secretlevel.' + document.secretlevel)"></td>
            <td>{{ document.createtime }}</td>
            <td>{{ document.creatorname }}</td>
            <td>
              <div v-if="document.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: document.creatorid.id } }">{{
                  document.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="document.projectwbs">
                <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: document.projectwbs.id } }">{{
                  document.projectwbs.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'DocumentView', params: { documentId: document.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'DocumentEdit', params: { documentId: document.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(document)"
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
        <span id="jy1App.document.delete.question" data-cy="documentDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-document-heading" v-text="t$('jy1App.document.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-document"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeDocument()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./document.component.ts"></script>
