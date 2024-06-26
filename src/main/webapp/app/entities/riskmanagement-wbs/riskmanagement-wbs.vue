<template>
  <div>
    <h2 id="page-heading" data-cy="RiskmanagementWbsHeading">
      <span v-text="t$('jHipster0App.riskmanagementWbs.home.title')" id="riskmanagement-wbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.riskmanagementWbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RiskmanagementWbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-riskmanagement-wbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.riskmanagementWbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && riskmanagementWbs && riskmanagementWbs.length === 0">
      <span v-text="t$('jHipster0App.riskmanagementWbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="riskmanagementWbs && riskmanagementWbs.length > 0">
      <table class="table table-striped" aria-describedby="riskmanagementWbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagementWbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagementWbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagementWbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagementWbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagementWbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagementWbs.riskidentification')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskmanagementWbs.riskreport')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="riskmanagementWbs in riskmanagementWbs" :key="riskmanagementWbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RiskmanagementWbsView', params: { riskmanagementWbsId: riskmanagementWbs.id } }">{{
                riskmanagementWbs.id
              }}</router-link>
            </td>
            <td>{{ riskmanagementWbs.wbsspare1 }}</td>
            <td>{{ riskmanagementWbs.wbsspare2 }}</td>
            <td>{{ riskmanagementWbs.wbsspare3 }}</td>
            <td>{{ riskmanagementWbs.wbsspare4 }}</td>
            <td>{{ riskmanagementWbs.wbsspare5 }}</td>
            <td>
              <div v-if="riskmanagementWbs.riskidentification">
                <router-link
                  :to="{ name: 'RiskidentificationView', params: { riskidentificationId: riskmanagementWbs.riskidentification.id } }"
                  >{{ riskmanagementWbs.riskidentification.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="riskmanagementWbs.riskreport">
                <router-link :to="{ name: 'RiskreportView', params: { riskreportId: riskmanagementWbs.riskreport.id } }">{{
                  riskmanagementWbs.riskreport.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RiskmanagementWbsView', params: { riskmanagementWbsId: riskmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RiskmanagementWbsEdit', params: { riskmanagementWbsId: riskmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(riskmanagementWbs)"
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
          id="jHipster0App.riskmanagementWbs.delete.question"
          data-cy="riskmanagementWbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-riskmanagementWbs-heading" v-text="t$('jHipster0App.riskmanagementWbs.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-riskmanagementWbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRiskmanagementWbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./riskmanagement-wbs.component.ts"></script>
