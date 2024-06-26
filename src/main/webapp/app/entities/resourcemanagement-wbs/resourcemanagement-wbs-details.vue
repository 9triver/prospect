<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="resourcemanagementWbs">
        <h2 class="jh-entity-heading" data-cy="resourcemanagementWbsDetailsHeading">
          <span v-text="t$('jHipster0App.resourcemanagementWbs.detail.title')"></span> {{ resourcemanagementWbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagementWbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagementWbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagementWbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagementWbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ resourcemanagementWbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagementWbs.projectHumanresourcesplan')"></span>
          </dt>
          <dd>
            <div v-if="resourcemanagementWbs.projectHumanresourcesplan">
              <router-link
                :to="{
                  name: 'ProjectHumanresourcesplanView',
                  params: { projectHumanresourcesplanId: resourcemanagementWbs.projectHumanresourcesplan.id },
                }"
                >{{ resourcemanagementWbs.projectHumanresourcesplan.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagementWbs.projectremit')"></span>
          </dt>
          <dd>
            <div v-if="resourcemanagementWbs.projectremit">
              <router-link :to="{ name: 'ProjectremitView', params: { projectremitId: resourcemanagementWbs.projectremit.id } }">{{
                resourcemanagementWbs.projectremit.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.resourcemanagementWbs.humanresources')"></span>
          </dt>
          <dd>
            <div v-if="resourcemanagementWbs.humanresources">
              <router-link :to="{ name: 'HumanresourcesView', params: { humanresourcesId: resourcemanagementWbs.humanresources.id } }">{{
                resourcemanagementWbs.humanresources.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="resourcemanagementWbs.id"
          :to="{ name: 'ResourcemanagementWbsEdit', params: { resourcemanagementWbsId: resourcemanagementWbs.id } }"
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

<script lang="ts" src="./resourcemanagement-wbs-details.component.ts"></script>
