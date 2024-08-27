<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectremit">
        <h2 class="jh-entity-heading" data-cy="projectremitDetailsHeading">
          <span v-text="t$('jy1App.projectremit.detail.title')"></span> 
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.projectremit.remit')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectremit.remit }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectremit.outdeportment')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectremit.outdeportment }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectremit.indeportment')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectremit.indeportment }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectremit.projectname')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectremit.projectname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectremit.deportment')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectremit.deportment }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectremit.projectleader')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectremit.projectleader }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectremit.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + projectremit.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectremit.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + projectremit.auditStatus)"></span>
          </dd>
        </dl>
        <el-button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </el-button>
        <router-link
          v-if="projectremit.id"
          :to="{ name: 'ProjectremitEdit', params: { projectremitId: projectremit.id } }"
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

<script lang="ts" src="./projectremit-details.component.ts"></script>
