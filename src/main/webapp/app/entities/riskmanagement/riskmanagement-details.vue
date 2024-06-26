<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="riskmanagement">
        <h2 class="jh-entity-heading" data-cy="riskmanagementDetailsHeading">
          <span v-text="t$('jHipster0App.riskmanagement.detail.title')"></span> {{ riskmanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.riskmanagement.name')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskmanagement.description')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskmanagement.starttime')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskmanagement.endtime')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagement.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskmanagement.wbs')"></span>
          </dt>
          <dd>
            <div v-if="riskmanagement.wbs">
              <router-link :to="{ name: 'RiskmanagementWbsView', params: { riskmanagementWbsId: riskmanagement.wbs.id } }">{{
                riskmanagement.wbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="riskmanagement.id"
          :to="{ name: 'RiskmanagementEdit', params: { riskmanagementId: riskmanagement.id } }"
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

<script lang="ts" src="./riskmanagement-details.component.ts"></script>
