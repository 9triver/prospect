<template>
  <div>
    <h2 id="page-heading" data-cy="ProgressmanagementWbsHeading">
      <span v-text="t$('jHipster0App.progressmanagementWbs.home.title')" id="progressmanagement-wbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.progressmanagementWbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProgressmanagementWbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-progressmanagement-wbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.progressmanagementWbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && progressmanagementWbs && progressmanagementWbs.length === 0">
      <span v-text="t$('jHipster0App.progressmanagementWbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="progressmanagementWbs && progressmanagementWbs.length > 0">
      <table class="table table-striped" aria-describedby="progressmanagementWbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressmanagementWbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressmanagementWbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressmanagementWbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressmanagementWbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressmanagementWbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressmanagementWbs.progressplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressmanagementWbs.progressplanreturns')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressmanagementWbs.progressbaseline')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="progressmanagementWbs in progressmanagementWbs" :key="progressmanagementWbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProgressmanagementWbsView', params: { progressmanagementWbsId: progressmanagementWbs.id } }">{{
                progressmanagementWbs.id
              }}</router-link>
            </td>
            <td>{{ progressmanagementWbs.wbsspare1 }}</td>
            <td>{{ progressmanagementWbs.wbsspare2 }}</td>
            <td>{{ progressmanagementWbs.wbsspare3 }}</td>
            <td>{{ progressmanagementWbs.wbsspare4 }}</td>
            <td>{{ progressmanagementWbs.wbsspare5 }}</td>
            <td>
              <div v-if="progressmanagementWbs.progressplan">
                <router-link :to="{ name: 'ProgressplanView', params: { progressplanId: progressmanagementWbs.progressplan.id } }">{{
                  progressmanagementWbs.progressplan.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressmanagementWbs.progressplanreturns">
                <router-link
                  :to="{ name: 'ProgressplanreturnsView', params: { progressplanreturnsId: progressmanagementWbs.progressplanreturns.id } }"
                  >{{ progressmanagementWbs.progressplanreturns.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="progressmanagementWbs.progressbaseline">
                <router-link
                  :to="{ name: 'ProgressbaselineView', params: { progressbaselineId: progressmanagementWbs.progressbaseline.id } }"
                  >{{ progressmanagementWbs.progressbaseline.id }}</router-link
                >
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProgressmanagementWbsView', params: { progressmanagementWbsId: progressmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProgressmanagementWbsEdit', params: { progressmanagementWbsId: progressmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(progressmanagementWbs)"
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
          id="jHipster0App.progressmanagementWbs.delete.question"
          data-cy="progressmanagementWbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-progressmanagementWbs-heading"
          v-text="t$('jHipster0App.progressmanagementWbs.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-progressmanagementWbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProgressmanagementWbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./progressmanagement-wbs.component.ts"></script>
