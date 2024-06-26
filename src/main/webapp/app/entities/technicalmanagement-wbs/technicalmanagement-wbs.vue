<template>
  <div>
    <h2 id="page-heading" data-cy="TechnicalmanagementWbsHeading">
      <span v-text="t$('jHipster0App.technicalmanagementWbs.home.title')" id="technicalmanagement-wbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.technicalmanagementWbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'TechnicalmanagementWbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-technicalmanagement-wbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.technicalmanagementWbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && technicalmanagementWbs && technicalmanagementWbs.length === 0">
      <span v-text="t$('jHipster0App.technicalmanagementWbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="technicalmanagementWbs && technicalmanagementWbs.length > 0">
      <table class="table table-striped" aria-describedby="technicalmanagementWbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagementWbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagementWbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagementWbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagementWbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagementWbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.technicalmanagementWbs.technicalCondition')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="technicalmanagementWbs in technicalmanagementWbs" :key="technicalmanagementWbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TechnicalmanagementWbsView', params: { technicalmanagementWbsId: technicalmanagementWbs.id } }">{{
                technicalmanagementWbs.id
              }}</router-link>
            </td>
            <td>{{ technicalmanagementWbs.wbsspare1 }}</td>
            <td>{{ technicalmanagementWbs.wbsspare2 }}</td>
            <td>{{ technicalmanagementWbs.wbsspare3 }}</td>
            <td>{{ technicalmanagementWbs.wbsspare4 }}</td>
            <td>{{ technicalmanagementWbs.wbsspare5 }}</td>
            <td>
              <div v-if="technicalmanagementWbs.technicalCondition">
                <router-link
                  :to="{ name: 'TechnicalConditionView', params: { technicalConditionId: technicalmanagementWbs.technicalCondition.id } }"
                  >{{ technicalmanagementWbs.technicalCondition.id }}</router-link
                >
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'TechnicalmanagementWbsView', params: { technicalmanagementWbsId: technicalmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TechnicalmanagementWbsEdit', params: { technicalmanagementWbsId: technicalmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(technicalmanagementWbs)"
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
          id="jHipster0App.technicalmanagementWbs.delete.question"
          data-cy="technicalmanagementWbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-technicalmanagementWbs-heading"
          v-text="t$('jHipster0App.technicalmanagementWbs.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-technicalmanagementWbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTechnicalmanagementWbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./technicalmanagement-wbs.component.ts"></script>
