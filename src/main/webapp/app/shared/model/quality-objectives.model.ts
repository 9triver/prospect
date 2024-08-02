import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IQualityReturns } from '@/shared/model/quality-returns.model';

import { type QualityType } from '@/shared/model/enumerations/quality-type.model';
import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IQualityObjectives {
  id?: string;
  name?: string | null;
  objectives?: string | null;
  qualitytype?: keyof typeof QualityType | null;
  secretlevel?: keyof typeof Secretlevel | null;
  target?: number | null;
  statisticalmethod?: string | null;
  statisticalfrequency?: string | null;
  istarget?: number | null;
  progress?: number | null;
  description?: string | null;
  problems?: string | null;
  improvementmeasures?: string | null;
  returntime?: Date | null;
  createtime?: Date | null;
  auditStatus?: keyof typeof AuditStatus | null;
  responsibleperson?: IOfficers | null;
  auditorid?: IOfficers | null;
  projectwbs?: IProjectwbs[] | null;
  qualityReturns?: IQualityReturns[] | null;
}

export class QualityObjectives implements IQualityObjectives {
  constructor(
    public id?: string,
    public name?: string | null,
    public objectives?: string | null,
    public qualitytype?: keyof typeof QualityType | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public target?: number | null,
    public statisticalmethod?: string | null,
    public statisticalfrequency?: string | null,
    public istarget?: number | null,
    public progress?: number | null,
    public description?: string | null,
    public problems?: string | null,
    public improvementmeasures?: string | null,
    public returntime?: Date | null,
    public createtime?: Date | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public responsibleperson?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public projectwbs?: IProjectwbs[] | null,
    public qualityReturns?: IQualityReturns[] | null,
  ) {}
}
