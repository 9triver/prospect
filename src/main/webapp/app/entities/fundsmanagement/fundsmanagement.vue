<template>
  <div>
    <h2 id="page-heading" data-cy="FundsmanagementHeading">
      <span v-text="t$('jHipster3App.fundsmanagement.home.title')" id="fundsmanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.fundsmanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'FundsmanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-fundsmanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.fundsmanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && fundsmanagements && fundsmanagements.length === 0">
      <span v-text="t$('jHipster3App.fundsmanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="fundsmanagements && fundsmanagements.length > 0">
      <table class="table table-striped" aria-describedby="fundsmanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.fundsid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.year')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.budgit')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.dapartmentid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.draftapproval')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.totalbudgetid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.unitbudgetid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.documentid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.maintainerid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.totalbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.unitbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.document')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.fundsmanagement.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="fundsmanagement in fundsmanagements" :key="fundsmanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: fundsmanagement.id } }">{{
                fundsmanagement.id
              }}</router-link>
            </td>
            <td>{{ fundsmanagement.fundsid }}</td>
            <td>{{ fundsmanagement.createtime }}</td>
            <td>{{ fundsmanagement.creatorname }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + fundsmanagement.secretlevel)"></td>
            <td>{{ fundsmanagement.year }}</td>
            <td>{{ fundsmanagement.budgit }}</td>
            <td>{{ fundsmanagement.dapartmentid }}</td>
            <td>{{ fundsmanagement.draftapproval }}</td>
            <td>{{ fundsmanagement.totalbudgetid }}</td>
            <td>{{ fundsmanagement.unitbudgetid }}</td>
            <td>{{ fundsmanagement.documentid }}</td>
            <td>{{ fundsmanagement.maintainerid }}</td>
            <td v-text="t$('jHipster3App.AuditStatus.' + fundsmanagement.auditStatus)"></td>
            <td>
              <div v-if="fundsmanagement.totalbudget">
                <router-link :to="{ name: 'TotalbudgetView', params: { totalbudgetId: fundsmanagement.totalbudget.id } }">{{
                  fundsmanagement.totalbudget.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="fundsmanagement.unitbudget">
                <router-link :to="{ name: 'UnitbudgetView', params: { unitbudgetId: fundsmanagement.unitbudget.id } }">{{
                  fundsmanagement.unitbudget.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="fundsmanagement.document">
                <router-link :to="{ name: 'DocumentView', params: { documentId: fundsmanagement.document.id } }">{{
                  fundsmanagement.document.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="fundsmanagement.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: fundsmanagement.creatorid.id } }">{{
                  fundsmanagement.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="fundsmanagement.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: fundsmanagement.auditorid.id } }">{{
                  fundsmanagement.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: fundsmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'FundsmanagementEdit', params: { fundsmanagementId: fundsmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(fundsmanagement)"
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
          id="jHipster3App.fundsmanagement.delete.question"
          data-cy="fundsmanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-fundsmanagement-heading" v-text="t$('jHipster3App.fundsmanagement.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-fundsmanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeFundsmanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./fundsmanagement.component.ts"></script>
