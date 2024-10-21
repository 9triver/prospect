<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="frontline">
        <h2 class="jh-entity-heading" data-cy="frontlineDetailsHeading">
          <span v-text="t$('jy1App.frontline.detail.title')"></span> {{ frontline.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.frontline.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ frontline.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.frontline.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ frontline.description }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.frontline.officers')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(officers, i) in frontline.officers" :key="officers.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'OfficersView', params: { officersId: officers.id } }">{{ officers.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="frontline.id"
          :to="{ name: 'FrontlineEdit', params: { frontlineId: frontline.id } }"
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

<script lang="ts" src="./frontline-details.component.ts"></script>
