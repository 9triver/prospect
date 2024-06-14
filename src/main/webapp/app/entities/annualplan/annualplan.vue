<template>
  <div>
    <h2 id="page-heading" data-cy="AnnualplanHeading">
      <span v-text="t$('jHipster3App.annualplan.home.title')" id="annualplan-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.annualplan.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'AnnualplanCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-annualplan"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.annualplan.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && annualplans && annualplans.length === 0">
      <span v-text="t$('jHipster3App.annualplan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="annualplans && annualplans.length > 0">
      <table class="table table-striped" aria-describedby="annualplans">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.annualplanid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.annualplanname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.year')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.document')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.monthplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.projectcharge')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.annualplan.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="annualplan in annualplans" :key="annualplan.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'AnnualplanView', params: { annualplanId: annualplan.id } }">{{ annualplan.id }}</router-link>
            </td>
            <td>{{ annualplan.annualplanid }}</td>
            <td>{{ annualplan.annualplanname }}</td>
            <td>{{ annualplan.year }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + annualplan.secretlevel)"></td>
            <td>{{ annualplan.creatorname }}</td>
            <td v-text="t$('jHipster3App.Annualplanstatus.' + annualplan.status)"></td>
            <td v-text="t$('jHipster3App.AuditStatus.' + annualplan.auditStatus)"></td>
            <td>
              <div v-if="annualplan.document">
                <router-link :to="{ name: 'DocumentView', params: { documentId: annualplan.document.id } }">{{
                  annualplan.document.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="annualplan.monthplan">
                <router-link :to="{ name: 'MonthplanView', params: { monthplanId: annualplan.monthplan.id } }">{{
                  annualplan.monthplan.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="annualplan.projectcharge">
                <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: annualplan.projectcharge.id } }">{{
                  annualplan.projectcharge.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="annualplan.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: annualplan.creatorid.id } }">{{
                  annualplan.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="annualplan.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: annualplan.auditorid.id } }">{{
                  annualplan.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'AnnualplanView', params: { annualplanId: annualplan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'AnnualplanEdit', params: { annualplanId: annualplan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(annualplan)"
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
          id="jHipster3App.annualplan.delete.question"
          data-cy="annualplanDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-annualplan-heading" v-text="t$('jHipster3App.annualplan.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-annualplan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeAnnualplan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./annualplan.component.ts"></script>
