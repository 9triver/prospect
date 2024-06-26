<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="document">
        <h2 class="jh-entity-heading" data-cy="documentDetailsHeading">
          <span v-text="t$('jHipster0App.document.detail.title')"></span> {{ document.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.document.documentname')"></span>
          </dt>
          <dd>
            <span>{{ document.documentname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.document.documenttype')"></span>
          </dt>
          <dd>
            <span>{{ document.documenttype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.document.documentsize')"></span>
          </dt>
          <dd>
            <span>{{ document.documentsize }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.document.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + document.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.document.createtime')"></span>
          </dt>
          <dd>
            <span>{{ document.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.document.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ document.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.document.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="document.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: document.creatorid.id } }">{{
                document.creatorid.id
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
