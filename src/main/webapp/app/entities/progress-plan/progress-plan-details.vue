<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="progressPlan">
        <h2 class="jh-entity-heading" data-cy="progressPlanDetailsHeading">
          <span v-text="t$('jy1App.progressPlan.detail.title')"></span> {{ progressPlan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.progressPlan.planname')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.planname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + progressPlan.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.plantype')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.plantype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.planlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.PlanLevel.' + progressPlan.planlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.planstage')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.planstage }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.readytime')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.readytime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.description')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.deliverables')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.deliverables }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.planobjectives')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.planobjectives }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.preplan')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.preplan }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.starttime')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.endtime')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.actualstarttime')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.actualstarttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.actualendtime')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.actualendtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.progress')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.progress }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.progresstype')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Progressstatus.' + progressPlan.progresstype)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.iskey')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.iskey }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Planstatus.' + progressPlan.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + progressPlan.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.remark')"></span>
          </dt>
          <dd>
            <span>{{ progressPlan.remark }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.responsibleperson')"></span>
          </dt>
          <dd>
            <div v-if="progressPlan.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: progressPlan.responsibleperson.id } }">{{
                progressPlan.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.cooperatingperson')"></span>
          </dt>
          <dd>
            <div v-if="progressPlan.cooperatingperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: progressPlan.cooperatingperson.id } }">{{
                progressPlan.cooperatingperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="progressPlan.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: progressPlan.auditorid.id } }">{{
                progressPlan.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.responsibledepartment')"></span>
          </dt>
          <dd>
            <div v-if="progressPlan.responsibledepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: progressPlan.responsibledepartment.id } }">{{
                progressPlan.responsibledepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.cooperatingdepartment')"></span>
          </dt>
          <dd>
            <div v-if="progressPlan.cooperatingdepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: progressPlan.cooperatingdepartment.id } }">{{
                progressPlan.cooperatingdepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.planReturns')"></span>
          </dt>
          <dd>
            <div v-if="progressPlan.planReturns">
              <router-link :to="{ name: 'PlanReturnsView', params: { planReturnsId: progressPlan.planReturns.id } }">{{
                progressPlan.planReturns.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.projectwbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectwbs, i) in progressPlan.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.progressPlan.projectRisk')"></span>
          </dt>
          <dd>
            <span v-for="(projectRisk, i) in progressPlan.projectRisks" :key="projectRisk.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectRiskView', params: { projectRiskId: projectRisk.id } }">{{ projectRisk.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="progressPlan.id"
          :to="{ name: 'ProgressPlanEdit', params: { progressPlanId: progressPlan.id } }"
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

<script lang="ts" src="./progress-plan-details.component.ts"></script>
