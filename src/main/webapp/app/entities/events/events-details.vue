<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="events">
        <h2 class="jh-entity-heading" data-cy="eventsDetailsHeading">
          <span v-text="t$('jy1App.events.detail.title')"></span> {{ events.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.events.title')"></span>
          </dt>
          <dd class="field">
            <span>{{ events.title }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.events.content')"></span>
          </dt>
          <dd class="field">
            <span>{{ events.content }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.events.time')"></span>
          </dt>
          <dd class="field">
            <span>{{ events.time }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.events.place')"></span>
          </dt>
          <dd class="field">
            <span>{{ events.place }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.events.participants')"></span>
          </dt>
          <dd class="field">
            <span>{{ events.participants }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.events.picture')"></span>
          </dt>
          <dd class="field">
            <span>{{ events.picture }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.events.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ events.description }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.events.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + events.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.events.attachment')"></span>
          </dt>
          <dd class="field">
            <span>{{ events.attachment }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="events.id" :to="{ name: 'EventsEdit', params: { eventsId: events.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./events-details.component.ts"></script>
