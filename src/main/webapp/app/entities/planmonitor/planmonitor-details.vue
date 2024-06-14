<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="planmonitor">
        <h2 class="jh-entity-heading" data-cy="planmonitorDetailsHeading">
          <span v-text="t$('jHipster3App.planmonitor.detail.title')"></span> {{ planmonitor.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.planmonitor.month')"></span>
          </dt>
          <dd>
            <span>{{ planmonitor.month }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planmonitor.type')"></span>
          </dt>
          <dd>
            <span>{{ planmonitor.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planmonitor.year')"></span>
          </dt>
          <dd>
            <span>{{ planmonitor.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planmonitor.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + planmonitor.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planmonitor.status')"></span>
          </dt>
          <dd>
            <span>{{ planmonitor.status }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="planmonitor.id"
          :to="{ name: 'PlanmonitorEdit', params: { planmonitorId: planmonitor.id } }"
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

<script lang="ts" src="./planmonitor-details.component.ts"></script>
