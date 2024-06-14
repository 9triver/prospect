<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="evaluationCriteria">
        <h2 class="jh-entity-heading" data-cy="evaluationCriteriaDetailsHeading">
          <span v-text="t$('jHipster3App.evaluationCriteria.detail.title')"></span> {{ evaluationCriteria.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.evaluationCriteria.standardtype')"></span>
          </dt>
          <dd>
            <span>{{ evaluationCriteria.standardtype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.evaluationCriteria.standardname')"></span>
          </dt>
          <dd>
            <span>{{ evaluationCriteria.standardname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.evaluationCriteria.mark')"></span>
          </dt>
          <dd>
            <span>{{ evaluationCriteria.mark }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.evaluationCriteria.department')"></span>
          </dt>
          <dd>
            <div v-if="evaluationCriteria.department">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: evaluationCriteria.department.id } }">{{
                evaluationCriteria.department.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="evaluationCriteria.id"
          :to="{ name: 'EvaluationCriteriaEdit', params: { evaluationCriteriaId: evaluationCriteria.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./evaluation-criteria-details.component.ts"></script>
