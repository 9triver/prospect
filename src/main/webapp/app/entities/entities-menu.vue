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
    <b-dropdown-item to="/role">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.role')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/permission">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.permission')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/project">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.project')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/projectwbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectwbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/integratedmanagement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.integratedmanagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/integratedmanagement-wbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.integratedmanagementWbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/planstrategy">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.planstrategy')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/comprehensivecontrol">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.comprehensivecontrol')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/document">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.document')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/comprehensiveledger">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.comprehensiveledger')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/cycleplan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.cycleplan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/annualplan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.annualplan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/monthplan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.monthplan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/planreturns">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.planreturns')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/planmonitor">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.planmonitor')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/planexecute">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.planexecute')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/projectcharge">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectcharge')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/pbsmanage">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.pbsmanage')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/pbssubmanage">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.pbssubmanage')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/wbsmanage">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.wbsmanage')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/wbssubmanage">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.wbssubmanage')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/pbsbaseline">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.pbsbaseline')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/wbsbaseline">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.wbsbaseline')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/progressmanagement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.progressmanagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/progressmanagement-wbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.progressmanagementWbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/progressplan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.progressplan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/progressplanreturns">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.progressplanreturns')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/progressbaseline">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.progressbaseline')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/qualitymanagement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.qualitymanagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/qualitymanagement-wbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.qualitymanagementWbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/qualityobjectives">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.qualityobjectives')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/qualityreturns">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.qualityreturns')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/un-quality-audit">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.unQualityAudit')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/fundsmanagement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.fundsmanagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/fundsmanagement-wbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.fundsmanagementWbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/auditedbudget">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.auditedbudget')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/totalbudget">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.totalbudget')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/unitbudget">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.unitbudget')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/fundsavailability">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.fundsavailability')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/contractualfunds">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.contractualfunds')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/outsourcingmanagement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.outsourcingmanagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/outsourcingmanagement-wbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.outsourcingmanagementWbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/outsourcing-purchase-plan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.outsourcingPurchasePlan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/outsourcing-purchase-execute">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.outsourcingPurchaseExecute')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/resourcemanagement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.resourcemanagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/resourcemanagement-wbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.resourcemanagementWbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/project-humanresourcesplan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectHumanresourcesplan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/projectremit">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectremit')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/humanresources">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.humanresources')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/technicalmanagement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.technicalmanagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/technicalmanagement-wbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.technicalmanagementWbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/technical-condition">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.technicalCondition')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/riskmanagement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.riskmanagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/riskmanagement-wbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.riskmanagementWbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/riskidentification">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.riskidentification')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/riskreport">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.riskreport')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/secrecymanagement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.secrecymanagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/secrecymanagement-wbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.secrecymanagementWbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/secrecysystem">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.secrecysystem')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/project-secrecy">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectSecrecy')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/securitymanagement">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.securitymanagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/securitymanagement-wbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.securitymanagementWbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/annual-security-plan">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.annualSecurityPlan')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/safetycheck">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.safetycheck')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/evaluation-criteria">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.evaluationCriteria')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/management-capacity-evaluation">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.managementCapacityEvaluation')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/approval-agent">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.approvalAgent')"></span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
