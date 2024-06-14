import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const Officers = () => import('@/entities/officers/officers.vue');
const OfficersUpdate = () => import('@/entities/officers/officers-update.vue');
const OfficersDetails = () => import('@/entities/officers/officers-details.vue');

const Department = () => import('@/entities/department/department.vue');
const DepartmentUpdate = () => import('@/entities/department/department-update.vue');
const DepartmentDetails = () => import('@/entities/department/department-details.vue');

const Role = () => import('@/entities/role/role.vue');
const RoleUpdate = () => import('@/entities/role/role-update.vue');
const RoleDetails = () => import('@/entities/role/role-details.vue');

const Permission = () => import('@/entities/permission/permission.vue');
const PermissionUpdate = () => import('@/entities/permission/permission-update.vue');
const PermissionDetails = () => import('@/entities/permission/permission-details.vue');

const Project = () => import('@/entities/project/project.vue');
const ProjectUpdate = () => import('@/entities/project/project-update.vue');
const ProjectDetails = () => import('@/entities/project/project-details.vue');

const Planstrategy = () => import('@/entities/planstrategy/planstrategy.vue');
const PlanstrategyUpdate = () => import('@/entities/planstrategy/planstrategy-update.vue');
const PlanstrategyDetails = () => import('@/entities/planstrategy/planstrategy-details.vue');

const Comprehensivecontrol = () => import('@/entities/comprehensivecontrol/comprehensivecontrol.vue');
const ComprehensivecontrolUpdate = () => import('@/entities/comprehensivecontrol/comprehensivecontrol-update.vue');
const ComprehensivecontrolDetails = () => import('@/entities/comprehensivecontrol/comprehensivecontrol-details.vue');

const Document = () => import('@/entities/document/document.vue');
const DocumentUpdate = () => import('@/entities/document/document-update.vue');
const DocumentDetails = () => import('@/entities/document/document-details.vue');

const Comprehensiveledger = () => import('@/entities/comprehensiveledger/comprehensiveledger.vue');
const ComprehensiveledgerUpdate = () => import('@/entities/comprehensiveledger/comprehensiveledger-update.vue');
const ComprehensiveledgerDetails = () => import('@/entities/comprehensiveledger/comprehensiveledger-details.vue');

const Cycleplan = () => import('@/entities/cycleplan/cycleplan.vue');
const CycleplanUpdate = () => import('@/entities/cycleplan/cycleplan-update.vue');
const CycleplanDetails = () => import('@/entities/cycleplan/cycleplan-details.vue');

const Annualplan = () => import('@/entities/annualplan/annualplan.vue');
const AnnualplanUpdate = () => import('@/entities/annualplan/annualplan-update.vue');
const AnnualplanDetails = () => import('@/entities/annualplan/annualplan-details.vue');

const Monthplan = () => import('@/entities/monthplan/monthplan.vue');
const MonthplanUpdate = () => import('@/entities/monthplan/monthplan-update.vue');
const MonthplanDetails = () => import('@/entities/monthplan/monthplan-details.vue');

const Planreturns = () => import('@/entities/planreturns/planreturns.vue');
const PlanreturnsUpdate = () => import('@/entities/planreturns/planreturns-update.vue');
const PlanreturnsDetails = () => import('@/entities/planreturns/planreturns-details.vue');

const Planmonitor = () => import('@/entities/planmonitor/planmonitor.vue');
const PlanmonitorUpdate = () => import('@/entities/planmonitor/planmonitor-update.vue');
const PlanmonitorDetails = () => import('@/entities/planmonitor/planmonitor-details.vue');

const Planexecute = () => import('@/entities/planexecute/planexecute.vue');
const PlanexecuteUpdate = () => import('@/entities/planexecute/planexecute-update.vue');
const PlanexecuteDetails = () => import('@/entities/planexecute/planexecute-details.vue');

const Projectcharge = () => import('@/entities/projectcharge/projectcharge.vue');
const ProjectchargeUpdate = () => import('@/entities/projectcharge/projectcharge-update.vue');
const ProjectchargeDetails = () => import('@/entities/projectcharge/projectcharge-details.vue');

