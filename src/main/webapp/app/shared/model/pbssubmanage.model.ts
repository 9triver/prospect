import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IPbssubmanage {
  id?: string;
  pbssubname?: string | null;
  responsiblename?: string | null;
  responsibledepartment?: string | null;
  relevantdepartment?: string | null;
  type?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  responsibleid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Pbssubmanage implements IPbssubmanage {
  constructor(
    public id?: string,
    public pbssubname?: string | null,
    public responsiblename?: string | null,
    public responsibledepartment?: string | null,
    public relevantdepartment?: string | null,
    public type?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public responsibleid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}