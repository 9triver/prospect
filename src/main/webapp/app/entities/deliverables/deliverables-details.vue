<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="deliverables">
        <h2 class="jh-entity-heading" data-cy="deliverablesDetailsHeading">
          <span v-text="t$('jy1App.deliverables.detail.title')"></span> {{ deliverables.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.deliverables.code')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliverables.code }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliverables.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliverables.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliverables.parentcode')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliverables.parentcode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliverables.level')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliverables.level }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliverables.status')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliverables.status }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliverables.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliverables.description }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="deliverables.id"
          :to="{ name: 'DeliverablesEdit', params: { deliverablesId: deliverables.id } }"
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

<script lang="ts" src="./deliverables-details.component.ts"></script>
