<template>
  <div>
    <h2 id="page-heading" data-cy="CycleplanHeading">
      <span v-text="t$('jHipster3App.cycleplan.home.title')" id="cycleplan-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.cycleplan.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'CycleplanCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-cycleplan"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.cycleplan.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && cycleplans && cycleplans.length === 0">
      <span v-text="t$('jHipster3App.cycleplan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="cycleplans && cycleplans.length > 0">
      <table class="table table-striped" aria-describedby="cycleplans">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.cycleplanid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.cycleplanname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.actualstarttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.actualendtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.responsiblename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.document')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.annualplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.monthplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.projectcharge')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.cycleplan.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="cycleplan in cycleplans" :key="cycleplan.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CycleplanView', params: { cycleplanId: cycleplan.id } }">{{ cycleplan.id }}</router-link>
            </td>
            <td>{{ cycleplan.cycleplanid }}</td>
            <td>{{ cycleplan.cycleplanname }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + cycleplan.secretlevel)"></td>
            <td>{{ cycleplan.starttime }}</td>
            <td>{{ cycleplan.endtime }}</td>
            <td>{{ cycleplan.actualstarttime }}</td>
            <td>{{ cycleplan.actualendtime }}</td>
            <td>{{ cycleplan.responsiblename }}</td>
            <td v-text="t$('jHipster3App.Cycleplanstatus.' + cycleplan.status)"></td>
            <td v-text="t$('jHipster3App.AuditStatus.' + cycleplan.auditStatus)"></td>
            <td>
              <div v-if="cycleplan.document">
                <router-link :to="{ name: 'DocumentView', params: { documentId: cycleplan.document.id } }">{{
                  cycleplan.document.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="cycleplan.annualplan">
                <router-link :to="{ name: 'AnnualplanView', params: { annualplanId: cycleplan.annualplan.id } }">{{
                  cycleplan.annualplan.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="cycleplan.monthplan">
                <router-link :to="{ name: 'MonthplanView', params: { monthplanId: cycleplan.monthplan.id } }">{{
                  cycleplan.monthplan.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="cycleplan.projectcharge">
                <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: cycleplan.projectcharge.id } }">{{
                  cycleplan.projectcharge.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="cycleplan.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: cycleplan.responsibleid.id } }">{{
                  cycleplan.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="cycleplan.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: cycleplan.auditorid.id } }">{{
                  cycleplan.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CycleplanView', params: { cycleplanId: cycleplan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'CycleplanEdit', params: { cycleplanId: cycleplan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(cycleplan)"
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
        <span id="jHipster3App.cycleplan.delete.question" data-cy="cycleplanDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-cycleplan-heading" v-text="t$('jHipster3App.cycleplan.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-cycleplan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeCycleplan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./cycleplan.component.ts"></script>
