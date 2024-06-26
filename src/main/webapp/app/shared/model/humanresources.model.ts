import { type IProject } from '@/shared/model/project.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IHumanresources {
  id?: string;
  name?: string | null;
  outdeportment?: string | null;
  indeportment?: string | null;
  adjusttime?: Date | null;
  projectname?: string | null;
  deportment?: string | null;
  projectleader?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  project?: IProject | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Humanresources implements IHumanresources {
  constructor(
    public id?: string,
    public name?: string | null,
    public outdeportment?: string | null,
    public indeportment?: string | null,
    public adjusttime?: Date | null,
    public projectname?: string | null,
    public deportment?: string | null,
    public projectleader?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public project?: IProject | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
