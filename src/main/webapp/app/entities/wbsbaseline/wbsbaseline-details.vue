<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="wbsbaseline">
        <h2 class="jh-entity-heading" data-cy="wbsbaselineDetailsHeading">
          <span v-text="t$('jHipster0App.wbsbaseline.detail.title')"></span> {{ wbsbaseline.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.wbsbaseline.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + wbsbaseline.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.wbsbaseline.requestdeportment')"></span>
          </dt>
          <dd>
            <span>{{ wbsbaseline.requestdeportment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.wbsbaseline.chargetype')"></span>
          </dt>
          <dd>
            <span>{{ wbsbaseline.chargetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.wbsbaseline.chargecontent')"></span>
          </dt>
          <dd>
            <span>{{ wbsbaseline.chargecontent }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.wbsbaseline.status')"></span>
          </dt>
          <dd>
            <span>{{ wbsbaseline.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.wbsbaseline.version')"></span>
          </dt>
          <dd>
            <span>{{ wbsbaseline.version }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.wbsbaseline.remark')"></span>
          </dt>
          <dd>
            <span>{{ wbsbaseline.remark }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.wbsbaseline.projectcharge')"></span>
          </dt>
          <dd>
            <div v-if="wbsbaseline.projectcharge">
              <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: wbsbaseline.projectcharge.id } }">{{
                wbsbaseline.projectcharge.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="wbsbaseline.id"
          :to="{ name: 'WbsbaselineEdit', params: { wbsbaselineId: wbsbaseline.id } }"
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

<script lang="ts" src="./wbsbaseline-details.component.ts"></script>
