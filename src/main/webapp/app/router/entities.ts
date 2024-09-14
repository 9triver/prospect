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

const Projectpbs = () => import('@/entities/projectpbs/projectpbs.vue');
const Projectpbs1 = () => import('@/entities/projectpbs/projectpbs1.vue');
const ProjectpbsUpdate = () => import('@/entities/projectpbs/projectpbs-update.vue');
const ProjectpbsDetails = () => import('@/entities/projectpbs/projectpbs-details.vue');

const Projectwbs = () => import('@/entities/projectwbs/projectwbs.vue');
const Projectwbs1 = () => import('@/entities/projectwbs/projectwbs1.vue');
const ProjectwbsUpdate = () => import('@/entities/projectwbs/projectwbs-update.vue');
const ProjectwbsDetails = () => import('@/entities/projectwbs/projectwbs-details.vue');
const projectwbsSelect = ()=> import('@/entities/projectwbs/projectwbsSelect.vue')
const ProjectwbsOne = ()=> import('@/entities/projectwbs/projectwbs-one.vue')
const ProjectTotalwbs = () => import('@/entities/projecttotalwbs/projecttotalwbs.vue');
const ProjectTotalwbs1 = () => import('@/entities/projecttotalwbs/projecttotalwbs1.vue');
const ProjectTotalwbsUpdate = () => import('@/entities/projecttotalwbs/projecttotalwbs-update.vue');
const ProjectTotalwbsDetails = () => import('@/entities/projecttotalwbs/projecttotalwbs-details.vue');
const projectTotalwbsSelect = ()=> import('@/entities/projecttotalwbs/projecttotalwbsSelect.vue')

const Role = () => import('@/entities/role/role.vue');
const RoleUpdate = () => import('@/entities/role/role-update.vue');
const RoleDetails = () => import('@/entities/role/role-details.vue');

const Permission = () => import('@/entities/permission/permission.vue');
const PermissionUpdate = () => import('@/entities/permission/permission-update.vue');
const PermissionDetails = () => import('@/entities/permission/permission-details.vue');

const Document = () => import('@/entities/document/document.vue');
const DocumentUpdate = () => import('@/entities/document/document-update.vue');
const DocumentDetails = () => import('@/entities/document/document-details.vue');
const Fileupload = () => import('@/entities/document/fileupload.vue');
const Documentmenu = () => import('@/entities/documentmenu/documentmenu.vue');

const ProgressPlan = () => import('@/entities/progress-plan/progress-plan.vue');
const ProgressPlanUpdate = () => import('@/entities/progress-plan/progress-plan-update.vue');
const ProgressPlanDetails = () => import('@/entities/progress-plan/progress-plan-details.vue');

const PlanReturns = () => import('@/entities/plan-returns/plan-returns.vue');
const PlanReturnsUpdate = () => import('@/entities/plan-returns/plan-returns-update.vue');
const PlanReturnsDetails = () => import('@/entities/plan-returns/plan-returns-details.vue');

const FundsEstimation = () => import('@/entities/funds-estimation/funds-estimation.vue');
const FundsEstimationUpdate = () => import('@/entities/funds-estimation/funds-estimation-update.vue');
const FundsEstimationDetails = () => import('@/entities/funds-estimation/funds-estimation-details.vue');

const Contract = () => import('@/entities/contract/contract.vue');
const ContractUpdate = () => import('@/entities/contract/contract-update.vue');
const ContractDetails = () => import('@/entities/contract/contract-details.vue');

const ContractCostBudget = () => import('@/entities/contract-cost-budget/contract-cost-budget.vue');
const ContractCostBudgetUpdate = () => import('@/entities/contract-cost-budget/contract-cost-budget-update.vue');
const ContractCostBudgetDetails = () => import('@/entities/contract-cost-budget/contract-cost-budget-details.vue');

const CostControlSystem = () => import('@/entities/cost-control-system/cost-control-system.vue');
const CostControlSystemUpdate = () => import('@/entities/cost-control-system/cost-control-system-update.vue');
const CostControlSystemDetails = () => import('@/entities/cost-control-system/cost-control-system-details.vue');

