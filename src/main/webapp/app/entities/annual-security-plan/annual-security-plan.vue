<template>
  <div>
    <h2 id="page-heading" data-cy="AnnualSecurityPlanHeading">
      <span v-text="t$('jHipster0App.annualSecurityPlan.home.title')" id="annual-security-plan-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.annualSecurityPlan.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'AnnualSecurityPlanCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-annual-security-plan"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.annualSecurityPlan.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && annualSecurityPlans && annualSecurityPlans.length === 0">
      <span v-text="t$('jHipster0App.annualSecurityPlan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="annualSecurityPlans && annualSecurityPlans.length > 0">
      <table class="table table-striped" aria-describedby="annualSecurityPlans">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.annualSecurityPlan.securityplanname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.annualSecurityPlan.releasetime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.annualSecurityPlan.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.annualSecurityPlan.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.annualSecurityPlan.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.annualSecurityPlan.version')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.annualSecurityPlan.project')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.annualSecurityPlan.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.annualSecurityPlan.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="annualSecurityPlan in annualSecurityPlans" :key="annualSecurityPlan.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'AnnualSecurityPlanView', params: { annualSecurityPlanId: annualSecurityPlan.id } }">{{
                annualSecurityPlan.id
              }}</router-link>
            </td>
            <td>{{ annualSecurityPlan.securityplanname }}</td>
            <td>{{ annualSecurityPlan.releasetime }}</td>
            <td>{{ annualSecurityPlan.createtime }}</td>
            <td>{{ annualSecurityPlan.creatorname }}</td>
            <td v-text="t$('jHipster0App.AuditStatus.' + annualSecurityPlan.auditStatus)"></td>
            <td>{{ annualSecurityPlan.version }}</td>
            <td>
              <div v-if="annualSecurityPlan.project">
                <router-link :to="{ name: 'ProjectView', params: { projectId: annualSecurityPlan.project.id } }">{{
                  annualSecurityPlan.project.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="annualSecurityPlan.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: annualSecurityPlan.creatorid.id } }">{{
                  annualSecurityPlan.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="annualSecurityPlan.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: annualSecurityPlan.auditorid.id } }">{{
                  annualSecurityPlan.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'AnnualSecurityPlanView', params: { annualSecurityPlanId: annualSecurityPlan.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'AnnualSecurityPlanEdit', params: { annualSecurityPlanId: annualSecurityPlan.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(annualSecurityPlan)"
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
          id="jHipster0App.annualSecurityPlan.delete.question"
          data-cy="annualSecurityPlanDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-annualSecurityPlan-heading" v-text="t$('jHipster0App.annualSecurityPlan.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-annualSecurityPlan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeAnnualSecurityPlan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./annual-security-plan.component.ts"></script>
