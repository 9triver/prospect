<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="unitbudget">
        <h2 class="jh-entity-heading" data-cy="unitbudgetDetailsHeading">
          <span v-text="t$('jHipster0App.unitbudget.detail.title')"></span> {{ unitbudget.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.subprojectname')"></span>
          </dt>
          <dd>
            <span>{{ unitbudget.subprojectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.unitbudgername')"></span>
          </dt>
          <dd>
            <span>{{ unitbudget.unitbudgername }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.billingunit')"></span>
          </dt>
          <dd>
            <span>{{ unitbudget.billingunit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.number')"></span>
          </dt>
          <dd>
            <span>{{ unitbudget.number }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.totalbudget')"></span>
          </dt>
          <dd>
            <span>{{ unitbudget.totalbudget }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.maintainerbudget')"></span>
          </dt>
          <dd>
            <span>{{ unitbudget.maintainerbudget }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.outsourcingbudget')"></span>
          </dt>
          <dd>
            <span>{{ unitbudget.outsourcingbudget }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.earmarkedbudget')"></span>
          </dt>
          <dd>
            <span>{{ unitbudget.earmarkedbudget }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.testbudget')"></span>
          </dt>
          <dd>
            <span>{{ unitbudget.testbudget }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="unitbudget.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: unitbudget.creatorid.id } }">{{
                unitbudget.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.unitbudget.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="unitbudget.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: unitbudget.auditorid.id } }">{{
                unitbudget.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="unitbudget.id"
          :to="{ name: 'UnitbudgetEdit', params: { unitbudgetId: unitbudget.id } }"
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

<script lang="ts" src="./unitbudget-details.component.ts"></script>