const Pbsmanage = () => import('@/entities/pbsmanage/pbsmanage.vue');
const PbsmanageUpdate = () => import('@/entities/pbsmanage/pbsmanage-update.vue');
const PbsmanageDetails = () => import('@/entities/pbsmanage/pbsmanage-details.vue');

const Pbssubmanage = () => import('@/entities/pbssubmanage/pbssubmanage.vue');
const PbssubmanageUpdate = () => import('@/entities/pbssubmanage/pbssubmanage-update.vue');
const PbssubmanageDetails = () => import('@/entities/pbssubmanage/pbssubmanage-details.vue');

const Wbsmanage = () => import('@/entities/wbsmanage/wbsmanage.vue');
const WbsmanageUpdate = () => import('@/entities/wbsmanage/wbsmanage-update.vue');
const WbsmanageDetails = () => import('@/entities/wbsmanage/wbsmanage-details.vue');

const Wbssubmanage = () => import('@/entities/wbssubmanage/wbssubmanage.vue');
const WbssubmanageUpdate = () => import('@/entities/wbssubmanage/wbssubmanage-update.vue');
const WbssubmanageDetails = () => import('@/entities/wbssubmanage/wbssubmanage-details.vue');

const Pbsbaseline = () => import('@/entities/pbsbaseline/pbsbaseline.vue');
const PbsbaselineUpdate = () => import('@/entities/pbsbaseline/pbsbaseline-update.vue');
const PbsbaselineDetails = () => import('@/entities/pbsbaseline/pbsbaseline-details.vue');

const Wbsbaseline = () => import('@/entities/wbsbaseline/wbsbaseline.vue');
const WbsbaselineUpdate = () => import('@/entities/wbsbaseline/wbsbaseline-update.vue');
const WbsbaselineDetails = () => import('@/entities/wbsbaseline/wbsbaseline-details.vue');

const Progressmanagement = () => import('@/entities/progressmanagement/progressmanagement.vue');
const ProgressmanagementUpdate = () => import('@/entities/progressmanagement/progressmanagement-update.vue');
const ProgressmanagementDetails = () => import('@/entities/progressmanagement/progressmanagement-details.vue');

const Qualitymanagement = () => import('@/entities/qualitymanagement/qualitymanagement.vue');
const QualitymanagementUpdate = () => import('@/entities/qualitymanagement/qualitymanagement-update.vue');
const QualitymanagementDetails = () => import('@/entities/qualitymanagement/qualitymanagement-details.vue');

const Qualityobjectives = () => import('@/entities/qualityobjectives/qualityobjectives.vue');
const QualityobjectivesUpdate = () => import('@/entities/qualityobjectives/qualityobjectives-update.vue');
const QualityobjectivesDetails = () => import('@/entities/qualityobjectives/qualityobjectives-details.vue');

const Qualityreturns = () => import('@/entities/qualityreturns/qualityreturns.vue');
const QualityreturnsUpdate = () => import('@/entities/qualityreturns/qualityreturns-update.vue');
const QualityreturnsDetails = () => import('@/entities/qualityreturns/qualityreturns-details.vue');

const UnQualityAudit = () => import('@/entities/un-quality-audit/un-quality-audit.vue');
const UnQualityAuditUpdate = () => import('@/entities/un-quality-audit/un-quality-audit-update.vue');
const UnQualityAuditDetails = () => import('@/entities/un-quality-audit/un-quality-audit-details.vue');

const Fundsmanagement = () => import('@/entities/fundsmanagement/fundsmanagement.vue');
const FundsmanagementUpdate = () => import('@/entities/fundsmanagement/fundsmanagement-update.vue');
const FundsmanagementDetails = () => import('@/entities/fundsmanagement/fundsmanagement-details.vue');

const Totalbudget = () => import('@/entities/totalbudget/totalbudget.vue');
const TotalbudgetUpdate = () => import('@/entities/totalbudget/totalbudget-update.vue');
const TotalbudgetDetails = () => import('@/entities/totalbudget/totalbudget-details.vue');

