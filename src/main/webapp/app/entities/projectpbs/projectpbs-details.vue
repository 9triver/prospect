<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectpbs">
        <h2 class="jh-entity-heading" data-cy="projectpbsDetailsHeading">
          <span v-text="t$('jy1App.projectpbs.detail.title')"></span> {{ projectpbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.projectpbs.pbsname')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.pbsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.parentpbsid')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.parentpbsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.description')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.starttime')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.endtime')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.progress')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.progress }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.type')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.priorty')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.priorty }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + projectpbs.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.ProjectStatus.' + projectpbs.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + projectpbs.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.wbsid')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.wbsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.workbag')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.workbag }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="projectpbs.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.responsibleid.id } }">{{
                projectpbs.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="projectpbs.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.auditorid.id } }">{{
                projectpbs.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.department')"></span>
          </dt>
          <dd>
            <div v-if="projectpbs.department">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectpbs.department.id } }">{{
                projectpbs.department.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.project')"></span>
          </dt>
          <dd>
            <span v-for="(project, i) in projectpbs.projects" :key="project.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectView', params: { projectId: project.id } }">{{ project.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectpbs.id"
          :to="{ name: 'ProjectpbsEdit', params: { projectpbsId: projectpbs.id } }"
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

<script lang="ts" src="./projectpbs-details.component.ts"></script>
