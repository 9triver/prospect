<template>
  <div>
    <h2 id="page-heading" data-cy="ComprehensiveledgerHeading">
      <span v-text="t$('jHipster0App.comprehensiveledger.home.title')" id="comprehensiveledger-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.comprehensiveledger.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ComprehensiveledgerCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-comprehensiveledger"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.comprehensiveledger.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && comprehensiveledgers && comprehensiveledgers.length === 0">
      <span v-text="t$('jHipster0App.comprehensiveledger.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="comprehensiveledgers && comprehensiveledgers.length > 0">
      <table class="table table-striped" aria-describedby="comprehensiveledgers">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensiveledger.fundsname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensiveledger.wbsname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensiveledger.unitname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensiveledger.budgetsection')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensiveledger.purpose')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensiveledger.unit')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensiveledger.number')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensiveledger.unitprice')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensiveledger.foreignexchange')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="comprehensiveledger in comprehensiveledgers" :key="comprehensiveledger.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ComprehensiveledgerView', params: { comprehensiveledgerId: comprehensiveledger.id } }">{{
                comprehensiveledger.id
              }}</router-link>
            </td>
            <td>{{ comprehensiveledger.fundsname }}</td>
            <td>{{ comprehensiveledger.wbsname }}</td>
            <td>{{ comprehensiveledger.unitname }}</td>
            <td>{{ comprehensiveledger.budgetsection }}</td>
            <td>{{ comprehensiveledger.purpose }}</td>
            <td>{{ comprehensiveledger.unit }}</td>
            <td>{{ comprehensiveledger.number }}</td>
            <td>{{ comprehensiveledger.unitprice }}</td>
            <td>{{ comprehensiveledger.foreignexchange }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ComprehensiveledgerView', params: { comprehensiveledgerId: comprehensiveledger.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ComprehensiveledgerEdit', params: { comprehensiveledgerId: comprehensiveledger.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(comprehensiveledger)"
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
          id="jHipster0App.comprehensiveledger.delete.question"
          data-cy="comprehensiveledgerDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-comprehensiveledger-heading"
          v-text="t$('jHipster0App.comprehensiveledger.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-comprehensiveledger"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeComprehensiveledger()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./comprehensiveledger.component.ts"></script>
