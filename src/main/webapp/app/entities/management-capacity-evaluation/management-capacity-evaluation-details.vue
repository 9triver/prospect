<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="managementCapacityEvaluation">
        <h2 class="jh-entity-heading" data-cy="managementCapacityEvaluationDetailsHeading">
          <span v-text="t$('jHipster3App.managementCapacityEvaluation.detail.title')"></span> {{ managementCapacityEvaluation.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.year')"></span>
          </dt>
          <dd>
            <span>{{ managementCapacityEvaluation.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.deprotment')"></span>
          </dt>
          <dd>
            <span>{{ managementCapacityEvaluation.deprotment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.createtime')"></span>
          </dt>
          <dd>
            <span>{{ managementCapacityEvaluation.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.status')"></span>
          </dt>
          <dd>
            <span>{{ managementCapacityEvaluation.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.totalmark')"></span>
          </dt>
          <dd>
            <span>{{ managementCapacityEvaluation.totalmark }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.mark')"></span>
          </dt>
          <dd>
            <span>{{ managementCapacityEvaluation.mark }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.ratingpersonname')"></span>
          </dt>
          <dd>
            <span>{{ managementCapacityEvaluation.ratingpersonname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.ratingdepartment')"></span>
          </dt>
          <dd>
            <span>{{ managementCapacityEvaluation.ratingdepartment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.ratingtimg')"></span>
          </dt>
          <dd>
            <span>{{ managementCapacityEvaluation.ratingtimg }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.evaluationCriteria')"></span>
          </dt>
          <dd>
            <div v-if="managementCapacityEvaluation.evaluationCriteria">
              <router-link
                :to="{
                  name: 'EvaluationCriteriaView',
                  params: { evaluationCriteriaId: managementCapacityEvaluation.evaluationCriteria.id },
                }"
                >{{ managementCapacityEvaluation.evaluationCriteria.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.project')"></span>
          </dt>
          <dd>
            <div v-if="managementCapacityEvaluation.project">
              <router-link :to="{ name: 'ProjectView', params: { projectId: managementCapacityEvaluation.project.id } }">{{
                managementCapacityEvaluation.project.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="managementCapacityEvaluation.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: managementCapacityEvaluation.creatorid.id } }">{{
                managementCapacityEvaluation.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="managementCapacityEvaluation.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: managementCapacityEvaluation.responsibleid.id } }">{{
                managementCapacityEvaluation.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.managementCapacityEvaluation.ratingperson')"></span>
          </dt>
          <dd>
            <div v-if="managementCapacityEvaluation.ratingperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: managementCapacityEvaluation.ratingperson.id } }">{{
                managementCapacityEvaluation.ratingperson.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="managementCapacityEvaluation.id"
          :to="{ name: 'ManagementCapacityEvaluationEdit', params: { managementCapacityEvaluationId: managementCapacityEvaluation.id } }"
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

<script lang="ts" src="./management-capacity-evaluation-details.component.ts"></script>
