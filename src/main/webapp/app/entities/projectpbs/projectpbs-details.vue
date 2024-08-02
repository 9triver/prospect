<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectpbs">
        <h2 class="jh-entity-heading" data-cy="projectpbsDetailsHeading">
          <span v-text="t$('jy1App.projectpbs.detail.title')"></span> {{ projectpbs.id }}
        </h2>
        <h1>
          <div class="mb-4">
            <router-link :to="{ name: 'ProgressPlan'}" custom v-slot="{ navigate }">
              <el-button type="primary" @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                进度计划
              </el-button>
            </router-link>
            <router-link :to="{ name: 'OutsourcingContractual'}" custom v-slot="{ navigate }">
              <el-button type="success" @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                外协管理
              </el-button>
            </router-link>
            <router-link :to="{ name: 'QualityObjectives'}" custom v-slot="{ navigate }">
              <el-button type="warning" @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                质量管理
              </el-button>
            </router-link>
            <router-link :to="{ name: 'CostControlSystem'}" custom v-slot="{ navigate }">
              <el-button type="info" @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                资金管理
              </el-button>
            </router-link>
            <router-link :to="{ name: 'ProjectRisk'}" custom v-slot="{ navigate }">
              <el-button type="danger" @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                项目风险
              </el-button>
            </router-link>
            <router-link :to="{ name: 'Document'}" custom v-slot="{ navigate }">
              <el-button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                项目文档
              </el-button>
            </router-link>
          </div>
        </h1>

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
            <span v-text="t$('jy1App.projectpbs.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + projectpbs.secretlevel)"></span>
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
            <span v-text="t$('jy1App.projectpbs.productlevel')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.productlevel }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.ifkey')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.ifkey }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.ifimporttant')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.ifimporttant }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.description')"></span>
          </dt>
          <dd>
            <span>{{ projectpbs.description }}</span>
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
            <span v-text="t$('jy1App.projectpbs.technicaldirector')"></span>
          </dt>
          <dd>
            <div v-if="projectpbs.technicaldirector">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.technicaldirector.id } }">{{
                projectpbs.technicaldirector.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.administrativedirector')"></span>
          </dt>
          <dd>
            <div v-if="projectpbs.administrativedirector">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.administrativedirector.id } }">{{
                projectpbs.administrativedirector.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.knowingpeople')"></span>
          </dt>
          <dd>
            <div v-if="projectpbs.knowingpeople">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectpbs.knowingpeople.id } }">{{
                projectpbs.knowingpeople.id
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
            <span v-text="t$('jy1App.projectpbs.responsibledepartment')"></span>
          </dt>
          <dd>
            <div v-if="projectpbs.responsibledepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectpbs.responsibledepartment.id } }">{{
                projectpbs.responsibledepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectpbs.relevantdepartment')"></span>
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
