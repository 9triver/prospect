<template>
  <div>
    <b-dropdown-item to="/officers">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.officers')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/department">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.department')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/projectpbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectpbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/projectwbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectwbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/role">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.role')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/permission">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.permission')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/document">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.document')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/progress-plan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.progressPlan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/plan-returns">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.planReturns')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/contract">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.contract')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/quality-objectives">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.qualityObjectives')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/quality-returns">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.qualityReturns')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/un-quality-audit">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.unQualityAudit')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/technical-condition">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.technicalCondition')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/project-risk">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectRisk')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/risk-report">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.riskReport')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/documentmenu">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.documentmenu')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/leave-application-info">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.leaveApplicationInfo')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/hr-management">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.hrManagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/frontline">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.frontline')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/events">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.events')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/archives">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.archives')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/projectdeliverables">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectdeliverables')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/deliverables">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.deliverables')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/workbag">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.workbag')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/work">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.work')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/subject">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.subject')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/subject-cost-budget">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.subjectCostBudget')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/project-budget">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectBudget')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/outsourcing-contract">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.outsourcingContract')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/delivery-content">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.deliveryContent')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/milestone-node">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.milestoneNode')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/payment-application">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.paymentApplication')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/transaction-payment">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.transactionPayment')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/sporadic-purchase-payment">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.sporadicPurchasePayment')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/share-payment">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.sharePayment')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/contract-payment">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.contractPayment')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/payment-cost-list">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.paymentCostList')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/fund-source-list">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.fundSourceList')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/quality-objectives-dictionary">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.qualityObjectivesDictionary')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/quality-plan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.qualityPlan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/regular-inspection">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.regularInspection')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/key-node-inspection">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.keyNodeInspection')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/qualitytozero">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.qualitytozero')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/deviation-permit-application">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.deviationPermitApplication')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/communication-plan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.communicationPlan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/communication-record">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.communicationRecord')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/communication-dictionary">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.communicationDictionary')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/communication-form-dictionary">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.communicationFormDictionary')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/letter">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.letter')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/customer-satisfaction">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.customerSatisfaction')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/risk-possibility">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.riskPossibility')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/risk-type">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.riskType')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/risk-level">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.riskLevel')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/system-level">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.systemLevel')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/risk-return">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.riskReturn')"></span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
