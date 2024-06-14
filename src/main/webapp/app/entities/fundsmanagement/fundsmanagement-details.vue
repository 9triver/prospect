<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="fundsmanagement">
        <h2 class="jh-entity-heading" data-cy="fundsmanagementDetailsHeading">
          <span v-text="t$('jHipster3App.fundsmanagement.detail.title')"></span> {{ fundsmanagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.fundsid')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.fundsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.createtime')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.Secretlevel.' + fundsmanagement.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.year')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.budgit')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.budgit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.dapartmentid')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.dapartmentid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.draftapproval')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.draftapproval }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.totalbudgetid')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.totalbudgetid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.unitbudgetid')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.unitbudgetid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.documentid')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.documentid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.maintainerid')"></span>
          </dt>
          <dd>
            <span>{{ fundsmanagement.maintainerid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + fundsmanagement.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.totalbudget')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagement.totalbudget">
              <router-link :to="{ name: 'TotalbudgetView', params: { totalbudgetId: fundsmanagement.totalbudget.id } }">{{
                fundsmanagement.totalbudget.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.unitbudget')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagement.unitbudget">
              <router-link :to="{ name: 'UnitbudgetView', params: { unitbudgetId: fundsmanagement.unitbudget.id } }">{{
                fundsmanagement.unitbudget.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.document')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagement.document">
              <router-link :to="{ name: 'DocumentView', params: { documentId: fundsmanagement.document.id } }">{{
                fundsmanagement.document.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagement.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: fundsmanagement.creatorid.id } }">{{
                fundsmanagement.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.fundsmanagement.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="fundsmanagement.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: fundsmanagement.auditorid.id } }">{{
                fundsmanagement.auditorid.id
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
