<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="secrecymanagementWbs">
        <h2 class="jh-entity-heading" data-cy="secrecymanagementWbsDetailsHeading">
          <span v-text="t$('jHipster0App.secrecymanagementWbs.detail.title')"></span> {{ secrecymanagementWbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagementWbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagementWbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagementWbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagementWbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ secrecymanagementWbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagementWbs.secrecysystem')"></span>
          </dt>
          <dd>
            <div v-if="secrecymanagementWbs.secrecysystem">
              <router-link :to="{ name: 'SecrecysystemView', params: { secrecysystemId: secrecymanagementWbs.secrecysystem.id } }">{{
                secrecymanagementWbs.secrecysystem.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.secrecymanagementWbs.projectSecrecy')"></span>
          </dt>
          <dd>
            <div v-if="secrecymanagementWbs.projectSecrecy">
              <router-link :to="{ name: 'ProjectSecrecyView', params: { projectSecrecyId: secrecymanagementWbs.projectSecrecy.id } }">{{
                secrecymanagementWbs.projectSecrecy.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="secrecymanagementWbs.id"
          :to="{ name: 'SecrecymanagementWbsEdit', params: { secrecymanagementWbsId: secrecymanagementWbs.id } }"
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

<script lang="ts" src="./secrecymanagement-wbs-details.component.ts"></script>
