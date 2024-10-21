<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectdeliverables">
        <h2 class="jh-entity-heading" data-cy="projectdeliverablesDetailsHeading">
          <span v-text="t$('jy1App.projectdeliverables.detail.title')"></span> {{ projectdeliverables.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.projectdeliverables.parentcode')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectdeliverables.parentcode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectdeliverables.isSubmit')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectdeliverables.isSubmit }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.projectdeliverables.status')"></span>
          </dt>
          <dd class="field">
            <span>{{ projectdeliverables.status }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectdeliverables.code')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="projectdeliverables.code">
              <router-link :to="{ name: 'DeliverablesView', params: { deliverablesId: projectdeliverables.code.id } }">{{
                projectdeliverables.code.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectdeliverables.belongwbsid')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(belongwbsid, i) in projectdeliverables.belongwbsids" :key="belongwbsid.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: belongwbsid.id } }">{{ belongwbsid.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.projectdeliverables.belongworkbagid')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(belongworkbagid, i) in projectdeliverables.belongworkbagids" :key="belongworkbagid.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: belongworkbagid.id } }">{{ belongworkbagid.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectdeliverables.id"
          :to="{ name: 'ProjectdeliverablesEdit', params: { projectdeliverablesId: projectdeliverables.id } }"
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

<script lang="ts" src="./projectdeliverables-details.component.ts"></script>
