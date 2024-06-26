<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="planreturns">
        <h2 class="jh-entity-heading" data-cy="planreturnsDetailsHeading">
          <span v-text="t$('jHipster0App.planreturns.detail.title')"></span> {{ planreturns.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.planreturns.planreturnsname')"></span>
          </dt>
          <dd>
            <span>{{ planreturns.planreturnsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.planreturns.starttime')"></span>
          </dt>
          <dd>
            <span>{{ planreturns.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.planreturns.endtime')"></span>
          </dt>
          <dd>
            <span>{{ planreturns.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.planreturns.plantype')"></span>
          </dt>
          <dd>
            <span>{{ planreturns.plantype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.planreturns.returnstime')"></span>
          </dt>
          <dd>
            <span>{{ planreturns.returnstime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.planreturns.returnsstatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.ReturnsStatus.' + planreturns.returnsstatus)"></span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="planreturns.id"
          :to="{ name: 'PlanreturnsEdit', params: { planreturnsId: planreturns.id } }"
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

<script lang="ts" src="./planreturns-details.component.ts"></script>
