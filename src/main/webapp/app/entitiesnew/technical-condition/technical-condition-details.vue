<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="technicalCondition">
        <h2 class="jh-entity-heading" data-cy="technicalConditionDetailsHeading">
          <span v-text="t$('jy1App.technicalCondition.detail.title')"></span> {{ technicalCondition.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.belongwbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.belongwbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.outsourcingcontractid')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.outsourcingcontractid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.technicalid')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.technicalid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.technicalname')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.technicalname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.changedfilename')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.changedfilename }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.applicant')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.applicant }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.applicationdate')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.applicationdate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.changedreason')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.changedreason }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.changedbefore')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.changedbefore }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.changedafter')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.changedafter }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.distributionrange')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.distributionrange }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.remarks')"></span>
          </dt>
          <dd class="field">
            <span>{{ technicalCondition.remarks }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.technicalCondition.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + technicalCondition.auditStatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.technicalCondition.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="technicalCondition.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: technicalCondition.workbag.id } }">{{
                technicalCondition.workbag.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="technicalCondition.id"
          :to="{ name: 'TechnicalConditionEdit', params: { technicalConditionId: technicalCondition.id } }"
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

<script lang="ts" src="./technical-condition-details.component.ts"></script>
