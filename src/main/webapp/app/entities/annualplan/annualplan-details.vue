<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="annualplan">
        <h2 class="jh-entity-heading" data-cy="annualplanDetailsHeading">
          <span v-text="t$('jHipster0App.annualplan.detail.title')"></span> {{ annualplan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.annualplan.annualplanname')"></span>
          </dt>
          <dd>
            <span>{{ annualplan.annualplanname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.annualplan.year')"></span>
          </dt>
          <dd>
            <span>{{ annualplan.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.annualplan.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + annualplan.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.annualplan.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ annualplan.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.annualplan.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Annualplanstatus.' + annualplan.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.annualplan.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + annualplan.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.annualplan.document')"></span>
          </dt>
          <dd>
            <div v-if="annualplan.document">
              <router-link :to="{ name: 'DocumentView', params: { documentId: annualplan.document.id } }">{{
                annualplan.document.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.annualplan.monthplan')"></span>
          </dt>
          <dd>
            <div v-if="annualplan.monthplan">
              <router-link :to="{ name: 'MonthplanView', params: { monthplanId: annualplan.monthplan.id } }">{{
                annualplan.monthplan.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.annualplan.projectcharge')"></span>
          </dt>
          <dd>
            <div v-if="annualplan.projectcharge">
              <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: annualplan.projectcharge.id } }">{{
                annualplan.projectcharge.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.annualplan.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="annualplan.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: annualplan.creatorid.id } }">{{
                annualplan.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.annualplan.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="annualplan.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: annualplan.auditorid.id } }">{{
                annualplan.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="annualplan.id"
          :to="{ name: 'AnnualplanEdit', params: { annualplanId: annualplan.id } }"
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

<script lang="ts" src="./annualplan-details.component.ts"></script>
