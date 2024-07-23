import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProject {
  id?: string;
  projectname?: string | null;
  parentid?: string | null;
  pbsid?: string | null;
  description?: string | null;
  number?: number | null;
  projecttype?: number | null;
  priorty?: number | null;
  createdate?: Date | null;
  secretlevel?: keyof typeof Secretlevel | null;
  status?: keyof typeof ProjectStatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  progress?: number | null;
  projectpbs?: IProjectpbs[] | null;
  projectwbs?: IProjectwbs[] | null;
}

export class Project implements IProject {
  constructor(
    public id?: string,
    public projectname?: string | null,
    public parentid?: string | null,
    public pbsid?: string | null,
    public description?: string | null,
    public number?: number | null,
    public projecttype?: number | null,
    public priorty?: number | null,
    public createdate?: Date | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public status?: keyof typeof ProjectStatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public progress?: number | null,
    public projectpbs?: IProjectpbs[] | null,
    public projectwbs?: IProjectwbs[] | null,
  ) {}
}
