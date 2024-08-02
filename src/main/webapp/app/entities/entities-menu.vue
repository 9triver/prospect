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
    <b-dropdown-item to="/funds-estimation">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.fundsEstimation')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/contract">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.contract')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/contract-cost-budget">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.contractCostBudget')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/cost-control-system">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.costControlSystem')"></span>
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
    <b-dropdown-item to="/outsourcing-contractual">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.outsourcingContractual')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/outsourcing-purchase-plan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.outsourcingPurchasePlan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/outsourcing-purchase-execute">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.outsourcingPurchaseExecute')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/projectremit">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectremit')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/technical">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.technical')"></span>
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
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
