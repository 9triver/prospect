import { type ISecrecysystem } from '@/shared/model/secrecysystem.model';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IProject } from '@/shared/model/project.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProjectSecrecy {
  id?: number;
  projectname?: string | null;
  description?: string | null;
  createtime?: Date | null;
  auditStatus?: keyof typeof AuditStatus | null;
  secrecysystem?: ISecrecysystem | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
  projectid?: IProject | null;
}

export class ProjectSecrecy implements IProjectSecrecy {
  constructor(
    public id?: number,
    public projectname?: string | null,
    public description?: string | null,
    public createtime?: Date | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public secrecysystem?: ISecrecysystem | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public projectid?: IProject | null,
  ) {}
}
