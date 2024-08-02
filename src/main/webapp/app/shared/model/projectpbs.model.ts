import { type IOfficers } from '@/shared/model/officers.model';
import { type IDepartment } from '@/shared/model/department.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
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
  productlevel?: number | null;
  ifkey?: number | null;
  ifimporttant?: number | null;
  description?: string | null;
  progress?: number | null;
  type?: number | null;
  priorty?: number | null;
  status?: keyof typeof ProjectStatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  technicaldirector?: IOfficers | null;
  administrativedirector?: IOfficers | null;
  knowingpeople?: IOfficers | null;
  auditorid?: IOfficers | null;
  responsibledepartment?: IDepartment | null;
  relevantdepartment?: IDepartment | null;
  projectwbs?: IProjectwbs[] | null;
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
    public productlevel?: number | null,
    public ifkey?: number | null,
    public ifimporttant?: number | null,
    public description?: string | null,
    public progress?: number | null,
    public type?: number | null,
    public priorty?: number | null,
    public status?: keyof typeof ProjectStatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public technicaldirector?: IOfficers | null,
    public administrativedirector?: IOfficers | null,
    public knowingpeople?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public responsibledepartment?: IDepartment | null,
    public relevantdepartment?: IDepartment | null,
    public projectwbs?: IProjectwbs[] | null,
    public projects?: IProject[] | null,
  ) {}
}
