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
const ProjectpbsUpdate = () => import('@/entities/projectpbs/projectpbs-update.vue');
const ProjectpbsDetails = () => import('@/entities/projectpbs/projectpbs-details.vue');

const Projectwbs = () => import('@/entities/projectwbs/projectwbs.vue');
const ProjectwbsUpdate = () => import('@/entities/projectwbs/projectwbs-update.vue');
const ProjectwbsDetails = () => import('@/entities/projectwbs/projectwbs-details.vue');

const Role = () => import('@/entities/role/role.vue');
const RoleUpdate = () => import('@/entities/role/role-update.vue');
const RoleDetails = () => import('@/entities/role/role-details.vue');

const Permission = () => import('@/entities/permission/permission.vue');
const PermissionUpdate = () => import('@/entities/permission/permission-update.vue');
const PermissionDetails = () => import('@/entities/permission/permission-details.vue');

const Document = () => import('@/entities/document/document.vue');
const DocumentUpdate = () => import('@/entities/document/document-update.vue');
const DocumentDetails = () => import('@/entities/document/document-details.vue');

const ProgressPlan = () => import('@/entities/progress-plan/progress-plan.vue');
const ProgressPlanUpdate = () => import('@/entities/progress-plan/progress-plan-update.vue');
const ProgressPlanDetails = () => import('@/entities/progress-plan/progress-plan-details.vue');

const PlanReturns = () => import('@/entities/plan-returns/plan-returns.vue');
const PlanReturnsUpdate = () => import('@/entities/plan-returns/plan-returns-update.vue');
const PlanReturnsDetails = () => import('@/entities/plan-returns/plan-returns-details.vue');

const Contract = () => import('@/entities/contract/contract.vue');
const ContractUpdate = () => import('@/entities/contract/contract-update.vue');
const ContractDetails = () => import('@/entities/contract/contract-details.vue');

const QualityObjectives = () => import('@/entities/quality-objectives/quality-objectives.vue');
const QualityObjectivesUpdate = () => import('@/entities/quality-objectives/quality-objectives-update.vue');
const QualityObjectivesDetails = () => import('@/entities/quality-objectives/quality-objectives-details.vue');

const QualityReturns = () => import('@/entities/quality-returns/quality-returns.vue');
const QualityReturnsUpdate = () => import('@/entities/quality-returns/quality-returns-update.vue');
const QualityReturnsDetails = () => import('@/entities/quality-returns/quality-returns-details.vue');

const UnQualityAudit = () => import('@/entities/un-quality-audit/un-quality-audit.vue');
const UnQualityAuditUpdate = () => import('@/entities/un-quality-audit/un-quality-audit-update.vue');
const UnQualityAuditDetails = () => import('@/entities/un-quality-audit/un-quality-audit-details.vue');

const TechnicalCondition = () => import('@/entities/technical-condition/technical-condition.vue');
const TechnicalConditionUpdate = () => import('@/entities/technical-condition/technical-condition-update.vue');
const TechnicalConditionDetails = () => import('@/entities/technical-condition/technical-condition-details.vue');

const ProjectRisk = () => import('@/entities/project-risk/project-risk.vue');
const ProjectRiskUpdate = () => import('@/entities/project-risk/project-risk-update.vue');
const ProjectRiskDetails = () => import('@/entities/project-risk/project-risk-details.vue');

const RiskReport = () => import('@/entities/risk-report/risk-report.vue');
const RiskReportUpdate = () => import('@/entities/risk-report/risk-report-update.vue');
const RiskReportDetails = () => import('@/entities/risk-report/risk-report-details.vue');

const Documentmenu = () => import('@/entities/documentmenu/documentmenu.vue');
const DocumentmenuUpdate = () => import('@/entities/documentmenu/documentmenu-update.vue');
const DocumentmenuDetails = () => import('@/entities/documentmenu/documentmenu-details.vue');

const LeaveApplicationInfo = () => import('@/entities/leave-application-info/leave-application-info.vue');
const LeaveApplicationInfoUpdate = () => import('@/entities/leave-application-info/leave-application-info-update.vue');
const LeaveApplicationInfoDetails = () => import('@/entities/leave-application-info/leave-application-info-details.vue');

