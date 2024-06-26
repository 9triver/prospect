<template>
  <div>
    <h2 id="page-heading" data-cy="WbsbaselineHeading">
      <span v-text="t$('jHipster0App.wbsbaseline.home.title')" id="wbsbaseline-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.wbsbaseline.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'WbsbaselineCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-wbsbaseline"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.wbsbaseline.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && wbsbaselines && wbsbaselines.length === 0">
      <span v-text="t$('jHipster0App.wbsbaseline.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="wbsbaselines && wbsbaselines.length > 0">
      <table class="table table-striped" aria-describedby="wbsbaselines">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbsbaseline.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbsbaseline.requestdeportment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbsbaseline.chargetype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbsbaseline.chargecontent')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbsbaseline.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbsbaseline.version')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbsbaseline.remark')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbsbaseline.projectcharge')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="wbsbaseline in wbsbaselines" :key="wbsbaseline.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'WbsbaselineView', params: { wbsbaselineId: wbsbaseline.id } }">{{ wbsbaseline.id }}</router-link>
            </td>
            <td v-text="t$('jHipster0App.Secretlevel.' + wbsbaseline.secretlevel)"></td>
            <td>{{ wbsbaseline.requestdeportment }}</td>
            <td>{{ wbsbaseline.chargetype }}</td>
            <td>{{ wbsbaseline.chargecontent }}</td>
            <td>{{ wbsbaseline.status }}</td>
            <td>{{ wbsbaseline.version }}</td>
            <td>{{ wbsbaseline.remark }}</td>
            <td>
              <div v-if="wbsbaseline.projectcharge">
                <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: wbsbaseline.projectcharge.id } }">{{
                  wbsbaseline.projectcharge.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'WbsbaselineView', params: { wbsbaselineId: wbsbaseline.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'WbsbaselineEdit', params: { wbsbaselineId: wbsbaseline.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(wbsbaseline)"
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
          id="jHipster0App.wbsbaseline.delete.question"
          data-cy="wbsbaselineDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-wbsbaseline-heading" v-text="t$('jHipster0App.wbsbaseline.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-wbsbaseline"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeWbsbaseline()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./wbsbaseline.component.ts"></script>
