<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="secrecysystem">
        <h2 class="jh-entity-heading" data-cy="secrecysystemDetailsHeading">
          <span v-text="t$('jHipster0App.secrecysystem.detail.title')"></span> {{ secrecysystem.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.secrecysystem.publishedby')"></span>
          </dt>
          <dd>
            <span>{{ secrecysystem.publishedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecysystem.documentname')"></span>
          </dt>
          <dd>
            <span>{{ secrecysystem.documentname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecysystem.documenttype')"></span>
          </dt>
          <dd>
            <span>{{ secrecysystem.documenttype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecysystem.documentsize')"></span>
          </dt>
          <dd>
            <span>{{ secrecysystem.documentsize }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecysystem.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + secrecysystem.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecysystem.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + secrecysystem.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecysystem.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="secrecysystem.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: secrecysystem.creatorid.id } }">{{
                secrecysystem.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecysystem.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="secrecysystem.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: secrecysystem.auditorid.id } }">{{
                secrecysystem.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="secrecysystem.id"
          :to="{ name: 'SecrecysystemEdit', params: { secrecysystemId: secrecysystem.id } }"
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

<script lang="ts" src="./secrecysystem-details.component.ts"></script>
