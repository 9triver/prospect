<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="planReturns">
        <h2 class="jh-entity-heading" data-cy="planReturnsDetailsHeading">
          <span v-text="t$('jy1App.planReturns.detail.title')"></span> {{ planReturns.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.planReturns.planreturnsname')"></span>
          </dt>
          <dd>
            <span>{{ planReturns.planreturnsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.plantype')"></span>
          </dt>
          <dd>
            <span>{{ planReturns.plantype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.planlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.PlanLevel.' + planReturns.planlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.description')"></span>
          </dt>
          <dd>
            <span>{{ planReturns.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.actualstarttime')"></span>
          </dt>
          <dd>
            <span>{{ planReturns.actualstarttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.actualendtime')"></span>
          </dt>
          <dd>
            <span>{{ planReturns.actualendtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.deliverables')"></span>
          </dt>
          <dd>
            <span>{{ planReturns.deliverables }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.progress')"></span>
          </dt>
          <dd>
            <span>{{ planReturns.progress }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Planstatus.' + planReturns.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.impactanalysis')"></span>
          </dt>
          <dd>
            <span>{{ planReturns.impactanalysis }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.returnstime')"></span>
          </dt>
          <dd>
            <span>{{ planReturns.returnstime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.rejectionreason')"></span>
          </dt>
          <dd>
            <span>{{ planReturns.rejectionreason }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.returnsstatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.ReturnsStatus.' + planReturns.returnsstatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.responsibleperson')"></span>
          </dt>
          <dd>
            <div v-if="planReturns.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: planReturns.responsibleperson.id } }">{{
                planReturns.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.planReturns.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="planReturns.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: planReturns.auditorid.id } }">{{
                planReturns.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="planReturns.id"
          :to="{ name: 'PlanReturnsEdit', params: { planReturnsId: planReturns.id } }"
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

<script lang="ts" src="./plan-returns-details.component.ts"></script>
