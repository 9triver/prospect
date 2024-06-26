<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="progressbaseline">
        <h2 class="jh-entity-heading" data-cy="progressbaselineDetailsHeading">
          <span v-text="t$('jHipster0App.progressbaseline.detail.title')"></span> {{ progressbaseline.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.progressbaseline.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + progressbaseline.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressbaseline.requestdeportment')"></span>
          </dt>
          <dd>
            <span>{{ progressbaseline.requestdeportment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressbaseline.chargetype')"></span>
          </dt>
          <dd>
            <span>{{ progressbaseline.chargetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressbaseline.chargecontent')"></span>
          </dt>
          <dd>
            <span>{{ progressbaseline.chargecontent }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressbaseline.status')"></span>
          </dt>
          <dd>
            <span>{{ progressbaseline.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressbaseline.version')"></span>
          </dt>
          <dd>
            <span>{{ progressbaseline.version }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressbaseline.remark')"></span>
          </dt>
          <dd>
            <span>{{ progressbaseline.remark }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="progressbaseline.id"
          :to="{ name: 'ProgressbaselineEdit', params: { progressbaselineId: progressbaseline.id } }"
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

<script lang="ts" src="./progressbaseline-details.component.ts"></script>
