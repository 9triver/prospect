<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="progressmanagementWbs">
        <h2 class="jh-entity-heading" data-cy="progressmanagementWbsDetailsHeading">
          <span v-text="t$('jHipster0App.progressmanagementWbs.detail.title')"></span> {{ progressmanagementWbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.progressmanagementWbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagementWbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagementWbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagementWbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagementWbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagementWbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagementWbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagementWbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagementWbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagementWbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagementWbs.progressplan')"></span>
          </dt>
          <dd>
            <div v-if="progressmanagementWbs.progressplan">
              <router-link :to="{ name: 'ProgressplanView', params: { progressplanId: progressmanagementWbs.progressplan.id } }">{{
                progressmanagementWbs.progressplan.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagementWbs.progressplanreturns')"></span>
          </dt>
          <dd>
            <div v-if="progressmanagementWbs.progressplanreturns">
              <router-link
                :to="{ name: 'ProgressplanreturnsView', params: { progressplanreturnsId: progressmanagementWbs.progressplanreturns.id } }"
                >{{ progressmanagementWbs.progressplanreturns.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressmanagementWbs.progressbaseline')"></span>
          </dt>
          <dd>
            <div v-if="progressmanagementWbs.progressbaseline">
              <router-link
                :to="{ name: 'ProgressbaselineView', params: { progressbaselineId: progressmanagementWbs.progressbaseline.id } }"
                >{{ progressmanagementWbs.progressbaseline.id }}</router-link
              >
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="progressmanagementWbs.id"
          :to="{ name: 'ProgressmanagementWbsEdit', params: { progressmanagementWbsId: progressmanagementWbs.id } }"
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

<script lang="ts" src="./progressmanagement-wbs-details.component.ts"></script>
