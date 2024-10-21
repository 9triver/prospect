<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="communicationFormDictionary">
        <h2 class="jh-entity-heading" data-cy="communicationFormDictionaryDetailsHeading">
          <span v-text="t$('jy1App.communicationFormDictionary.detail.title')"></span> {{ communicationFormDictionary.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.communicationFormDictionary.communicationformname')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationFormDictionary.communicationformname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationFormDictionary.communicationformtype')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationFormDictionary.communicationformtype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationFormDictionary.status')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationFormDictionary.status }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="communicationFormDictionary.id"
          :to="{ name: 'CommunicationFormDictionaryEdit', params: { communicationFormDictionaryId: communicationFormDictionary.id } }"
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

<script lang="ts" src="./communication-form-dictionary-details.component.ts"></script>
