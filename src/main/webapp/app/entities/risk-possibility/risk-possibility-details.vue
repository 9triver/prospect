<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="riskPossibility">
        <h2 class="jh-entity-heading" data-cy="riskPossibilityDetailsHeading">
          <span v-text="t$('jy1App.riskPossibility.detail.title')"></span> {{ riskPossibility.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.riskPossibility.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskPossibility.name }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="riskPossibility.id"
          :to="{ name: 'RiskPossibilityEdit', params: { riskPossibilityId: riskPossibility.id } }"
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

<script lang="ts" src="./risk-possibility-details.component.ts"></script>
