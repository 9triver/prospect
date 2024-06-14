<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="pbssubmanage">
        <h2 class="jh-entity-heading" data-cy="pbssubmanageDetailsHeading">
          <span v-text="t$('jHipster3App.pbssubmanage.detail.title')"></span> {{ pbssubmanage.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.pbssubid')"></span>
          </dt>
          <dd>
            <span>{{ pbssubmanage.pbssubid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.pbssubname')"></span>
          </dt>
          <dd>
            <span>{{ pbssubmanage.pbssubname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.responsiblename')"></span>
          </dt>
          <dd>
            <span>{{ pbssubmanage.responsiblename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.responsibledepartment')"></span>
          </dt>
          <dd>
            <span>{{ pbssubmanage.responsibledepartment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.relevantdepartment')"></span>
          </dt>
          <dd>
            <span>{{ pbssubmanage.relevantdepartment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.type')"></span>
          </dt>
          <dd>
            <span>{{ pbssubmanage.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.starttime')"></span>
          </dt>
          <dd>
            <span>{{ pbssubmanage.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.endtime')"></span>
          </dt>
          <dd>
            <span>{{ pbssubmanage.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + pbssubmanage.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + pbssubmanage.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="pbssubmanage.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: pbssubmanage.responsibleid.id } }">{{
                pbssubmanage.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.pbssubmanage.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="pbssubmanage.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: pbssubmanage.auditorid.id } }">{{
                pbssubmanage.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="pbssubmanage.id"
          :to="{ name: 'PbssubmanageEdit', params: { pbssubmanageId: pbssubmanage.id } }"
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

<script lang="ts" src="./pbssubmanage-details.component.ts"></script>
