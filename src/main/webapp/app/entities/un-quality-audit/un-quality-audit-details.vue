<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="unQualityAudit">
        <h2 class="jh-entity-heading" data-cy="unQualityAuditDetailsHeading">
          <span v-text="t$('jHipster0App.unQualityAudit.detail.title')"></span> {{ unQualityAudit.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.unqualityname')"></span>
          </dt>
          <dd>
            <span>{{ unQualityAudit.unqualityname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.unqualitytype')"></span>
          </dt>
          <dd>
            <span>{{ unQualityAudit.unqualitytype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.belongunitid')"></span>
          </dt>
          <dd>
            <span>{{ unQualityAudit.belongunitid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.belongunitname')"></span>
          </dt>
          <dd>
            <span>{{ unQualityAudit.belongunitname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.auditteam')"></span>
          </dt>
          <dd>
            <span>{{ unQualityAudit.auditteam }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.auditperson')"></span>
          </dt>
          <dd>
            <span>{{ unQualityAudit.auditperson }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.unqualitynum')"></span>
          </dt>
          <dd>
            <span>{{ unQualityAudit.unqualitynum }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ unQualityAudit.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + unQualityAudit.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.inspector')"></span>
          </dt>
          <dd>
            <div v-if="unQualityAudit.inspector">
              <router-link :to="{ name: 'OfficersView', params: { officersId: unQualityAudit.inspector.id } }">{{
                unQualityAudit.inspector.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unQualityAudit.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="unQualityAudit.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: unQualityAudit.auditorid.id } }">{{
                unQualityAudit.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="unQualityAudit.id"
          :to="{ name: 'UnQualityAuditEdit', params: { unQualityAuditId: unQualityAudit.id } }"
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

<script lang="ts" src="./un-quality-audit-details.component.ts"></script>
