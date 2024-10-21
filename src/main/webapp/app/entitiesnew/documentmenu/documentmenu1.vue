<template>
  <div>
    <h2 id="page-heading" data-cy="DocumentmenuHeading">
      <span v-text="t$('jy1App.documentmenu.home.title')" id="documentmenu-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.documentmenu.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'DocumentmenuCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-documentmenu"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.documentmenu.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && documentmenus && documentmenus.length === 0">
      <span v-text="t$('jy1App.documentmenu.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="documentmenus && documentmenus.length > 0">
      <table class="table table-striped" aria-describedby="documentmenus">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.menuid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.belongtype')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.menuname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.parentmenuid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.type')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.filenum')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.departmentid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.departmentname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.spare1')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.spare2')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.documentmenu.spare3')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="documentmenu in documentmenus" :key="documentmenu.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'DocumentmenuView', params: { documentmenuId: documentmenu.id } }">{{
                documentmenu.id
              }}</router-link>
            </td>
            <td>{{ documentmenu.menuid }}</td>
            <td>{{ documentmenu.belongtype }}</td>
            <td>{{ documentmenu.menuname }}</td>
            <td>{{ documentmenu.parentmenuid }}</td>
            <td>{{ documentmenu.createtime }}</td>
            <td>{{ documentmenu.creatorid }}</td>
            <td>{{ documentmenu.creatorname }}</td>
            <td>{{ documentmenu.type }}</td>
            <td>{{ documentmenu.filenum }}</td>
            <td>{{ documentmenu.departmentid }}</td>
            <td>{{ documentmenu.departmentname }}</td>
            <td>{{ documentmenu.spare1 }}</td>
            <td>{{ documentmenu.spare2 }}</td>
            <td>{{ documentmenu.spare3 }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'DocumentmenuView', params: { documentmenuId: documentmenu.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'DocumentmenuEdit', params: { documentmenuId: documentmenu.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(documentmenu)"
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
        <span id="jy1App.documentmenu.delete.question" data-cy="documentmenuDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-documentmenu-heading" v-text="t$('jy1App.documentmenu.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-documentmenu"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeDocumentmenu()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./documentmenu.component.ts"></script>
