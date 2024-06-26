<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="securitymanagement">
        <h2 class="jh-entity-heading" data-cy="securitymanagementDetailsHeading">
          <span v-text="t$('jHipster0App.securitymanagement.detail.title')"></span> {{ securitymanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.securitymanagement.name')"></span>
          </dt>
          <dd>
            <span>{{ securitymanagement.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.securitymanagement.description')"></span>
          </dt>
          <dd>
            <span>{{ securitymanagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.securitymanagement.starttime')"></span>
          </dt>
          <dd>
            <span>{{ securitymanagement.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.securitymanagement.endtime')"></span>
          </dt>
          <dd>
            <span>{{ securitymanagement.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.securitymanagement.wbs')"></span>
          </dt>
          <dd>
            <div v-if="securitymanagement.wbs">
              <router-link :to="{ name: 'SecuritymanagementWbsView', params: { securitymanagementWbsId: securitymanagement.wbs.id } }">{{
                securitymanagement.wbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="securitymanagement.id"
          :to="{ name: 'SecuritymanagementEdit', params: { securitymanagementId: securitymanagement.id } }"
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

<script lang="ts" src="./securitymanagement-details.component.ts"></script>
