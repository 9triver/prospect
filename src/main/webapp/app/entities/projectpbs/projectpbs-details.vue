<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectpbs">
        <h2 class="jh-entity-heading" data-cy="projectpbsDetailsHeading" style="text-align: left;">
          <span>项目编号：</span> {{ projectpbs.id }}
        </h2>

        <dl class="row jh-entity-details">
          <dt>
            <span>项目编号</span>
          </dt>
          <dd>
            <span>{{ projectpbs.id }}</span>
          </dd>
          <dt>
            <span>项目名称</span>
          </dt>
          <dd>
            <span>{{ projectpbs.pbsname }}</span>
          </dd>
          <dt>
            <span>父级编号</span>
          </dt>
          <dd>
            <span>{{ projectpbs.parentpbsid }}</span>
          </dd>
          <dt>
            <span>项目密级</span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + projectpbs.secretlevel)"></span>
          </dd>
          <dt>
            <span>开始时间</span>
          </dt>
          <dd>
            <span>{{ projectpbs.starttime }}</span>
          </dd>
          <dt>
            <span>结束时间</span>
          </dt>
          <dd>
            <span>{{ projectpbs.endtime }}</span>
          </dd>
          <dt>
            <span>产品级别</span>
          </dt>
          <dd>
            <span>{{ projectpbs.productlevel }}</span>
          </dd>
          <dt>
            <span>是否关键件</span>
          </dt>
          <dd>
            <span>{{ projectpbs.ifkey }}</span>
          </dd>
          <dt>
            <span>是否重要件</span>
          </dt>
          <dd>
            <span>{{ projectpbs.ifimporttant }}</span>
          </dd>
          <dt>
            <span>项目描述</span>
          </dt>
          <dd>
            <span>{{ projectpbs.description }}</span>
          </dd>
          <dt>
            <span>项目进度</span>
          </dt>
          <dd>
            <span>{{ projectpbs.progress }}</span>
          </dd>
          <dt>
            <span>项目类型</span>
          </dt>
          <dd>
            <span>{{ projectpbs.type }}</span>
          </dd>
          <dt>
            <span>优先级</span>
          </dt>
          <dd>
            <span>{{ projectpbs.priorty }}</span>
          </dd>
          <dt>
            <span>状态</span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.ProjectStatus.' + projectpbs.status)"></span>
          </dd>
          <dt>
            <span>审核状态</span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + projectpbs.auditStatus)"></span>
          </dd>
          <dt>
            <span>技术负责人</span>
          </dt>
          <dd>
            <div v-if="projectpbs.technicaldirector">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.technicaldirector.id } }">{{
                projectpbs.technicaldirector.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>行政负责人</span>
          </dt>
          <dd>
            <div v-if="projectpbs.administrativedirector">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.administrativedirector.id } }">{{
                projectpbs.administrativedirector.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>知悉人</span>
          </dt>
          <dd>
            <div v-if="projectpbs.knowingpeople">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.knowingpeople.id } }">{{
                projectpbs.knowingpeople.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>审核人</span>
          </dt>
          <dd>
            <div v-if="projectpbs.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.auditorid.id } }">{{
                projectpbs.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>责任部门</span>
          </dt>
          <dd>
            <div v-if="projectpbs.responsibledepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectpbs.responsibledepartment.id } }">{{
                projectpbs.responsibledepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>相关部门</span>
          </dt>
          <dd>
            <div v-if="projectpbs.relevantdepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectpbs.relevantdepartment.id } }">{{
                projectpbs.relevantdepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.projectwbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectwbs, i) in projectpbs.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
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
