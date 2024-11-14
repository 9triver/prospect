<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="outsourcingContract">
        <h2 class="jh-entity-heading" data-cy="outsourcingContractDetailsHeading">
          <span v-text="t$('jy1App.outsourcingContract.detail.title')"></span> {{ outsourcingContract.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
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
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.deliverycontent')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.deliverycontent }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.warrantyrequirement')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.warrantyrequirement }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.purchaseplanno')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.purchaseplanno }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.purchaseplandate')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.purchaseplandate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.purchaseplanamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.purchaseplanamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.purchasemethod')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.purchasemethod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.purchasesecretlevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.purchasesecretlevel }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.reviewmethod')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.reviewmethod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.requirementdepartment')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.requirementdepartment }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.requirementperson')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.requirementperson }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.undertaker')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.undertaker }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.undertakingdepartment')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.undertakingdepartment }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.projectmanager')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.projectmanager }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.fundsource')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.fundsource }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.thesisname')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.thesisname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.contractauxiliaryno')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.contractauxiliaryno }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.reasonfornosuppliers')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.reasonfornosuppliers }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.reasonforchange')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.reasonforchange }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.negotiationfiletime')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.negotiationfiletime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.bidopeningtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.bidopeningtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.judges')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.judges }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.responsevendorname')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.responsevendorname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.finalquoteandscore')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.finalquoteandscore }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.noticeofcompletiontime')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.noticeofcompletiontime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.signingdate')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.signingdate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.contractenddate')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.contractenddate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.actualcompletiontime')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.actualcompletiontime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.issubmitsecrecyagreement')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.issubmitsecrecyagreement }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.issubmitsecurityagreement')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.issubmitsecurityagreement }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.outsourcingContract.remark')"></span>
          </dt>
          <dd class="field">
            <span>{{ outsourcingContract.remark }}</span>
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
          <dt class="relationship">
            <span v-text="t$('jy1App.outsourcingContract.milestoneNode')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(milestoneNode, i) in outsourcingContract.milestoneNodes" :key="milestoneNode.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'MilestoneNodeView', params: { milestoneNodeId: milestoneNode.id } }">{{
                milestoneNode.id
              }}</router-link>
            </span>
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
