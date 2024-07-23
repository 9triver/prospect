import { type IOfficers } from '@/shared/model/officers.model';
import { type IDepartment } from '@/shared/model/department.model';
import { type IProject } from '@/shared/model/project.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProjectpbs {
  id?: string;
  pbsname?: string | null;
  parentpbsid?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  progress?: number | null;
  type?: number | null;
  priorty?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  status?: keyof typeof ProjectStatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  wbsid?: string | null;
  workbag?: number | null;
  responsibleid?: IOfficers | null;
  auditorid?: IOfficers | null;
  department?: IDepartment | null;
  projects?: IProject[] | null;
}

export class Projectpbs implements IProjectpbs {
  constructor(
    public id?: string,
    public pbsname?: string | null,
    public parentpbsid?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public progress?: number | null,
    public type?: number | null,
    public priorty?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public status?: keyof typeof ProjectStatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public wbsid?: string | null,
    public workbag?: number | null,
    public responsibleid?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public department?: IDepartment | null,
    public projects?: IProject[] | null,
  ) {}
}
