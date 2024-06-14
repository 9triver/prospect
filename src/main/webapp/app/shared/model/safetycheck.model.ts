import { type IOfficers } from '@/shared/model/officers.model';

import { type Risklevel } from '@/shared/model/enumerations/risklevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface ISafetycheck {
  id?: number;
  safetycheckid?: number | null;
  safetycheckname?: string | null;
  checksource?: string | null;
  checktime?: Date | null;
  effectivetime?: Date | null;
  operatinglocation?: string | null;
  deprotment?: string | null;
  phonenumber?: number | null;
  risklevel?: keyof typeof Risklevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  auditorid?: IOfficers | null;
  responsibleid?: IOfficers | null;
}

export class Safetycheck implements ISafetycheck {
  constructor(
    public id?: number,
    public safetycheckid?: number | null,
    public safetycheckname?: string | null,
    public checksource?: string | null,
    public checktime?: Date | null,
    public effectivetime?: Date | null,
    public operatinglocation?: string | null,
    public deprotment?: string | null,
    public phonenumber?: number | null,
    public risklevel?: keyof typeof Risklevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public auditorid?: IOfficers | null,
    public responsibleid?: IOfficers | null,
  ) {}
}
