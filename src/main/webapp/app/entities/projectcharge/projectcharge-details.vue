<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectcharge">
        <h2 class="jh-entity-heading" data-cy="projectchargeDetailsHeading">
          <span v-text="t$('jHipster3App.projectcharge.detail.title')"></span> {{ projectcharge.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.projectcharge.projectname')"></span>
          </dt>
          <dd>
            <span>{{ projectcharge.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.projectcharge.formid')"></span>
          </dt>
          <dd>
            <span>{{ projectcharge.formid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.projectcharge.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + projectcharge.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.projectcharge.requestdeportment')"></span>
          </dt>
          <dd>
            <span>{{ projectcharge.requestdeportment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.projectcharge.chargetype')"></span>
          </dt>
          <dd>
            <span>{{ projectcharge.chargetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.projectcharge.chargecontent')"></span>
          </dt>
          <dd>
            <span>{{ projectcharge.chargecontent }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.projectcharge.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="projectcharge.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectcharge.creatorid.id } }">{{
                projectcharge.creatorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectcharge.id"
          :to="{ name: 'ProjectchargeEdit', params: { projectchargeId: projectcharge.id } }"
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

<script lang="ts" src="./projectcharge-details.component.ts"></script>