const Unitbudget = () => import('@/entities/unitbudget/unitbudget.vue');
const UnitbudgetUpdate = () => import('@/entities/unitbudget/unitbudget-update.vue');
const UnitbudgetDetails = () => import('@/entities/unitbudget/unitbudget-details.vue');

const Fundsavailability = () => import('@/entities/fundsavailability/fundsavailability.vue');
const FundsavailabilityUpdate = () => import('@/entities/fundsavailability/fundsavailability-update.vue');
const FundsavailabilityDetails = () => import('@/entities/fundsavailability/fundsavailability-details.vue');

const Contractualfunds = () => import('@/entities/contractualfunds/contractualfunds.vue');
const ContractualfundsUpdate = () => import('@/entities/contractualfunds/contractualfunds-update.vue');
const ContractualfundsDetails = () => import('@/entities/contractualfunds/contractualfunds-details.vue');

const OutsourcingmPurchasePlan = () => import('@/entities/outsourcingm-purchase-plan/outsourcingm-purchase-plan.vue');
const OutsourcingmPurchasePlanUpdate = () => import('@/entities/outsourcingm-purchase-plan/outsourcingm-purchase-plan-update.vue');
const OutsourcingmPurchasePlanDetails = () => import('@/entities/outsourcingm-purchase-plan/outsourcingm-purchase-plan-details.vue');

const OutsourcingmPurchaseExecute = () => import('@/entities/outsourcingm-purchase-execute/outsourcingm-purchase-execute.vue');
const OutsourcingmPurchaseExecuteUpdate = () => import('@/entities/outsourcingm-purchase-execute/outsourcingm-purchase-execute-update.vue');
const OutsourcingmPurchaseExecuteDetails = () =>
  import('@/entities/outsourcingm-purchase-execute/outsourcingm-purchase-execute-details.vue');

const Resourcemanagement = () => import('@/entities/resourcemanagement/resourcemanagement.vue');
const ResourcemanagementUpdate = () => import('@/entities/resourcemanagement/resourcemanagement-update.vue');
const ResourcemanagementDetails = () => import('@/entities/resourcemanagement/resourcemanagement-details.vue');

const Humanresources = () => import('@/entities/humanresources/humanresources.vue');
const HumanresourcesUpdate = () => import('@/entities/humanresources/humanresources-update.vue');
const HumanresourcesDetails = () => import('@/entities/humanresources/humanresources-details.vue');

const TechnicalCondition = () => import('@/entities/technical-condition/technical-condition.vue');
const TechnicalConditionUpdate = () => import('@/entities/technical-condition/technical-condition-update.vue');
const TechnicalConditionDetails = () => import('@/entities/technical-condition/technical-condition-details.vue');

const Riskmanagement = () => import('@/entities/riskmanagement/riskmanagement.vue');
const RiskmanagementUpdate = () => import('@/entities/riskmanagement/riskmanagement-update.vue');
const RiskmanagementDetails = () => import('@/entities/riskmanagement/riskmanagement-details.vue');

const Riskreport = () => import('@/entities/riskreport/riskreport.vue');
const RiskreportUpdate = () => import('@/entities/riskreport/riskreport-update.vue');
const RiskreportDetails = () => import('@/entities/riskreport/riskreport-details.vue');

const Secrecymanagement = () => import('@/entities/secrecymanagement/secrecymanagement.vue');
const SecrecymanagementUpdate = () => import('@/entities/secrecymanagement/secrecymanagement-update.vue');
const SecrecymanagementDetails = () => import('@/entities/secrecymanagement/secrecymanagement-details.vue');

const ProjectSecrecy = () => import('@/entities/project-secrecy/project-secrecy.vue');
const ProjectSecrecyUpdate = () => import('@/entities/project-secrecy/project-secrecy-update.vue');
const ProjectSecrecyDetails = () => import('@/entities/project-secrecy/project-secrecy-details.vue');

const AnnualSecurityPlan = () => import('@/entities/annual-security-plan/annual-security-plan.vue');
const AnnualSecurityPlanUpdate = () => import('@/entities/annual-security-plan/annual-security-plan-update.vue');
const AnnualSecurityPlanDetails = () => import('@/entities/annual-security-plan/annual-security-plan-details.vue');

