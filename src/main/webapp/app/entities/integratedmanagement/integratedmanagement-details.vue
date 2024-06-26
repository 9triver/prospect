<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="integratedmanagement">
        <h2 class="jh-entity-heading" data-cy="integratedmanagementDetailsHeading">
          <span v-text="t$('jHipster0App.integratedmanagement.detail.title')"></span> {{ integratedmanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagement.name')"></span>
          </dt>
          <dd>
            <span>{{ integratedmanagement.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagement.description')"></span>
          </dt>
          <dd>
            <span>{{ integratedmanagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagement.wbs')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagement.wbs">
              <router-link
                :to="{ name: 'IntegratedmanagementWbsView', params: { integratedmanagementWbsId: integratedmanagement.wbs.id } }"
                >{{ integratedmanagement.wbs.id }}</router-link
              >
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="integratedmanagement.id"
          :to="{ name: 'IntegratedmanagementEdit', params: { integratedmanagementId: integratedmanagement.id } }"
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

<script lang="ts" src="./integratedmanagement-details.component.ts"></script>