const HrManagement = () => import('@/entities/hr-management/hr-management.vue');
const HrManagementUpdate = () => import('@/entities/hr-management/hr-management-update.vue');
const HrManagementDetails = () => import('@/entities/hr-management/hr-management-details.vue');

const Frontline = () => import('@/entities/frontline/frontline.vue');
const FrontlineUpdate = () => import('@/entities/frontline/frontline-update.vue');
const FrontlineDetails = () => import('@/entities/frontline/frontline-details.vue');

const Events = () => import('@/entities/events/events.vue');
const EventsUpdate = () => import('@/entities/events/events-update.vue');
const EventsDetails = () => import('@/entities/events/events-details.vue');

const Archives = () => import('@/entities/archives/archives.vue');
const ArchivesUpdate = () => import('@/entities/archives/archives-update.vue');
const ArchivesDetails = () => import('@/entities/archives/archives-details.vue');

const Projectdeliverables = () => import('@/entities/projectdeliverables/projectdeliverables.vue');
const ProjectdeliverablesUpdate = () => import('@/entities/projectdeliverables/projectdeliverables-update.vue');
const ProjectdeliverablesDetails = () => import('@/entities/projectdeliverables/projectdeliverables-details.vue');

const Deliverables = () => import('@/entities/deliverables/deliverables.vue');
const DeliverablesUpdate = () => import('@/entities/deliverables/deliverables-update.vue');
const DeliverablesDetails = () => import('@/entities/deliverables/deliverables-details.vue');

const Workbag = () => import('@/entities/workbag/workbag.vue');
const WorkbagUpdate = () => import('@/entities/workbag/workbag-update.vue');
const WorkbagDetails = () => import('@/entities/workbag/workbag-details.vue');

const Work = () => import('@/entities/work/work.vue');
const WorkUpdate = () => import('@/entities/work/work-update.vue');
const WorkDetails = () => import('@/entities/work/work-details.vue');

const Subject = () => import('@/entities/subject/subject.vue');
const SubjectUpdate = () => import('@/entities/subject/subject-update.vue');
const SubjectDetails = () => import('@/entities/subject/subject-details.vue');

const SubjectCostBudget = () => import('@/entities/subject-cost-budget/subject-cost-budget.vue');
const SubjectCostBudgetUpdate = () => import('@/entities/subject-cost-budget/subject-cost-budget-update.vue');
const SubjectCostBudgetDetails = () => import('@/entities/subject-cost-budget/subject-cost-budget-details.vue');

const ProjectBudget = () => import('@/entities/project-budget/project-budget.vue');
const ProjectBudgetUpdate = () => import('@/entities/project-budget/project-budget-update.vue');
const ProjectBudgetDetails = () => import('@/entities/project-budget/project-budget-details.vue');

const OutsourcingContract = () => import('@/entities/outsourcing-contract/outsourcing-contract.vue');
const OutsourcingContractUpdate = () => import('@/entities/outsourcing-contract/outsourcing-contract-update.vue');
const OutsourcingContractDetails = () => import('@/entities/outsourcing-contract/outsourcing-contract-details.vue');

const MilestoneNode = () => import('@/entities/milestone-node/milestone-node.vue');
const MilestoneNodeUpdate = () => import('@/entities/milestone-node/milestone-node-update.vue');
const MilestoneNodeDetails = () => import('@/entities/milestone-node/milestone-node-details.vue');

const PaymentApplication = () => import('@/entities/payment-application/payment-application.vue');
const PaymentApplicationUpdate = () => import('@/entities/payment-application/payment-application-update.vue');
const PaymentApplicationDetails = () => import('@/entities/payment-application/payment-application-details.vue');

const ContractPayment = () => import('@/entities/contract-payment/contract-payment.vue');
const ContractPaymentUpdate = () => import('@/entities/contract-payment/contract-payment-update.vue');
const ContractPaymentDetails = () => import('@/entities/contract-payment/contract-payment-details.vue');

