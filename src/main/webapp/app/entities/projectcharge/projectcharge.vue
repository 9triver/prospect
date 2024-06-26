<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectchargeHeading">
      <span v-text="t$('jHipster0App.projectcharge.home.title')" id="projectcharge-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.projectcharge.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectchargeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectcharge"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.projectcharge.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectcharges && projectcharges.length === 0">
      <span v-text="t$('jHipster0App.projectcharge.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectcharges && projectcharges.length > 0">
      <table class="table table-striped" aria-describedby="projectcharges">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectcharge.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectcharge.formid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectcharge.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectcharge.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectcharge.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectcharge.requestdeportment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectcharge.chargetype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectcharge.chargecontent')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.projectcharge.creatorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectcharge in projectcharges" :key="projectcharge.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: projectcharge.id } }">{{
                projectcharge.id
              }}</router-link>
            </td>
            <td>{{ projectcharge.projectname }}</td>
            <td>{{ projectcharge.formid }}</td>
            <td>{{ projectcharge.starttime }}</td>
            <td>{{ projectcharge.endtime }}</td>
            <td v-text="t$('jHipster0App.Secretlevel.' + projectcharge.secretlevel)"></td>
            <td>{{ projectcharge.requestdeportment }}</td>
            <td>{{ projectcharge.chargetype }}</td>
            <td>{{ projectcharge.chargecontent }}</td>
            <td>
              <div v-if="projectcharge.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectcharge.creatorid.id } }">{{
                  projectcharge.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProjectchargeView', params: { projectchargeId: projectcharge.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProjectchargeEdit', params: { projectchargeId: projectcharge.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(projectcharge)"
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
          id="jHipster0App.projectcharge.delete.question"
          data-cy="projectchargeDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-projectcharge-heading" v-text="t$('jHipster0App.projectcharge.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-projectcharge"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProjectcharge()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./projectcharge.component.ts"></script>
