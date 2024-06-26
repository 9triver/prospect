<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="cycleplan">
        <h2 class="jh-entity-heading" data-cy="cycleplanDetailsHeading">
          <span v-text="t$('jHipster0App.cycleplan.detail.title')"></span> {{ cycleplan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.cycleplanname')"></span>
          </dt>
          <dd>
            <span>{{ cycleplan.cycleplanname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + cycleplan.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.starttime')"></span>
          </dt>
          <dd>
            <span>{{ cycleplan.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.endtime')"></span>
          </dt>
          <dd>
            <span>{{ cycleplan.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.actualstarttime')"></span>
          </dt>
          <dd>
            <span>{{ cycleplan.actualstarttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.actualendtime')"></span>
          </dt>
          <dd>
            <span>{{ cycleplan.actualendtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.responsiblename')"></span>
          </dt>
          <dd>
            <span>{{ cycleplan.responsiblename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Cycleplanstatus.' + cycleplan.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + cycleplan.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.document')"></span>
          </dt>
          <dd>
            <div v-if="cycleplan.document">
              <router-link :to="{ name: 'DocumentView', params: { documentId: cycleplan.document.id } }">{{
                cycleplan.document.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.annualplan')"></span>
          </dt>
          <dd>
            <div v-if="cycleplan.annualplan">
              <router-link :to="{ name: 'AnnualplanView', params: { annualplanId: cycleplan.annualplan.id } }">{{
                cycleplan.annualplan.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.monthplan')"></span>
          </dt>
          <dd>
            <div v-if="cycleplan.monthplan">
              <router-link :to="{ name: 'MonthplanView', params: { monthplanId: cycleplan.monthplan.id } }">{{
                cycleplan.monthplan.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.projectcharge')"></span>
          </dt>
          <dd>
            <div v-if="cycleplan.projectcharge">
              <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: cycleplan.projectcharge.id } }">{{
                cycleplan.projectcharge.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="cycleplan.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: cycleplan.responsibleid.id } }">{{
                cycleplan.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.cycleplan.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="cycleplan.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: cycleplan.auditorid.id } }">{{
                cycleplan.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="cycleplan.id"
          :to="{ name: 'CycleplanEdit', params: { cycleplanId: cycleplan.id } }"
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

<script lang="ts" src="./cycleplan-details.component.ts"></script>
