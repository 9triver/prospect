<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualityobjectives">
        <h2 class="jh-entity-heading" data-cy="qualityobjectivesDetailsHeading">
          <span v-text="t$('jHipster0App.qualityobjectives.detail.title')"></span> {{ qualityobjectives.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.qualityobjectives.qualityobjectivesname')"></span>
          </dt>
          <dd>
            <span>{{ qualityobjectives.qualityobjectivesname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityobjectives.year')"></span>
          </dt>
          <dd>
            <span>{{ qualityobjectives.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityobjectives.createtime')"></span>
          </dt>
          <dd>
            <span>{{ qualityobjectives.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityobjectives.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ qualityobjectives.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityobjectives.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + qualityobjectives.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityobjectives.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + qualityobjectives.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityobjectives.qualityreturns')"></span>
          </dt>
          <dd>
            <div v-if="qualityobjectives.qualityreturns">
              <router-link :to="{ name: 'QualityreturnsView', params: { qualityreturnsId: qualityobjectives.qualityreturns.id } }">{{
                qualityobjectives.qualityreturns.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityobjectives.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="qualityobjectives.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: qualityobjectives.creatorid.id } }">{{
                qualityobjectives.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityobjectives.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="qualityobjectives.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: qualityobjectives.auditorid.id } }">{{
                qualityobjectives.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualityobjectives.id"
          :to="{ name: 'QualityobjectivesEdit', params: { qualityobjectivesId: qualityobjectives.id } }"
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

<script lang="ts" src="./qualityobjectives-details.component.ts"></script>
