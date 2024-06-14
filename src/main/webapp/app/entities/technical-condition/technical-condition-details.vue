<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="technicalCondition">
        <h2 class="jh-entity-heading" data-cy="technicalConditionDetailsHeading">
          <span v-text="t$('jHipster3App.technicalCondition.detail.title')"></span> {{ technicalCondition.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.caption')"></span>
          </dt>
          <dd>
            <span>{{ technicalCondition.caption }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.projectname')"></span>
          </dt>
          <dd>
            <span>{{ technicalCondition.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.decumentid')"></span>
          </dt>
          <dd>
            <span>{{ technicalCondition.decumentid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.claimant')"></span>
          </dt>
          <dd>
            <span>{{ technicalCondition.claimant }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.applicant')"></span>
          </dt>
          <dd>
            <span>{{ technicalCondition.applicant }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.applicanttime')"></span>
          </dt>
          <dd>
            <span>{{ technicalCondition.applicanttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.validrange')"></span>
          </dt>
          <dd>
            <span>{{ technicalCondition.validrange }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.createtime')"></span>
          </dt>
          <dd>
            <span>{{ technicalCondition.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + technicalCondition.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="technicalCondition.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: technicalCondition.creatorid.id } }">{{
                technicalCondition.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.technicalCondition.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="technicalCondition.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: technicalCondition.auditorid.id } }">{{
                technicalCondition.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="technicalCondition.id"
          :to="{ name: 'TechnicalConditionEdit', params: { technicalConditionId: technicalCondition.id } }"
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

<script lang="ts" src="./technical-condition-details.component.ts"></script>
