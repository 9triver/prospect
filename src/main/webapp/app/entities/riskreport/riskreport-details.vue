<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="riskreport">
        <h2 class="jh-entity-heading" data-cy="riskreportDetailsHeading">
          <span v-text="t$('jHipster0App.riskreport.detail.title')"></span> {{ riskreport.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.riskreport.type')"></span>
          </dt>
          <dd>
            <span>{{ riskreport.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskreport.riskreportname')"></span>
          </dt>
          <dd>
            <span>{{ riskreport.riskreportname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskreport.releasetime')"></span>
          </dt>
          <dd>
            <span>{{ riskreport.releasetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskreport.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + riskreport.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskreport.riskidentification')"></span>
          </dt>
          <dd>
            <div v-if="riskreport.riskidentification">
              <router-link :to="{ name: 'RiskidentificationView', params: { riskidentificationId: riskreport.riskidentification.id } }">{{
                riskreport.riskidentification.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskreport.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="riskreport.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskreport.creatorid.id } }">{{
                riskreport.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.riskreport.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="riskreport.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: riskreport.auditorid.id } }">{{
                riskreport.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="riskreport.id"
          :to="{ name: 'RiskreportEdit', params: { riskreportId: riskreport.id } }"
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

<script lang="ts" src="./riskreport-details.component.ts"></script>
