<template>
  <div>
    <h2 id="page-heading" data-cy="SecrecymanagementWbsHeading">
      <span v-text="t$('jHipster0App.secrecymanagementWbs.home.title')" id="secrecymanagement-wbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.secrecymanagementWbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SecrecymanagementWbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-secrecymanagement-wbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.secrecymanagementWbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && secrecymanagementWbs && secrecymanagementWbs.length === 0">
      <span v-text="t$('jHipster0App.secrecymanagementWbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="secrecymanagementWbs && secrecymanagementWbs.length > 0">
      <table class="table table-striped" aria-describedby="secrecymanagementWbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecymanagementWbs.secrecysystem')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.secrecymanagementWbs.projectSecrecy')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="secrecymanagementWbs in secrecymanagementWbs" :key="secrecymanagementWbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SecrecymanagementWbsView', params: { secrecymanagementWbsId: secrecymanagementWbs.id } }">{{
                secrecymanagementWbs.id
              }}</router-link>
            </td>
            <td>{{ secrecymanagementWbs.wbsspare1 }}</td>
            <td>{{ secrecymanagementWbs.wbsspare2 }}</td>
            <td>{{ secrecymanagementWbs.wbsspare3 }}</td>
            <td>{{ secrecymanagementWbs.wbsspare4 }}</td>
            <td>{{ secrecymanagementWbs.wbsspare5 }}</td>
            <td>
              <div v-if="secrecymanagementWbs.secrecysystem">
                <router-link :to="{ name: 'SecrecysystemView', params: { secrecysystemId: secrecymanagementWbs.secrecysystem.id } }">{{
                  secrecymanagementWbs.secrecysystem.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="secrecymanagementWbs.projectSecrecy">
                <router-link :to="{ name: 'ProjectSecrecyView', params: { projectSecrecyId: secrecymanagementWbs.projectSecrecy.id } }">{{
                  secrecymanagementWbs.projectSecrecy.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SecrecymanagementWbsView', params: { secrecymanagementWbsId: secrecymanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SecrecymanagementWbsEdit', params: { secrecymanagementWbsId: secrecymanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(secrecymanagementWbs)"
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
          id="jHipster0App.secrecymanagementWbs.delete.question"
          data-cy="secrecymanagementWbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-secrecymanagementWbs-heading"
          v-text="t$('jHipster0App.secrecymanagementWbs.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-secrecymanagementWbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSecrecymanagementWbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./secrecymanagement-wbs.component.ts"></script>
