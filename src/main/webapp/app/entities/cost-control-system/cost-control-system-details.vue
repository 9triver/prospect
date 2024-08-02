<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="costControlSystem">
        <h2 class="jh-entity-heading" data-cy="costControlSystemDetailsHeading">
          <span v-text="t$('jy1App.costControlSystem.detail.title')"></span> {{ costControlSystem.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.costControlSystem.type')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.subject')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.ContractSubject.' + costControlSystem.subject)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.implementedamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.implementedamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.approvedamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.approvedamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.pendingimplementationamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.pendingimplementationamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.contractpaymentamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.contractpaymentamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.managementregistrationnumber')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.managementregistrationnumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.financialregistrationnumber')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.financialregistrationnumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.contractbudgetamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.contractbudgetamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.contractsigningamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.contractsigningamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.contractsettlementamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.contractsettlementamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.unforeseeableamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.unforeseeableamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.invoicepaymentamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.invoicepaymentamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.loanpaymentamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.loanpaymentamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.accountoutstandingamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.accountoutstandingamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.pendingpaymentamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.pendingpaymentamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.pendinginvoiceamount')"></span>
          </dt>
          <dd>
            <span>{{ costControlSystem.pendinginvoiceamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.responsibleperson')"></span>
          </dt>
          <dd>
            <div v-if="costControlSystem.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: costControlSystem.responsibleperson.id } }">{{
                costControlSystem.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="costControlSystem.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: costControlSystem.auditorid.id } }">{{
                costControlSystem.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.projectwbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectwbs, i) in costControlSystem.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.costControlSystem.contract')"></span>
          </dt>
          <dd>
            <span v-for="(contract, i) in costControlSystem.contracts" :key="contract.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ContractView', params: { contractId: contract.id } }">{{ contract.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="costControlSystem.id"
          :to="{ name: 'CostControlSystemEdit', params: { costControlSystemId: costControlSystem.id } }"
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

<script lang="ts" src="./cost-control-system-details.component.ts"></script>
