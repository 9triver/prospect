<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="planstrategy">
        <h2 class="jh-entity-heading" data-cy="planstrategyDetailsHeading">
          <span v-text="t$('jHipster3App.planstrategy.detail.title')"></span> {{ planstrategy.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.planstrategy.strategyid')"></span>
          </dt>
          <dd>
            <span>{{ planstrategy.strategyid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planstrategy.strategyname')"></span>
          </dt>
          <dd>
            <span>{{ planstrategy.strategyname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planstrategy.number')"></span>
          </dt>
          <dd>
            <span>{{ planstrategy.number }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planstrategy.type')"></span>
          </dt>
          <dd>
            <span>{{ planstrategy.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planstrategy.decument')"></span>
          </dt>
          <dd>
            <div v-if="planstrategy.decument">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: planstrategy.decument.id } }">{{
                planstrategy.decument.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planstrategy.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="planstrategy.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: planstrategy.responsibleid.id } }">{{
                planstrategy.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.planstrategy.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="planstrategy.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: planstrategy.auditorid.id } }">{{
                planstrategy.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="planstrategy.id"
          :to="{ name: 'PlanstrategyEdit', params: { planstrategyId: planstrategy.id } }"
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

<script lang="ts" src="./planstrategy-details.component.ts"></script>
