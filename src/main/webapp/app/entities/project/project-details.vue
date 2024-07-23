<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="project">
        <h2 class="jh-entity-heading" data-cy="projectDetailsHeading">
          <span v-text="t$('jy1App.project.detail.title')"></span> {{ project.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.project.projectname')"></span>
          </dt>
          <dd>
            <span>{{ project.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.parentid')"></span>
          </dt>
          <dd>
            <span>{{ project.parentid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.pbsid')"></span>
          </dt>
          <dd>
            <span>{{ project.pbsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.description')"></span>
          </dt>
          <dd>
            <span>{{ project.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.number')"></span>
          </dt>
          <dd>
            <span>{{ project.number }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.projecttype')"></span>
          </dt>
          <dd>
            <span>{{ project.projecttype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.priorty')"></span>
          </dt>
          <dd>
            <span>{{ project.priorty }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.createdate')"></span>
          </dt>
          <dd>
            <span>{{ project.createdate }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + project.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.ProjectStatus.' + project.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + project.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.progress')"></span>
          </dt>
          <dd>
            <span>{{ project.progress }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.projectpbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectpbs, i) in project.projectpbs" :key="projectpbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectpbsView', params: { projectpbsId: projectpbs.id } }">{{ projectpbs.id }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.project.projectwbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectwbs, i) in project.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
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
