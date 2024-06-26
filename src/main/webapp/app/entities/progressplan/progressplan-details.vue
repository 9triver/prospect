<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="progressplan">
        <h2 class="jh-entity-heading" data-cy="progressplanDetailsHeading">
          <span v-text="t$('jHipster0App.progressplan.detail.title')"></span> {{ progressplan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.progressplan.progressname')"></span>
          </dt>
          <dd>
            <span>{{ progressplan.progressname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.progresstype')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Progresstype.' + progressplan.progresstype)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.workfocus')"></span>
          </dt>
          <dd>
            <span>{{ progressplan.workfocus }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.createtime')"></span>
          </dt>
          <dd>
            <span>{{ progressplan.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ progressplan.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.responsiblename')"></span>
          </dt>
          <dd>
            <span>{{ progressplan.responsiblename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Progressstatus.' + progressplan.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + progressplan.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.department')"></span>
          </dt>
          <dd>
            <div v-if="progressplan.department">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: progressplan.department.id } }">{{
                progressplan.department.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.planreturns')"></span>
          </dt>
          <dd>
            <div v-if="progressplan.planreturns">
              <router-link :to="{ name: 'PlanreturnsView', params: { planreturnsId: progressplan.planreturns.id } }">{{
                progressplan.planreturns.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="progressplan.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: progressplan.responsibleid.id } }">{{
                progressplan.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="progressplan.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: progressplan.creatorid.id } }">{{
                progressplan.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.progressplan.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="progressplan.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: progressplan.auditorid.id } }">{{
                progressplan.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="progressplan.id"
          :to="{ name: 'ProgressplanEdit', params: { progressplanId: progressplan.id } }"
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

<script lang="ts" src="./progressplan-details.component.ts"></script>
