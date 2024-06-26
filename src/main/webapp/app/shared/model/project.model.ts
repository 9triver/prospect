import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectSecrecy } from '@/shared/model/project-secrecy.model';

import { type ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProject {
  id?: string;
  projectname?: string | null;
  description?: string | null;
  number?: number | null;
  projecttype?: number | null;
  responsiblename?: string | null;
  duedate?: Date | null;
  priorty?: number | null;
  status?: keyof typeof ProjectStatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  projectwbs?: IProjectwbs | null;
  responsibleid?: IOfficers | null;
  auditorid?: IOfficers | null;
  projectSecrecy?: IProjectSecrecy | null;
}

export class Project implements IProject {
  constructor(
    public id?: string,
    public projectname?: string | null,
    public description?: string | null,
    public number?: number | null,
    public projecttype?: number | null,
    public responsiblename?: string | null,
    public duedate?: Date | null,
    public priorty?: number | null,
    public status?: keyof typeof ProjectStatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public projectwbs?: IProjectwbs | null,
    public responsibleid?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public projectSecrecy?: IProjectSecrecy | null,
  ) {}
}
