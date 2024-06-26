<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="comprehensivecontrol">
        <h2 class="jh-entity-heading" data-cy="comprehensivecontrolDetailsHeading">
          <span v-text="t$('jHipster0App.comprehensivecontrol.detail.title')"></span> {{ comprehensivecontrol.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.description')"></span>
          </dt>
          <dd>
            <span>{{ comprehensivecontrol.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.number')"></span>
          </dt>
          <dd>
            <span>{{ comprehensivecontrol.number }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.type')"></span>
          </dt>
          <dd>
            <span>{{ comprehensivecontrol.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.starttime')"></span>
          </dt>
          <dd>
            <span>{{ comprehensivecontrol.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.endtime')"></span>
          </dt>
          <dd>
            <span>{{ comprehensivecontrol.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.actualstarttime')"></span>
          </dt>
          <dd>
            <span>{{ comprehensivecontrol.actualstarttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.actualendtime')"></span>
          </dt>
          <dd>
            <span>{{ comprehensivecontrol.actualendtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.result')"></span>
          </dt>
          <dd>
            <span>{{ comprehensivecontrol.result }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.acceptancetype')"></span>
          </dt>
          <dd>
            <span>{{ comprehensivecontrol.acceptancetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.ProjectStatus.' + comprehensivecontrol.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + comprehensivecontrol.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.responsiblename')"></span>
          </dt>
          <dd>
            <span>{{ comprehensivecontrol.responsiblename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.progress')"></span>
          </dt>
          <dd>
            <div v-if="comprehensivecontrol.progress">
              <router-link :to="{ name: 'ProgressplanView', params: { progressplanId: comprehensivecontrol.progress.id } }">{{
                comprehensivecontrol.progress.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.project')"></span>
          </dt>
          <dd>
            <div v-if="comprehensivecontrol.project">
              <router-link :to="{ name: 'ProjectView', params: { projectId: comprehensivecontrol.project.id } }">{{
                comprehensivecontrol.project.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.funds')"></span>
          </dt>
          <dd>
            <div v-if="comprehensivecontrol.funds">
              <router-link :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: comprehensivecontrol.funds.id } }">{{
                comprehensivecontrol.funds.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.totalbudget')"></span>
          </dt>
          <dd>
            <div v-if="comprehensivecontrol.totalbudget">
              <router-link :to="{ name: 'TotalbudgetView', params: { totalbudgetId: comprehensivecontrol.totalbudget.id } }">{{
                comprehensivecontrol.totalbudget.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.unitbudget')"></span>
          </dt>
          <dd>
            <div v-if="comprehensivecontrol.unitbudget">
              <router-link :to="{ name: 'UnitbudgetView', params: { unitbudgetId: comprehensivecontrol.unitbudget.id } }">{{
                comprehensivecontrol.unitbudget.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="comprehensivecontrol.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: comprehensivecontrol.responsibleid.id } }">{{
                comprehensivecontrol.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="comprehensivecontrol.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: comprehensivecontrol.auditorid.id } }">{{
                comprehensivecontrol.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.decument')"></span>
          </dt>
          <dd>
            <div v-if="comprehensivecontrol.decument">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: comprehensivecontrol.decument.id } }">{{
                comprehensivecontrol.decument.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensivecontrol.coordinationdepartment')"></span>
          </dt>
          <dd>
            <div v-if="comprehensivecontrol.coordinationdepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: comprehensivecontrol.coordinationdepartment.id } }">{{
                comprehensivecontrol.coordinationdepartment.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="comprehensivecontrol.id"
          :to="{ name: 'ComprehensivecontrolEdit', params: { comprehensivecontrolId: comprehensivecontrol.id } }"
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

<script lang="ts" src="./comprehensivecontrol-details.component.ts"></script>
