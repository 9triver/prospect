<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="riskReturn">
        <h2 class="jh-entity-heading" data-cy="riskReturnDetailsHeading">
          <span v-text="t$('jy1App.riskReturn.detail.title')"></span> {{ riskReturn.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.riskReturn.belongriskid')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReturn.belongriskid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.riskReturn.status')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReturn.status }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.riskReturn.closestatus')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReturn.closestatus }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.riskReturn.evidencefile')"></span>
          </dt>
          <dd class="field">
            <span>{{ riskReturn.evidencefile }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.riskReturn.riskid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="riskReturn.riskid">
              <router-link :to="{ name: 'ProjectRiskView', params: { projectRiskId: riskReturn.riskid.id } }">{{
                riskReturn.riskid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.riskReturn.creatorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="riskReturn.creatorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: riskReturn.creatorid.id } }">{{
                riskReturn.creatorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="riskReturn.id"
          :to="{ name: 'RiskReturnEdit', params: { riskReturnId: riskReturn.id } }"
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

<script lang="ts" src="./risk-return-details.component.ts"></script>
