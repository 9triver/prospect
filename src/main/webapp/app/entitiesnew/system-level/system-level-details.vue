<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="systemLevel">
        <h2 class="jh-entity-heading" data-cy="systemLevelDetailsHeading">
          <span v-text="t$('jy1App.systemLevel.detail.title')"></span> {{ systemLevel.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.systemLevel.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ systemLevel.name }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="systemLevel.id"
          :to="{ name: 'SystemLevelEdit', params: { systemLevelId: systemLevel.id } }"
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

<script lang="ts" src="./system-level-details.component.ts"></script>
