<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="fundsmanagement">
        <h2 class="jh-entity-heading" data-cy="fundsmanagementDetailsHeading">
          <span v-text="t$('jHipster0App.fundsmanagement.detail.title')"></span> {{ fundsmanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagement.name')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagement.description')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagement.starttime')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagement.endtime')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.fundsmanagement.wbs')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagement.wbs">
              <router-link :to="{ name: 'FundsmanagementWbsView', params: { fundsmanagementWbsId: fundsmanagement.wbs.id } }">{{
                fundsmanagement.wbs.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="fundsmanagement.id"
          :to="{ name: 'FundsmanagementEdit', params: { fundsmanagementId: fundsmanagement.id } }"
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

<script lang="ts" src="./fundsmanagement-details.component.ts"></script>
