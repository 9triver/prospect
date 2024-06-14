import { type IQualityreturns } from '@/shared/model/qualityreturns.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IQualityobjectives {
  id?: number;
  qualityobjectivesid?: number | null;
  qualityobjectivesname?: string | null;
  year?: number | null;
  createtime?: Date | null;
  creatorname?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  qualityreturns?: IQualityreturns | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Qualityobjectives implements IQualityobjectives {
  constructor(
    public id?: number,
    public qualityobjectivesid?: number | null,
    public qualityobjectivesname?: string | null,
    public year?: number | null,
    public createtime?: Date | null,
    public creatorname?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public qualityreturns?: IQualityreturns | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
