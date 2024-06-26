<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="monthplan">
        <h2 class="jh-entity-heading" data-cy="monthplanDetailsHeading">
          <span v-text="t$('jHipster0App.monthplan.detail.title')"></span> {{ monthplan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.monthplan.monthplanname')"></span>
          </dt>
          <dd>
            <span>{{ monthplan.monthplanname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.monthplan.month')"></span>
          </dt>
          <dd>
            <span>{{ monthplan.month }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.monthplan.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + monthplan.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.monthplan.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ monthplan.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.monthplan.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Annualplanstatus.' + monthplan.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.monthplan.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + monthplan.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.monthplan.document')"></span>
          </dt>
          <dd>
            <div v-if="monthplan.document">
              <router-link :to="{ name: 'DocumentView', params: { documentId: monthplan.document.id } }">{{
                monthplan.document.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.monthplan.planreturns')"></span>
          </dt>
          <dd>
            <div v-if="monthplan.planreturns">
              <router-link :to="{ name: 'PlanexecuteView', params: { planexecuteId: monthplan.planreturns.id } }">{{
                monthplan.planreturns.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.monthplan.projectcharge')"></span>
          </dt>
          <dd>
            <div v-if="monthplan.projectcharge">
              <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: monthplan.projectcharge.id } }">{{
                monthplan.projectcharge.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.monthplan.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="monthplan.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: monthplan.creatorid.id } }">{{
                monthplan.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.monthplan.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="monthplan.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: monthplan.auditorid.id } }">{{
                monthplan.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="monthplan.id"
          :to="{ name: 'MonthplanEdit', params: { monthplanId: monthplan.id } }"
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

<script lang="ts" src="./monthplan-details.component.ts"></script>
