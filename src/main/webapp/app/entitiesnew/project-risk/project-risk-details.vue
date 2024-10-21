<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectRisk">
        <h2 class="jh-entity-heading" data-cy="projectRiskDetailsHeading">
          <span v-text="t$('jy1App.projectRisk.detail.title')"></span> {{ projectRisk.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.year')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.year }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.riskcontent')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.riskcontent }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.identificationtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.identificationtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.riskreason')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.riskreason }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.importantrange')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.importantrange }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.measuresandtimelimit')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.measuresandtimelimit }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.conditions')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.conditions }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.closedloopindicator')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.closedloopindicator }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.wbsid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.wbsid">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectRisk.wbsid.id } }">{{
                projectRisk.wbsid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: projectRisk.workbag.id } }">{{
                projectRisk.workbag.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.frontlineid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.frontlineid">
              <router-link :to="{ name: 'FrontlineView', params: { frontlineId: projectRisk.frontlineid.id } }">{{
                projectRisk.frontlineid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.systemLevel')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.systemLevel">
              <router-link :to="{ name: 'SystemLevelView', params: { systemLevelId: projectRisk.systemLevel.id } }">{{
                projectRisk.systemLevel.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.riskType')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.riskType">
              <router-link :to="{ name: 'RiskTypeView', params: { riskTypeId: projectRisk.riskType.id } }">{{
                projectRisk.riskType.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.riskLevel')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.riskLevel">
              <router-link :to="{ name: 'RiskLevelView', params: { riskLevelId: projectRisk.riskLevel.id } }">{{
                projectRisk.riskLevel.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.riskPossibility')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.riskPossibility">
              <router-link :to="{ name: 'RiskPossibilityView', params: { riskPossibilityId: projectRisk.riskPossibility.id } }">{{
                projectRisk.riskPossibility.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.progressPlan')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(progressPlan, i) in projectRisk.progressPlans" :key="progressPlan.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProgressPlanView', params: { progressPlanId: progressPlan.id } }">{{
                progressPlan.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectRisk.id"
          :to="{ name: 'ProjectRiskEdit', params: { projectRiskId: projectRisk.id } }"
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

<script lang="ts" src="./project-risk-details.component.ts"></script>
