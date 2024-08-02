<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectremit">
        <h2 class="jh-entity-heading" data-cy="projectremitDetailsHeading">
          <span v-text="t$('jy1App.projectremit.detail.title')"></span> {{ projectremit.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.projectremit.remit')"></span>
          </dt>
          <dd>
            <span>{{ projectremit.remit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectremit.outdeportment')"></span>
          </dt>
          <dd>
            <span>{{ projectremit.outdeportment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectremit.indeportment')"></span>
          </dt>
          <dd>
            <span>{{ projectremit.indeportment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectremit.projectname')"></span>
          </dt>
          <dd>
            <span>{{ projectremit.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectremit.deportment')"></span>
          </dt>
          <dd>
            <span>{{ projectremit.deportment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectremit.projectleader')"></span>
          </dt>
          <dd>
            <span>{{ projectremit.projectleader }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectremit.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + projectremit.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.projectremit.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + projectremit.auditStatus)"></span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectremit.id"
          :to="{ name: 'ProjectremitEdit', params: { projectremitId: projectremit.id } }"
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

<script lang="ts" src="./projectremit-details.component.ts"></script>
