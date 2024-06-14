<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="progressmanagement">
        <h2 class="jh-entity-heading" data-cy="progressmanagementDetailsHeading">
          <span v-text="t$('jHipster3App.progressmanagement.detail.title')"></span> {{ progressmanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.progressid')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.progressid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.progressname')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.progressname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.progresstype')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Progresstype.' + progressmanagement.progresstype)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.workfocus')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.workfocus }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.createtime')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.responsiblename')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.responsiblename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Progressstatus.' + progressmanagement.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.baselineid')"></span>
          </dt>
          <dd>
            <span>{{ progressmanagement.baselineid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + progressmanagement.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.department')"></span>
          </dt>
          <dd>
            <div v-if="progressmanagement.department">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: progressmanagement.department.id } }">{{
                progressmanagement.department.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.planreturns')"></span>
          </dt>
          <dd>
            <div v-if="progressmanagement.planreturns">
              <router-link :to="{ name: 'PlanreturnsView', params: { planreturnsId: progressmanagement.planreturns.id } }">{{
                progressmanagement.planreturns.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="progressmanagement.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: progressmanagement.responsibleid.id } }">{{
                progressmanagement.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="progressmanagement.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: progressmanagement.creatorid.id } }">{{
                progressmanagement.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.progressmanagement.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="progressmanagement.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: progressmanagement.auditorid.id } }">{{
                progressmanagement.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="progressmanagement.id"
          :to="{ name: 'ProgressmanagementEdit', params: { progressmanagementId: progressmanagement.id } }"
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

<script lang="ts" src="./progressmanagement-details.component.ts"></script>
