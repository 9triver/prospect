<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualityObjectives">
        <h2 class="jh-entity-heading" data-cy="qualityObjectivesDetailsHeading">
          <span v-text="t$('jy1App.qualityObjectives.detail.title')"></span> {{ qualityObjectives.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.name')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.objectives')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.objectives }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.qualitytype')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.QualityType.' + qualityObjectives.qualitytype)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + qualityObjectives.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.target')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.target }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.statisticalmethod')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.statisticalmethod }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.statisticalfrequency')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.statisticalfrequency }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.istarget')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.istarget }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.progress')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.progress }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.description')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.problems')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.problems }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.improvementmeasures')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.improvementmeasures }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.returntime')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.returntime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.createtime')"></span>
          </dt>
          <dd>
            <span>{{ qualityObjectives.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + qualityObjectives.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.responsibleperson')"></span>
          </dt>
          <dd>
            <div v-if="qualityObjectives.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: qualityObjectives.responsibleperson.id } }">{{
                qualityObjectives.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="qualityObjectives.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: qualityObjectives.auditorid.id } }">{{
                qualityObjectives.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.projectwbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectwbs, i) in qualityObjectives.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityObjectives.qualityReturns')"></span>
          </dt>
          <dd>
            <span v-for="(qualityReturns, i) in qualityObjectives.qualityReturns" :key="qualityReturns.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'QualityReturnsView', params: { qualityReturnsId: qualityReturns.id } }">{{
                qualityReturns.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualityObjectives.id"
          :to="{ name: 'QualityObjectivesEdit', params: { qualityObjectivesId: qualityObjectives.id } }"
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

<script lang="ts" src="./quality-objectives-details.component.ts"></script>
