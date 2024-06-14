<template>
  <div>
    <h2 id="page-heading" data-cy="PbsbaselineHeading">
      <span v-text="t$('jHipster3App.pbsbaseline.home.title')" id="pbsbaseline-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.pbsbaseline.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'PbsbaselineCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-pbsbaseline"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.pbsbaseline.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && pbsbaselines && pbsbaselines.length === 0">
      <span v-text="t$('jHipster3App.pbsbaseline.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="pbsbaselines && pbsbaselines.length > 0">
      <table class="table table-striped" aria-describedby="pbsbaselines">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.pbsbaseline.formid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.pbsbaseline.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.pbsbaseline.requestdeportment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.pbsbaseline.chargetype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.pbsbaseline.chargecontent')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.pbsbaseline.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.pbsbaseline.version')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.pbsbaseline.remark')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.pbsbaseline.projectcharge')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="pbsbaseline in pbsbaselines" :key="pbsbaseline.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PbsbaselineView', params: { pbsbaselineId: pbsbaseline.id } }">{{ pbsbaseline.id }}</router-link>
            </td>
            <td>{{ pbsbaseline.formid }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + pbsbaseline.secretlevel)"></td>
            <td>{{ pbsbaseline.requestdeportment }}</td>
            <td>{{ pbsbaseline.chargetype }}</td>
            <td>{{ pbsbaseline.chargecontent }}</td>
            <td>{{ pbsbaseline.status }}</td>
            <td>{{ pbsbaseline.version }}</td>
            <td>{{ pbsbaseline.remark }}</td>
            <td>
              <div v-if="pbsbaseline.projectcharge">
                <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: pbsbaseline.projectcharge.id } }">{{
                  pbsbaseline.projectcharge.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PbsbaselineView', params: { pbsbaselineId: pbsbaseline.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PbsbaselineEdit', params: { pbsbaselineId: pbsbaseline.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(pbsbaseline)"
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
          id="jHipster3App.pbsbaseline.delete.question"
          data-cy="pbsbaselineDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-pbsbaseline-heading" v-text="t$('jHipster3App.pbsbaseline.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-pbsbaseline"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePbsbaseline()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./pbsbaseline.component.ts"></script>
