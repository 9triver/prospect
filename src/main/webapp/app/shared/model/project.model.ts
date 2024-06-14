import { type ICycleplan } from '@/shared/model/cycleplan.model';
import { type IProgressmanagement } from '@/shared/model/progressmanagement.model';
import { type IQualitymanagement } from '@/shared/model/qualitymanagement.model';
import { type IFundsmanagement } from '@/shared/model/fundsmanagement.model';
import { type ITechnicalCondition } from '@/shared/model/technical-condition.model';
import { type IContractualfunds } from '@/shared/model/contractualfunds.model';
import { type IOutsourcingmPurchaseExecute } from '@/shared/model/outsourcingm-purchase-execute.model';
import { type IResourcemanagement } from '@/shared/model/resourcemanagement.model';
import { type IRiskmanagement } from '@/shared/model/riskmanagement.model';
import { type IDocument } from '@/shared/model/document.model';
import { type ISafetycheck } from '@/shared/model/safetycheck.model';
import { type IDepartment } from '@/shared/model/department.model';
import { type IEvaluationCriteria } from '@/shared/model/evaluation-criteria.model';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectSecrecy } from '@/shared/model/project-secrecy.model';

import { type ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProject {
  id?: number;
  projectid?: number | null;
  projectname?: string | null;
  description?: string | null;
  number?: number | null;
  projecttype?: number | null;
  responsiblename?: string | null;
  duedate?: Date | null;
  priorty?: number | null;
  progressid?: number | null;
  returnsid?: number | null;
  qualityid?: number | null;
  fundsid?: number | null;
  status?: keyof typeof ProjectStatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  cycleplan?: ICycleplan | null;
  progressmanagement?: IProgressmanagement | null;
  qualitymanagement?: IQualitymanagement | null;
  fundsmanagement?: IFundsmanagement | null;
  technicalCondition?: ITechnicalCondition | null;
  contractualfunds?: IContractualfunds | null;
  outsourcingmPurchaseExecute?: IOutsourcingmPurchaseExecute | null;
  resourcemanagement?: IResourcemanagement | null;
  riskmanagement?: IRiskmanagement | null;
  document?: IDocument | null;
  safetycheck?: ISafetycheck | null;
  department?: IDepartment | null;
  evaluationCriteria?: IEvaluationCriteria | null;
  responsibleid?: IOfficers | null;
  auditorid?: IOfficers | null;
  projectSecrecy?: IProjectSecrecy | null;
}

export class Project implements IProject {
  constructor(
    public id?: number,
    public projectid?: number | null,
    public projectname?: string | null,
    public description?: string | null,
    public number?: number | null,
    public projecttype?: number | null,
    public responsiblename?: string | null,
    public duedate?: Date | null,
    public priorty?: number | null,
    public progressid?: number | null,
    public returnsid?: number | null,
    public qualityid?: number | null,
    public fundsid?: number | null,
    public status?: keyof typeof ProjectStatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public cycleplan?: ICycleplan | null,
    public progressmanagement?: IProgressmanagement | null,
    public qualitymanagement?: IQualitymanagement | null,
    public fundsmanagement?: IFundsmanagement | null,
    public technicalCondition?: ITechnicalCondition | null,
    public contractualfunds?: IContractualfunds | null,
    public outsourcingmPurchaseExecute?: IOutsourcingmPurchaseExecute | null,
    public resourcemanagement?: IResourcemanagement | null,
    public riskmanagement?: IRiskmanagement | null,
    public document?: IDocument | null,
    public safetycheck?: ISafetycheck | null,
    public department?: IDepartment | null,
    public evaluationCriteria?: IEvaluationCriteria | null,
    public responsibleid?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public projectSecrecy?: IProjectSecrecy | null,
  ) {}
}
