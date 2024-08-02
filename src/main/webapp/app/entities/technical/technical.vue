<template>
  <div>
    <h2 id="page-heading" data-cy="TechnicalHeading">
      <span v-text="t$('jy1App.technical.home.title')" id="technical-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.technical.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'TechnicalCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-technical"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.technical.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && technicals && technicals.length === 0">
      <span v-text="t$('jy1App.technical.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="technicals && technicals.length > 0">
      <table class="table table-striped" aria-describedby="technicals">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.technical.name')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.technical.description')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.technical.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.technical.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.technical.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.technical.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.technical.projectwbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="technical in technicals" :key="technical.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TechnicalView', params: { technicalId: technical.id } }">{{ technical.id }}</router-link>
            </td>
            <td>{{ technical.name }}</td>
            <td>{{ technical.description }}</td>
            <td>{{ technical.starttime }}</td>
            <td>{{ technical.endtime }}</td>
            <td>
              <div v-if="technical.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: technical.creatorid.id } }">{{
                  technical.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="technical.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: technical.auditorid.id } }">{{
                  technical.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(projectwbs, i) in technical.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TechnicalView', params: { technicalId: technical.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TechnicalEdit', params: { technicalId: technical.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(technical)"
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
        <span id="jy1App.technical.delete.question" data-cy="technicalDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-technical-heading" v-text="t$('jy1App.technical.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-technical"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTechnical()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./technical.component.ts"></script>
