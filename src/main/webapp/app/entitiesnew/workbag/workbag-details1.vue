<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="workbag">
        <h2 class="jh-entity-heading" data-cy="workbagDetailsHeading">
          <span v-text="t$('jy1App.workbag.detail.title')"></span> {{ workbag.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.workbag.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.pbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.pbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.workbagtype')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.workbagtype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.supplier')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.supplier }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.iskeyimportant')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.iskeyimportant }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.keypbsname')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.keypbsname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.importantpbsname')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.importantpbsname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + workbag.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.description }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.starttime')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.starttime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.endtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.endtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.estimatedpurchasingtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.estimatedpurchasingtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.progress')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.progress }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.issafetywork')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.issafetywork }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.remark')"></span>
          </dt>
          <dd class="field">
            <span>{{ workbag.remark }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.workbag.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + workbag.auditStatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.workbag.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="workbag.responsibleperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: workbag.responsibleperson.id } }">{{
                workbag.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.workbag.projectmanager')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="workbag.projectmanager">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: workbag.projectmanager.id } }">{{
                workbag.projectmanager.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.workbag.knowingpeople')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="workbag.knowingpeople">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: workbag.knowingpeople.id } }">{{
                workbag.knowingpeople.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.workbag.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="workbag.auditorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: workbag.auditorid.id } }">{{
                workbag.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.workbag.responsibledepartment')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="workbag.responsibledepartment">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: workbag.responsibledepartment.id } }">{{
                workbag.responsibledepartment.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.workbag.department')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="workbag.department">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: workbag.department.id } }">{{
                workbag.department.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.workbag.projectdeliverables')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(projectdeliverables, i) in workbag.projectdeliverables" :key="projectdeliverables.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectdeliverablesView', params: { projectdeliverablesId: projectdeliverables.id } }">{{
                projectdeliverables.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.workbag.relevantdepartment')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(relevantdepartment, i) in workbag.relevantdepartments" :key="relevantdepartment.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: relevantdepartment.id } }">{{
                relevantdepartment.id
              }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.workbag.wbsid')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(wbsid, i) in workbag.wbsids" :key="wbsid.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: wbsid.id } }">{{ wbsid.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.workbag.work')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(work, i) in workbag.works" :key="work.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'WorkView', params: { workId: work.id } }">{{ work.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="workbag.id" :to="{ name: 'WorkbagEdit', params: { workbagId: workbag.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./workbag-details.component.ts"></script>
