import { type IProject } from '@/shared/model/project.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IOutsourcingmPurchasePlan {
  id?: number;
  outsourcingplanid?: number | null;
  matarialname?: string | null;
  purchasingmethod?: number | null;
  budgit?: number | null;
  needtime?: Date | null;
  planusetime?: Date | null;
  supplierid?: number | null;
  price?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  project?: IProject | null;
  responsibleid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class OutsourcingmPurchasePlan implements IOutsourcingmPurchasePlan {
  constructor(
    public id?: number,
    public outsourcingplanid?: number | null,
    public matarialname?: string | null,
    public purchasingmethod?: number | null,
    public budgit?: number | null,
    public needtime?: Date | null,
    public planusetime?: Date | null,
    public supplierid?: number | null,
    public price?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public project?: IProject | null,
    public responsibleid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
