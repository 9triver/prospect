<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="fundsavailability">
        <h2 class="jh-entity-heading" data-cy="fundsavailabilityDetailsHeading">
          <span v-text="t$('jHipster3App.fundsavailability.detail.title')"></span> {{ fundsavailability.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.fundsavailability.fundsavailabilityid')"></span>
          </dt>
          <dd>
            <span>{{ fundsavailability.fundsavailabilityid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsavailability.fundsid')"></span>
          </dt>
          <dd>
            <span>{{ fundsavailability.fundsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsavailability.year')"></span>
          </dt>
          <dd>
            <span>{{ fundsavailability.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsavailability.budgit')"></span>
          </dt>
          <dd>
            <span>{{ fundsavailability.budgit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsavailability.funding')"></span>
          </dt>
          <dd>
            <span>{{ fundsavailability.funding }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsavailability.fundsmanagement')"></span>
          </dt>
          <dd>
            <div v-if="fundsavailability.fundsmanagement">
              <router-link :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: fundsavailability.fundsmanagement.id } }">{{
                fundsavailability.fundsmanagement.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="fundsavailability.id"
          :to="{ name: 'FundsavailabilityEdit', params: { fundsavailabilityId: fundsavailability.id } }"
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

<script lang="ts" src="./fundsavailability-details.component.ts"></script>
