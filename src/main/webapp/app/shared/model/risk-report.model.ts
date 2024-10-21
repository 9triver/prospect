import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IWorkbag } from '@/shared/model/workbag.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IRiskReport {
  id?: number;
  type?: string | null;
  year?: number | null;
  riskreportname?: string | null;
  reporttime?: Date | null;
  auditStatus?: keyof typeof AuditStatus | null;
  creatorid?: IHrManagement | null;
  wbsid?: IProjectwbs | null;
  workbag?: IWorkbag | null;
}

export class RiskReport implements IRiskReport {
  constructor(
    public id?: number,
    public type?: string | null,
    public year?: number | null,
    public riskreportname?: string | null,
    public reporttime?: Date | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public creatorid?: IHrManagement | null,
    public wbsid?: IProjectwbs | null,
    public workbag?: IWorkbag | null,
  ) {}
}
