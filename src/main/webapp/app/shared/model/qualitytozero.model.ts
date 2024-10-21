import { type IWorkbag } from '@/shared/model/workbag.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IQualitytozero {
  id?: number;
  workbagid?: string | null;
  belongwbsid?: string | null;
  outsourcingcontractid?: string | null;
  qualityproblemid?: string | null;
  qualityproblemname?: string | null;
  problemhappentime?: Date | null;
  problemresponsibleperson?: string | null;
  problemresponsibleunit?: string | null;
  producttype?: string | null;
  productname?: string | null;
  problemphenomenon?: string | null;
  problemtype?: string | null;
  qualitylevel?: string | null;
  zerotype?: string | null;
  problemreasonanalysis?: string | null;
  problemreasoncategory?: string | null;
  takemeasures?: string | null;
  onebyonecategory?: string | null;
  verificationeffect?: string | null;
  qualityprojectreport?: string | null;
  qualitytozeroreport?: string | null;
  reviewopinion?: string | null;
  implementationverificationtable?: string | null;
  auditStatus?: keyof typeof AuditStatus | null;
  workbag?: IWorkbag | null;
}

export class Qualitytozero implements IQualitytozero {
  constructor(
    public id?: number,
    public workbagid?: string | null,
    public belongwbsid?: string | null,
    public outsourcingcontractid?: string | null,
    public qualityproblemid?: string | null,
    public qualityproblemname?: string | null,
    public problemhappentime?: Date | null,
    public problemresponsibleperson?: string | null,
    public problemresponsibleunit?: string | null,
    public producttype?: string | null,
    public productname?: string | null,
    public problemphenomenon?: string | null,
    public problemtype?: string | null,
    public qualitylevel?: string | null,
    public zerotype?: string | null,
    public problemreasonanalysis?: string | null,
    public problemreasoncategory?: string | null,
    public takemeasures?: string | null,
    public onebyonecategory?: string | null,
    public verificationeffect?: string | null,
    public qualityprojectreport?: string | null,
    public qualitytozeroreport?: string | null,
    public reviewopinion?: string | null,
    public implementationverificationtable?: string | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public workbag?: IWorkbag | null,
  ) {}
}
