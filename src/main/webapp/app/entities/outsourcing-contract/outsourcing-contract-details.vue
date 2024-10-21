<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="outsourcingContract">
        <h2 class="jh-entity-heading" data-cy="outsourcingContractDetailsHeading">
          <span v-text="t$('jy1App.outsourcingContract.detail.title')"></span> {{ outsourcingContract.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.contractid')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.contractid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.contractcode')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.contractcode }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.contractname')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.contractname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.contractqualityid')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.contractqualityid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.contractcostid')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.contractcostid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.contractfinanceid')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.contractfinanceid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.projectid')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.projectid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.projectsecretlevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.projectsecretlevel }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.counterpartyunit')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.counterpartyunit }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.negotiationdate')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.negotiationdate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.negotiationlocation')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.negotiationlocation }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.negotiator')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.negotiator }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.budgetamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.budgetamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.contractamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.contractamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.approver')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.approver }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.approvaldate')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.approvaldate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.contractsecretlevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.contractsecretlevel }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.outsourcingContract.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="outsourcingContract.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: outsourcingContract.workbag.id } }">{{
                outsourcingContract.workbag.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="outsourcingContract.id"
          :to="{ name: 'OutsourcingContractEdit', params: { outsourcingContractId: outsourcingContract.id } }"
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

<script lang="ts" src="./outsourcing-contract-details.component.ts"></script>
