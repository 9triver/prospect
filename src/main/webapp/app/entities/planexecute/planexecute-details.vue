<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="planexecute">
        <h2 class="jh-entity-heading" data-cy="planexecuteDetailsHeading">
          <span v-text="t$('jHipster3App.planexecute.detail.title')"></span> {{ planexecute.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.planexecute.planname')"></span>
          </dt>
          <dd>
            <span>{{ planexecute.planname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planexecute.planstarttime')"></span>
          </dt>
          <dd>
            <span>{{ planexecute.planstarttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planexecute.planendtime')"></span>
          </dt>
          <dd>
            <span>{{ planexecute.planendtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planexecute.planreturns')"></span>
          </dt>
          <dd>
            <div v-if="planexecute.planreturns">
              <router-link :to="{ name: 'PlanreturnsView', params: { planreturnsId: planexecute.planreturns.id } }">{{
                planexecute.planreturns.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planexecute.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="planexecute.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: planexecute.responsibleid.id } }">{{
                planexecute.responsibleid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="planexecute.id"
          :to="{ name: 'PlanexecuteEdit', params: { planexecuteId: planexecute.id } }"
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

<script lang="ts" src="./planexecute-details.component.ts"></script>
