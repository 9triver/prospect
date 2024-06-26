<template>
  <div>
    <h2 id="page-heading" data-cy="ComprehensivecontrolHeading">
      <span v-text="t$('jHipster0App.comprehensivecontrol.home.title')" id="comprehensivecontrol-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.comprehensivecontrol.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ComprehensivecontrolCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-comprehensivecontrol"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.comprehensivecontrol.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && comprehensivecontrols && comprehensivecontrols.length === 0">
      <span v-text="t$('jHipster0App.comprehensivecontrol.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="comprehensivecontrols && comprehensivecontrols.length > 0">
      <table class="table table-striped" aria-describedby="comprehensivecontrols">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.number')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.type')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.actualstarttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.actualendtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.result')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.acceptancetype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.responsiblename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.progress')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.project')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.funds')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.totalbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.unitbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.decument')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.comprehensivecontrol.coordinationdepartment')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="comprehensivecontrol in comprehensivecontrols" :key="comprehensivecontrol.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ComprehensivecontrolView', params: { comprehensivecontrolId: comprehensivecontrol.id } }">{{
                comprehensivecontrol.id
              }}</router-link>
            </td>
            <td>{{ comprehensivecontrol.description }}</td>
            <td>{{ comprehensivecontrol.number }}</td>
            <td>{{ comprehensivecontrol.type }}</td>
            <td>{{ comprehensivecontrol.starttime }}</td>
            <td>{{ comprehensivecontrol.endtime }}</td>
            <td>{{ comprehensivecontrol.actualstarttime }}</td>
            <td>{{ comprehensivecontrol.actualendtime }}</td>
            <td>{{ comprehensivecontrol.result }}</td>
            <td>{{ comprehensivecontrol.acceptancetype }}</td>
            <td v-text="t$('jHipster0App.ProjectStatus.' + comprehensivecontrol.status)"></td>
            <td v-text="t$('jHipster0App.AuditStatus.' + comprehensivecontrol.auditStatus)"></td>
            <td>{{ comprehensivecontrol.responsiblename }}</td>
            <td>
              <div v-if="comprehensivecontrol.progress">
                <router-link :to="{ name: 'ProgressplanView', params: { progressplanId: comprehensivecontrol.progress.id } }">{{
                  comprehensivecontrol.progress.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="comprehensivecontrol.project">
                <router-link :to="{ name: 'ProjectView', params: { projectId: comprehensivecontrol.project.id } }">{{
                  comprehensivecontrol.project.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="comprehensivecontrol.funds">
                <router-link :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: comprehensivecontrol.funds.id } }">{{
                  comprehensivecontrol.funds.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="comprehensivecontrol.totalbudget">
                <router-link :to="{ name: 'TotalbudgetView', params: { totalbudgetId: comprehensivecontrol.totalbudget.id } }">{{
                  comprehensivecontrol.totalbudget.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="comprehensivecontrol.unitbudget">
                <router-link :to="{ name: 'UnitbudgetView', params: { unitbudgetId: comprehensivecontrol.unitbudget.id } }">{{
                  comprehensivecontrol.unitbudget.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="comprehensivecontrol.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: comprehensivecontrol.responsibleid.id } }">{{
                  comprehensivecontrol.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="comprehensivecontrol.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: comprehensivecontrol.auditorid.id } }">{{
                  comprehensivecontrol.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="comprehensivecontrol.decument">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: comprehensivecontrol.decument.id } }">{{
                  comprehensivecontrol.decument.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="comprehensivecontrol.coordinationdepartment">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: comprehensivecontrol.coordinationdepartment.id } }">{{
                  comprehensivecontrol.coordinationdepartment.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ComprehensivecontrolView', params: { comprehensivecontrolId: comprehensivecontrol.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ComprehensivecontrolEdit', params: { comprehensivecontrolId: comprehensivecontrol.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(comprehensivecontrol)"
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
          id="jHipster0App.comprehensivecontrol.delete.question"
          data-cy="comprehensivecontrolDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-comprehensivecontrol-heading"
          v-text="t$('jHipster0App.comprehensivecontrol.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-comprehensivecontrol"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeComprehensivecontrol()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./comprehensivecontrol.component.ts"></script>