const PaymentCostList = () => import('@/entities/payment-cost-list/payment-cost-list.vue');
const PaymentCostListUpdate = () => import('@/entities/payment-cost-list/payment-cost-list-update.vue');
const PaymentCostListDetails = () => import('@/entities/payment-cost-list/payment-cost-list-details.vue');

const FundSourceList = () => import('@/entities/fund-source-list/fund-source-list.vue');
const FundSourceListUpdate = () => import('@/entities/fund-source-list/fund-source-list-update.vue');
const FundSourceListDetails = () => import('@/entities/fund-source-list/fund-source-list-details.vue');

const QualityObjectivesDictionary = () => import('@/entities/quality-objectives-dictionary/quality-objectives-dictionary.vue');
const QualityObjectivesDictionaryUpdate = () => import('@/entities/quality-objectives-dictionary/quality-objectives-dictionary-update.vue');
const QualityObjectivesDictionaryDetails = () =>
  import('@/entities/quality-objectives-dictionary/quality-objectives-dictionary-details.vue');

const QualityPlan = () => import('@/entities/quality-plan/quality-plan.vue');
const QualityPlanUpdate = () => import('@/entities/quality-plan/quality-plan-update.vue');
const QualityPlanDetails = () => import('@/entities/quality-plan/quality-plan-details.vue');

const RegularInspection = () => import('@/entities/regular-inspection/regular-inspection.vue');
const RegularInspectionUpdate = () => import('@/entities/regular-inspection/regular-inspection-update.vue');
const RegularInspectionDetails = () => import('@/entities/regular-inspection/regular-inspection-details.vue');

const KeyNodeInspection = () => import('@/entities/key-node-inspection/key-node-inspection.vue');
const KeyNodeInspectionUpdate = () => import('@/entities/key-node-inspection/key-node-inspection-update.vue');
const KeyNodeInspectionDetails = () => import('@/entities/key-node-inspection/key-node-inspection-details.vue');

const Qualitytozero = () => import('@/entities/qualitytozero/qualitytozero.vue');
const QualitytozeroUpdate = () => import('@/entities/qualitytozero/qualitytozero-update.vue');
const QualitytozeroDetails = () => import('@/entities/qualitytozero/qualitytozero-details.vue');

const DeviationPermitApplication = () => import('@/entities/deviation-permit-application/deviation-permit-application.vue');
const DeviationPermitApplicationUpdate = () => import('@/entities/deviation-permit-application/deviation-permit-application-update.vue');
const DeviationPermitApplicationDetails = () => import('@/entities/deviation-permit-application/deviation-permit-application-details.vue');

const CommunicationPlan = () => import('@/entities/communication-plan/communication-plan.vue');
const CommunicationPlanUpdate = () => import('@/entities/communication-plan/communication-plan-update.vue');
const CommunicationPlanDetails = () => import('@/entities/communication-plan/communication-plan-details.vue');

const CommunicationRecord = () => import('@/entities/communication-record/communication-record.vue');
const CommunicationRecordUpdate = () => import('@/entities/communication-record/communication-record-update.vue');
const CommunicationRecordDetails = () => import('@/entities/communication-record/communication-record-details.vue');

const CommunicationDictionary = () => import('@/entities/communication-dictionary/communication-dictionary.vue');
const CommunicationDictionaryUpdate = () => import('@/entities/communication-dictionary/communication-dictionary-update.vue');
const CommunicationDictionaryDetails = () => import('@/entities/communication-dictionary/communication-dictionary-details.vue');

const CommunicationFormDictionary = () => import('@/entities/communication-form-dictionary/communication-form-dictionary.vue');
const CommunicationFormDictionaryUpdate = () => import('@/entities/communication-form-dictionary/communication-form-dictionary-update.vue');
const CommunicationFormDictionaryDetails = () =>
  import('@/entities/communication-form-dictionary/communication-form-dictionary-details.vue');

const Letter = () => import('@/entities/letter/letter.vue');
const LetterUpdate = () => import('@/entities/letter/letter-update.vue');
const LetterDetails = () => import('@/entities/letter/letter-details.vue');

