<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="archives">
        <h2 class="jh-entity-heading" data-cy="archivesDetailsHeading">
          <span v-text="t$('jy1App.archives.detail.title')"></span> {{ archives.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.archives.title')"></span>
          </dt>
          <dd class="field">
            <span>{{ archives.title }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.archives.content')"></span>
          </dt>
          <dd class="field">
            <span>{{ archives.content }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.archives.date')"></span>
          </dt>
          <dd class="field">
            <span>{{ archives.date }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.archives.page')"></span>
          </dt>
          <dd class="field">
            <span>{{ archives.page }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.archives.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + archives.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.archives.confidentialityperiod')"></span>
          </dt>
          <dd class="field">
            <span>{{ archives.confidentialityperiod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.archives.confidentialnumber')"></span>
          </dt>
          <dd class="field">
            <span>{{ archives.confidentialnumber }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.archives.storageperiod')"></span>
          </dt>
          <dd class="field">
            <span>{{ archives.storageperiod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.archives.plannumber')"></span>
          </dt>
          <dd class="field">
            <span>{{ archives.plannumber }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.archives.remarks')"></span>
          </dt>
          <dd class="field">
            <span>{{ archives.remarks }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.archives.receivingnumber')"></span>
          </dt>
          <dd class="field">
            <span>{{ archives.receivingnumber }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.archives.responsibleid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="archives.responsibleid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: archives.responsibleid.id } }">{{
                archives.responsibleid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="archives.id" :to="{ name: 'ArchivesEdit', params: { archivesId: archives.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./archives-details.component.ts"></script>
