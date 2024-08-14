<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="technicalCondition">
        <h2 class="jh-entity-heading" data-cy="technicalConditionDetailsHeading">
          <span v-text="t$('jy1App.technicalCondition.detail.title')"></span> {{ technicalCondition.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.caption')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.caption }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.projectname')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.projectname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.decumentid')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.decumentid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.claimant')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.claimant }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.applicant')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.applicant }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.applicanttime')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.applicanttime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.validrange')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.validrange }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.createtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.createtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + technicalCondition.auditStatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.technicalCondition.creatorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="technicalCondition.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: technicalCondition.creatorid.id } }">{{
                technicalCondition.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.technicalCondition.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="technicalCondition.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: technicalCondition.auditorid.id } }">{{
                technicalCondition.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.technicalCondition.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(projectwbs, i) in technicalCondition.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
        </dl>
        <el-button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </el-button>
        <router-link
          v-if="technicalCondition.id"
          :to="{ name: 'TechnicalConditionEdit', params: { technicalConditionId: technicalCondition.id } }"
          custom
          v-slot="{ navigate }"
        >
          <el-button @click="navigate" class="btn btn-primary" type="primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </el-button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./technical-condition-details.component.ts"></script>
