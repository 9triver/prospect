<template>
  <div>
    <h2 id="page-heading" data-cy="PlanstrategyHeading">
      <span v-text="t$('jHipster3App.planstrategy.home.title')" id="planstrategy-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.planstrategy.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'PlanstrategyCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-planstrategy"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.planstrategy.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && planstrategies && planstrategies.length === 0">
      <span v-text="t$('jHipster3App.planstrategy.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="planstrategies && planstrategies.length > 0">
      <table class="table table-striped" aria-describedby="planstrategies">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planstrategy.strategyid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planstrategy.strategyname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planstrategy.number')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planstrategy.type')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planstrategy.decument')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planstrategy.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.planstrategy.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="planstrategy in planstrategies" :key="planstrategy.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PlanstrategyView', params: { planstrategyId: planstrategy.id } }">{{
                planstrategy.id
              }}</router-link>
            </td>
            <td>{{ planstrategy.strategyid }}</td>
            <td>{{ planstrategy.strategyname }}</td>
            <td>{{ planstrategy.number }}</td>
            <td>{{ planstrategy.type }}</td>
            <td>
              <div v-if="planstrategy.decument">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: planstrategy.decument.id } }">{{
                  planstrategy.decument.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="planstrategy.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: planstrategy.responsibleid.id } }">{{
                  planstrategy.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="planstrategy.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: planstrategy.auditorid.id } }">{{
                  planstrategy.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PlanstrategyView', params: { planstrategyId: planstrategy.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PlanstrategyEdit', params: { planstrategyId: planstrategy.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(planstrategy)"
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
          id="jHipster3App.planstrategy.delete.question"
          data-cy="planstrategyDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-planstrategy-heading" v-text="t$('jHipster3App.planstrategy.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-planstrategy"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePlanstrategy()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./planstrategy.component.ts"></script>
