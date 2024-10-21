<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="communicationPlan">
        <h2 class="jh-entity-heading" data-cy="communicationPlanDetailsHeading">
          <span v-text="t$('jy1App.communicationPlan.detail.title')"></span> {{ communicationPlan.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.communicationPlan.wbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationPlan.wbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationPlan.communicationtopic')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationPlan.communicationtopic }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationPlan.communicationtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationPlan.communicationtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationPlan.worktarget')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationPlan.worktarget }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationPlan.workcontent')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationPlan.workcontent }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationPlan.remarks')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationPlan.remarks }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationPlan.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + communicationPlan.auditStatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.communicationPlan.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="communicationPlan.projectwbs">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: communicationPlan.projectwbs.id } }">{{
                communicationPlan.projectwbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="communicationPlan.id"
          :to="{ name: 'CommunicationPlanEdit', params: { communicationPlanId: communicationPlan.id } }"
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

<script lang="ts" src="./communication-plan-details.component.ts"></script>
