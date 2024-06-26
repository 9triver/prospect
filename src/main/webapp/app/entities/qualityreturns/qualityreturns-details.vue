<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualityreturns">
        <h2 class="jh-entity-heading" data-cy="qualityreturnsDetailsHeading">
          <span v-text="t$('jHipster0App.qualityreturns.detail.title')"></span> {{ qualityreturns.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.qualityreturns.qualityreturnsname')"></span>
          </dt>
          <dd>
            <span>{{ qualityreturns.qualityreturnsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityreturns.starttime')"></span>
          </dt>
          <dd>
            <span>{{ qualityreturns.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityreturns.endtime')"></span>
          </dt>
          <dd>
            <span>{{ qualityreturns.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityreturns.qualitytype')"></span>
          </dt>
          <dd>
            <span>{{ qualityreturns.qualitytype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityreturns.returnstime')"></span>
          </dt>
          <dd>
            <span>{{ qualityreturns.returnstime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualityreturns.returnsstatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.ReturnsStatus.' + qualityreturns.returnsstatus)"></span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualityreturns.id"
          :to="{ name: 'QualityreturnsEdit', params: { qualityreturnsId: qualityreturns.id } }"
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

<script lang="ts" src="./qualityreturns-details.component.ts"></script>
