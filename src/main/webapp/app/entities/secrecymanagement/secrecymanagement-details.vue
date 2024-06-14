<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="secrecymanagement">
        <h2 class="jh-entity-heading" data-cy="secrecymanagementDetailsHeading">
          <span v-text="t$('jHipster3App.secrecymanagement.detail.title')"></span> {{ secrecymanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.secrecymanagement.secrecyid')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagement.secrecyid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.secrecymanagement.publishedby')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagement.publishedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.secrecymanagement.documentname')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagement.documentname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.secrecymanagement.documenttype')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagement.documenttype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.secrecymanagement.documentsize')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagement.documentsize }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.secrecymanagement.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + secrecymanagement.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.secrecymanagement.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + secrecymanagement.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.secrecymanagement.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="secrecymanagement.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: secrecymanagement.creatorid.id } }">{{
                secrecymanagement.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.secrecymanagement.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="secrecymanagement.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: secrecymanagement.auditorid.id } }">{{
                secrecymanagement.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="secrecymanagement.id"
          :to="{ name: 'SecrecymanagementEdit', params: { secrecymanagementId: secrecymanagement.id } }"
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

<script lang="ts" src="./secrecymanagement-details.component.ts"></script>
