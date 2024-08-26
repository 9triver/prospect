<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="riskReport">
        <h2 class="jh-entity-heading" data-cy="riskReportDetailsHeading">
          <span v-text="t$('jy1App.riskReport.detail.title')"></span>
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.riskReport.type')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReport.type }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.riskReport.riskreportname')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReport.riskreportname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.riskReport.releasetime')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReport.releasetime }}</span>
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
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskReport.creatorid.id } }">{{
                riskReport.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.riskReport.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="riskReport.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskReport.auditorid.id } }">{{
                riskReport.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <el-button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </el-button>
        <router-link
          v-if="riskReport.id"
          :to="{ name: 'RiskReportEdit', params: { riskReportId: riskReport.id } }"
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

<script lang="ts" src="./risk-report-details.component.ts"></script>
