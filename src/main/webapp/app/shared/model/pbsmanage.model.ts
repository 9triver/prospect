import { type IPbssubmanage } from '@/shared/model/pbssubmanage.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IPbsmanage {
  id?: number;
  pbsid?: string | null;
  pbsname?: string | null;
  number?: number | null;
  type?: string | null;
  administratorid?: string | null;
  administratorname?: string | null;
  responsiblename?: string | null;
  department?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  auditUserid?: string | null;
  pbssubmanage?: IPbssubmanage | null;
  responsibleid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Pbsmanage implements IPbsmanage {
  constructor(
    public id?: number,
    public pbsid?: string | null,
    public pbsname?: string | null,
    public number?: number | null,
    public type?: string | null,
    public administratorid?: string | null,
    public administratorname?: string | null,
    public responsiblename?: string | null,
    public department?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public auditUserid?: string | null,
    public pbssubmanage?: IPbssubmanage | null,
    public responsibleid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
