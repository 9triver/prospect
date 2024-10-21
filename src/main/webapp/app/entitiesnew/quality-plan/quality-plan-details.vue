<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualityPlan">
        <h2 class="jh-entity-heading" data-cy="qualityPlanDetailsHeading">
          <span v-text="t$('jy1App.qualityPlan.detail.title')"></span> {{ qualityPlan.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.qualityPlan.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityPlan.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityPlan.qualitytype')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.QualityType.' + qualityPlan.qualitytype)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityPlan.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + qualityPlan.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityPlan.wbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityPlan.wbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityPlan.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityPlan.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityPlan.fileversion')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityPlan.fileversion }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityPlan.author')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityPlan.author }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualityPlan.attachment')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualityPlan.attachment }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.qualityPlan.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="qualityPlan.projectwbs">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: qualityPlan.projectwbs.id } }">{{
                qualityPlan.projectwbs.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.qualityPlan.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="qualityPlan.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: qualityPlan.workbag.id } }">{{
                qualityPlan.workbag.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualityPlan.id"
          :to="{ name: 'QualityPlanEdit', params: { qualityPlanId: qualityPlan.id } }"
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

<script lang="ts" src="./quality-plan-details.component.ts"></script>
