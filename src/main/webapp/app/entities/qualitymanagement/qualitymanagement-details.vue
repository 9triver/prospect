<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualitymanagement">
        <h2 class="jh-entity-heading" data-cy="qualitymanagementDetailsHeading">
          <span v-text="t$('jHipster3App.qualitymanagement.detail.title')"></span> {{ qualitymanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.qualitymanagement.qualityid')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagement.qualityid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.qualitymanagement.createtime')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagement.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.qualitymanagement.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagement.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.qualitymanagement.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + qualitymanagement.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.qualitymanagement.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + qualitymanagement.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.qualitymanagement.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="qualitymanagement.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: qualitymanagement.creatorid.id } }">{{
                qualitymanagement.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.qualitymanagement.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="qualitymanagement.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: qualitymanagement.auditorid.id } }">{{
                qualitymanagement.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualitymanagement.id"
          :to="{ name: 'QualitymanagementEdit', params: { qualitymanagementId: qualitymanagement.id } }"
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

<script lang="ts" src="./qualitymanagement-details.component.ts"></script>
