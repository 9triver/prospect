<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="riskReport">
        <h2 class="jh-entity-heading" data-cy="riskReportDetailsHeading">
          <span v-text="t$('jy1App.riskReport.detail.title')"></span> {{ riskReport.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.riskReport.type')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReport.type }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.riskReport.year')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReport.year }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.riskReport.riskreportname')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReport.riskreportname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.riskReport.reporttime')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReport.reporttime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.riskReport.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + riskReport.auditStatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.riskReport.creatorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="riskReport.creatorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: riskReport.creatorid.id } }">{{
                riskReport.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.riskReport.wbsid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="riskReport.wbsid">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: riskReport.wbsid.id } }">{{
                riskReport.wbsid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.riskReport.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="riskReport.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: riskReport.workbag.id } }">{{
                riskReport.workbag.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="riskReport.id"
          :to="{ name: 'RiskReportEdit', params: { riskReportId: riskReport.id } }"
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

<script lang="ts" src="./risk-report-details.component.ts"></script>
