<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualityReturns">
        <h2 class="jh-entity-heading" data-cy="qualityReturnsDetailsHeading">
          <span v-text="t$('jy1App.qualityReturns.detail.title')"></span> {{ qualityReturns.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.qualityplanid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.qualityplanid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.qualityobjectivesid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.qualityobjectivesid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.department')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.department }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.responsibleid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.responsibleid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.wbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.wbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.objectiveslevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.objectiveslevel }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.objectives')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.objectives }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.objectivesvalue')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.objectivesvalue }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.calculationmethod')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.calculationmethod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.frequency')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.frequency }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.isobjectivesvalue')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.isobjectivesvalue }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.percentage')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.percentage }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.objectivescompletion')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.objectivescompletion }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.problem')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.problem }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.takeaction')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.takeaction }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.continuousimprovement')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.continuousimprovement }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.workevidence')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.workevidence }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.returntime')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.returntime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityReturns.status')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityReturns.status }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.qualityReturns.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="qualityReturns.responsibleperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: qualityReturns.responsibleperson.id } }">{{
                qualityReturns.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.qualityReturns.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="qualityReturns.auditorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: qualityReturns.auditorid.id } }">{{
                qualityReturns.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.qualityReturns.qualityObjectives')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(qualityObjectives, i) in qualityReturns.qualityObjectives" :key="qualityObjectives.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'QualityObjectivesView', params: { qualityObjectivesId: qualityObjectives.id } }">{{
                qualityObjectives.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.qualityReturns.qualityPlan')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="qualityReturns.qualityPlan">
              <router-link :to="{ name: 'QualityPlanView', params: { qualityPlanId: qualityReturns.qualityPlan.id } }">{{
                qualityReturns.qualityPlan.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualityReturns.id"
          :to="{ name: 'QualityReturnsEdit', params: { qualityReturnsId: qualityReturns.id } }"
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

<script lang="ts" src="./quality-returns-details.component.ts"></script>
