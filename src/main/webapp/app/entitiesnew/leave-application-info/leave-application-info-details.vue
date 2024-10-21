<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="leaveApplicationInfo">
        <h2 class="jh-entity-heading" data-cy="leaveApplicationInfoDetailsHeading">
          <span v-text="t$('jy1App.leaveApplicationInfo.detail.title')"></span> {{ leaveApplicationInfo.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.leaveApplicationInfo.startDate')"></span>
          </dt>
          <dd class="field">
            <span>{{ leaveApplicationInfo.startDate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.leaveApplicationInfo.endDate')"></span>
          </dt>
          <dd class="field">
            <span>{{ leaveApplicationInfo.endDate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.leaveApplicationInfo.leaveType')"></span>
          </dt>
          <dd class="field">
            <span>{{ leaveApplicationInfo.leaveType }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.leaveApplicationInfo.reason')"></span>
          </dt>
          <dd class="field">
            <span>{{ leaveApplicationInfo.reason }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.leaveApplicationInfo.status')"></span>
          </dt>
          <dd class="field">
            <span>{{ leaveApplicationInfo.status }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="leaveApplicationInfo.id"
          :to="{ name: 'LeaveApplicationInfoEdit', params: { leaveApplicationInfoId: leaveApplicationInfo.id } }"
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

<script lang="ts" src="./leave-application-info-details.component.ts"></script>
