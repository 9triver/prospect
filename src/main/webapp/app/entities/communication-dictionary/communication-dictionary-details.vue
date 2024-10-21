<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="communicationDictionary">
        <h2 class="jh-entity-heading" data-cy="communicationDictionaryDetailsHeading">
          <span v-text="t$('jy1App.communicationDictionary.detail.title')"></span> {{ communicationDictionary.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.communicationDictionary.partiesname')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationDictionary.partiesname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationDictionary.partiestype')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationDictionary.partiestype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationDictionary.partiesduty')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationDictionary.partiesduty }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="communicationDictionary.id"
          :to="{ name: 'CommunicationDictionaryEdit', params: { communicationDictionaryId: communicationDictionary.id } }"
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

<script lang="ts" src="./communication-dictionary-details.component.ts"></script>
