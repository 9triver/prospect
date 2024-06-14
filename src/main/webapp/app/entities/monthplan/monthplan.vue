<template>
  <div>
    <h2 id="page-heading" data-cy="MonthplanHeading">
      <span v-text="t$('jHipster3App.monthplan.home.title')" id="monthplan-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.monthplan.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'MonthplanCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-monthplan"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.monthplan.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && monthplans && monthplans.length === 0">
      <span v-text="t$('jHipster3App.monthplan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="monthplans && monthplans.length > 0">
      <table class="table table-striped" aria-describedby="monthplans">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.monthplanid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.monthplanname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.month')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.document')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.planreturns')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.projectcharge')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.monthplan.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="monthplan in monthplans" :key="monthplan.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MonthplanView', params: { monthplanId: monthplan.id } }">{{ monthplan.id }}</router-link>
            </td>
            <td>{{ monthplan.monthplanid }}</td>
            <td>{{ monthplan.monthplanname }}</td>
            <td>{{ monthplan.month }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + monthplan.secretlevel)"></td>
            <td>{{ monthplan.creatorname }}</td>
            <td v-text="t$('jHipster3App.Annualplanstatus.' + monthplan.status)"></td>
            <td v-text="t$('jHipster3App.AuditStatus.' + monthplan.auditStatus)"></td>
            <td>
              <div v-if="monthplan.document">
                <router-link :to="{ name: 'DocumentView', params: { documentId: monthplan.document.id } }">{{
                  monthplan.document.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="monthplan.planreturns">
                <router-link :to="{ name: 'PlanexecuteView', params: { planexecuteId: monthplan.planreturns.id } }">{{
                  monthplan.planreturns.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="monthplan.projectcharge">
                <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: monthplan.projectcharge.id } }">{{
                  monthplan.projectcharge.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="monthplan.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: monthplan.creatorid.id } }">{{
                  monthplan.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="monthplan.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: monthplan.auditorid.id } }">{{
                  monthplan.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'MonthplanView', params: { monthplanId: monthplan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'MonthplanEdit', params: { monthplanId: monthplan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(monthplan)"
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
        <span id="jHipster3App.monthplan.delete.question" data-cy="monthplanDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-monthplan-heading" v-text="t$('jHipster3App.monthplan.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-monthplan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeMonthplan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./monthplan.component.ts"></script>