const QualityObjectives = () => import('@/entities/quality-objectives/quality-objectives.vue');
const QualityObjectivesUpdate = () => import('@/entities/quality-objectives/quality-objectives-update.vue');
const QualityObjectivesDetails = () => import('@/entities/quality-objectives/quality-objectives-details.vue');

const QualityReturns = () => import('@/entities/quality-returns/quality-returns.vue');
const QualityReturnsUpdate = () => import('@/entities/quality-returns/quality-returns-update.vue');
const QualityReturnsDetails = () => import('@/entities/quality-returns/quality-returns-details.vue');

const UnQualityAudit = () => import('@/entities/un-quality-audit/un-quality-audit.vue');
const UnQualityAuditUpdate = () => import('@/entities/un-quality-audit/un-quality-audit-update.vue');
const UnQualityAuditDetails = () => import('@/entities/un-quality-audit/un-quality-audit-details.vue');

const OutsourcingContractual = () => import('@/entities/outsourcing-contractual/outsourcing-contractual.vue');
const OutsourcingContractualUpdate = () => import('@/entities/outsourcing-contractual/outsourcing-contractual-update.vue');
const OutsourcingContractualDetails = () => import('@/entities/outsourcing-contractual/outsourcing-contractual-details.vue');

const OutsourcingPurchasePlan = () => import('@/entities/outsourcing-purchase-plan/outsourcing-purchase-plan.vue');
const OutsourcingPurchasePlanUpdate = () => import('@/entities/outsourcing-purchase-plan/outsourcing-purchase-plan-update.vue');
const OutsourcingPurchasePlanDetails = () => import('@/entities/outsourcing-purchase-plan/outsourcing-purchase-plan-details.vue');

const OutsourcingPurchaseExecute = () => import('@/entities/outsourcing-purchase-execute/outsourcing-purchase-execute.vue');
const OutsourcingPurchaseExecuteUpdate = () => import('@/entities/outsourcing-purchase-execute/outsourcing-purchase-execute-update.vue');
const OutsourcingPurchaseExecuteDetails = () => import('@/entities/outsourcing-purchase-execute/outsourcing-purchase-execute-details.vue');

const Projectremit = () => import('@/entities/projectremit/projectremit.vue');
const ProjectremitUpdate = () => import('@/entities/projectremit/projectremit-update.vue');
const ProjectremitDetails = () => import('@/entities/projectremit/projectremit-details.vue');

const Technical = () => import('@/entities/technical/technical.vue');
const TechnicalUpdate = () => import('@/entities/technical/technical-update.vue');
const TechnicalDetails = () => import('@/entities/technical/technical-details.vue');

const TechnicalCondition = () => import('@/entities/technical-condition/technical-condition.vue');
const TechnicalConditionUpdate = () => import('@/entities/technical-condition/technical-condition-update.vue');
const TechnicalConditionDetails = () => import('@/entities/technical-condition/technical-condition-details.vue');

const ProjectRisk = () => import('@/entities/project-risk/project-risk.vue');
const ProjectRiskUpdate = () => import('@/entities/project-risk/project-risk-update.vue');
const ProjectRiskDetails = () => import('@/entities/project-risk/project-risk-details.vue');

const RiskReport = () => import('@/entities/risk-report/risk-report.vue');
const RiskReportUpdate = () => import('@/entities/risk-report/risk-report-update.vue');
const RiskReportDetails = () => import('@/entities/risk-report/risk-report-details.vue');

