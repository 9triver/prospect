<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="progressPlan">
        <h2 class="jh-entity-heading" data-cy="progressPlanDetailsHeading">
          <span v-text="t$('jy1App.progressPlan.detail.title')"></span> {{ progressPlan.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.planname')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.planname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.belongproject')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.belongproject }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + progressPlan.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.plantype')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.plantype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.planlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.PlanLevel.' + progressPlan.planlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.planstage')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.planstage }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.readytime')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.readytime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.description }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.deliverables')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.deliverables }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.planobjectives')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.planobjectives }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.preplan')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.preplan }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.starttime')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.starttime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.endtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.endtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.actualstarttime')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.actualstarttime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.actualendtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.actualendtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.progress')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.progress }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.progresstype')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Progressstatus.' + progressPlan.progresstype)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.iskey')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.iskey }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.status')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Planstatus.' + progressPlan.status)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + progressPlan.auditStatus)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.returns')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.returns }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.progressPlan.remark')"></span>
          </dt>
          <dd class="field">
            <span>{{ progressPlan.remark }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.progressPlan.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="progressPlan.responsibleperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: progressPlan.responsibleperson.id } }">{{
                progressPlan.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.progressPlan.cooperatingperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="progressPlan.cooperatingperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: progressPlan.cooperatingperson.id } }">{{
                progressPlan.cooperatingperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.progressPlan.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="progressPlan.auditorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: progressPlan.auditorid.id } }">{{
                progressPlan.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.progressPlan.responsibledepartment')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="progressPlan.responsibledepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: progressPlan.responsibledepartment.id } }">{{
                progressPlan.responsibledepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.progressPlan.cooperatingdepartment')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="progressPlan.cooperatingdepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: progressPlan.cooperatingdepartment.id } }">{{
                progressPlan.cooperatingdepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.progressPlan.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(projectwbs, i) in progressPlan.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.progressPlan.projectRisk')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(projectRisk, i) in progressPlan.projectRisks" :key="projectRisk.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectRiskView', params: { projectRiskId: projectRisk.id } }">{{ projectRisk.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.progressPlan.riskReturn')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="progressPlan.riskReturn">
              <router-link :to="{ name: 'RiskReturnView', params: { riskReturnId: progressPlan.riskReturn.id } }">{{
                progressPlan.riskReturn.id
              }}</router-link>
            </div>
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
