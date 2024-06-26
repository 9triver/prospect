<template>
  <div>
    <h2 id="page-heading" data-cy="ProgressplanHeading">
      <span v-text="t$('jHipster0App.progressplan.home.title')" id="progressplan-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.progressplan.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProgressplanCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-progressplan"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.progressplan.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && progressplans && progressplans.length === 0">
      <span v-text="t$('jHipster0App.progressplan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="progressplans && progressplans.length > 0">
      <table class="table table-striped" aria-describedby="progressplans">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.progressname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.progresstype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.workfocus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.responsiblename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.department')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.planreturns')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.progressplan.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="progressplan in progressplans" :key="progressplan.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProgressplanView', params: { progressplanId: progressplan.id } }">{{
                progressplan.id
              }}</router-link>
            </td>
            <td>{{ progressplan.progressname }}</td>
            <td v-text="t$('jHipster0App.Progresstype.' + progressplan.progresstype)"></td>
            <td>{{ progressplan.workfocus }}</td>
            <td>{{ progressplan.createtime }}</td>
            <td>{{ progressplan.creatorname }}</td>
            <td>{{ progressplan.responsiblename }}</td>
            <td v-text="t$('jHipster0App.Progressstatus.' + progressplan.status)"></td>
            <td v-text="t$('jHipster0App.AuditStatus.' + progressplan.auditStatus)"></td>
            <td>
              <div v-if="progressplan.department">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: progressplan.department.id } }">{{
                  progressplan.department.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressplan.planreturns">
                <router-link :to="{ name: 'PlanreturnsView', params: { planreturnsId: progressplan.planreturns.id } }">{{
                  progressplan.planreturns.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressplan.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: progressplan.responsibleid.id } }">{{
                  progressplan.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressplan.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: progressplan.creatorid.id } }">{{
                  progressplan.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="progressplan.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: progressplan.auditorid.id } }">{{
                  progressplan.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProgressplanView', params: { progressplanId: progressplan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProgressplanEdit', params: { progressplanId: progressplan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(progressplan)"
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
          id="jHipster0App.progressplan.delete.question"
          data-cy="progressplanDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-progressplan-heading" v-text="t$('jHipster0App.progressplan.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-progressplan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProgressplan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./progressplan.component.ts"></script>
