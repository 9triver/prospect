<template>
  <div>
    <h2 id="page-heading" data-cy="EvaluationCriteriaHeading">
      <span v-text="t$('jHipster0App.evaluationCriteria.home.title')" id="evaluation-criteria-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.evaluationCriteria.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'EvaluationCriteriaCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-evaluation-criteria"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.evaluationCriteria.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && evaluationCriteria && evaluationCriteria.length === 0">
      <span v-text="t$('jHipster0App.evaluationCriteria.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="evaluationCriteria && evaluationCriteria.length > 0">
      <table class="table table-striped" aria-describedby="evaluationCriteria">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.evaluationCriteria.standardtype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.evaluationCriteria.standardname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.evaluationCriteria.mark')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.evaluationCriteria.department')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="evaluationCriteria in evaluationCriteria" :key="evaluationCriteria.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EvaluationCriteriaView', params: { evaluationCriteriaId: evaluationCriteria.id } }">{{
                evaluationCriteria.id
              }}</router-link>
            </td>
            <td>{{ evaluationCriteria.standardtype }}</td>
            <td>{{ evaluationCriteria.standardname }}</td>
            <td>{{ evaluationCriteria.mark }}</td>
            <td>
              <div v-if="evaluationCriteria.department">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: evaluationCriteria.department.id } }">{{
                  evaluationCriteria.department.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'EvaluationCriteriaView', params: { evaluationCriteriaId: evaluationCriteria.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'EvaluationCriteriaEdit', params: { evaluationCriteriaId: evaluationCriteria.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(evaluationCriteria)"
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
          id="jHipster0App.evaluationCriteria.delete.question"
          data-cy="evaluationCriteriaDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-evaluationCriteria-heading" v-text="t$('jHipster0App.evaluationCriteria.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-evaluationCriteria"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeEvaluationCriteria()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./evaluation-criteria.component.ts"></script>
