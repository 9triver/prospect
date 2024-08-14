<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="unQualityAudit">
        <h2 class="jh-entity-heading" data-cy="unQualityAuditDetailsHeading">
          <span v-text="t$('jy1App.unQualityAudit.detail.title')"></span> {{ unQualityAudit.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualityname')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualityname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualitytype')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualitytype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.belongunitid')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.belongunitid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.belongunitname')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.belongunitname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.auditteam')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.auditteam }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.auditperson')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.auditperson }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualitynum')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualitynum }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.creatorname')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.creatorname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + unQualityAudit.auditStatus)"></span>
          </dd>
        </dl>
        <el-button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </el-button>
        <router-link
          v-if="unQualityAudit.id"
          :to="{ name: 'UnQualityAuditEdit', params: { unQualityAuditId: unQualityAudit.id } }"
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

<script lang="ts" src="./un-quality-audit-details.component.ts"></script>
