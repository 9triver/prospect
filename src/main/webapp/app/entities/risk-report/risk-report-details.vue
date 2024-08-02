<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="riskReport">
        <h2 class="jh-entity-heading" data-cy="riskReportDetailsHeading">
          <span v-text="t$('jy1App.riskReport.detail.title')"></span> {{ riskReport.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.riskReport.type')"></span>
          </dt>
          <dd>
            <span>{{ riskReport.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.riskReport.riskreportname')"></span>
          </dt>
          <dd>
            <span>{{ riskReport.riskreportname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.riskReport.releasetime')"></span>
          </dt>
          <dd>
            <span>{{ riskReport.releasetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.riskReport.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + riskReport.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.riskReport.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="riskReport.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskReport.creatorid.id } }">{{
                riskReport.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.riskReport.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="riskReport.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskReport.auditorid.id } }">{{
                riskReport.auditorid.id
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
