<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="subject">
        <h2 class="jh-entity-heading" data-cy="subjectDetailsHeading">
          <span v-text="t$('jy1App.subject.detail.title')"></span> {{ subject.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.subject.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ subject.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.subject.type')"></span>
          </dt>
          <dd class="field">
            <span>{{ subject.type }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.subject.parentid')"></span>
          </dt>
          <dd class="field">
            <span>{{ subject.parentid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.subject.remark')"></span>
          </dt>
          <dd class="field">
            <span>{{ subject.remark }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="subject.id" :to="{ name: 'SubjectEdit', params: { subjectId: subject.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./subject-details.component.ts"></script>
