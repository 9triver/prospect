<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualityReturns">
        <h2 class="jh-entity-heading" data-cy="qualityReturnsDetailsHeading">
          <span v-text="t$('jy1App.qualityReturns.detail.title')"></span> {{ qualityReturns.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.qualityReturns.name')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.objectives')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.objectives }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.qualitytype')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.QualityType.' + qualityReturns.qualitytype)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + qualityReturns.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.target')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.target }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.statisticalmethod')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.statisticalmethod }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.statisticalfrequency')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.statisticalfrequency }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.istarget')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.istarget }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.progress')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.progress }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.description')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.problems')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.problems }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.improvementmeasures')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.improvementmeasures }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.returntime')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.returntime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.createtime')"></span>
          </dt>
          <dd>
            <span>{{ qualityReturns.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.AuditStatus.' + qualityReturns.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.responsibleperson')"></span>
          </dt>
          <dd>
            <div v-if="qualityReturns.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: qualityReturns.responsibleperson.id } }">{{
                qualityReturns.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="qualityReturns.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: qualityReturns.auditorid.id } }">{{
                qualityReturns.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="qualityReturns.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: qualityReturns.creatorid.id } }">{{
                qualityReturns.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.qualityReturns.qualityObjectives')"></span>
          </dt>
          <dd>
            <span v-for="(qualityObjectives, i) in qualityReturns.qualityObjectives" :key="qualityObjectives.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'QualityObjectivesView', params: { qualityObjectivesId: qualityObjectives.id } }">{{
                qualityObjectives.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualityReturns.id"
          :to="{ name: 'QualityReturnsEdit', params: { qualityReturnsId: qualityReturns.id } }"
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

<script lang="ts" src="./quality-returns-details.component.ts"></script>
