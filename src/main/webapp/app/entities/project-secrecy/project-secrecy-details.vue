<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectSecrecy">
        <h2 class="jh-entity-heading" data-cy="projectSecrecyDetailsHeading">
          <span v-text="t$('jHipster0App.projectSecrecy.detail.title')"></span> {{ projectSecrecy.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.projectSecrecy.projectname')"></span>
          </dt>
          <dd>
            <span>{{ projectSecrecy.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectSecrecy.description')"></span>
          </dt>
          <dd>
            <span>{{ projectSecrecy.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectSecrecy.createtime')"></span>
          </dt>
          <dd>
            <span>{{ projectSecrecy.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectSecrecy.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + projectSecrecy.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectSecrecy.secrecysystem')"></span>
          </dt>
          <dd>
            <div v-if="projectSecrecy.secrecysystem">
              <router-link :to="{ name: 'SecrecysystemView', params: { secrecysystemId: projectSecrecy.secrecysystem.id } }">{{
                projectSecrecy.secrecysystem.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectSecrecy.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="projectSecrecy.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectSecrecy.creatorid.id } }">{{
                projectSecrecy.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectSecrecy.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="projectSecrecy.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectSecrecy.auditorid.id } }">{{
                projectSecrecy.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectSecrecy.projectid')"></span>
          </dt>
          <dd>
            <div v-if="projectSecrecy.projectid">
              <router-link :to="{ name: 'ProjectView', params: { projectId: projectSecrecy.projectid.id } }">{{
                projectSecrecy.projectid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectSecrecy.id"
          :to="{ name: 'ProjectSecrecyEdit', params: { projectSecrecyId: projectSecrecy.id } }"
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

<script lang="ts" src="./project-secrecy-details.component.ts"></script>
