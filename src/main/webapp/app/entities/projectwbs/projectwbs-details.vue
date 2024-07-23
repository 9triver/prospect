<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectwbs">
        <h2 class="jh-entity-heading" data-cy="projectwbsDetailsHeading">
          <span v-text="t$('jy1App.projectwbs.detail.title')"></span> {{ projectwbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.projectwbs.wbsname')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.wbsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.pbsid')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.pbsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.parentwbsid')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.parentwbsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.description')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.starttime')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.endtime')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.progress')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.progress }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.type')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.priorty')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.priorty }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + projectwbs.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.ProjectStatus.' + projectwbs.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + projectwbs.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.workbag')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.workbag }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.responsibleid.id } }">{{
                projectwbs.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.auditorid.id } }">{{
                projectwbs.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.department')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.department">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectwbs.department.id } }">{{
                projectwbs.department.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectwbs.project')"></span>
          </dt>
          <dd>
            <span v-for="(project, i) in projectwbs.projects" :key="project.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectView', params: { projectId: project.id } }">{{ project.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectwbs.id"
          :to="{ name: 'ProjectwbsEdit', params: { projectwbsId: projectwbs.id } }"
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

<script lang="ts" src="./projectwbs-details.component.ts"></script>
