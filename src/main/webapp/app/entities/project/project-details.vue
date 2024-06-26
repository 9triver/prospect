<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="project">
        <h2 class="jh-entity-heading" data-cy="projectDetailsHeading">
          <span v-text="t$('jHipster0App.project.detail.title')"></span> {{ project.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.project.projectname')"></span>
          </dt>
          <dd>
            <span>{{ project.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.description')"></span>
          </dt>
          <dd>
            <span>{{ project.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.number')"></span>
          </dt>
          <dd>
            <span>{{ project.number }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.projecttype')"></span>
          </dt>
          <dd>
            <span>{{ project.projecttype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.responsiblename')"></span>
          </dt>
          <dd>
            <span>{{ project.responsiblename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.duedate')"></span>
          </dt>
          <dd>
            <span>{{ project.duedate }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.priorty')"></span>
          </dt>
          <dd>
            <span>{{ project.priorty }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.ProjectStatus.' + project.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + project.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.projectwbs')"></span>
          </dt>
          <dd>
            <div v-if="project.projectwbs">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: project.projectwbs.id } }">{{
                project.projectwbs.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="project.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: project.responsibleid.id } }">{{
                project.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="project.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: project.auditorid.id } }">{{
                project.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.project.projectSecrecy')"></span>
          </dt>
          <dd>
            <div v-if="project.projectSecrecy">
              <router-link :to="{ name: 'ProjectSecrecyView', params: { projectSecrecyId: project.projectSecrecy.id } }">{{
                project.projectSecrecy.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="project.id" :to="{ name: 'ProjectEdit', params: { projectId: project.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./project-details.component.ts"></script>
