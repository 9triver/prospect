<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="progressplanreturns">
        <h2 class="jh-entity-heading" data-cy="progressplanreturnsDetailsHeading">
          <span v-text="t$('jHipster0App.progressplanreturns.detail.title')"></span> {{ progressplanreturns.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.progressplanreturns.planstarttime')"></span>
          </dt>
          <dd>
            <span>{{ progressplanreturns.planstarttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplanreturns.planendtime')"></span>
          </dt>
          <dd>
            <span>{{ progressplanreturns.planendtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplanreturns.returnstime')"></span>
          </dt>
          <dd>
            <span>{{ progressplanreturns.returnstime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplanreturns.progressplan')"></span>
          </dt>
          <dd>
            <div v-if="progressplanreturns.progressplan">
              <router-link :to="{ name: 'ProgressplanView', params: { progressplanId: progressplanreturns.progressplan.id } }">{{
                progressplanreturns.progressplan.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplanreturns.document')"></span>
          </dt>
          <dd>
            <div v-if="progressplanreturns.document">
              <router-link :to="{ name: 'DocumentView', params: { documentId: progressplanreturns.document.id } }">{{
                progressplanreturns.document.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="progressplanreturns.id"
          :to="{ name: 'ProgressplanreturnsEdit', params: { progressplanreturnsId: progressplanreturns.id } }"
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

<script lang="ts" src="./progressplanreturns-details.component.ts"></script>
