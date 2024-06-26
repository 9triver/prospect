<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="outsourcingmanagement">
        <h2 class="jh-entity-heading" data-cy="outsourcingmanagementDetailsHeading">
          <span v-text="t$('jHipster0App.outsourcingmanagement.detail.title')"></span> {{ outsourcingmanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagement.name')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmanagement.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagement.description')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmanagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagement.starttime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmanagement.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagement.endtime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingmanagement.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.outsourcingmanagement.wbs')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingmanagement.wbs">
              <router-link
                :to="{ name: 'OutsourcingmanagementWbsView', params: { outsourcingmanagementWbsId: outsourcingmanagement.wbs.id } }"
                >{{ outsourcingmanagement.wbs.id }}</router-link
              >
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="outsourcingmanagement.id"
          :to="{ name: 'OutsourcingmanagementEdit', params: { outsourcingmanagementId: outsourcingmanagement.id } }"
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

<script lang="ts" src="./outsourcingmanagement-details.component.ts"></script>
