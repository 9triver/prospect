<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="totalbudget">
        <h2 class="jh-entity-heading" data-cy="totalbudgetDetailsHeading">
          <span v-text="t$('jHipster3App.totalbudget.detail.title')"></span> {{ totalbudget.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.totalbudget.totalbudgetid')"></span>
          </dt>
          <dd>
            <span>{{ totalbudget.totalbudgetid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.totalbudget.valuationsubjects')"></span>
          </dt>
          <dd>
            <span>{{ totalbudget.valuationsubjects }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.totalbudget.budget')"></span>
          </dt>
          <dd>
            <span>{{ totalbudget.budget }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.totalbudget.percentage')"></span>
          </dt>
          <dd>
            <span>{{ totalbudget.percentage }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.totalbudget.remarks')"></span>
          </dt>
          <dd>
            <span>{{ totalbudget.remarks }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="totalbudget.id"
          :to="{ name: 'TotalbudgetEdit', params: { totalbudgetId: totalbudget.id } }"
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

<script lang="ts" src="./totalbudget-details.component.ts"></script>
