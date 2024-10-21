<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="deviationPermitApplication">
        <h2 class="jh-entity-heading" data-cy="deviationPermitApplicationDetailsHeading">
          <span v-text="t$('jy1App.deviationPermitApplication.detail.title')"></span> {{ deviationPermitApplication.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.wbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.wbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.technicalfileid')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.technicalfileid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.applicationunit')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.applicationunit }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.applicant')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.applicant }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.applicationdate')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.applicationdate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.permitcontent')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.permitcontent }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.permitreason')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.permitreason }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.projectinfluence')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.projectinfluence }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.contractinfluence')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.contractinfluence }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.permitrange')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.permitrange }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.implementationdate')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.implementationdate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.remarks')"></span>
          </dt>
          <dd class="field">
            <span>{{ deviationPermitApplication.remarks }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deviationPermitApplication.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + deviationPermitApplication.auditStatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.deviationPermitApplication.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="deviationPermitApplication.projectwbs">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: deviationPermitApplication.projectwbs.id } }">{{
                deviationPermitApplication.projectwbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="deviationPermitApplication.id"
          :to="{ name: 'DeviationPermitApplicationEdit', params: { deviationPermitApplicationId: deviationPermitApplication.id } }"
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

<script lang="ts" src="./deviation-permit-application-details.component.ts"></script>
