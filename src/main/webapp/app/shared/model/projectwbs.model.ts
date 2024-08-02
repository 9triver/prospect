import { type IOfficers } from '@/shared/model/officers.model';
import { type IDepartment } from '@/shared/model/department.model';
import { type IProject } from '@/shared/model/project.model';
import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';
import { type IFundsEstimation } from '@/shared/model/funds-estimation.model';
import { type IContractCostBudget } from '@/shared/model/contract-cost-budget.model';
import { type ICostControlSystem } from '@/shared/model/cost-control-system.model';
import { type IQualityObjectives } from '@/shared/model/quality-objectives.model';
import { type IOutsourcingContractual } from '@/shared/model/outsourcing-contractual.model';
import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import { type ITechnical } from '@/shared/model/technical.model';
import { type ITechnicalCondition } from '@/shared/model/technical-condition.model';
import { type IProjectRisk } from '@/shared/model/project-risk.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProjectwbs {
  id?: string;
  wbsname?: string | null;
  parentwbsid?: string | null;
  pbsid?: string | null;
  description?: string | null;
  belongfront?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  progress?: number | null;
  type?: number | null;
  priorty?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  deliverables?: string | null;
  status?: keyof typeof ProjectStatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  workbag?: number | null;
  responsibleperson?: IOfficers | null;
  technicaldirector?: IOfficers | null;
  administrativedirector?: IOfficers | null;
  knowingpeople?: IOfficers | null;
  auditorid?: IOfficers | null;
  responsibledepartment?: IDepartment | null;
  relevantdepartment?: IDepartment | null;
  department?: IDepartment | null;
  projects?: IProject[] | null;
  projectpbs?: IProjectpbs[] | null;
  progressPlans?: IProgressPlan[] | null;
  fundsEstimations?: IFundsEstimation[] | null;
  contractCostBudgets?: IContractCostBudget[] | null;
  costControlSystems?: ICostControlSystem[] | null;
  qualityObjectives?: IQualityObjectives[] | null;
  outsourcingContractuals?: IOutsourcingContractual[] | null;
  outsourcingPurchasePlans?: IOutsourcingPurchasePlan[] | null;
  technicals?: ITechnical[] | null;
  technicalConditions?: ITechnicalCondition[] | null;
  projectRisks?: IProjectRisk[] | null;
}

export class Projectwbs implements IProjectwbs {
  constructor(
    public id?: string,
    public wbsname?: string | null,
    public parentwbsid?: string | null,
    public pbsid?: string | null,
    public description?: string | null,
    public belongfront?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public progress?: number | null,
    public type?: number | null,
    public priorty?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public deliverables?: string | null,
    public status?: keyof typeof ProjectStatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public workbag?: number | null,
    public responsibleperson?: IOfficers | null,
    public technicaldirector?: IOfficers | null,
    public administrativedirector?: IOfficers | null,
    public knowingpeople?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public responsibledepartment?: IDepartment | null,
    public relevantdepartment?: IDepartment | null,
    public department?: IDepartment | null,
    public projects?: IProject[] | null,
    public projectpbs?: IProjectpbs[] | null,
    public progressPlans?: IProgressPlan[] | null,
    public fundsEstimations?: IFundsEstimation[] | null,
    public contractCostBudgets?: IContractCostBudget[] | null,
    public costControlSystems?: ICostControlSystem[] | null,
    public qualityObjectives?: IQualityObjectives[] | null,
    public outsourcingContractuals?: IOutsourcingContractual[] | null,
    public outsourcingPurchasePlans?: IOutsourcingPurchasePlan[] | null,
    public technicals?: ITechnical[] | null,
    public technicalConditions?: ITechnicalCondition[] | null,
    public projectRisks?: IProjectRisk[] | null,
  ) {}
}