const Safetycheck = () => import('@/entities/safetycheck/safetycheck.vue');
const SafetycheckUpdate = () => import('@/entities/safetycheck/safetycheck-update.vue');
const SafetycheckDetails = () => import('@/entities/safetycheck/safetycheck-details.vue');

const EvaluationCriteria = () => import('@/entities/evaluation-criteria/evaluation-criteria.vue');
const EvaluationCriteriaUpdate = () => import('@/entities/evaluation-criteria/evaluation-criteria-update.vue');
const EvaluationCriteriaDetails = () => import('@/entities/evaluation-criteria/evaluation-criteria-details.vue');

const ManagementCapacityEvaluation = () => import('@/entities/management-capacity-evaluation/management-capacity-evaluation.vue');
const ManagementCapacityEvaluationUpdate = () =>
  import('@/entities/management-capacity-evaluation/management-capacity-evaluation-update.vue');
const ManagementCapacityEvaluationDetails = () =>
  import('@/entities/management-capacity-evaluation/management-capacity-evaluation-details.vue');

const ApprovalAgent = () => import('@/entities/approval-agent/approval-agent.vue');
const ApprovalAgentUpdate = () => import('@/entities/approval-agent/approval-agent-update.vue');
const ApprovalAgentDetails = () => import('@/entities/approval-agent/approval-agent-details.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'officers',
      name: 'Officers',
      component: Officers,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'officers/new',
      name: 'OfficersCreate',
      component: OfficersUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'officers/:officersId/edit',
      name: 'OfficersEdit',
      component: OfficersUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'officers/:officersId/view',
      name: 'OfficersView',
      component: OfficersDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department',
      name: 'Department',
      component: Department,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department/new',
      name: 'DepartmentCreate',
      component: DepartmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department/:departmentId/edit',
      name: 'DepartmentEdit',
      component: DepartmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department/:departmentId/view',
      name: 'DepartmentView',
      component: DepartmentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role',
      name: 'Role',
      component: Role,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role/new',
      name: 'RoleCreate',
      component: RoleUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role/:roleId/edit',
      name: 'RoleEdit',
      component: RoleUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role/:roleId/view',
      name: 'RoleView',
      component: RoleDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'permission',
      name: 'Permission',
      component: Permission,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'permission/new',
      name: 'PermissionCreate',
      component: PermissionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'permission/:permissionId/edit',
      name: 'PermissionEdit',
      component: PermissionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'permission/:permissionId/view',
      name: 'PermissionView',
      component: PermissionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project',
      name: 'Project',
      component: Project,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project/new',
      name: 'ProjectCreate',
      component: ProjectUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project/:projectId/edit',
      name: 'ProjectEdit',
      component: ProjectUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project/:projectId/view',
      name: 'ProjectView',
      component: ProjectDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planstrategy',
      name: 'Planstrategy',
      component: Planstrategy,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planstrategy/new',
      name: 'PlanstrategyCreate',
      component: PlanstrategyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planstrategy/:planstrategyId/edit',
      name: 'PlanstrategyEdit',
      component: PlanstrategyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planstrategy/:planstrategyId/view',
      name: 'PlanstrategyView',
      component: PlanstrategyDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'comprehensivecontrol',
      name: 'Comprehensivecontrol',
      component: Comprehensivecontrol,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'comprehensivecontrol/new',
      name: 'ComprehensivecontrolCreate',
      component: ComprehensivecontrolUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'comprehensivecontrol/:comprehensivecontrolId/edit',
      name: 'ComprehensivecontrolEdit',
      component: ComprehensivecontrolUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'comprehensivecontrol/:comprehensivecontrolId/view',
      name: 'ComprehensivecontrolView',
      component: ComprehensivecontrolDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'document',
      name: 'Document',
      component: Document,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'document/new',
      name: 'DocumentCreate',
      component: DocumentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'document/:documentId/edit',
      name: 'DocumentEdit',
      component: DocumentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'document/:documentId/view',
      name: 'DocumentView',
      component: DocumentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'comprehensiveledger',
      name: 'Comprehensiveledger',
      component: Comprehensiveledger,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'comprehensiveledger/new',
      name: 'ComprehensiveledgerCreate',
      component: ComprehensiveledgerUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'comprehensiveledger/:comprehensiveledgerId/edit',
      name: 'ComprehensiveledgerEdit',
      component: ComprehensiveledgerUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'comprehensiveledger/:comprehensiveledgerId/view',
      name: 'ComprehensiveledgerView',
      component: ComprehensiveledgerDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cycleplan',
      name: 'Cycleplan',
      component: Cycleplan,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cycleplan/new',
      name: 'CycleplanCreate',
      component: CycleplanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cycleplan/:cycleplanId/edit',
      name: 'CycleplanEdit',
      component: CycleplanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cycleplan/:cycleplanId/view',
      name: 'CycleplanView',
      component: CycleplanDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'annualplan',
      name: 'Annualplan',
      component: Annualplan,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'annualplan/new',
      name: 'AnnualplanCreate',
      component: AnnualplanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'annualplan/:annualplanId/edit',
      name: 'AnnualplanEdit',
      component: AnnualplanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'annualplan/:annualplanId/view',
      name: 'AnnualplanView',
      component: AnnualplanDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthplan',
      name: 'Monthplan',
      component: Monthplan,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthplan/new',
      name: 'MonthplanCreate',
      component: MonthplanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthplan/:monthplanId/edit',
      name: 'MonthplanEdit',
      component: MonthplanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthplan/:monthplanId/view',
      name: 'MonthplanView',
      component: MonthplanDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planreturns',
      name: 'Planreturns',
      component: Planreturns,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planreturns/new',
      name: 'PlanreturnsCreate',
      component: PlanreturnsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planreturns/:planreturnsId/edit',
      name: 'PlanreturnsEdit',
      component: PlanreturnsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planreturns/:planreturnsId/view',
      name: 'PlanreturnsView',
      component: PlanreturnsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planmonitor',
      name: 'Planmonitor',
      component: Planmonitor,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planmonitor/new',
      name: 'PlanmonitorCreate',
      component: PlanmonitorUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planmonitor/:planmonitorId/edit',
      name: 'PlanmonitorEdit',
      component: PlanmonitorUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planmonitor/:planmonitorId/view',
      name: 'PlanmonitorView',
      component: PlanmonitorDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planexecute',
      name: 'Planexecute',
      component: Planexecute,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planexecute/new',
      name: 'PlanexecuteCreate',
      component: PlanexecuteUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planexecute/:planexecuteId/edit',
      name: 'PlanexecuteEdit',
      component: PlanexecuteUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'planexecute/:planexecuteId/view',
      name: 'PlanexecuteView',
      component: PlanexecuteDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectcharge',
      name: 'Projectcharge',
      component: Projectcharge,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectcharge/new',
      name: 'ProjectchargeCreate',
      component: ProjectchargeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectcharge/:projectchargeId/edit',
      name: 'ProjectchargeEdit',
      component: ProjectchargeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectcharge/:projectchargeId/view',
      name: 'ProjectchargeView',
      component: ProjectchargeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbsmanage',
      name: 'Pbsmanage',
      component: Pbsmanage,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbsmanage/new',
      name: 'PbsmanageCreate',
      component: PbsmanageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbsmanage/:pbsmanageId/edit',
      name: 'PbsmanageEdit',
      component: PbsmanageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbsmanage/:pbsmanageId/view',
      name: 'PbsmanageView',
      component: PbsmanageDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbssubmanage',
      name: 'Pbssubmanage',
      component: Pbssubmanage,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbssubmanage/new',
      name: 'PbssubmanageCreate',
      component: PbssubmanageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbssubmanage/:pbssubmanageId/edit',
      name: 'PbssubmanageEdit',
      component: PbssubmanageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbssubmanage/:pbssubmanageId/view',
      name: 'PbssubmanageView',
      component: PbssubmanageDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbsmanage',
      name: 'Wbsmanage',
      component: Wbsmanage,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbsmanage/new',
      name: 'WbsmanageCreate',
      component: WbsmanageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbsmanage/:wbsmanageId/edit',
      name: 'WbsmanageEdit',
      component: WbsmanageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbsmanage/:wbsmanageId/view',
      name: 'WbsmanageView',
      component: WbsmanageDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbssubmanage',
      name: 'Wbssubmanage',
      component: Wbssubmanage,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbssubmanage/new',
      name: 'WbssubmanageCreate',
      component: WbssubmanageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbssubmanage/:wbssubmanageId/edit',
      name: 'WbssubmanageEdit',
      component: WbssubmanageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbssubmanage/:wbssubmanageId/view',
      name: 'WbssubmanageView',
      component: WbssubmanageDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbsbaseline',
      name: 'Pbsbaseline',
      component: Pbsbaseline,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbsbaseline/new',
      name: 'PbsbaselineCreate',
      component: PbsbaselineUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbsbaseline/:pbsbaselineId/edit',
      name: 'PbsbaselineEdit',
      component: PbsbaselineUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pbsbaseline/:pbsbaselineId/view',
      name: 'PbsbaselineView',
      component: PbsbaselineDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbsbaseline',
      name: 'Wbsbaseline',
      component: Wbsbaseline,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbsbaseline/new',
      name: 'WbsbaselineCreate',
      component: WbsbaselineUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbsbaseline/:wbsbaselineId/edit',
      name: 'WbsbaselineEdit',
      component: WbsbaselineUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wbsbaseline/:wbsbaselineId/view',
      name: 'WbsbaselineView',
      component: WbsbaselineDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'progressmanagement',
      name: 'Progressmanagement',
      component: Progressmanagement,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'progressmanagement/new',
      name: 'ProgressmanagementCreate',
      component: ProgressmanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'progressmanagement/:progressmanagementId/edit',
      name: 'ProgressmanagementEdit',
      component: ProgressmanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'progressmanagement/:progressmanagementId/view',
      name: 'ProgressmanagementView',
      component: ProgressmanagementDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualitymanagement',
      name: 'Qualitymanagement',
      component: Qualitymanagement,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualitymanagement/new',
      name: 'QualitymanagementCreate',
      component: QualitymanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualitymanagement/:qualitymanagementId/edit',
      name: 'QualitymanagementEdit',
      component: QualitymanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualitymanagement/:qualitymanagementId/view',
      name: 'QualitymanagementView',
      component: QualitymanagementDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualityobjectives',
      name: 'Qualityobjectives',
      component: Qualityobjectives,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualityobjectives/new',
      name: 'QualityobjectivesCreate',
      component: QualityobjectivesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualityobjectives/:qualityobjectivesId/edit',
      name: 'QualityobjectivesEdit',
      component: QualityobjectivesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualityobjectives/:qualityobjectivesId/view',
      name: 'QualityobjectivesView',
      component: QualityobjectivesDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualityreturns',
      name: 'Qualityreturns',
      component: Qualityreturns,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualityreturns/new',
      name: 'QualityreturnsCreate',
      component: QualityreturnsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualityreturns/:qualityreturnsId/edit',
      name: 'QualityreturnsEdit',
      component: QualityreturnsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualityreturns/:qualityreturnsId/view',
      name: 'QualityreturnsView',
      component: QualityreturnsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'un-quality-audit',
      name: 'UnQualityAudit',
      component: UnQualityAudit,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'un-quality-audit/new',
      name: 'UnQualityAuditCreate',
      component: UnQualityAuditUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'un-quality-audit/:unQualityAuditId/edit',
      name: 'UnQualityAuditEdit',
      component: UnQualityAuditUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'un-quality-audit/:unQualityAuditId/view',
      name: 'UnQualityAuditView',
      component: UnQualityAuditDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fundsmanagement',
      name: 'Fundsmanagement',
      component: Fundsmanagement,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fundsmanagement/new',
      name: 'FundsmanagementCreate',
      component: FundsmanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fundsmanagement/:fundsmanagementId/edit',
      name: 'FundsmanagementEdit',
      component: FundsmanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fundsmanagement/:fundsmanagementId/view',
      name: 'FundsmanagementView',
      component: FundsmanagementDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'totalbudget',
      name: 'Totalbudget',
      component: Totalbudget,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'totalbudget/new',
      name: 'TotalbudgetCreate',
      component: TotalbudgetUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'totalbudget/:totalbudgetId/edit',
      name: 'TotalbudgetEdit',
      component: TotalbudgetUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'totalbudget/:totalbudgetId/view',
      name: 'TotalbudgetView',
      component: TotalbudgetDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'unitbudget',
      name: 'Unitbudget',
      component: Unitbudget,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'unitbudget/new',
      name: 'UnitbudgetCreate',
      component: UnitbudgetUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'unitbudget/:unitbudgetId/edit',
      name: 'UnitbudgetEdit',
      component: UnitbudgetUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'unitbudget/:unitbudgetId/view',
      name: 'UnitbudgetView',
      component: UnitbudgetDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fundsavailability',
      name: 'Fundsavailability',
      component: Fundsavailability,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fundsavailability/new',
      name: 'FundsavailabilityCreate',
      component: FundsavailabilityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fundsavailability/:fundsavailabilityId/edit',
      name: 'FundsavailabilityEdit',
      component: FundsavailabilityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fundsavailability/:fundsavailabilityId/view',
      name: 'FundsavailabilityView',
      component: FundsavailabilityDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contractualfunds',
      name: 'Contractualfunds',
      component: Contractualfunds,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contractualfunds/new',
      name: 'ContractualfundsCreate',
      component: ContractualfundsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contractualfunds/:contractualfundsId/edit',
      name: 'ContractualfundsEdit',
      component: ContractualfundsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contractualfunds/:contractualfundsId/view',
      name: 'ContractualfundsView',
      component: ContractualfundsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcingm-purchase-plan',
      name: 'OutsourcingmPurchasePlan',
      component: OutsourcingmPurchasePlan,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcingm-purchase-plan/new',
      name: 'OutsourcingmPurchasePlanCreate',
      component: OutsourcingmPurchasePlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcingm-purchase-plan/:outsourcingmPurchasePlanId/edit',
      name: 'OutsourcingmPurchasePlanEdit',
      component: OutsourcingmPurchasePlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcingm-purchase-plan/:outsourcingmPurchasePlanId/view',
      name: 'OutsourcingmPurchasePlanView',
      component: OutsourcingmPurchasePlanDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcingm-purchase-execute',
      name: 'OutsourcingmPurchaseExecute',
      component: OutsourcingmPurchaseExecute,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcingm-purchase-execute/new',
      name: 'OutsourcingmPurchaseExecuteCreate',
      component: OutsourcingmPurchaseExecuteUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcingm-purchase-execute/:outsourcingmPurchaseExecuteId/edit',
      name: 'OutsourcingmPurchaseExecuteEdit',
      component: OutsourcingmPurchaseExecuteUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcingm-purchase-execute/:outsourcingmPurchaseExecuteId/view',
      name: 'OutsourcingmPurchaseExecuteView',
      component: OutsourcingmPurchaseExecuteDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'resourcemanagement',
      name: 'Resourcemanagement',
      component: Resourcemanagement,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'resourcemanagement/new',
      name: 'ResourcemanagementCreate',
      component: ResourcemanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'resourcemanagement/:resourcemanagementId/edit',
      name: 'ResourcemanagementEdit',
      component: ResourcemanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'resourcemanagement/:resourcemanagementId/view',
      name: 'ResourcemanagementView',
      component: ResourcemanagementDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'humanresources',
      name: 'Humanresources',
      component: Humanresources,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'humanresources/new',
      name: 'HumanresourcesCreate',
      component: HumanresourcesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'humanresources/:humanresourcesId/edit',
      name: 'HumanresourcesEdit',
      component: HumanresourcesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'humanresources/:humanresourcesId/view',
      name: 'HumanresourcesView',
      component: HumanresourcesDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'technical-condition',
      name: 'TechnicalCondition',
      component: TechnicalCondition,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'technical-condition/new',
      name: 'TechnicalConditionCreate',
      component: TechnicalConditionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'technical-condition/:technicalConditionId/edit',
      name: 'TechnicalConditionEdit',
      component: TechnicalConditionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'technical-condition/:technicalConditionId/view',
      name: 'TechnicalConditionView',
      component: TechnicalConditionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'riskmanagement',
      name: 'Riskmanagement',
      component: Riskmanagement,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'riskmanagement/new',
      name: 'RiskmanagementCreate',
      component: RiskmanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'riskmanagement/:riskmanagementId/edit',
      name: 'RiskmanagementEdit',
      component: RiskmanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'riskmanagement/:riskmanagementId/view',
      name: 'RiskmanagementView',
      component: RiskmanagementDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'riskreport',
      name: 'Riskreport',
      component: Riskreport,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'riskreport/new',
      name: 'RiskreportCreate',
      component: RiskreportUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'riskreport/:riskreportId/edit',
      name: 'RiskreportEdit',
      component: RiskreportUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'riskreport/:riskreportId/view',
      name: 'RiskreportView',
      component: RiskreportDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'secrecymanagement',
      name: 'Secrecymanagement',
      component: Secrecymanagement,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'secrecymanagement/new',
      name: 'SecrecymanagementCreate',
      component: SecrecymanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'secrecymanagement/:secrecymanagementId/edit',
      name: 'SecrecymanagementEdit',
      component: SecrecymanagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'secrecymanagement/:secrecymanagementId/view',
      name: 'SecrecymanagementView',
      component: SecrecymanagementDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-secrecy',
      name: 'ProjectSecrecy',
      component: ProjectSecrecy,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-secrecy/new',
      name: 'ProjectSecrecyCreate',
      component: ProjectSecrecyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-secrecy/:projectSecrecyId/edit',
      name: 'ProjectSecrecyEdit',
      component: ProjectSecrecyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-secrecy/:projectSecrecyId/view',
      name: 'ProjectSecrecyView',
      component: ProjectSecrecyDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'annual-security-plan',
      name: 'AnnualSecurityPlan',
      component: AnnualSecurityPlan,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'annual-security-plan/new',
      name: 'AnnualSecurityPlanCreate',
      component: AnnualSecurityPlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'annual-security-plan/:annualSecurityPlanId/edit',
      name: 'AnnualSecurityPlanEdit',
      component: AnnualSecurityPlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'annual-security-plan/:annualSecurityPlanId/view',
      name: 'AnnualSecurityPlanView',
      component: AnnualSecurityPlanDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'safetycheck',
      name: 'Safetycheck',
      component: Safetycheck,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'safetycheck/new',
      name: 'SafetycheckCreate',
      component: SafetycheckUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'safetycheck/:safetycheckId/edit',
      name: 'SafetycheckEdit',
      component: SafetycheckUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'safetycheck/:safetycheckId/view',
      name: 'SafetycheckView',
      component: SafetycheckDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'evaluation-criteria',
      name: 'EvaluationCriteria',
      component: EvaluationCriteria,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'evaluation-criteria/new',
      name: 'EvaluationCriteriaCreate',
      component: EvaluationCriteriaUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'evaluation-criteria/:evaluationCriteriaId/edit',
      name: 'EvaluationCriteriaEdit',
      component: EvaluationCriteriaUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'evaluation-criteria/:evaluationCriteriaId/view',
      name: 'EvaluationCriteriaView',
      component: EvaluationCriteriaDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'management-capacity-evaluation',
      name: 'ManagementCapacityEvaluation',
      component: ManagementCapacityEvaluation,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'management-capacity-evaluation/new',
      name: 'ManagementCapacityEvaluationCreate',
      component: ManagementCapacityEvaluationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'management-capacity-evaluation/:managementCapacityEvaluationId/edit',
      name: 'ManagementCapacityEvaluationEdit',
      component: ManagementCapacityEvaluationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'management-capacity-evaluation/:managementCapacityEvaluationId/view',
      name: 'ManagementCapacityEvaluationView',
      component: ManagementCapacityEvaluationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'approval-agent',
      name: 'ApprovalAgent',
      component: ApprovalAgent,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'approval-agent/new',
      name: 'ApprovalAgentCreate',
      component: ApprovalAgentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'approval-agent/:approvalAgentId/edit',
      name: 'ApprovalAgentEdit',
      component: ApprovalAgentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'approval-agent/:approvalAgentId/view',
      name: 'ApprovalAgentView',
      component: ApprovalAgentDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
