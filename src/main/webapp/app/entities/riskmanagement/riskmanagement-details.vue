<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="riskmanagement">
        <h2 class="jh-entity-heading" data-cy="riskmanagementDetailsHeading">
          <span v-text="t$('jHipster3App.riskmanagement.detail.title')"></span> {{ riskmanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.riskid')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.riskid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.projectname')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.year')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.nodename')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.nodename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.risktype')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.risktype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.decumentid')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.decumentid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.version')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.version }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.usetime')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.usetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.systemlevel')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.systemlevel }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.risklevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Risklevel.' + riskmanagement.risklevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.limitationtime')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.limitationtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.closetype')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.closetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="riskmanagement.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskmanagement.creatorid.id } }">{{
                riskmanagement.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="riskmanagement.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskmanagement.responsibleid.id } }">{{
                riskmanagement.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.riskmanagement.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="riskmanagement.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskmanagement.auditorid.id } }">{{
                riskmanagement.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="riskmanagement.id"
          :to="{ name: 'RiskmanagementEdit', params: { riskmanagementId: riskmanagement.id } }"
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

<script lang="ts" src="./riskmanagement-details.component.ts"></script>