const CustomerSatisfaction = () => import('@/entities/customer-satisfaction/customer-satisfaction.vue');
const CustomerSatisfactionUpdate = () => import('@/entities/customer-satisfaction/customer-satisfaction-update.vue');
const CustomerSatisfactionDetails = () => import('@/entities/customer-satisfaction/customer-satisfaction-details.vue');

const RiskPossibility = () => import('@/entities/risk-possibility/risk-possibility.vue');
const RiskPossibilityUpdate = () => import('@/entities/risk-possibility/risk-possibility-update.vue');
const RiskPossibilityDetails = () => import('@/entities/risk-possibility/risk-possibility-details.vue');

const RiskType = () => import('@/entities/risk-type/risk-type.vue');
const RiskTypeUpdate = () => import('@/entities/risk-type/risk-type-update.vue');
const RiskTypeDetails = () => import('@/entities/risk-type/risk-type-details.vue');

const RiskLevel = () => import('@/entities/risk-level/risk-level.vue');
const RiskLevelUpdate = () => import('@/entities/risk-level/risk-level-update.vue');
const RiskLevelDetails = () => import('@/entities/risk-level/risk-level-details.vue');

const SystemLevel = () => import('@/entities/system-level/system-level.vue');
const SystemLevelUpdate = () => import('@/entities/system-level/system-level-update.vue');
const SystemLevelDetails = () => import('@/entities/system-level/system-level-details.vue');

const RiskReturn = () => import('@/entities/risk-return/risk-return.vue');
const RiskReturnUpdate = () => import('@/entities/risk-return/risk-return-update.vue');
const RiskReturnDetails = () => import('@/entities/risk-return/risk-return-details.vue');

