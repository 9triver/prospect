<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualityObjectives">
        <h2 class="jh-entity-heading" data-cy="qualityObjectivesDetailsHeading">
          <span v-text="t$('jy1App.qualityObjectives.detail.title')"></span> {{ qualityObjectives.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectives.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectives.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectives.objectiveslevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectives.objectiveslevel }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectives.objectives')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectives.objectives }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectives.objectivesvalue')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectives.objectivesvalue }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectives.calculationmethod')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectives.calculationmethod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectives.frequency')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectives.frequency }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectives.takeaction')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectives.takeaction }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectives.needresource')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectives.needresource }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectives.status')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectives.status }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.qualityObjectives.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="qualityObjectives.responsibleperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: qualityObjectives.responsibleperson.id } }">{{
                qualityObjectives.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.qualityObjectives.qualityReturns')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(qualityReturns, i) in qualityObjectives.qualityReturns" :key="qualityReturns.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'QualityReturnsView', params: { qualityReturnsId: qualityReturns.id } }">{{
                qualityReturns.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.qualityObjectives.qualityPlan')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="qualityObjectives.qualityPlan">
              <router-link :to="{ name: 'QualityPlanView', params: { qualityPlanId: qualityObjectives.qualityPlan.id } }">{{
                qualityObjectives.qualityPlan.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualityObjectives.id"
          :to="{ name: 'QualityObjectivesEdit', params: { qualityObjectivesId: qualityObjectives.id } }"
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

<script lang="ts" src="./quality-objectives-details.component.ts"></script>
