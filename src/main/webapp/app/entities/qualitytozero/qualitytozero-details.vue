<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualitytozero">
        <h2 class="jh-entity-heading" data-cy="qualitytozeroDetailsHeading">
          <span v-text="t$('jy1App.qualitytozero.detail.title')"></span> {{ qualitytozero.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.belongwbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.belongwbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.outsourcingcontractid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.outsourcingcontractid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.qualityproblemid')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.qualityproblemid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.qualityproblemname')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.qualityproblemname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.problemhappentime')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.problemhappentime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.problemresponsibleperson')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.problemresponsibleperson }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.problemresponsibleunit')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.problemresponsibleunit }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.producttype')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.producttype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.productname')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.productname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.problemphenomenon')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.problemphenomenon }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.problemtype')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.problemtype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.qualitylevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.qualitylevel }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.zerotype')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.zerotype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.problemreasonanalysis')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.problemreasonanalysis }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.problemreasoncategory')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.problemreasoncategory }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.takemeasures')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.takemeasures }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.onebyonecategory')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.onebyonecategory }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.verificationeffect')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.verificationeffect }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.qualityprojectreport')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.qualityprojectreport }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.qualitytozeroreport')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.qualitytozeroreport }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.reviewopinion')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.reviewopinion }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.implementationverificationtable')"></span>
          </dt>
          <dd class="field">
            <span>{{ qualitytozero.implementationverificationtable }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.qualitytozero.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + qualitytozero.auditStatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.qualitytozero.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="qualitytozero.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: qualitytozero.workbag.id } }">{{
                qualitytozero.workbag.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualitytozero.id"
          :to="{ name: 'QualitytozeroEdit', params: { qualitytozeroId: qualitytozero.id } }"
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

<script lang="ts" src="./qualitytozero-details.component.ts"></script>
