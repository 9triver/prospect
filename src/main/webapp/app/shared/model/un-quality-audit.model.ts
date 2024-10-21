import { type IWorkbag } from '@/shared/model/workbag.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IUnQualityAudit {
  id?: number;
  workbagid?: string | null;
  belongwbsid?: string | null;
  outsourcingcontractid?: string | null;
  unqualityid?: string | null;
  unqualityname?: string | null;
  unqualityunit?: string | null;
  unqualitytrialgroup?: string | null;
  inspector?: string | null;
  unqualitystage?: string | null;
  unqualitynumber?: number | null;
  unqualityintroduction?: string | null;
  unqualitycategory?: string | null;
  handlingopinion?: string | null;
  applicant?: string | null;
  applicationdate?: string | null;
  auditStatus?: keyof typeof AuditStatus | null;
  attachment?: string | null;
  disposalmethod?: string | null;
  causeanalysis?: string | null;
  correctivemeasures?: string | null;
  remarks?: string | null;
  workbag?: IWorkbag | null;
}

export class UnQualityAudit implements IUnQualityAudit {
  constructor(
    public id?: number,
    public workbagid?: string | null,
    public belongwbsid?: string | null,
    public outsourcingcontractid?: string | null,
    public unqualityid?: string | null,
    public unqualityname?: string | null,
    public unqualityunit?: string | null,
    public unqualitytrialgroup?: string | null,
    public inspector?: string | null,
    public unqualitystage?: string | null,
    public unqualitynumber?: number | null,
    public unqualityintroduction?: string | null,
    public unqualitycategory?: string | null,
    public handlingopinion?: string | null,
    public applicant?: string | null,
    public applicationdate?: string | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public attachment?: string | null,
    public disposalmethod?: string | null,
    public causeanalysis?: string | null,
    public correctivemeasures?: string | null,
    public remarks?: string | null,
    public workbag?: IWorkbag | null,
  ) {}
}
