<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualityObjectivesDictionary">
        <h2 class="jh-entity-heading" data-cy="qualityObjectivesDictionaryDetailsHeading">
          <span v-text="t$('jy1App.qualityObjectivesDictionary.detail.title')"></span> {{ qualityObjectivesDictionary.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectivesDictionary.objectiveslevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectivesDictionary.objectiveslevel }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectivesDictionary.objectivestype')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectivesDictionary.objectivestype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectivesDictionary.objectivesname')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectivesDictionary.objectivesname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectivesDictionary.objectivescontent')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectivesDictionary.objectivescontent }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectivesDictionary.calculationmethod')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectivesDictionary.calculationmethod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectivesDictionary.frequency')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectivesDictionary.frequency }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityObjectivesDictionary.evaluationcriteria')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityObjectivesDictionary.evaluationcriteria }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualityObjectivesDictionary.id"
          :to="{ name: 'QualityObjectivesDictionaryEdit', params: { qualityObjectivesDictionaryId: qualityObjectivesDictionary.id } }"
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

<script lang="ts" src="./quality-objectives-dictionary-details.component.ts"></script>
