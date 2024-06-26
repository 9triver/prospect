import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface ISecrecysystem {
  id?: string;
  publishedby?: string | null;
  documentname?: string | null;
  documenttype?: number | null;
  documentsize?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Secrecysystem implements ISecrecysystem {
  constructor(
    public id?: string,
    public publishedby?: string | null,
    public documentname?: string | null,
    public documenttype?: number | null,
    public documentsize?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
