<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="progressmanagement">
        <h2 class="jh-entity-heading" data-cy="progressmanagementDetailsHeading">
          <span v-text="t$('jHipster0App.progressmanagement.detail.title')"></span> {{ progressmanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.progressmanagement.name')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagement.description')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagement.starttime')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagement.endtime')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagement.wbs')"></span>
          </dt>
          <dd>
            <div v-if="progressmanagement.wbs">
              <router-link :to="{ name: 'ProgressmanagementWbsView', params: { progressmanagementWbsId: progressmanagement.wbs.id } }">{{
                progressmanagement.wbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="progressmanagement.id"
          :to="{ name: 'ProgressmanagementEdit', params: { progressmanagementId: progressmanagement.id } }"
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

<script lang="ts" src="./progressmanagement-details.component.ts"></script>
