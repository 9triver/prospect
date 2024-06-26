<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="auditedbudget">
        <h2 class="jh-entity-heading" data-cy="auditedbudgetDetailsHeading">
          <span v-text="t$('jHipster0App.auditedbudget.detail.title')"></span> {{ auditedbudget.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.createtime')"></span>
          </dt>
          <dd>
            <span>{{ auditedbudget.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ auditedbudget.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + auditedbudget.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.year')"></span>
          </dt>
          <dd>
            <span>{{ auditedbudget.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.budgit')"></span>
          </dt>
          <dd>
            <span>{{ auditedbudget.budgit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.dapartmentid')"></span>
          </dt>
          <dd>
            <span>{{ auditedbudget.dapartmentid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.draftapproval')"></span>
          </dt>
          <dd>
            <span>{{ auditedbudget.draftapproval }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.totalbudgetid')"></span>
          </dt>
          <dd>
            <span>{{ auditedbudget.totalbudgetid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.unitbudgetid')"></span>
          </dt>
          <dd>
            <span>{{ auditedbudget.unitbudgetid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.documentid')"></span>
          </dt>
          <dd>
            <span>{{ auditedbudget.documentid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.maintainerid')"></span>
          </dt>
          <dd>
            <span>{{ auditedbudget.maintainerid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + auditedbudget.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.totalbudget')"></span>
          </dt>
          <dd>
            <div v-if="auditedbudget.totalbudget">
              <router-link :to="{ name: 'TotalbudgetView', params: { totalbudgetId: auditedbudget.totalbudget.id } }">{{
                auditedbudget.totalbudget.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.unitbudget')"></span>
          </dt>
          <dd>
            <div v-if="auditedbudget.unitbudget">
              <router-link :to="{ name: 'UnitbudgetView', params: { unitbudgetId: auditedbudget.unitbudget.id } }">{{
                auditedbudget.unitbudget.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.document')"></span>
          </dt>
          <dd>
            <div v-if="auditedbudget.document">
              <router-link :to="{ name: 'DocumentView', params: { documentId: auditedbudget.document.id } }">{{
                auditedbudget.document.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="auditedbudget.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: auditedbudget.creatorid.id } }">{{
                auditedbudget.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.auditedbudget.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="auditedbudget.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: auditedbudget.auditorid.id } }">{{
                auditedbudget.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="auditedbudget.id"
          :to="{ name: 'AuditedbudgetEdit', params: { auditedbudgetId: auditedbudget.id } }"
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

<script lang="ts" src="./auditedbudget-details.component.ts"></script>
