<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="secrecymanagement">
        <h2 class="jh-entity-heading" data-cy="secrecymanagementDetailsHeading">
          <span v-text="t$('jHipster0App.secrecymanagement.detail.title')"></span> {{ secrecymanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagement.name')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagement.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagement.description')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagement.starttime')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagement.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagement.endtime')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagement.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagement.wbs')"></span>
          </dt>
          <dd>
            <div v-if="secrecymanagement.wbs">
              <router-link :to="{ name: 'SecrecymanagementWbsView', params: { secrecymanagementWbsId: secrecymanagement.wbs.id } }">{{
                secrecymanagement.wbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="secrecymanagement.id"
          :to="{ name: 'SecrecymanagementEdit', params: { secrecymanagementId: secrecymanagement.id } }"
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

<script lang="ts" src="./secrecymanagement-details.component.ts"></script>
