<template>
  <div>
    <h2 id="page-heading" data-cy="PlanReturnsHeading">
      <span v-text="t$('jy1App.planReturns.home.title')" id="plan-returns-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.planReturns.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'PlanReturnsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-plan-returns"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.planReturns.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && planReturns && planReturns.length === 0">
      <span v-text="t$('jy1App.planReturns.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="planReturns && planReturns.length > 0">
      <table class="table table-striped" aria-describedby="planReturns">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.planreturnsname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.plantype')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.planlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.description')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.actualstarttime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.actualendtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.deliverables')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.progress')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.status')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.impactanalysis')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.returnstime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.rejectionreason')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.returnsstatus')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.responsibleperson')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.planReturns.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="planReturns in planReturns" :key="planReturns.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PlanReturnsView', params: { planReturnsId: planReturns.id } }">{{ planReturns.id }}</router-link>
            </td>
            <td>{{ planReturns.planreturnsname }}</td>
            <td>{{ planReturns.plantype }}</td>
            <td v-text="t$('jy1App.PlanLevel.' + planReturns.planlevel)"></td>
            <td>{{ planReturns.description }}</td>
            <td>{{ planReturns.actualstarttime }}</td>
            <td>{{ planReturns.actualendtime }}</td>
            <td>{{ planReturns.deliverables }}</td>
            <td>{{ planReturns.progress }}</td>
            <td v-text="t$('jy1App.Planstatus.' + planReturns.status)"></td>
            <td>{{ planReturns.impactanalysis }}</td>
            <td>{{ planReturns.returnstime }}</td>
            <td>{{ planReturns.rejectionreason }}</td>
            <td v-text="t$('jy1App.ReturnsStatus.' + planReturns.returnsstatus)"></td>
            <td>
              <div v-if="planReturns.responsibleperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: planReturns.responsibleperson.id } }">{{
                  planReturns.responsibleperson.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="planReturns.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: planReturns.auditorid.id } }">{{
                  planReturns.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PlanReturnsView', params: { planReturnsId: planReturns.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PlanReturnsEdit', params: { planReturnsId: planReturns.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(planReturns)"
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
        <span id="jy1App.planReturns.delete.question" data-cy="planReturnsDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-planReturns-heading" v-text="t$('jy1App.planReturns.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-planReturns"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePlanReturns()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./plan-returns.component.ts"></script>
