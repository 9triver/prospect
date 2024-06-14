import { type IWbssubmanage } from '@/shared/model/wbssubmanage.model';
import { type IPbssubmanage } from '@/shared/model/pbssubmanage.model';
import { type IProject } from '@/shared/model/project.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IWbsmanage {
  id?: number;
  wbsid?: string | null;
  wbsname?: string | null;
  description?: string | null;
  result?: string | null;
  administratorname?: string | null;
  responsiblename?: string | null;
  department?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  wbssubmanage?: IWbssubmanage | null;
  pbssubmanage?: IPbssubmanage | null;
  project?: IProject | null;
  administratorid?: IOfficers | null;
  auditorid?: IOfficers | null;
  responsibleid?: IOfficers | null;
}

export class Wbsmanage implements IWbsmanage {
  constructor(
    public id?: number,
    public wbsid?: string | null,
    public wbsname?: string | null,
    public description?: string | null,
    public result?: string | null,
    public administratorname?: string | null,
    public responsiblename?: string | null,
    public department?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public wbssubmanage?: IWbssubmanage | null,
    public pbssubmanage?: IPbssubmanage | null,
    public project?: IProject | null,
    public administratorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public responsibleid?: IOfficers | null,
  ) {}
}
