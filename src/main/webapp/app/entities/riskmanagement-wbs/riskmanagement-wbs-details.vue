<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="riskmanagementWbs">
        <h2 class="jh-entity-heading" data-cy="riskmanagementWbsDetailsHeading">
          <span v-text="t$('jHipster0App.riskmanagementWbs.detail.title')"></span> {{ riskmanagementWbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.riskmanagementWbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagementWbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskmanagementWbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagementWbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskmanagementWbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagementWbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskmanagementWbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagementWbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskmanagementWbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ riskmanagementWbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskmanagementWbs.riskidentification')"></span>
          </dt>
          <dd>
            <div v-if="riskmanagementWbs.riskidentification">
              <router-link
                :to="{ name: 'RiskidentificationView', params: { riskidentificationId: riskmanagementWbs.riskidentification.id } }"
                >{{ riskmanagementWbs.riskidentification.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskmanagementWbs.riskreport')"></span>
          </dt>
          <dd>
            <div v-if="riskmanagementWbs.riskreport">
              <router-link :to="{ name: 'RiskreportView', params: { riskreportId: riskmanagementWbs.riskreport.id } }">{{
                riskmanagementWbs.riskreport.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="riskmanagementWbs.id"
          :to="{ name: 'RiskmanagementWbsEdit', params: { riskmanagementWbsId: riskmanagementWbs.id } }"
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

<script lang="ts" src="./riskmanagement-wbs-details.component.ts"></script>
