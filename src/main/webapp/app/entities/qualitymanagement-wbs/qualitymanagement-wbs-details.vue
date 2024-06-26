<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualitymanagementWbs">
        <h2 class="jh-entity-heading" data-cy="qualitymanagementWbsDetailsHeading">
          <span v-text="t$('jHipster0App.qualitymanagementWbs.detail.title')"></span> {{ qualitymanagementWbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagementWbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagementWbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagementWbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagementWbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagementWbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagementWbs.qualityobjectives')"></span>
          </dt>
          <dd>
            <div v-if="qualitymanagementWbs.qualityobjectives">
              <router-link
                :to="{ name: 'QualityobjectivesView', params: { qualityobjectivesId: qualitymanagementWbs.qualityobjectives.id } }"
                >{{ qualitymanagementWbs.qualityobjectives.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagementWbs.qualityreturns')"></span>
          </dt>
          <dd>
            <div v-if="qualitymanagementWbs.qualityreturns">
              <router-link :to="{ name: 'QualityreturnsView', params: { qualityreturnsId: qualitymanagementWbs.qualityreturns.id } }">{{
                qualitymanagementWbs.qualityreturns.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagementWbs.unQualityAudit')"></span>
          </dt>
          <dd>
            <div v-if="qualitymanagementWbs.unQualityAudit">
              <router-link :to="{ name: 'UnQualityAuditView', params: { unQualityAuditId: qualitymanagementWbs.unQualityAudit.id } }">{{
                qualitymanagementWbs.unQualityAudit.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualitymanagementWbs.id"
          :to="{ name: 'QualitymanagementWbsEdit', params: { qualitymanagementWbsId: qualitymanagementWbs.id } }"
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

<script lang="ts" src="./qualitymanagement-wbs-details.component.ts"></script>
