<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectHumanresourcesplan">
        <h2 class="jh-entity-heading" data-cy="projectHumanresourcesplanDetailsHeading">
          <span v-text="t$('jHipster0App.projectHumanresourcesplan.detail.title')"></span> {{ projectHumanresourcesplan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.projectHumanresourcesplan.projectname')"></span>
          </dt>
          <dd>
            <span>{{ projectHumanresourcesplan.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectHumanresourcesplan.clientname')"></span>
          </dt>
          <dd>
            <span>{{ projectHumanresourcesplan.clientname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectHumanresourcesplan.plandate')"></span>
          </dt>
          <dd>
            <span>{{ projectHumanresourcesplan.plandate }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectHumanresourcesplan.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + projectHumanresourcesplan.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectHumanresourcesplan.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + projectHumanresourcesplan.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectHumanresourcesplan.project')"></span>
          </dt>
          <dd>
            <div v-if="projectHumanresourcesplan.project">
              <router-link :to="{ name: 'ProjectView', params: { projectId: projectHumanresourcesplan.project.id } }">{{
                projectHumanresourcesplan.project.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectHumanresourcesplan.id"
          :to="{ name: 'ProjectHumanresourcesplanEdit', params: { projectHumanresourcesplanId: projectHumanresourcesplan.id } }"
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

<script lang="ts" src="./project-humanresourcesplan-details.component.ts"></script>
