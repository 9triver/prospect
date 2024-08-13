<template>
  <div>
    <h2 id="page-heading" data-cy="FundsEstimationHeading">
      <span v-text="t$('jy1App.fundsEstimation.home.title')" id="funds-estimation-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.fundsEstimation.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'FundsEstimationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-funds-estimation"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.fundsEstimation.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && fundsEstimations && fundsEstimations.length === 0">
      <span v-text="t$('jy1App.fundsEstimation.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="fundsEstimations && fundsEstimations.length > 0">
      <table class="table table-striped" aria-describedby="fundsEstimations">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.name')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.source')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.unit')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.number')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.unitprice')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.materialfee')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.specialfee')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.outsourcingfee')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.totalbudgetprice')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.responsibleperson')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.fundsEstimation.projectwbs')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="fundsEstimation in fundsEstimations" :key="fundsEstimation.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'FundsEstimationView', params: { fundsEstimationId: fundsEstimation.id } }">{{
                fundsEstimation.id
              }}</router-link>
            </td>
            <td>{{ fundsEstimation.name }}</td>
            <td>{{ fundsEstimation.source }}</td>
            <td>{{ fundsEstimation.unit }}</td>
            <td>{{ fundsEstimation.number }}</td>
            <td>{{ fundsEstimation.unitprice }}</td>
            <td>{{ fundsEstimation.materialfee }}</td>
            <td>{{ fundsEstimation.specialfee }}</td>
            <td>{{ fundsEstimation.outsourcingfee }}</td>
            <td>{{ fundsEstimation.totalbudgetprice }}</td>
            <td>
              <div v-if="fundsEstimation.responsibleperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: fundsEstimation.responsibleperson.id } }">{{
                  fundsEstimation.responsibleperson.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="fundsEstimation.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: fundsEstimation.auditorid.id } }">{{
                  fundsEstimation.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(projectwbs, i) in fundsEstimation.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'FundsEstimationView', params: { fundsEstimationId: fundsEstimation.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'FundsEstimationEdit', params: { fundsEstimationId: fundsEstimation.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(fundsEstimation)"
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
          id="jy1App.fundsEstimation.delete.question"
          data-cy="fundsEstimationDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-fundsEstimation-heading" v-text="t$('jy1App.fundsEstimation.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-fundsEstimation"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeFundsEstimation()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./funds-estimation.component.ts"></script>
