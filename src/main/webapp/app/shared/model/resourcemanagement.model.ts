import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IResourcemanagement {
  id?: number;
  resourceid?: number | null;
  projectname?: string | null;
  clientname?: string | null;
  plandate?: Date | null;
  creatorname?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Resourcemanagement implements IResourcemanagement {
  constructor(
    public id?: number,
    public resourceid?: number | null,
    public projectname?: string | null,
    public clientname?: string | null,
    public plandate?: Date | null,
    public creatorname?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
