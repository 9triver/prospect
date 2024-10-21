<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="document">
        <h2 class="jh-entity-heading" data-cy="documentDetailsHeading">
          <span v-text="t$('jy1App.document.detail.title')"></span> {{ document.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.document.documentname')"></span>
          </dt>
          <dd class="field">
            <span>{{ document.documentname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.document.documenttype')"></span>
          </dt>
          <dd class="field">
            <span>{{ document.documenttype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.document.documentsize')"></span>
          </dt>
          <dd class="field">
            <span>{{ document.documentsize }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.document.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + document.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.document.url')"></span>
          </dt>
          <dd class="field">
            <span>{{ document.url }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.document.createtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ document.createtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.document.creatorname')"></span>
          </dt>
          <dd class="field">
            <span>{{ document.creatorname }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.document.creatorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="document.creatorid">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: document.creatorid.id } }">{{
                document.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.document.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="document.projectwbs">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: document.projectwbs.id } }">{{
                document.projectwbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="document.id" :to="{ name: 'DocumentEdit', params: { documentId: document.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./document-details.component.ts"></script>
