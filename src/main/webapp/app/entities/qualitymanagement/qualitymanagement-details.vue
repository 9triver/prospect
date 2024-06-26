<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qualitymanagement">
        <h2 class="jh-entity-heading" data-cy="qualitymanagementDetailsHeading">
          <span v-text="t$('jHipster0App.qualitymanagement.detail.title')"></span> {{ qualitymanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagement.name')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagement.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagement.description')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagement.starttime')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagement.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagement.endtime')"></span>
          </dt>
          <dd>
            <span>{{ qualitymanagement.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.qualitymanagement.wbs')"></span>
          </dt>
          <dd>
            <div v-if="qualitymanagement.wbs">
              <router-link :to="{ name: 'QualitymanagementWbsView', params: { qualitymanagementWbsId: qualitymanagement.wbs.id } }">{{
                qualitymanagement.wbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="qualitymanagement.id"
          :to="{ name: 'QualitymanagementEdit', params: { qualitymanagementId: qualitymanagement.id } }"
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

<script lang="ts" src="./qualitymanagement-details.component.ts"></script>
