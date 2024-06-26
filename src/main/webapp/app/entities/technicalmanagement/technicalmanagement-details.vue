<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="technicalmanagement">
        <h2 class="jh-entity-heading" data-cy="technicalmanagementDetailsHeading">
          <span v-text="t$('jHipster0App.technicalmanagement.detail.title')"></span> {{ technicalmanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagement.name')"></span>
          </dt>
          <dd>
            <span>{{ technicalmanagement.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagement.description')"></span>
          </dt>
          <dd>
            <span>{{ technicalmanagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagement.starttime')"></span>
          </dt>
          <dd>
            <span>{{ technicalmanagement.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagement.endtime')"></span>
          </dt>
          <dd>
            <span>{{ technicalmanagement.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagement.wbs')"></span>
          </dt>
          <dd>
            <div v-if="technicalmanagement.wbs">
              <router-link :to="{ name: 'TechnicalmanagementWbsView', params: { technicalmanagementWbsId: technicalmanagement.wbs.id } }">{{
                technicalmanagement.wbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="technicalmanagement.id"
          :to="{ name: 'TechnicalmanagementEdit', params: { technicalmanagementId: technicalmanagement.id } }"
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

<script lang="ts" src="./technicalmanagement-details.component.ts"></script>
