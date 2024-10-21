<template>
  <div>
    <h2 id="page-heading" data-cy="CommunicationFormDictionaryHeading">
      <span v-text="t$('jy1App.communicationFormDictionary.home.title')" id="communication-form-dictionary-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.communicationFormDictionary.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'CommunicationFormDictionaryCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-communication-form-dictionary"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.communicationFormDictionary.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && communicationFormDictionaries && communicationFormDictionaries.length === 0">
      <span v-text="t$('jy1App.communicationFormDictionary.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="communicationFormDictionaries && communicationFormDictionaries.length > 0">
      <el-table :data="communicationFormDictionaries" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'CommunicationFormDictionaryView', params: { communicationFormDictionaryId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="communicationformname"
          :label="t$('jy1App.communicationFormDictionary.communicationformname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.communicationformname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="communicationformtype"
          :label="t$('jy1App.communicationFormDictionary.communicationformtype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.communicationformtype }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="status"
          :label="t$('jy1App.communicationFormDictionary.status')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.status }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'CommunicationFormDictionaryView', params: { communicationFormDictionaryId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'CommunicationFormDictionaryEdit', params: { communicationFormDictionaryId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(scope.row)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="trash"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </template>
        </el-table-column>
      </el-table>
      <!-- <table class="table table-striped" aria-describedby="communicationFormDictionaries">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationFormDictionary.communicationformname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationFormDictionary.communicationformtype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.communicationFormDictionary.status')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="communicationFormDictionary in communicationFormDictionaries"
                    :key="communicationFormDictionary.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'CommunicationFormDictionaryView', params: {communicationFormDictionaryId: communicationFormDictionary.id}}">{{communicationFormDictionary.id}}</router-link>
                    </td>
                    <td>{{communicationFormDictionary.communicationformname}}</td>
                    <td>{{communicationFormDictionary.communicationformtype}}</td>
                    <td>{{communicationFormDictionary.status}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CommunicationFormDictionaryView', params: {communicationFormDictionaryId: communicationFormDictionary.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'CommunicationFormDictionaryEdit', params: {communicationFormDictionaryId: communicationFormDictionary.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(communicationFormDictionary)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   data-cy="entityDeleteButton"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>-->
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="jy1App.communicationFormDictionary.delete.question"
          data-cy="communicationFormDictionaryDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-communicationFormDictionary-heading"
          v-text="t$('jy1App.communicationFormDictionary.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-communicationFormDictionary"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeCommunicationFormDictionary()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./communication-form-dictionary.component.ts"></script>
