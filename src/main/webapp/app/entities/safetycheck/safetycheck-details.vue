<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="safetycheck">
        <h2 class="jh-entity-heading" data-cy="safetycheckDetailsHeading">
          <span v-text="t$('jHipster0App.safetycheck.detail.title')"></span> {{ safetycheck.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.safetycheckname')"></span>
          </dt>
          <dd>
            <span>{{ safetycheck.safetycheckname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.checksource')"></span>
          </dt>
          <dd>
            <span>{{ safetycheck.checksource }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.checktime')"></span>
          </dt>
          <dd>
            <span>{{ safetycheck.checktime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.effectivetime')"></span>
          </dt>
          <dd>
            <span>{{ safetycheck.effectivetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.operatinglocation')"></span>
          </dt>
          <dd>
            <span>{{ safetycheck.operatinglocation }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.deprotment')"></span>
          </dt>
          <dd>
            <span>{{ safetycheck.deprotment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.phonenumber')"></span>
          </dt>
          <dd>
            <span>{{ safetycheck.phonenumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.risklevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Risklevel.' + safetycheck.risklevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + safetycheck.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="safetycheck.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: safetycheck.auditorid.id } }">{{
                safetycheck.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.safetycheck.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="safetycheck.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: safetycheck.responsibleid.id } }">{{
                safetycheck.responsibleid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="safetycheck.id"
          :to="{ name: 'SafetycheckEdit', params: { safetycheckId: safetycheck.id } }"
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

<script lang="ts" src="./safetycheck-details.component.ts"></script>
