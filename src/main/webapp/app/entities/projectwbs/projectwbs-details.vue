<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectwbs">
        <h1 style="text-align: right;">
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

        <h2 class="jh-entity-heading" data-cy="projectwbsDetailsHeading" style="text-align: left;">
          <span>项目编号：</span> {{ projectwbs.id }}
        </h2>

        <dl class="row jh-entity-details">
          <dt>
            <span>项目名称</span>
          </dt>
          <dd>
            <span>{{ projectwbs.wbsname }}</span>
          </dd>
          <dt>
            <span>项目编号</span>
          </dt>
          <dd>
            <span>{{ projectwbs.parentwbsid }}</span>
          </dd>
          <dt>
            <span>PBS编号</span>
          </dt>
          <dd>
            <span>{{ projectwbs.pbsid }}</span>
          </dd>
          <dt>
            <span>项目描述</span>
          </dt>
          <dd>
            <span>{{ projectwbs.description }}</span>
          </dd>
          <dt>
            <span>所属战线</span>
          </dt>
          <dd>
            <span>{{ projectwbs.belongfront }}</span>
          </dd>
          <dt>
            <span>开始时间</span>
          </dt>
          <dd>
            <span>{{ projectwbs.starttime }}</span>
          </dd>
          <dt>
            <span>结束时间</span>
          </dt>
          <dd>
            <span>{{ projectwbs.endtime }}</span>
          </dd>
          <dt>
            <span>项目进度</span>
          </dt>
          <dd>
            <span>{{ projectwbs.progress }}</span>
          </dd>
          <dt>
            <span>项目类型</span>
          </dt>
          <dd>
            <span>{{ projectwbs.type }}</span>
          </dd>
          <dt>
            <span>优先级</span>
          </dt>
          <dd>
            <span>{{ projectwbs.priorty }}</span>
          </dd>
          <dt>
            <span>密级</span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + projectwbs.secretlevel)"></span>
          </dd>
          <dt>
            <span>交付物</span>
          </dt>
          <dd>
            <span>{{ projectwbs.deliverables }}</span>
          </dd>
          <dt>
            <span>状态</span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.ProjectStatus.' + projectwbs.status)"></span>
          </dd>
          <dt>
            <span>审核状态</span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + projectwbs.auditStatus)"></span>
          </dd>
          <dt>
            <span>战线负责人</span>
          </dt>
          <dd>
            <div v-if="projectwbs.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.responsibleperson.id } }">{{
                projectwbs.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>技术负责人</span>
          </dt>
          <dd>
            <div v-if="projectwbs.technicaldirector">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.technicaldirector.id } }">{{
                projectwbs.technicaldirector.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>行政负责人</span>
          </dt>
          <dd>
            <div v-if="projectwbs.administrativedirector">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.administrativedirector.id } }">{{
                projectwbs.administrativedirector.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>知悉人</span>
          </dt>
          <dd>
            <div v-if="projectwbs.knowingpeople">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.knowingpeople.id } }">{{
                projectwbs.knowingpeople.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>审核人</span>
          </dt>
          <dd>
            <div v-if="projectwbs.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: projectwbs.auditorid.id } }">{{
                projectwbs.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>负责部门</span>
          </dt>
          <dd>
            <div v-if="projectwbs.responsibledepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectwbs.responsibledepartment.id } }">{{
                projectwbs.responsibledepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>相关部门</span>
          </dt>
          <dd>
            <div v-if="projectwbs.relevantdepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectwbs.relevantdepartment.id } }">{{
                projectwbs.relevantdepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>关联PBS</span>
          </dt>
          <dd>
            <span v-for="(projectpbs, i) in projectwbs.projectpbs" :key="projectpbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectpbsView', params: { projectpbsId: projectpbs.id } }">{{ projectpbs.id }}</router-link>
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
