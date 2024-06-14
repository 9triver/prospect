<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="wbssubmanage">
        <h2 class="jh-entity-heading" data-cy="wbssubmanageDetailsHeading">
          <span v-text="t$('jHipster3App.wbssubmanage.detail.title')"></span> {{ wbssubmanage.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.pbssubid')"></span>
          </dt>
          <dd>
            <span>{{ wbssubmanage.pbssubid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.pbssubname')"></span>
          </dt>
          <dd>
            <span>{{ wbssubmanage.pbssubname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.responsiblename')"></span>
          </dt>
          <dd>
            <span>{{ wbssubmanage.responsiblename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.responsibledepartment')"></span>
          </dt>
          <dd>
            <span>{{ wbssubmanage.responsibledepartment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.relevantdepartment')"></span>
          </dt>
          <dd>
            <span>{{ wbssubmanage.relevantdepartment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.type')"></span>
          </dt>
          <dd>
            <span>{{ wbssubmanage.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.starttime')"></span>
          </dt>
          <dd>
            <span>{{ wbssubmanage.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.endtime')"></span>
          </dt>
          <dd>
            <span>{{ wbssubmanage.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + wbssubmanage.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + wbssubmanage.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="wbssubmanage.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: wbssubmanage.responsibleid.id } }">{{
                wbssubmanage.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbssubmanage.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="wbssubmanage.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: wbssubmanage.auditorid.id } }">{{
                wbssubmanage.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="wbssubmanage.id"
          :to="{ name: 'WbssubmanageEdit', params: { wbssubmanageId: wbssubmanage.id } }"
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

<script lang="ts" src="./wbssubmanage-details.component.ts"></script>
