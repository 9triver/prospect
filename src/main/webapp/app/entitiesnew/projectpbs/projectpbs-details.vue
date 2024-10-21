<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectpbs">
        <h2 class="jh-entity-heading" data-cy="projectpbsDetailsHeading">
          <span v-text="t$('jy1App.projectpbs.detail.title')"></span> {{ projectpbs.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.pbsname')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.pbsname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.parentpbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.parentpbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + projectpbs.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.starttime')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.starttime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.endtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.endtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.productlevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.productlevel }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.iskey')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.iskey }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.isimportant')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.isimportant }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.description }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.progress')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.progress }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.type')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.type }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.priorty')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectpbs.priorty }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.status')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.ProjectStatus.' + projectpbs.status)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectpbs.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + projectpbs.auditStatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectpbs.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectpbs.projectwbs">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectpbs.projectwbs.id } }">{{
                projectpbs.projectwbs.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectpbs.technicaldirector')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectpbs.technicaldirector">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: projectpbs.technicaldirector.id } }">{{
                projectpbs.technicaldirector.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectpbs.administrativedirector')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectpbs.administrativedirector">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: projectpbs.administrativedirector.id } }">{{
                projectpbs.administrativedirector.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectpbs.knowingpeople')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectpbs.knowingpeople">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: projectpbs.knowingpeople.id } }">{{
                projectpbs.knowingpeople.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectpbs.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectpbs.auditorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: projectpbs.auditorid.id } }">{{
                projectpbs.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectpbs.responsibledepartment')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectpbs.responsibledepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectpbs.responsibledepartment.id } }">{{
                projectpbs.responsibledepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectpbs.relevantdepartment')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(relevantdepartment, i) in projectpbs.relevantdepartments" :key="relevantdepartment.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: relevantdepartment.id } }">{{
                relevantdepartment.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectpbs.project')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(project, i) in projectpbs.projects" :key="project.id"
              >{{ i > 0 ? '' : '' }}
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
