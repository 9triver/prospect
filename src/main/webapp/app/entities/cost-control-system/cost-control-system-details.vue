<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="costControlSystem">
        <h2 class="jh-entity-heading" data-cy="costControlSystemDetailsHeading">
          <span v-text="t$('jy1App.costControlSystem.detail.title')"></span> 
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.type')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.type }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.subject')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.ContractSubject.' + costControlSystem.subject)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.implementedamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.implementedamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.approvedamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.approvedamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.pendingimplementationamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.pendingimplementationamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.contractpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.contractpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.managementregistrationnumber')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.managementregistrationnumber }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.financialregistrationnumber')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.financialregistrationnumber }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.contractbudgetamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.contractbudgetamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.contractsigningamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.contractsigningamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.contractsettlementamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.contractsettlementamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.unforeseeableamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.unforeseeableamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.invoicepaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.invoicepaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.loanpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.loanpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.accountoutstandingamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.accountoutstandingamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.pendingpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.pendingpaymentamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.costControlSystem.pendinginvoiceamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ costControlSystem.pendinginvoiceamount }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.costControlSystem.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="costControlSystem.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: costControlSystem.responsibleperson.id } }">{{
                costControlSystem.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.costControlSystem.auditorid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="costControlSystem.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: costControlSystem.auditorid.id } }">{{
                costControlSystem.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.costControlSystem.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(projectwbs, i) in costControlSystem.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.costControlSystem.contract')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(contract, i) in costControlSystem.contracts" :key="contract.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ContractView', params: { contractId: contract.id } }">{{ contract.id }}</router-link>
            </span>
          </dd>
        </dl>
        <el-button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </el-button>
        <router-link
          v-if="costControlSystem.id"
          :to="{ name: 'CostControlSystemEdit', params: { costControlSystemId: costControlSystem.id } }"
          custom
          v-slot="{ navigate }"
        >
          <el-button @click="navigate" class="btn btn-primary" type="primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </el-button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./cost-control-system-details.component.ts"></script>
