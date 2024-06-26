<template>
  <div>
    <h2 id="page-heading" data-cy="SecuritymanagementWbsHeading">
      <span v-text="t$('jHipster0App.securitymanagementWbs.home.title')" id="securitymanagement-wbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.securitymanagementWbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SecuritymanagementWbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-securitymanagement-wbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.securitymanagementWbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && securitymanagementWbs && securitymanagementWbs.length === 0">
      <span v-text="t$('jHipster0App.securitymanagementWbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="securitymanagementWbs && securitymanagementWbs.length > 0">
      <table class="table table-striped" aria-describedby="securitymanagementWbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagementWbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagementWbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagementWbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagementWbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagementWbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagementWbs.annualSecurityPlan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.securitymanagementWbs.safetycheck')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="securitymanagementWbs in securitymanagementWbs" :key="securitymanagementWbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SecuritymanagementWbsView', params: { securitymanagementWbsId: securitymanagementWbs.id } }">{{
                securitymanagementWbs.id
              }}</router-link>
            </td>
            <td>{{ securitymanagementWbs.wbsspare1 }}</td>
            <td>{{ securitymanagementWbs.wbsspare2 }}</td>
            <td>{{ securitymanagementWbs.wbsspare3 }}</td>
            <td>{{ securitymanagementWbs.wbsspare4 }}</td>
            <td>{{ securitymanagementWbs.wbsspare5 }}</td>
            <td>
              <div v-if="securitymanagementWbs.annualSecurityPlan">
                <router-link
                  :to="{ name: 'AnnualSecurityPlanView', params: { annualSecurityPlanId: securitymanagementWbs.annualSecurityPlan.id } }"
                  >{{ securitymanagementWbs.annualSecurityPlan.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="securitymanagementWbs.safetycheck">
                <router-link :to="{ name: 'SafetycheckView', params: { safetycheckId: securitymanagementWbs.safetycheck.id } }">{{
                  securitymanagementWbs.safetycheck.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SecuritymanagementWbsView', params: { securitymanagementWbsId: securitymanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SecuritymanagementWbsEdit', params: { securitymanagementWbsId: securitymanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(securitymanagementWbs)"
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
          id="jHipster0App.securitymanagementWbs.delete.question"
          data-cy="securitymanagementWbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-securitymanagementWbs-heading"
          v-text="t$('jHipster0App.securitymanagementWbs.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-securitymanagementWbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSecuritymanagementWbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./securitymanagement-wbs.component.ts"></script>
