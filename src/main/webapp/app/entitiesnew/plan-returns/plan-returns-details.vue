<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="planReturns">
        <h2 class="jh-entity-heading" data-cy="planReturnsDetailsHeading">
          <span v-text="t$('jy1App.planReturns.detail.title')"></span> {{ planReturns.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.planreturnsname')"></span>
          </dt>
          <dd class="field">
            <span>{{ planReturns.planreturnsname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.plantype')"></span>
          </dt>
          <dd class="field">
            <span>{{ planReturns.plantype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.planlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.PlanLevel.' + planReturns.planlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ planReturns.description }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.actualstarttime')"></span>
          </dt>
          <dd class="field">
            <span>{{ planReturns.actualstarttime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.actualendtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ planReturns.actualendtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.deliverables')"></span>
          </dt>
          <dd class="field">
            <span>{{ planReturns.deliverables }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.progress')"></span>
          </dt>
          <dd class="field">
            <span>{{ planReturns.progress }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.status')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Planstatus.' + planReturns.status)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.impactanalysis')"></span>
          </dt>
          <dd class="field">
            <span>{{ planReturns.impactanalysis }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.returnstime')"></span>
          </dt>
          <dd class="field">
            <span>{{ planReturns.returnstime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.rejectionreason')"></span>
          </dt>
          <dd class="field">
            <span>{{ planReturns.rejectionreason }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.planReturns.returnsstatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.ReturnsStatus.' + planReturns.returnsstatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.planReturns.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="planReturns.responsibleperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: planReturns.responsibleperson.id } }">{{
                planReturns.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.planReturns.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="planReturns.auditorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: planReturns.auditorid.id } }">{{
                planReturns.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.planReturns.progressPlan')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="planReturns.progressPlan">
              <router-link :to="{ name: 'ProgressPlanView', params: { progressPlanId: planReturns.progressPlan.id } }">{{
                planReturns.progressPlan.id
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
