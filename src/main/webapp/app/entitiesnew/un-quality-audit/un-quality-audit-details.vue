<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="unQualityAudit">
        <h2 class="jh-entity-heading" data-cy="unQualityAuditDetailsHeading">
          <span v-text="t$('jy1App.unQualityAudit.detail.title')"></span> {{ unQualityAudit.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.belongwbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.belongwbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.outsourcingcontractid')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.outsourcingcontractid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualityid')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualityid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualityname')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualityname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualityunit')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualityunit }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualitytrialgroup')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualitytrialgroup }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.inspector')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.inspector }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualitystage')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualitystage }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualitynumber')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualitynumber }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualityintroduction')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualityintroduction }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.unqualitycategory')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.unqualitycategory }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.handlingopinion')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.handlingopinion }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.applicant')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.applicant }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.applicationdate')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.applicationdate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + unQualityAudit.auditStatus)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.attachment')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.attachment }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.disposalmethod')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.disposalmethod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.causeanalysis')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.causeanalysis }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.correctivemeasures')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.correctivemeasures }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.unQualityAudit.remarks')"></span>
          </dt>
          <dd class="field">
            <span>{{ unQualityAudit.remarks }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.unQualityAudit.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="unQualityAudit.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: unQualityAudit.workbag.id } }">{{
                unQualityAudit.workbag.id
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