const LeaveApplicationInfo = () => import('@/entities/leave-application-info/leave-application-info.vue');
const LeaveApplicationInfoUpdate = () => import('@/entities/leave-application-info/leave-application-info-update.vue');
const LeaveApplicationInfoDetails = () => import('@/entities/leave-application-info/leave-application-info-details.vue');

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
      path: 'projectpbs',
      name: 'Projectpbs',
      component: Projectpbs,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs/new',
      name: 'ProjectpbsCreate',
      component: ProjectpbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs/:projectpbsId/edit',
      name: 'ProjectpbsEdit',
      component: ProjectpbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs/:parentId/edit',
      name: 'ProjectpbsParentCreate',
      component: ProjectpbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs/:projectpbsId/view',
      name: 'ProjectpbsView',
      component: ProjectpbsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectTotalwbs',
      name: 'projectTotalwbs',
      component: ProjectTotalwbs,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectTotalwbs/new',
      name: 'ProjectTotalwbsCreate',
      component: ProjectTotalwbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectTotalwbs/:projectTotalwbsId/edit',
      name: 'ProjectTotalwbsEdit',
      component: ProjectTotalwbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectTotalwbs/:projectTotalwbsId/view',
      name: 'ProjectTotalwbsView',
      component: ProjectTotalwbsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: '/projectTotalwbsSelect',
      name: 'projectTotalwbsSelect',
      component: projectTotalwbsSelect,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectwbs',
      name: 'Projectwbs',
      component: Projectwbs,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectwbs/new',
      name: 'ProjectwbsCreate',
      component: ProjectwbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectwbs/:projectwbsId/edit',
      name: 'ProjectwbsEdit',
      component: ProjectwbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectwbs/:projectwbsId/view',
      name: 'ProjectwbsView',
      component: ProjectwbsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectwbs/:ProjectwbsOneId',
      name: 'ProjectwbsOne',
      component: ProjectwbsOne,
      props: true,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: '/projectwbsSelect',
      name: 'projectwbsSelect',
      component: projectwbsSelect,
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
      path: 'document',
      name: 'Document',
      component: Document,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fileupload',
      name: 'Fileupload',
      component: Fileupload,
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
      path: 'documentmenu',
      name: 'Documentmenu',
      component: Documentmenu,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'progress-plan',
      name: 'ProgressPlan',
      component: ProgressPlan,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'progress-plan/new',
      name: 'ProgressPlanCreate',
      component: ProgressPlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'progress-plan/:progressPlanId/edit',
      name: 'ProgressPlanEdit',
      component: ProgressPlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'progress-plan/:progressPlanId/view',
      name: 'ProgressPlanView',
      component: ProgressPlanDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'plan-returns',
      name: 'PlanReturns',
      component: PlanReturns,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'plan-returns/new',
      name: 'PlanReturnsCreate',
      component: PlanReturnsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'plan-returns/:planReturnsId/edit',
      name: 'PlanReturnsEdit',
      component: PlanReturnsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'plan-returns/:planReturnsId/view',
      name: 'PlanReturnsView',
      component: PlanReturnsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'funds-estimation',
      name: 'FundsEstimation',
      component: FundsEstimation,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'funds-estimation/new',
      name: 'FundsEstimationCreate',
      component: FundsEstimationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'funds-estimation/:fundsEstimationId/edit',
      name: 'FundsEstimationEdit',
      component: FundsEstimationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'funds-estimation/:fundsEstimationId/view',
      name: 'FundsEstimationView',
      component: FundsEstimationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract',
      name: 'Contract',
      component: Contract,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract/new',
      name: 'ContractCreate',
      component: ContractUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract/:contractId/edit',
      name: 'ContractEdit',
      component: ContractUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract/:contractId/view',
      name: 'ContractView',
      component: ContractDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract-cost-budget',
      name: 'ContractCostBudget',
      component: ContractCostBudget,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract-cost-budget/new',
      name: 'ContractCostBudgetCreate',
      component: ContractCostBudgetUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract-cost-budget/:contractCostBudgetId/edit',
      name: 'ContractCostBudgetEdit',
      component: ContractCostBudgetUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract-cost-budget/:contractCostBudgetId/view',
      name: 'ContractCostBudgetView',
      component: ContractCostBudgetDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cost-control-system',
      name: 'CostControlSystem',
      component: CostControlSystem,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cost-control-system/new',
      name: 'CostControlSystemCreate',
      component: CostControlSystemUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cost-control-system/:costControlSystemId/edit',
      name: 'CostControlSystemEdit',
      component: CostControlSystemUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cost-control-system/:costControlSystemId/view',
      name: 'CostControlSystemView',
      component: CostControlSystemDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-objectives',
      name: 'QualityObjectives',
      component: QualityObjectives,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-objectives/new',
      name: 'QualityObjectivesCreate',
      component: QualityObjectivesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-objectives/:qualityObjectivesId/edit',
      name: 'QualityObjectivesEdit',
      component: QualityObjectivesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-objectives/:qualityObjectivesId/view',
      name: 'QualityObjectivesView',
      component: QualityObjectivesDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-returns',
      name: 'QualityReturns',
      component: QualityReturns,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-returns/new',
      name: 'QualityReturnsCreate',
      component: QualityReturnsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-returns/:qualityReturnsId/edit',
      name: 'QualityReturnsEdit',
      component: QualityReturnsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-returns/:qualityReturnsId/view',
      name: 'QualityReturnsView',
      component: QualityReturnsDetails,
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
      path: 'outsourcing-contractual',
      name: 'OutsourcingContractual',
      component: OutsourcingContractual,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-contractual/new',
      name: 'OutsourcingContractualCreate',
      component: OutsourcingContractualUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-contractual/:outsourcingContractualId/edit',
      name: 'OutsourcingContractualEdit',
      component: OutsourcingContractualUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-contractual/:outsourcingContractualId/view',
      name: 'OutsourcingContractualView',
      component: OutsourcingContractualDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-purchase-plan',
      name: 'OutsourcingPurchasePlan',
      component: OutsourcingPurchasePlan,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-purchase-plan/new',
      name: 'OutsourcingPurchasePlanCreate',
      component: OutsourcingPurchasePlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-purchase-plan/:outsourcingPurchasePlanId/edit',
      name: 'OutsourcingPurchasePlanEdit',
      component: OutsourcingPurchasePlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-purchase-plan/:outsourcingPurchasePlanId/view',
      name: 'OutsourcingPurchasePlanView',
      component: OutsourcingPurchasePlanDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-purchase-execute',
      name: 'OutsourcingPurchaseExecute',
      component: OutsourcingPurchaseExecute,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-purchase-execute/new',
      name: 'OutsourcingPurchaseExecuteCreate',
      component: OutsourcingPurchaseExecuteUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-purchase-execute/:outsourcingPurchaseExecuteId/edit',
      name: 'OutsourcingPurchaseExecuteEdit',
      component: OutsourcingPurchaseExecuteUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-purchase-execute/:outsourcingPurchaseExecuteId/view',
      name: 'OutsourcingPurchaseExecuteView',
      component: OutsourcingPurchaseExecuteDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectremit',
      name: 'Projectremit',
      component: Projectremit,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectremit/new',
      name: 'ProjectremitCreate',
      component: ProjectremitUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectremit/:projectremitId/edit',
      name: 'ProjectremitEdit',
      component: ProjectremitUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectremit/:projectremitId/view',
      name: 'ProjectremitView',
      component: ProjectremitDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'technical',
      name: 'Technical',
      component: Technical,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'technical/new',
      name: 'TechnicalCreate',
      component: TechnicalUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'technical/:technicalId/edit',
      name: 'TechnicalEdit',
      component: TechnicalUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'technical/:technicalId/view',
      name: 'TechnicalView',
      component: TechnicalDetails,
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
      path: 'project-risk',
      name: 'ProjectRisk',
      component: ProjectRisk,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-risk/new',
      name: 'ProjectRiskCreate',
      component: ProjectRiskUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-risk/:projectRiskId/edit',
      name: 'ProjectRiskEdit',
      component: ProjectRiskUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-risk/:projectRiskId/view',
      name: 'ProjectRiskView',
      component: ProjectRiskDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-report',
      name: 'RiskReport',
      component: RiskReport,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-report/new',
      name: 'RiskReportCreate',
      component: RiskReportUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-report/:riskReportId/edit',
      name: 'RiskReportEdit',
      component: RiskReportUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-report/:riskReportId/view',
      name: 'RiskReportView',
      component: RiskReportDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leave-application-info',
      name: 'LeaveApplicationInfo',
      component: LeaveApplicationInfo,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leave-application-info/new',
      name: 'LeaveApplicationInfoCreate',
      component: LeaveApplicationInfoUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leave-application-info/:leaveApplicationInfoId/edit',
      name: 'LeaveApplicationInfoEdit',
      component: LeaveApplicationInfoUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leave-application-info/:leaveApplicationInfoId/view',
      name: 'LeaveApplicationInfoView',
      component: LeaveApplicationInfoDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
