import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IWorkbag } from '@/shared/model/workbag.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IRegularInspection {
  id?: number;
  name?: string | null;
  workbagid?: string | null;
  workbagname?: string | null;
  type?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  standard?: string | null;
  measurementmethod?: string | null;
  checkresult?: string | null;
  checktarget?: string | null;
  checktime?: Date | null;
  checkcompletion?: string | null;
  checkstatus?: string | null;
  auditStatus?: keyof typeof AuditStatus | null;
  responsibleperson?: IHrManagement | null;
  designer?: IHrManagement | null;
  checkperson?: IHrManagement | null;
  workbag?: IWorkbag | null;
}

export class RegularInspection implements IRegularInspection {
  constructor(
    public id?: number,
    public name?: string | null,
    public workbagid?: string | null,
    public workbagname?: string | null,
    public type?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public standard?: string | null,
    public measurementmethod?: string | null,
    public checkresult?: string | null,
    public checktarget?: string | null,
    public checktime?: Date | null,
    public checkcompletion?: string | null,
    public checkstatus?: string | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public responsibleperson?: IHrManagement | null,
    public designer?: IHrManagement | null,
    public checkperson?: IHrManagement | null,
    public workbag?: IWorkbag | null,
  ) {}
}
