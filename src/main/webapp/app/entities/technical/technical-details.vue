<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="technical">
        <h2 class="jh-entity-heading" data-cy="technicalDetailsHeading">
          <span v-text="t$('jy1App.technical.detail.title')"></span> {{ technical.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.technical.name')"></span>
          </dt>
          <dd>
            <span>{{ technical.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.technical.description')"></span>
          </dt>
          <dd>
            <span>{{ technical.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.technical.starttime')"></span>
          </dt>
          <dd>
            <span>{{ technical.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.technical.endtime')"></span>
          </dt>
          <dd>
            <span>{{ technical.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.technical.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="technical.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: technical.creatorid.id } }">{{
                technical.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.technical.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="technical.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: technical.auditorid.id } }">{{
                technical.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.technical.projectwbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectwbs, i) in technical.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="technical.id"
          :to="{ name: 'TechnicalEdit', params: { technicalId: technical.id } }"
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

<script lang="ts" src="./technical-details.component.ts"></script>
