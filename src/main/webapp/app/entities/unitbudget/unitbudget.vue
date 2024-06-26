<template>
  <div>
    <h2 id="page-heading" data-cy="UnitbudgetHeading">
      <span v-text="t$('jHipster0App.unitbudget.home.title')" id="unitbudget-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.unitbudget.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'UnitbudgetCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-unitbudget"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.unitbudget.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && unitbudgets && unitbudgets.length === 0">
      <span v-text="t$('jHipster0App.unitbudget.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="unitbudgets && unitbudgets.length > 0">
      <table class="table table-striped" aria-describedby="unitbudgets">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.subprojectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.unitbudgername')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.billingunit')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.number')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.totalbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.maintainerbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.outsourcingbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.earmarkedbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.testbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.unitbudget.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="unitbudget in unitbudgets" :key="unitbudget.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UnitbudgetView', params: { unitbudgetId: unitbudget.id } }">{{ unitbudget.id }}</router-link>
            </td>
            <td>{{ unitbudget.subprojectname }}</td>
            <td>{{ unitbudget.unitbudgername }}</td>
            <td>{{ unitbudget.billingunit }}</td>
            <td>{{ unitbudget.number }}</td>
            <td>{{ unitbudget.totalbudget }}</td>
            <td>{{ unitbudget.maintainerbudget }}</td>
            <td>{{ unitbudget.outsourcingbudget }}</td>
            <td>{{ unitbudget.earmarkedbudget }}</td>
            <td>{{ unitbudget.testbudget }}</td>
            <td>
              <div v-if="unitbudget.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: unitbudget.creatorid.id } }">{{
                  unitbudget.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="unitbudget.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: unitbudget.auditorid.id } }">{{
                  unitbudget.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'UnitbudgetView', params: { unitbudgetId: unitbudget.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'UnitbudgetEdit', params: { unitbudgetId: unitbudget.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(unitbudget)"
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
          id="jHipster0App.unitbudget.delete.question"
          data-cy="unitbudgetDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-unitbudget-heading" v-text="t$('jHipster0App.unitbudget.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-unitbudget"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeUnitbudget()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./unitbudget.component.ts"></script>
