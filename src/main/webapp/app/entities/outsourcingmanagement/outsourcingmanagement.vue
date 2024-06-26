<template>
  <div>
    <h2 id="page-heading" data-cy="OutsourcingmanagementHeading">
      <span v-text="t$('jHipster0App.outsourcingmanagement.home.title')" id="outsourcingmanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.outsourcingmanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OutsourcingmanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-outsourcingmanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.outsourcingmanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && outsourcingmanagements && outsourcingmanagements.length === 0">
      <span v-text="t$('jHipster0App.outsourcingmanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="outsourcingmanagements && outsourcingmanagements.length > 0">
      <table class="table table-striped" aria-describedby="outsourcingmanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagement.name')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagement.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagement.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagement.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.outsourcingmanagement.wbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="outsourcingmanagement in outsourcingmanagements" :key="outsourcingmanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'OutsourcingmanagementView', params: { outsourcingmanagementId: outsourcingmanagement.id } }">{{
                outsourcingmanagement.id
              }}</router-link>
            </td>
            <td>{{ outsourcingmanagement.name }}</td>
            <td>{{ outsourcingmanagement.description }}</td>
            <td>{{ outsourcingmanagement.starttime }}</td>
            <td>{{ outsourcingmanagement.endtime }}</td>
            <td>
              <div v-if="outsourcingmanagement.wbs">
                <router-link
                  :to="{ name: 'OutsourcingmanagementWbsView', params: { outsourcingmanagementWbsId: outsourcingmanagement.wbs.id } }"
                  >{{ outsourcingmanagement.wbs.id }}</router-link
                >
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OutsourcingmanagementView', params: { outsourcingmanagementId: outsourcingmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OutsourcingmanagementEdit', params: { outsourcingmanagementId: outsourcingmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(outsourcingmanagement)"
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
          id="jHipster0App.outsourcingmanagement.delete.question"
          data-cy="outsourcingmanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-outsourcingmanagement-heading"
          v-text="t$('jHipster0App.outsourcingmanagement.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-outsourcingmanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOutsourcingmanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./outsourcingmanagement.component.ts"></script>
