<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="resourcemanagement">
        <h2 class="jh-entity-heading" data-cy="resourcemanagementDetailsHeading">
          <span v-text="t$('jHipster0App.resourcemanagement.detail.title')"></span> {{ resourcemanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagement.name')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagement.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagement.description')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagement.starttime')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagement.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagement.endtime')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagement.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagement.wbs')"></span>
          </dt>
          <dd>
            <div v-if="resourcemanagement.wbs">
              <router-link :to="{ name: 'ResourcemanagementWbsView', params: { resourcemanagementWbsId: resourcemanagement.wbs.id } }">{{
                resourcemanagement.wbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="resourcemanagement.id"
          :to="{ name: 'ResourcemanagementEdit', params: { resourcemanagementId: resourcemanagement.id } }"
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

<script lang="ts" src="./resourcemanagement-details.component.ts"></script>
