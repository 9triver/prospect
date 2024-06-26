import { type IProject } from '@/shared/model/project.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProjectHumanresourcesplan {
  id?: string;
  projectname?: string | null;
  clientname?: string | null;
  plandate?: Date | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  project?: IProject | null;
}

export class ProjectHumanresourcesplan implements IProjectHumanresourcesplan {
  constructor(
    public id?: string,
    public projectname?: string | null,
    public clientname?: string | null,
    public plandate?: Date | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public project?: IProject | null,
  ) {}
}
