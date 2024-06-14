<template>
  <div>
    <h2 id="page-heading" data-cy="ManagementCapacityEvaluationHeading">
      <span v-text="t$('jHipster3App.managementCapacityEvaluation.home.title')" id="management-capacity-evaluation-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.managementCapacityEvaluation.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ManagementCapacityEvaluationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-management-capacity-evaluation"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && managementCapacityEvaluations && managementCapacityEvaluations.length === 0">
      <span v-text="t$('jHipster3App.managementCapacityEvaluation.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="managementCapacityEvaluations && managementCapacityEvaluations.length > 0">
      <table class="table table-striped" aria-describedby="managementCapacityEvaluations">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.year')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.deprotment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.status')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.totalmark')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.mark')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.ratingpersonname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.ratingdepartment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.ratingtimg')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.evaluationCriteria')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.project')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.managementCapacityEvaluation.ratingperson')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="managementCapacityEvaluation in managementCapacityEvaluations"
            :key="managementCapacityEvaluation.id"
            data-cy="entityTable"
          >
            <td>
              <router-link
                :to="{
                  name: 'ManagementCapacityEvaluationView',
                  params: { managementCapacityEvaluationId: managementCapacityEvaluation.id },
                }"
                >{{ managementCapacityEvaluation.id }}</router-link
              >
            </td>
            <td>{{ managementCapacityEvaluation.year }}</td>
            <td>{{ managementCapacityEvaluation.deprotment }}</td>
            <td>{{ managementCapacityEvaluation.createtime }}</td>
            <td>{{ managementCapacityEvaluation.status }}</td>
            <td>{{ managementCapacityEvaluation.totalmark }}</td>
            <td>{{ managementCapacityEvaluation.mark }}</td>
            <td>{{ managementCapacityEvaluation.ratingpersonname }}</td>
            <td>{{ managementCapacityEvaluation.ratingdepartment }}</td>
            <td>{{ managementCapacityEvaluation.ratingtimg }}</td>
            <td>
              <div v-if="managementCapacityEvaluation.evaluationCriteria">
                <router-link
                  :to="{
                    name: 'EvaluationCriteriaView',
                    params: { evaluationCriteriaId: managementCapacityEvaluation.evaluationCriteria.id },
                  }"
                  >{{ managementCapacityEvaluation.evaluationCriteria.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="managementCapacityEvaluation.project">
                <router-link :to="{ name: 'ProjectView', params: { projectId: managementCapacityEvaluation.project.id } }">{{
                  managementCapacityEvaluation.project.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="managementCapacityEvaluation.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: managementCapacityEvaluation.creatorid.id } }">{{
                  managementCapacityEvaluation.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="managementCapacityEvaluation.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: managementCapacityEvaluation.responsibleid.id } }">{{
                  managementCapacityEvaluation.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="managementCapacityEvaluation.ratingperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: managementCapacityEvaluation.ratingperson.id } }">{{
                  managementCapacityEvaluation.ratingperson.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{
                    name: 'ManagementCapacityEvaluationView',
                    params: { managementCapacityEvaluationId: managementCapacityEvaluation.id },
                  }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{
                    name: 'ManagementCapacityEvaluationEdit',
                    params: { managementCapacityEvaluationId: managementCapacityEvaluation.id },
                  }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(managementCapacityEvaluation)"
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
          id="jHipster3App.managementCapacityEvaluation.delete.question"
          data-cy="managementCapacityEvaluationDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-managementCapacityEvaluation-heading"
          v-text="t$('jHipster3App.managementCapacityEvaluation.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-managementCapacityEvaluation"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeManagementCapacityEvaluation()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./management-capacity-evaluation.component.ts"></script>
