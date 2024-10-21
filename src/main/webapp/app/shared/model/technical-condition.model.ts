import { type IWorkbag } from '@/shared/model/workbag.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface ITechnicalCondition {
  id?: number;
  workbagid?: string | null;
  belongwbsid?: string | null;
  outsourcingcontractid?: string | null;
  technicalid?: string | null;
  technicalname?: string | null;
  changedfilename?: string | null;
  applicant?: string | null;
  applicationdate?: Date | null;
  changedreason?: string | null;
  changedbefore?: string | null;
  changedafter?: string | null;
  distributionrange?: string | null;
  remarks?: string | null;
  auditStatus?: keyof typeof AuditStatus | null;
  workbag?: IWorkbag | null;
}

export class TechnicalCondition implements ITechnicalCondition {
  constructor(
    public id?: number,
    public workbagid?: string | null,
    public belongwbsid?: string | null,
    public outsourcingcontractid?: string | null,
    public technicalid?: string | null,
    public technicalname?: string | null,
    public changedfilename?: string | null,
    public applicant?: string | null,
    public applicationdate?: Date | null,
    public changedreason?: string | null,
    public changedbefore?: string | null,
    public changedafter?: string | null,
    public distributionrange?: string | null,
    public remarks?: string | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public workbag?: IWorkbag | null,
  ) {}
}
