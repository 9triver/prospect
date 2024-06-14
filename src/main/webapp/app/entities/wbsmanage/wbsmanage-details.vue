<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="wbsmanage">
        <h2 class="jh-entity-heading" data-cy="wbsmanageDetailsHeading">
          <span v-text="t$('jHipster3App.wbsmanage.detail.title')"></span> {{ wbsmanage.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.wbsid')"></span>
          </dt>
          <dd>
            <span>{{ wbsmanage.wbsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.wbsname')"></span>
          </dt>
          <dd>
            <span>{{ wbsmanage.wbsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.description')"></span>
          </dt>
          <dd>
            <span>{{ wbsmanage.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.result')"></span>
          </dt>
          <dd>
            <span>{{ wbsmanage.result }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.administratorname')"></span>
          </dt>
          <dd>
            <span>{{ wbsmanage.administratorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.responsiblename')"></span>
          </dt>
          <dd>
            <span>{{ wbsmanage.responsiblename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.department')"></span>
          </dt>
          <dd>
            <span>{{ wbsmanage.department }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + wbsmanage.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + wbsmanage.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.wbssubmanage')"></span>
          </dt>
          <dd>
            <div v-if="wbsmanage.wbssubmanage">
              <router-link :to="{ name: 'WbssubmanageView', params: { wbssubmanageId: wbsmanage.wbssubmanage.id } }">{{
                wbsmanage.wbssubmanage.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.pbssubmanage')"></span>
          </dt>
          <dd>
            <div v-if="wbsmanage.pbssubmanage">
              <router-link :to="{ name: 'PbssubmanageView', params: { pbssubmanageId: wbsmanage.pbssubmanage.id } }">{{
                wbsmanage.pbssubmanage.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.project')"></span>
          </dt>
          <dd>
            <div v-if="wbsmanage.project">
              <router-link :to="{ name: 'ProjectView', params: { projectId: wbsmanage.project.id } }">{{
                wbsmanage.project.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.administratorid')"></span>
          </dt>
          <dd>
            <div v-if="wbsmanage.administratorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: wbsmanage.administratorid.id } }">{{
                wbsmanage.administratorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="wbsmanage.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: wbsmanage.auditorid.id } }">{{
                wbsmanage.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.wbsmanage.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="wbsmanage.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: wbsmanage.responsibleid.id } }">{{
                wbsmanage.responsibleid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="wbsmanage.id"
          :to="{ name: 'WbsmanageEdit', params: { wbsmanageId: wbsmanage.id } }"
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

<script lang="ts" src="./wbsmanage-details.component.ts"></script>
