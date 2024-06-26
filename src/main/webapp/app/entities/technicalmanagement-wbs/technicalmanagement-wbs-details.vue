<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="technicalmanagementWbs">
        <h2 class="jh-entity-heading" data-cy="technicalmanagementWbsDetailsHeading">
          <span v-text="t$('jHipster0App.technicalmanagementWbs.detail.title')"></span> {{ technicalmanagementWbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagementWbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ technicalmanagementWbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagementWbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ technicalmanagementWbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagementWbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ technicalmanagementWbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagementWbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ technicalmanagementWbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagementWbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ technicalmanagementWbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.technicalmanagementWbs.technicalCondition')"></span>
          </dt>
          <dd>
            <div v-if="technicalmanagementWbs.technicalCondition">
              <router-link
                :to="{ name: 'TechnicalConditionView', params: { technicalConditionId: technicalmanagementWbs.technicalCondition.id } }"
                >{{ technicalmanagementWbs.technicalCondition.id }}</router-link
              >
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="technicalmanagementWbs.id"
          :to="{ name: 'TechnicalmanagementWbsEdit', params: { technicalmanagementWbsId: technicalmanagementWbs.id } }"
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

<script lang="ts" src="./technicalmanagement-wbs-details.component.ts"></script>
