<template>
  <div>
    <h2 id="page-heading" data-cy="ResourcemanagementWbsHeading">
      <span v-text="t$('jHipster0App.resourcemanagementWbs.home.title')" id="resourcemanagement-wbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.resourcemanagementWbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ResourcemanagementWbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-resourcemanagement-wbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.resourcemanagementWbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && resourcemanagementWbs && resourcemanagementWbs.length === 0">
      <span v-text="t$('jHipster0App.resourcemanagementWbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="resourcemanagementWbs && resourcemanagementWbs.length > 0">
      <table class="table table-striped" aria-describedby="resourcemanagementWbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.resourcemanagementWbs.projectHumanresourcesplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.resourcemanagementWbs.projectremit')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.resourcemanagementWbs.humanresources')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="resourcemanagementWbs in resourcemanagementWbs" :key="resourcemanagementWbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ResourcemanagementWbsView', params: { resourcemanagementWbsId: resourcemanagementWbs.id } }">{{
                resourcemanagementWbs.id
              }}</router-link>
            </td>
            <td>{{ resourcemanagementWbs.wbsspare1 }}</td>
            <td>{{ resourcemanagementWbs.wbsspare2 }}</td>
            <td>{{ resourcemanagementWbs.wbsspare3 }}</td>
            <td>{{ resourcemanagementWbs.wbsspare4 }}</td>
            <td>{{ resourcemanagementWbs.wbsspare5 }}</td>
            <td>
              <div v-if="resourcemanagementWbs.projectHumanresourcesplan">
                <router-link
                  :to="{
                    name: 'ProjectHumanresourcesplanView',
                    params: { projectHumanresourcesplanId: resourcemanagementWbs.projectHumanresourcesplan.id },
                  }"
                  >{{ resourcemanagementWbs.projectHumanresourcesplan.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="resourcemanagementWbs.projectremit">
                <router-link :to="{ name: 'ProjectremitView', params: { projectremitId: resourcemanagementWbs.projectremit.id } }">{{
                  resourcemanagementWbs.projectremit.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="resourcemanagementWbs.humanresources">
                <router-link :to="{ name: 'HumanresourcesView', params: { humanresourcesId: resourcemanagementWbs.humanresources.id } }">{{
                  resourcemanagementWbs.humanresources.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ResourcemanagementWbsView', params: { resourcemanagementWbsId: resourcemanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ResourcemanagementWbsEdit', params: { resourcemanagementWbsId: resourcemanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(resourcemanagementWbs)"
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
          id="jHipster0App.resourcemanagementWbs.delete.question"
          data-cy="resourcemanagementWbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-resourcemanagementWbs-heading"
          v-text="t$('jHipster0App.resourcemanagementWbs.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-resourcemanagementWbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeResourcemanagementWbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./resourcemanagement-wbs.component.ts"></script>
