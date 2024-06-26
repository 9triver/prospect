<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="humanresources">
        <h2 class="jh-entity-heading" data-cy="humanresourcesDetailsHeading">
          <span v-text="t$('jHipster0App.humanresources.detail.title')"></span> {{ humanresources.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.humanresources.name')"></span>
          </dt>
          <dd>
            <span>{{ humanresources.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.outdeportment')"></span>
          </dt>
          <dd>
            <span>{{ humanresources.outdeportment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.indeportment')"></span>
          </dt>
          <dd>
            <span>{{ humanresources.indeportment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.adjusttime')"></span>
          </dt>
          <dd>
            <span>{{ humanresources.adjusttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.projectname')"></span>
          </dt>
          <dd>
            <span>{{ humanresources.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.deportment')"></span>
          </dt>
          <dd>
            <span>{{ humanresources.deportment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.projectleader')"></span>
          </dt>
          <dd>
            <span>{{ humanresources.projectleader }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + humanresources.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + humanresources.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.project')"></span>
          </dt>
          <dd>
            <div v-if="humanresources.project">
              <router-link :to="{ name: 'ProjectView', params: { projectId: humanresources.project.id } }">{{
                humanresources.project.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="humanresources.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: humanresources.creatorid.id } }">{{
                humanresources.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.humanresources.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="humanresources.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: humanresources.auditorid.id } }">{{
                humanresources.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="humanresources.id"
          :to="{ name: 'HumanresourcesEdit', params: { humanresourcesId: humanresources.id } }"
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

<script lang="ts" src="./humanresources-details.component.ts"></script>
