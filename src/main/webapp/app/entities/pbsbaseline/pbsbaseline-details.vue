<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="pbsbaseline">
        <h2 class="jh-entity-heading" data-cy="pbsbaselineDetailsHeading">
          <span v-text="t$('jHipster3App.pbsbaseline.detail.title')"></span> {{ pbsbaseline.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.pbsbaseline.formid')"></span>
          </dt>
          <dd>
            <span>{{ pbsbaseline.formid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbsbaseline.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + pbsbaseline.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbsbaseline.requestdeportment')"></span>
          </dt>
          <dd>
            <span>{{ pbsbaseline.requestdeportment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbsbaseline.chargetype')"></span>
          </dt>
          <dd>
            <span>{{ pbsbaseline.chargetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbsbaseline.chargecontent')"></span>
          </dt>
          <dd>
            <span>{{ pbsbaseline.chargecontent }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbsbaseline.status')"></span>
          </dt>
          <dd>
            <span>{{ pbsbaseline.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbsbaseline.version')"></span>
          </dt>
          <dd>
            <span>{{ pbsbaseline.version }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbsbaseline.remark')"></span>
          </dt>
          <dd>
            <span>{{ pbsbaseline.remark }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbsbaseline.projectcharge')"></span>
          </dt>
          <dd>
            <div v-if="pbsbaseline.projectcharge">
              <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: pbsbaseline.projectcharge.id } }">{{
                pbsbaseline.projectcharge.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="pbsbaseline.id"
          :to="{ name: 'PbsbaselineEdit', params: { pbsbaselineId: pbsbaseline.id } }"
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

<script lang="ts" src="./pbsbaseline-details.component.ts"></script>
