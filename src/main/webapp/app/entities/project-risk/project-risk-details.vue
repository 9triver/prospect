<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectRisk">
        <h2 class="jh-entity-heading" data-cy="projectRiskDetailsHeading">
          <span v-text="t$('jy1App.projectRisk.detail.title')"></span> {{ projectRisk.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.year')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.year }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.nodename')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.nodename }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.risktype')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.risktype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.decumentid')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.decumentid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.version')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.version }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.usetime')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.usetime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.systemlevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.systemlevel }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.risklevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Risklevel.' + projectRisk.risklevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.limitationtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.limitationtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectRisk.closetype')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectRisk.closetype }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.riskReport')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.riskReport">
              <router-link :to="{ name: 'RiskReportView', params: { riskReportId: projectRisk.riskReport.id } }">{{
                projectRisk.riskReport.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.creatorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectRisk.creatorid.id } }">{{
                projectRisk.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectRisk.responsibleperson.id } }">{{
                projectRisk.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectRisk.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectRisk.auditorid.id } }">{{
                projectRisk.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectRisk.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(projectwbs, i) in projectRisk.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
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
        <el-button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </el-button>
        <router-link
          v-if="projectRisk.id"
          :to="{ name: 'ProjectRiskEdit', params: { projectRiskId: projectRisk.id } }"
          custom
          v-slot="{ navigate }"
        >
          <el-button @click="navigate" class="btn btn-primary" type="primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </el-button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./project-risk-details.component.ts"></script>
