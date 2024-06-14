<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="resourcemanagement">
        <h2 class="jh-entity-heading" data-cy="resourcemanagementDetailsHeading">
          <span v-text="t$('jHipster3App.resourcemanagement.detail.title')"></span> {{ resourcemanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.resourcemanagement.resourceid')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagement.resourceid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.resourcemanagement.projectname')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagement.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.resourcemanagement.clientname')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagement.clientname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.resourcemanagement.plandate')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagement.plandate }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.resourcemanagement.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagement.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.resourcemanagement.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + resourcemanagement.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.resourcemanagement.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + resourcemanagement.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.resourcemanagement.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="resourcemanagement.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: resourcemanagement.creatorid.id } }">{{
                resourcemanagement.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.resourcemanagement.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="resourcemanagement.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: resourcemanagement.auditorid.id } }">{{
                resourcemanagement.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="resourcemanagement.id"
          :to="{ name: 'ResourcemanagementEdit', params: { resourcemanagementId: resourcemanagement.id } }"
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

<script lang="ts" src="./resourcemanagement-details.component.ts"></script>
