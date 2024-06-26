<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="riskidentification">
        <h2 class="jh-entity-heading" data-cy="riskidentificationDetailsHeading">
          <span v-text="t$('jHipster0App.riskidentification.detail.title')"></span> {{ riskidentification.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.projectname')"></span>
          </dt>
          <dd>
            <span>{{ riskidentification.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.year')"></span>
          </dt>
          <dd>
            <span>{{ riskidentification.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.nodename')"></span>
          </dt>
          <dd>
            <span>{{ riskidentification.nodename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.risktype')"></span>
          </dt>
          <dd>
            <span>{{ riskidentification.risktype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.decumentid')"></span>
          </dt>
          <dd>
            <span>{{ riskidentification.decumentid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.version')"></span>
          </dt>
          <dd>
            <span>{{ riskidentification.version }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.usetime')"></span>
          </dt>
          <dd>
            <span>{{ riskidentification.usetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.systemlevel')"></span>
          </dt>
          <dd>
            <span>{{ riskidentification.systemlevel }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.risklevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Risklevel.' + riskidentification.risklevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.limitationtime')"></span>
          </dt>
          <dd>
            <span>{{ riskidentification.limitationtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.closetype')"></span>
          </dt>
          <dd>
            <span>{{ riskidentification.closetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="riskidentification.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskidentification.creatorid.id } }">{{
                riskidentification.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="riskidentification.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskidentification.responsibleid.id } }">{{
                riskidentification.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskidentification.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="riskidentification.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskidentification.auditorid.id } }">{{
                riskidentification.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="riskidentification.id"
          :to="{ name: 'RiskidentificationEdit', params: { riskidentificationId: riskidentification.id } }"
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

<script lang="ts" src="./riskidentification-details.component.ts"></script>
