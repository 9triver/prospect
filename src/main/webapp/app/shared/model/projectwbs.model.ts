import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IDepartment } from '@/shared/model/department.model';
import { type IProjectdeliverables } from '@/shared/model/projectdeliverables.model';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';
import { type IProjectBudget } from '@/shared/model/project-budget.model';
import { type IProject } from '@/shared/model/project.model';
import { type IFundsEstimation } from '@/shared/model/funds-estimation.model';
import { type IContractCostBudget } from '@/shared/model/contract-cost-budget.model';
import { type ICostControlSystem } from '@/shared/model/cost-control-system.model';
import { type IOutsourcingContractual } from '@/shared/model/outsourcing-contractual.model';
import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import { type ITechnical } from '@/shared/model/technical.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProjectwbs {
  id?: string;
  wbsname?: string | null;
  parentwbsid?: string | null;
  description?: string | null;
  belongfrontline?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  progress?: number | null;
  type?: number | null;
  priorty?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  deliverables?: string | null;
  status?: keyof typeof ProjectStatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  workbagid?: string | null;
  projectpbs?: IProjectpbs | null;
  responsibleperson?: IHrManagement | null;
  technicaldirector?: IHrManagement | null;
  knowingpeople?: IHrManagement | null;
  auditorid?: IHrManagement | null;
  responsibledepartment?: IDepartment | null;
  projectdeliverables?: IProjectdeliverables[] | null;
  relevantdepartments?: IDepartment[] | null;
  workbags?: IWorkbag[] | null;
  progressPlans?: IProgressPlan[] | null;
  projectBudgets?: IProjectBudget[] | null;
  projects?: IProject[] | null;
  fundsEstimations?: IFundsEstimation[] | null;
  contractCostBudgets?: IContractCostBudget[] | null;
  costControlSystems?: ICostControlSystem[] | null;
  outsourcingContractuals?: IOutsourcingContractual[] | null;
  outsourcingPurchasePlans?: IOutsourcingPurchasePlan[] | null;
  technicals?: ITechnical[] | null;
}

export class Projectwbs implements IProjectwbs {
  constructor(
    public id?: string,
    public wbsname?: string | null,
    public parentwbsid?: string | null,
    public description?: string | null,
    public belongfrontline?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public progress?: number | null,
    public type?: number | null,
    public priorty?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public deliverables?: string | null,
    public status?: keyof typeof ProjectStatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public workbagid?: string | null,
    public projectpbs?: IProjectpbs | null,
    public responsibleperson?: IHrManagement | null,
    public technicaldirector?: IHrManagement | null,
    public knowingpeople?: IHrManagement | null,
    public auditorid?: IHrManagement | null,
    public responsibledepartment?: IDepartment | null,
    public projectdeliverables?: IProjectdeliverables[] | null,
    public relevantdepartments?: IDepartment[] | null,
    public workbags?: IWorkbag[] | null,
    public progressPlans?: IProgressPlan[] | null,
    public projectBudgets?: IProjectBudget[] | null,
    public projects?: IProject[] | null,
    public fundsEstimations?: IFundsEstimation[] | null,
    public contractCostBudgets?: IContractCostBudget[] | null,
    public costControlSystems?: ICostControlSystem[] | null,
    public outsourcingContractuals?: IOutsourcingContractual[] | null,
    public outsourcingPurchasePlans?: IOutsourcingPurchasePlan[] | null,
    public technicals?: ITechnical[] | null,
  ) {}
}
