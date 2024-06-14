<template>
  <div>
    <h2 id="page-heading" data-cy="ProgressmanagementHeading">
      <span v-text="t$('jHipster3App.progressmanagement.home.title')" id="progressmanagement-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.progressmanagement.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProgressmanagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-progressmanagement"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.progressmanagement.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && progressmanagements && progressmanagements.length === 0">
      <span v-text="t$('jHipster3App.progressmanagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="progressmanagements && progressmanagements.length > 0">
      <table class="table table-striped" aria-describedby="progressmanagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.progressid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.progressname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.progresstype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.workfocus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.responsiblename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.baselineid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.department')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.planreturns')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.progressmanagement.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="progressmanagement in progressmanagements" :key="progressmanagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProgressmanagementView', params: { progressmanagementId: progressmanagement.id } }">{{
                progressmanagement.id
              }}</router-link>
            </td>
            <td>{{ progressmanagement.progressid }}</td>
            <td>{{ progressmanagement.progressname }}</td>
            <td v-text="t$('jHipster3App.Progresstype.' + progressmanagement.progresstype)"></td>
            <td>{{ progressmanagement.workfocus }}</td>
            <td>{{ progressmanagement.createtime }}</td>
            <td>{{ progressmanagement.creatorname }}</td>
            <td>{{ progressmanagement.responsiblename }}</td>
            <td v-text="t$('jHipster3App.Progressstatus.' + progressmanagement.status)"></td>
            <td>{{ progressmanagement.baselineid }}</td>
            <td v-text="t$('jHipster3App.AuditStatus.' + progressmanagement.auditStatus)"></td>
            <td>
              <div v-if="progressmanagement.department">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: progressmanagement.department.id } }">{{
                  progressmanagement.department.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressmanagement.planreturns">
                <router-link :to="{ name: 'PlanreturnsView', params: { planreturnsId: progressmanagement.planreturns.id } }">{{
                  progressmanagement.planreturns.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressmanagement.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: progressmanagement.responsibleid.id } }">{{
                  progressmanagement.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressmanagement.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: progressmanagement.creatorid.id } }">{{
                  progressmanagement.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressmanagement.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: progressmanagement.auditorid.id } }">{{
                  progressmanagement.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProgressmanagementView', params: { progressmanagementId: progressmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProgressmanagementEdit', params: { progressmanagementId: progressmanagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(progressmanagement)"
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
          id="jHipster3App.progressmanagement.delete.question"
          data-cy="progressmanagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-progressmanagement-heading" v-text="t$('jHipster3App.progressmanagement.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-progressmanagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProgressmanagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./progressmanagement.component.ts"></script>
