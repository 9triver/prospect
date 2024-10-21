<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="work">
        <h2 class="jh-entity-heading" data-cy="workDetailsHeading"><span v-text="t$('jy1App.work.detail.title')"></span> {{ work.id }}</h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.work.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ work.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.work.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + work.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.work.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ work.description }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.work.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ work.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.work.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + work.auditStatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.work.workbag')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(workbag, i) in work.workbags" :key="workbag.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: workbag.id } }">{{ workbag.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="work.id" :to="{ name: 'WorkEdit', params: { workId: work.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./work-details.component.ts"></script>
