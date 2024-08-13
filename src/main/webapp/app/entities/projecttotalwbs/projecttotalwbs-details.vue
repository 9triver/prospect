<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectTotalwbs">
        <h2 class="jh-entity-heading" data-cy="projectTotalwbsDetailsHeading">
          <span v-text="t$('jy1App.projectTotalwbs.detail.title')"></span> {{ projectTotalwbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.wbsname')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.wbsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.parentwbsid')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.parentwbsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.pbsid')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.pbsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.description')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.belongfront')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.belongfront }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.starttime')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.endtime')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.progress')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.progress }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.type')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.priorty')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.priorty }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + projectTotalwbs.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.deliverables')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.deliverables }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.ProjectStatus.' + projectTotalwbs.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + projectTotalwbs.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.workbag')"></span>
          </dt>
          <dd>
            <span>{{ projectTotalwbs.workbag }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.projectwbs')"></span>
          </dt>
          <dd>
            <div v-if="projectTotalwbs.projectwbs">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectTotalwbs.projectwbs.id } }">{{
                projectTotalwbs.projectwbs.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.responsibleperson')"></span>
          </dt>
          <dd>
            <div v-if="projectTotalwbs.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectTotalwbs.responsibleperson.id } }">{{
                projectTotalwbs.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.technicaldirector')"></span>
          </dt>
          <dd>
            <div v-if="projectTotalwbs.technicaldirector">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectTotalwbs.technicaldirector.id } }">{{
                projectTotalwbs.technicaldirector.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.administrativedirector')"></span>
          </dt>
          <dd>
            <div v-if="projectTotalwbs.administrativedirector">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectTotalwbs.administrativedirector.id } }">{{
                projectTotalwbs.administrativedirector.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.knowingpeople')"></span>
          </dt>
          <dd>
            <div v-if="projectTotalwbs.knowingpeople">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectTotalwbs.knowingpeople.id } }">{{
                projectTotalwbs.knowingpeople.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="projectTotalwbs.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectTotalwbs.auditorid.id } }">{{
                projectTotalwbs.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.responsibledepartment')"></span>
          </dt>
          <dd>
            <div v-if="projectTotalwbs.responsibledepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectTotalwbs.responsibledepartment.id } }">{{
                projectTotalwbs.responsibledepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.relevantdepartment')"></span>
          </dt>
          <dd>
            <div v-if="projectTotalwbs.relevantdepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectTotalwbs.relevantdepartment.id } }">{{
                projectTotalwbs.relevantdepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectTotalwbs.department')"></span>
          </dt>
          <dd>
            <div v-if="projectTotalwbs.department">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectTotalwbs.department.id } }">{{
                projectTotalwbs.department.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectTotalwbs.id"
          :to="{ name: 'ProjectTotalwbsEdit', params: { projectTotalwbsId: projectTotalwbs.id } }"
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

<script lang="ts" src="./projecttotalwbs-details.component.ts"></script>
