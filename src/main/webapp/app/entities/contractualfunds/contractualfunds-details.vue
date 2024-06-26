<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="contractualfunds">
        <h2 class="jh-entity-heading" data-cy="contractualfundsDetailsHeading">
          <span v-text="t$('jHipster0App.contractualfunds.detail.title')"></span> {{ contractualfunds.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.department')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.department }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.year')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.starttime')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.endtime')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.status')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + contractualfunds.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.foreigncurrency')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.foreigncurrency }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.totalbudget')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.totalbudget }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.fundsinplace')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.fundsinplace }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.responsibleunitname')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.responsibleunitname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.audittime')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.audittime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.accountbank')"></span>
          </dt>
          <dd>
            <span>{{ contractualfunds.accountbank }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="contractualfunds.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: contractualfunds.creatorid.id } }">{{
                contractualfunds.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.contractualfunds.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="contractualfunds.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: contractualfunds.auditorid.id } }">{{
                contractualfunds.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="contractualfunds.id"
          :to="{ name: 'ContractualfundsEdit', params: { contractualfundsId: contractualfunds.id } }"
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

<script lang="ts" src="./contractualfunds-details.component.ts"></script>
