import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IDepartment } from '@/shared/model/department.model';
import { type IProject } from '@/shared/model/project.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProjectpbs {
  id?: string;
  pbsname?: string | null;
  parentpbsid?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  starttime?: Date | null;
  endtime?: Date | null;
  productlevel?: string | null;
  iskey?: number | null;
  isimportant?: number | null;
  description?: string | null;
  progress?: number | null;
  type?: number | null;
  priorty?: number | null;
  wbsid?: string | null;
  status?: keyof typeof ProjectStatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  technicaldirector?: IHrManagement | null;
  administrativedirector?: IHrManagement | null;
  knowingpeople?: IHrManagement | null;
  auditorid?: IHrManagement | null;
  responsibledepartment?: IDepartment | null;
  relevantdepartments?: IDepartment[] | null;
  projects?: IProject[] | null;
}

export class Projectpbs implements IProjectpbs {
  constructor(
    public id?: string,
    public pbsname?: string | null,
    public parentpbsid?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public productlevel?: string | null,
    public iskey?: number | null,
    public isimportant?: number | null,
    public description?: string | null,
    public progress?: number | null,
    public type?: number | null,
    public priorty?: number | null,
    public wbsid?: string | null,
    public status?: keyof typeof ProjectStatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public technicaldirector?: IHrManagement | null,
    public administrativedirector?: IHrManagement | null,
    public knowingpeople?: IHrManagement | null,
    public auditorid?: IHrManagement | null,
    public responsibledepartment?: IDepartment | null,
    public relevantdepartments?: IDepartment[] | null,
    public projects?: IProject[] | null,
  ) {}
}