const OtherPayment = () => import('@/entities/other-payment/other-payment.vue');
const OtherPaymentUpdate = () => import('@/entities/other-payment/other-payment-update.vue');
const OtherPaymentDetails = () => import('@/entities/other-payment/other-payment-details.vue');

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
      path: 'projectpbs/:projectpbsId/view',
      name: 'ProjectpbsView',
      component: ProjectpbsDetails,
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
      path: 'documentmenu',
      name: 'Documentmenu',
      component: Documentmenu,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'documentmenu/new',
      name: 'DocumentmenuCreate',
      component: DocumentmenuUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'documentmenu/:documentmenuId/edit',
      name: 'DocumentmenuEdit',
      component: DocumentmenuUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'documentmenu/:documentmenuId/view',
      name: 'DocumentmenuView',
      component: DocumentmenuDetails,
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
    {
      path: 'hr-management',
      name: 'HrManagement',
      component: HrManagement,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'hr-management/new',
      name: 'HrManagementCreate',
      component: HrManagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'hr-management/:hrManagementId/edit',
      name: 'HrManagementEdit',
      component: HrManagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'hr-management/:hrManagementId/view',
      name: 'HrManagementView',
      component: HrManagementDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'frontline',
      name: 'Frontline',
      component: Frontline,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'frontline/new',
      name: 'FrontlineCreate',
      component: FrontlineUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'frontline/:frontlineId/edit',
      name: 'FrontlineEdit',
      component: FrontlineUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'frontline/:frontlineId/view',
      name: 'FrontlineView',
      component: FrontlineDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'events',
      name: 'Events',
      component: Events,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'events/new',
      name: 'EventsCreate',
      component: EventsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'events/:eventsId/edit',
      name: 'EventsEdit',
      component: EventsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'events/:eventsId/view',
      name: 'EventsView',
      component: EventsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'archives',
      name: 'Archives',
      component: Archives,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'archives/new',
      name: 'ArchivesCreate',
      component: ArchivesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'archives/:archivesId/edit',
      name: 'ArchivesEdit',
      component: ArchivesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'archives/:archivesId/view',
      name: 'ArchivesView',
      component: ArchivesDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectdeliverables',
      name: 'Projectdeliverables',
      component: Projectdeliverables,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectdeliverables/new',
      name: 'ProjectdeliverablesCreate',
      component: ProjectdeliverablesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectdeliverables/:projectdeliverablesId/edit',
      name: 'ProjectdeliverablesEdit',
      component: ProjectdeliverablesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectdeliverables/:projectdeliverablesId/view',
      name: 'ProjectdeliverablesView',
      component: ProjectdeliverablesDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'deliverables',
      name: 'Deliverables',
      component: Deliverables,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'deliverables/new',
      name: 'DeliverablesCreate',
      component: DeliverablesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'deliverables/:deliverablesId/edit',
      name: 'DeliverablesEdit',
      component: DeliverablesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'deliverables/:deliverablesId/view',
      name: 'DeliverablesView',
      component: DeliverablesDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'workbag',
      name: 'Workbag',
      component: Workbag,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'workbag/new',
      name: 'WorkbagCreate',
      component: WorkbagUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'workbag/:workbagId/edit',
      name: 'WorkbagEdit',
      component: WorkbagUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'workbag/:workbagId/view',
      name: 'WorkbagView',
      component: WorkbagDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'work',
      name: 'Work',
      component: Work,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'work/new',
      name: 'WorkCreate',
      component: WorkUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'work/:workId/edit',
      name: 'WorkEdit',
      component: WorkUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'work/:workId/view',
      name: 'WorkView',
      component: WorkDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'subject',
      name: 'Subject',
      component: Subject,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'subject/new',
      name: 'SubjectCreate',
      component: SubjectUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'subject/:subjectId/edit',
      name: 'SubjectEdit',
      component: SubjectUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'subject/:subjectId/view',
      name: 'SubjectView',
      component: SubjectDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'subject-cost-budget',
      name: 'SubjectCostBudget',
      component: SubjectCostBudget,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'subject-cost-budget/new',
      name: 'SubjectCostBudgetCreate',
      component: SubjectCostBudgetUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'subject-cost-budget/:subjectCostBudgetId/edit',
      name: 'SubjectCostBudgetEdit',
      component: SubjectCostBudgetUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'subject-cost-budget/:subjectCostBudgetId/view',
      name: 'SubjectCostBudgetView',
      component: SubjectCostBudgetDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-budget',
      name: 'ProjectBudget',
      component: ProjectBudget,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-budget/new',
      name: 'ProjectBudgetCreate',
      component: ProjectBudgetUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-budget/:projectBudgetId/edit',
      name: 'ProjectBudgetEdit',
      component: ProjectBudgetUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-budget/:projectBudgetId/view',
      name: 'ProjectBudgetView',
      component: ProjectBudgetDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-contract',
      name: 'OutsourcingContract',
      component: OutsourcingContract,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-contract/new',
      name: 'OutsourcingContractCreate',
      component: OutsourcingContractUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-contract/:outsourcingContractId/edit',
      name: 'OutsourcingContractEdit',
      component: OutsourcingContractUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'outsourcing-contract/:outsourcingContractId/view',
      name: 'OutsourcingContractView',
      component: OutsourcingContractDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'milestone-node',
      name: 'MilestoneNode',
      component: MilestoneNode,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'milestone-node/new',
      name: 'MilestoneNodeCreate',
      component: MilestoneNodeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'milestone-node/:milestoneNodeId/edit',
      name: 'MilestoneNodeEdit',
      component: MilestoneNodeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'milestone-node/:milestoneNodeId/view',
      name: 'MilestoneNodeView',
      component: MilestoneNodeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-application',
      name: 'PaymentApplication',
      component: PaymentApplication,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-application/new',
      name: 'PaymentApplicationCreate',
      component: PaymentApplicationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-application/:paymentApplicationId/edit',
      name: 'PaymentApplicationEdit',
      component: PaymentApplicationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-application/:paymentApplicationId/view',
      name: 'PaymentApplicationView',
      component: PaymentApplicationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract-payment',
      name: 'ContractPayment',
      component: ContractPayment,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract-payment/new',
      name: 'ContractPaymentCreate',
      component: ContractPaymentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract-payment/:contractPaymentId/edit',
      name: 'ContractPaymentEdit',
      component: ContractPaymentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'contract-payment/:contractPaymentId/view',
      name: 'ContractPaymentView',
      component: ContractPaymentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-cost-list',
      name: 'PaymentCostList',
      component: PaymentCostList,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-cost-list/new',
      name: 'PaymentCostListCreate',
      component: PaymentCostListUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-cost-list/:paymentCostListId/edit',
      name: 'PaymentCostListEdit',
      component: PaymentCostListUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-cost-list/:paymentCostListId/view',
      name: 'PaymentCostListView',
      component: PaymentCostListDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fund-source-list',
      name: 'FundSourceList',
      component: FundSourceList,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fund-source-list/new',
      name: 'FundSourceListCreate',
      component: FundSourceListUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fund-source-list/:fundSourceListId/edit',
      name: 'FundSourceListEdit',
      component: FundSourceListUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'fund-source-list/:fundSourceListId/view',
      name: 'FundSourceListView',
      component: FundSourceListDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-objectives-dictionary',
      name: 'QualityObjectivesDictionary',
      component: QualityObjectivesDictionary,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-objectives-dictionary/new',
      name: 'QualityObjectivesDictionaryCreate',
      component: QualityObjectivesDictionaryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-objectives-dictionary/:qualityObjectivesDictionaryId/edit',
      name: 'QualityObjectivesDictionaryEdit',
      component: QualityObjectivesDictionaryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-objectives-dictionary/:qualityObjectivesDictionaryId/view',
      name: 'QualityObjectivesDictionaryView',
      component: QualityObjectivesDictionaryDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-plan',
      name: 'QualityPlan',
      component: QualityPlan,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-plan/new',
      name: 'QualityPlanCreate',
      component: QualityPlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-plan/:qualityPlanId/edit',
      name: 'QualityPlanEdit',
      component: QualityPlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quality-plan/:qualityPlanId/view',
      name: 'QualityPlanView',
      component: QualityPlanDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'regular-inspection',
      name: 'RegularInspection',
      component: RegularInspection,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'regular-inspection/new',
      name: 'RegularInspectionCreate',
      component: RegularInspectionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'regular-inspection/:regularInspectionId/edit',
      name: 'RegularInspectionEdit',
      component: RegularInspectionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'regular-inspection/:regularInspectionId/view',
      name: 'RegularInspectionView',
      component: RegularInspectionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'key-node-inspection',
      name: 'KeyNodeInspection',
      component: KeyNodeInspection,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'key-node-inspection/new',
      name: 'KeyNodeInspectionCreate',
      component: KeyNodeInspectionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'key-node-inspection/:keyNodeInspectionId/edit',
      name: 'KeyNodeInspectionEdit',
      component: KeyNodeInspectionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'key-node-inspection/:keyNodeInspectionId/view',
      name: 'KeyNodeInspectionView',
      component: KeyNodeInspectionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualitytozero',
      name: 'Qualitytozero',
      component: Qualitytozero,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualitytozero/new',
      name: 'QualitytozeroCreate',
      component: QualitytozeroUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualitytozero/:qualitytozeroId/edit',
      name: 'QualitytozeroEdit',
      component: QualitytozeroUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qualitytozero/:qualitytozeroId/view',
      name: 'QualitytozeroView',
      component: QualitytozeroDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'deviation-permit-application',
      name: 'DeviationPermitApplication',
      component: DeviationPermitApplication,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'deviation-permit-application/new',
      name: 'DeviationPermitApplicationCreate',
      component: DeviationPermitApplicationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'deviation-permit-application/:deviationPermitApplicationId/edit',
      name: 'DeviationPermitApplicationEdit',
      component: DeviationPermitApplicationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'deviation-permit-application/:deviationPermitApplicationId/view',
      name: 'DeviationPermitApplicationView',
      component: DeviationPermitApplicationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-plan',
      name: 'CommunicationPlan',
      component: CommunicationPlan,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-plan/new',
      name: 'CommunicationPlanCreate',
      component: CommunicationPlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-plan/:communicationPlanId/edit',
      name: 'CommunicationPlanEdit',
      component: CommunicationPlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-plan/:communicationPlanId/view',
      name: 'CommunicationPlanView',
      component: CommunicationPlanDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-record',
      name: 'CommunicationRecord',
      component: CommunicationRecord,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-record/new',
      name: 'CommunicationRecordCreate',
      component: CommunicationRecordUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-record/:communicationRecordId/edit',
      name: 'CommunicationRecordEdit',
      component: CommunicationRecordUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-record/:communicationRecordId/view',
      name: 'CommunicationRecordView',
      component: CommunicationRecordDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-dictionary',
      name: 'CommunicationDictionary',
      component: CommunicationDictionary,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-dictionary/new',
      name: 'CommunicationDictionaryCreate',
      component: CommunicationDictionaryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-dictionary/:communicationDictionaryId/edit',
      name: 'CommunicationDictionaryEdit',
      component: CommunicationDictionaryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-dictionary/:communicationDictionaryId/view',
      name: 'CommunicationDictionaryView',
      component: CommunicationDictionaryDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-form-dictionary',
      name: 'CommunicationFormDictionary',
      component: CommunicationFormDictionary,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-form-dictionary/new',
      name: 'CommunicationFormDictionaryCreate',
      component: CommunicationFormDictionaryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-form-dictionary/:communicationFormDictionaryId/edit',
      name: 'CommunicationFormDictionaryEdit',
      component: CommunicationFormDictionaryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'communication-form-dictionary/:communicationFormDictionaryId/view',
      name: 'CommunicationFormDictionaryView',
      component: CommunicationFormDictionaryDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'letter',
      name: 'Letter',
      component: Letter,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'letter/new',
      name: 'LetterCreate',
      component: LetterUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'letter/:letterId/edit',
      name: 'LetterEdit',
      component: LetterUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'letter/:letterId/view',
      name: 'LetterView',
      component: LetterDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer-satisfaction',
      name: 'CustomerSatisfaction',
      component: CustomerSatisfaction,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer-satisfaction/new',
      name: 'CustomerSatisfactionCreate',
      component: CustomerSatisfactionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer-satisfaction/:customerSatisfactionId/edit',
      name: 'CustomerSatisfactionEdit',
      component: CustomerSatisfactionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer-satisfaction/:customerSatisfactionId/view',
      name: 'CustomerSatisfactionView',
      component: CustomerSatisfactionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-possibility',
      name: 'RiskPossibility',
      component: RiskPossibility,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-possibility/new',
      name: 'RiskPossibilityCreate',
      component: RiskPossibilityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-possibility/:riskPossibilityId/edit',
      name: 'RiskPossibilityEdit',
      component: RiskPossibilityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-possibility/:riskPossibilityId/view',
      name: 'RiskPossibilityView',
      component: RiskPossibilityDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-type',
      name: 'RiskType',
      component: RiskType,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-type/new',
      name: 'RiskTypeCreate',
      component: RiskTypeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-type/:riskTypeId/edit',
      name: 'RiskTypeEdit',
      component: RiskTypeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-type/:riskTypeId/view',
      name: 'RiskTypeView',
      component: RiskTypeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-level',
      name: 'RiskLevel',
      component: RiskLevel,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-level/new',
      name: 'RiskLevelCreate',
      component: RiskLevelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-level/:riskLevelId/edit',
      name: 'RiskLevelEdit',
      component: RiskLevelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-level/:riskLevelId/view',
      name: 'RiskLevelView',
      component: RiskLevelDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'system-level',
      name: 'SystemLevel',
      component: SystemLevel,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'system-level/new',
      name: 'SystemLevelCreate',
      component: SystemLevelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'system-level/:systemLevelId/edit',
      name: 'SystemLevelEdit',
      component: SystemLevelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'system-level/:systemLevelId/view',
      name: 'SystemLevelView',
      component: SystemLevelDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-return',
      name: 'RiskReturn',
      component: RiskReturn,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-return/new',
      name: 'RiskReturnCreate',
      component: RiskReturnUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-return/:riskReturnId/edit',
      name: 'RiskReturnEdit',
      component: RiskReturnUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'risk-return/:riskReturnId/view',
      name: 'RiskReturnView',
      component: RiskReturnDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'other-payment',
      name: 'OtherPayment',
      component: OtherPayment,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'other-payment/new',
      name: 'OtherPaymentCreate',
      component: OtherPaymentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'other-payment/:otherPaymentId/edit',
      name: 'OtherPaymentEdit',
      component: OtherPaymentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'other-payment/:otherPaymentId/view',
      name: 'OtherPaymentView',
      component: OtherPaymentDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
