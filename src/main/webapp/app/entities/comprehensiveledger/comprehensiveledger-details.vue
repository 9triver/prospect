<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="comprehensiveledger">
        <h2 class="jh-entity-heading" data-cy="comprehensiveledgerDetailsHeading">
          <span v-text="t$('jHipster0App.comprehensiveledger.detail.title')"></span> {{ comprehensiveledger.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.comprehensiveledger.fundsname')"></span>
          </dt>
          <dd>
            <span>{{ comprehensiveledger.fundsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensiveledger.wbsname')"></span>
          </dt>
          <dd>
            <span>{{ comprehensiveledger.wbsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensiveledger.unitname')"></span>
          </dt>
          <dd>
            <span>{{ comprehensiveledger.unitname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensiveledger.budgetsection')"></span>
          </dt>
          <dd>
            <span>{{ comprehensiveledger.budgetsection }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensiveledger.purpose')"></span>
          </dt>
          <dd>
            <span>{{ comprehensiveledger.purpose }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensiveledger.unit')"></span>
          </dt>
          <dd>
            <span>{{ comprehensiveledger.unit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensiveledger.number')"></span>
          </dt>
          <dd>
            <span>{{ comprehensiveledger.number }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensiveledger.unitprice')"></span>
          </dt>
          <dd>
            <span>{{ comprehensiveledger.unitprice }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.comprehensiveledger.foreignexchange')"></span>
          </dt>
          <dd>
            <span>{{ comprehensiveledger.foreignexchange }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="comprehensiveledger.id"
          :to="{ name: 'ComprehensiveledgerEdit', params: { comprehensiveledgerId: comprehensiveledger.id } }"
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

<script lang="ts" src="./comprehensiveledger-details.component.ts"></script>
