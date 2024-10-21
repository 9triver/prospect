import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IDepartment } from '@/shared/model/department.model';
import { type IProjectdeliverables } from '@/shared/model/projectdeliverables.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IWork } from '@/shared/model/work.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IWorkbag {
  id?: string;
  name?: string | null;
  pbsid?: string | null;
  workbagtype?: number | null;
  supplier?: string | null;
  iskeyimportant?: number | null;
  keypbsname?: string | null;
  importantpbsname?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  estimatedpurchasingtime?: Date | null;
  progress?: number | null;
  issafetywork?: number | null;
  remark?: string | null;
  auditStatus?: keyof typeof AuditStatus | null;
  responsibleperson?: IHrManagement | null;
  projectmanager?: IHrManagement | null;
  knowingpeople?: IHrManagement | null;
  auditorid?: IHrManagement | null;
  responsibledepartment?: IDepartment | null;
  department?: IDepartment | null;
  projectdeliverables?: IProjectdeliverables[] | null;
  relevantdepartments?: IDepartment[] | null;
  wbsids?: IProjectwbs[] | null;
  works?: IWork[] | null;
}

export class Workbag implements IWorkbag {
  constructor(
    public id?: string,
    public name?: string | null,
    public pbsid?: string | null,
    public workbagtype?: number | null,
    public supplier?: string | null,
    public iskeyimportant?: number | null,
    public keypbsname?: string | null,
    public importantpbsname?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public estimatedpurchasingtime?: Date | null,
    public progress?: number | null,
    public issafetywork?: number | null,
    public remark?: string | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public responsibleperson?: IHrManagement | null,
    public projectmanager?: IHrManagement | null,
    public knowingpeople?: IHrManagement | null,
    public auditorid?: IHrManagement | null,
    public responsibledepartment?: IDepartment | null,
    public department?: IDepartment | null,
    public projectdeliverables?: IProjectdeliverables[] | null,
    public relevantdepartments?: IDepartment[] | null,
    public wbsids?: IProjectwbs[] | null,
    public works?: IWork[] | null,
  ) {}
}
