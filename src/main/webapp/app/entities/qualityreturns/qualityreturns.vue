<template>
  <div>
    <h2 id="page-heading" data-cy="QualityreturnsHeading">
      <span v-text="t$('jHipster0App.qualityreturns.home.title')" id="qualityreturns-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.qualityreturns.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'QualityreturnsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-qualityreturns"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.qualityreturns.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualityreturns && qualityreturns.length === 0">
      <span v-text="t$('jHipster0App.qualityreturns.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualityreturns && qualityreturns.length > 0">
      <table class="table table-striped" aria-describedby="qualityreturns">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityreturns.qualityreturnsname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityreturns.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityreturns.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityreturns.qualitytype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityreturns.returnstime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityreturns.returnsstatus')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="qualityreturns in qualityreturns" :key="qualityreturns.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'QualityreturnsView', params: { qualityreturnsId: qualityreturns.id } }">{{
                qualityreturns.id
              }}</router-link>
            </td>
            <td>{{ qualityreturns.qualityreturnsname }}</td>
            <td>{{ qualityreturns.starttime }}</td>
            <td>{{ qualityreturns.endtime }}</td>
            <td>{{ qualityreturns.qualitytype }}</td>
            <td>{{ qualityreturns.returnstime }}</td>
            <td v-text="t$('jHipster0App.ReturnsStatus.' + qualityreturns.returnsstatus)"></td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'QualityreturnsView', params: { qualityreturnsId: qualityreturns.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'QualityreturnsEdit', params: { qualityreturnsId: qualityreturns.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(qualityreturns)"
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
          id="jHipster0App.qualityreturns.delete.question"
          data-cy="qualityreturnsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-qualityreturns-heading" v-text="t$('jHipster0App.qualityreturns.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-qualityreturns"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeQualityreturns()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./qualityreturns.component.ts"></script>
